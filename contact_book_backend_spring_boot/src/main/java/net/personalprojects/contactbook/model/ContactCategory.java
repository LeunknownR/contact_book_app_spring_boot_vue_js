package net.personalprojects.contactbook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "contact_category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class ContactCategory {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    @Setter
    private String name;
}
