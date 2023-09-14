package net.personalprojects.contactbook.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contact")
@Getter
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private long id;
    @Column(name = "name")
    @Setter
    private String name;
    @Column(name = "email")
    @Setter
    private String email;
    @Column(name = "is_favorite")
    @Setter
    private Boolean isFavorite;
    @ManyToOne
    @Setter
    @JoinColumn(name = "contact_category_id")
    private ContactCategory category;
    @OneToMany(
            mappedBy = "contact",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @OrderBy("id ASC")
    private Set<ContactPhone> phones = new HashSet<>();
    public void addContactPhone(ContactPhone contactPhone) {
        contactPhone.setContact(this);
        phones.add(contactPhone);
    }
}
