package net.personalprojects.contactbook.dto;

import lombok.Data;
import net.personalprojects.contactbook.model.ContactPhone;

import java.util.Set;

@Data
public class ContactDTO {
    private long id;
    private String name;
    private String email;
    private Boolean isFavorite;
    private String categoryId;
    private Set<ContactPhone> phonesToEdit;
    private String[] phonesToAdd;
}
