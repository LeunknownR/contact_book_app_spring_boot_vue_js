package net.personalprojects.contactbook.repository.implementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import net.personalprojects.contactbook.common.ResponseActionMessages;
import net.personalprojects.contactbook.exception.InvalidContactException;
import net.personalprojects.contactbook.model.Contact;
import net.personalprojects.contactbook.model.ContactPhone;
import net.personalprojects.contactbook.repository.ContactRepository;
import net.personalprojects.contactbook.repository.utils.CheckContactValidator;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ContactRepositoryImpl implements ContactRepository {
    @PersistenceContext
    private EntityManager entityManager;
    private CheckContactValidator checkContactValidator = new CheckContactValidator(entityManager);
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
        final ResponseActionMessages message = checkContactValidator.checkContactForAdding(contact);
        if (message != null)
            return message;
        entityManager.merge(contact);
        return ResponseActionMessages.SUCCESS;
    }
    @Override
    public Contact findContactById(final long contactId) {
        return entityManager.find(Contact.class, contactId);
    }
    @Override
    @Transactional
    public ResponseActionMessages editContact(Contact contact) {
        final ResponseActionMessages message = checkContactValidator.checkContactForEdit(contact);
        if (message != null)
            return message;
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
                "FROM Contact c JOIN FETCH c.phones " +
                "WHERE isFavorite = true " +
                "ORDER BY c.name";
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
