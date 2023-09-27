package net.personalprojects.contactbook.domain.contactphone;

import lombok.EqualsAndHashCode;
import net.personalprojects.contactbook.dto.ContactPhoneDTO;
import net.personalprojects.contactbook.exception.InvalidContactException;

import java.util.Set;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class ContactPhoneList {
    private static final String ERROR_MESSAGE = "Invalid contact phones. Only between 1 and 3 phones";
    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 3;
    private Set<ContactPhoneVO> _value;
    public ContactPhoneList(final Set<ContactPhoneDTO> value) {
        if (value == null
            || value.size() < MIN_SIZE
            || value.size() > MAX_SIZE)
            throw new InvalidContactException(ERROR_MESSAGE);
        this._value = value.stream().map(ContactPhoneVO::new).collect(Collectors.toSet());
    }
    public Set<ContactPhoneVO> value() {
        return this._value;
    }
}
