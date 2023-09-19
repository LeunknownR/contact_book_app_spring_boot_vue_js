package net.personalprojects.contactbook.repository.utils;

import jakarta.persistence.EntityManager;
import net.personalprojects.contactbook.common.ResponseActionMessages;
import net.personalprojects.contactbook.exception.InvalidContactException;
import net.personalprojects.contactbook.model.Contact;
import net.personalprojects.contactbook.model.ContactPhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CheckContactValidator {
    private final EntityManager entityManager;
    @Autowired
    public CheckContactValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public boolean contactExists(final long contactId) {
        return entityManager.find(Contact.class, contactId) != null;
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
    public ResponseActionMessages checkContactForEdit(final Contact contact) {
        if (!contactExists(contact.getId()))
            throw new InvalidContactException("Contact to edit not exists");
        if (checkExistenceOfContactNameAndEmail(contact))
            return ResponseActionMessages.CONTACT_NAME_OR_EMAIL_ALREADY_EXISTS;
        final Set<ContactPhone> contactPhonesToCheck = contact.getPhones();
        if (checkExistenceNewNumber(contactPhonesToCheck) || checkExistenceNumbersToEdit(contactPhonesToCheck))
            return ResponseActionMessages.SOME_PHONES_ALREADY_EXIST;
        return null;
    }
    public ResponseActionMessages checkContactForAdding(final Contact contact) {
        if (checkExistenceOfContactNameAndEmail(contact))
            return ResponseActionMessages.CONTACT_NAME_OR_EMAIL_ALREADY_EXISTS;
        final Set<ContactPhone> contactPhonesToCheck = contact.getPhones();
        if (checkExistenceNewNumber(contactPhonesToCheck))
            return ResponseActionMessages.SOME_PHONES_ALREADY_EXIST;
        return null;
    }
}
