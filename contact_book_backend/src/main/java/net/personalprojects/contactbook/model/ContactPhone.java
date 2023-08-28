package net.personalprojects.contactbook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "contact_phone")
@Setter
public class ContactPhone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Column(name = "number")
    @Getter
    private String number;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="contact_id")
    private Contact contact;
}
