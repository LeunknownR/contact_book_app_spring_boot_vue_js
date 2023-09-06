package net.personalprojects.contactbook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "contact_category")
@Data
public class ContactCategory {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
}
