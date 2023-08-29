package net.personalprojects.contactbook.repository.implementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import net.personalprojects.contactbook.common.ResponseActionMessages;
import net.personalprojects.contactbook.exception.InvalidContactException;
import net.personalprojects.contactbook.model.Contact;
import net.personalprojects.contactbook.model.ContactPhone;
import net.personalprojects.contactbook.repository.ContactRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ContactRepositoryImpl implements ContactRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Contact> getAllContacts(final String contactName, final String contactPhoneNumber) {
        final String query =
                "FROM Contact c RIGHT JOIN FETCH c.phones cp " +
                "WHERE c.name LIKE :name " +
                    "AND EXISTS (FROM ContactPhone cp WHERE cp.contact = c AND cp.number LIKE :number) " +
                "ORDER BY c.name";
        final List<Contact> allContacts = entityManager
                .createQuery(query, Contact.class)
                .setParameter("name", "%" + contactName + "%")
                .setParameter("number", "%" + contactPhoneNumber + "%")
                .getResultList();
        return allContacts;
    }
    @Override
    @Transactional
    public ResponseActionMessages addContact(Contact contact) {
        if (checkExistenceOfContactNameAndEmail(contact))
            return ResponseActionMessages.CONTACT_NAME_OR_EMAIL_ALREADY_EXISTS;
        final Set<ContactPhone> contactPhonesToCheck = contact.getPhones();
        if (checkExistenceNewNumber(contactPhonesToCheck))
            return ResponseActionMessages.SOME_NUMBERS_ALREADY_EXISTS;
        // Agregando nuevo contacto
        entityManager.merge(contact);
        return ResponseActionMessages.SUCCESS;
    }
    private boolean checkExistenceOfContactNameAndEmail(final Contact contactToCheck) {
        final String query =
                "SELECT COUNT(c) > 0 " +
                "FROM Contact c " +
                "WHERE (c.id IS NULL OR c.id != :id) AND (c.name = :name OR c.email = :email)";
        return entityManager
                .createQuery(query, Boolean.class)
                .setParameter("id", contactToCheck.getId())
                .setParameter("name", contactToCheck.getName())
                .setParameter("email", contactToCheck.getEmail())
                .getSingleResult();
    }
    private boolean checkExistenceNewNumber(final Set<ContactPhone> contactPhonesToCheck) {
        final String query =
                "SELECT COUNT(cp) > 0 " +
                "FROM ContactPhone cp " +
                "WHERE cp.number IN :newNumbers";
        final Set<String> phoneNumberList = contactPhonesToCheck.stream()
                .filter(contact -> contact.getId() == null)
                .map(ContactPhone::getNumber)
                .collect(Collectors.toSet());
        return entityManager
                .createQuery(query, Boolean.class)
                .setParameter("newNumbers", phoneNumberList)
                .getSingleResult();
    }
    private boolean checkExistenceNumbersToEdit(final Set<ContactPhone> contactPhonesToCheck) {
        final String query =
                "SELECT COUNT(cp) > 0 " +
                "FROM ContactPhone cp " +
                "WHERE cp.number IN :numbersToEdit AND cp.id NOT IN :idsOfNumbersToEdit";
        final Set<ContactPhone> contactPhoneList = contactPhonesToCheck.stream()
                .filter(contact -> contact.getId() != null)
                .collect(Collectors.toSet());
        final List<String> numbersToEdit = contactPhoneList.stream().map(ContactPhone::getNumber).toList();
        final List<Long> idsOfNumberToEdit = contactPhoneList.stream().map(ContactPhone::getId).toList();
        return entityManager
                .createQuery(query, Boolean.class)
                .setParameter("numbersToEdit", numbersToEdit)
                .setParameter("idsOfNumbersToEdit", idsOfNumberToEdit)
                .getSingleResult();
    }
    private Contact findContactById(final Long contactId) {
        return entityManager.find(Contact.class, contactId);
    }
    @Override
    @Transactional
    public ResponseActionMessages editContact(Contact contact) {
        // Validaciones
        if (findContactById(contact.getId()) == null)
            throw new InvalidContactException("Contact to edit not exists");
        if (checkExistenceOfContactNameAndEmail(contact))
            return ResponseActionMessages.CONTACT_NAME_OR_EMAIL_ALREADY_EXISTS;
        final Set<ContactPhone> contactPhonesToCheck = contact.getPhones();
        if (checkExistenceNewNumber(contactPhonesToCheck) || checkExistenceNumbersToEdit(contactPhonesToCheck))
            return ResponseActionMessages.SOME_NUMBERS_ALREADY_EXISTS;
        // Aplicando cambios
        entityManager.merge(contact);
        return ResponseActionMessages.SUCCESS;
    }
    @Override
    @Transactional
    public void removeContact(long contactId) {
        final Contact contactToRemove = findContactById(contactId);
        if (contactToRemove == null)
            throw new InvalidContactException("Contact to remove not exists");
        entityManager.remove(contactToRemove);
    }
    @Override
    public List<Contact> getFavoriteContacts() {
        final String query =
                "FROM Contact C JOIN FETCH C.phones " +
                "WHERE isFavorite = true " +
                "ORDER BY C.name";
        final List<Contact> contactList = entityManager
                .createQuery(query, Contact.class)
                .getResultList();
        return contactList;
    }
    @Transactional
    @Override
    public void toggleFavoriteContact(Long contactId) {
        final Contact contactFound = entityManager.find(Contact.class, contactId);
        contactFound.setIsFavorite(!contactFound.getIsFavorite());
        entityManager.merge(contactFound);
    }
}
