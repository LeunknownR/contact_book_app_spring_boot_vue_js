package net.personalprojects.contactbook.domain.contactfilters;

public class ContactFilters {
    private ContactNameFilter _contactName;
    private ContactPhoneNumberFilter _contactPhoneNumber;
    public ContactFilters(final String contactName, final String contactPhoneNumber) {
        this._contactName = new ContactNameFilter(contactName);
        this._contactPhoneNumber = new ContactPhoneNumberFilter(contactPhoneNumber);
    }
    public String contactName() {
        return this._contactName.value();
    }
    public String contactPhoneNumber() {
        return this._contactPhoneNumber.value();
    }
}
