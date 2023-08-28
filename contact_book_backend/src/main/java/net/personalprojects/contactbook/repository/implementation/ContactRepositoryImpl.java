package net.personalprojects.contactbook.repository.implementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import net.personalprojects.contactbook.domain.contactfilters.ContactFilters;
import net.personalprojects.contactbook.model.Contact;
import net.personalprojects.contactbook.repository.ContactRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactRepositoryImpl implements ContactRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Contact> getAllContacts(final String contactName, final String contactPhoneNumber) {
        final String query =
                "FROM Contact c RIGHT JOIN FETCH c.phones cp " +
                "WHERE c.name LIKE :name AND " +
                "EXISTS (FROM ContactPhone cp WHERE cp.contact = c AND cp.number LIKE :number) " +
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
    public void addContact(Contact newContact) {
        entityManager.merge(newContact);
    }
    @Override
    @Transactional
    public void editContact(Contact contact) {
        contact.getPhones().retainAll(contact.getPhones());
        entityManager.merge(contact);
    }
    @Override
    @Transactional
    public void removeContact(long contactId) {
        final Contact contactToRemove = entityManager.find(Contact.class, contactId);
        entityManager.remove(contactToRemove);
    }
    @Override
    public List<Contact> getFavoriteContacts() {
        final String query = "FROM Contact C JOIN FETCH C.phones WHERE isFavorite = true ORDER BY C.name";
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
