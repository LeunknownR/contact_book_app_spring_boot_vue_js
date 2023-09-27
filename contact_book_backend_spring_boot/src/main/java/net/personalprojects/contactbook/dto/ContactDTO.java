package net.personalprojects.contactbook.dto;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ContactDTO {
    private long id;
    private String name;
    private String email;
    private Boolean isFavorite;
    private String categoryId;
    private Set<ContactPhoneDTO> phonesToEdit;
    private Set<String> phonesToAdd;
}
