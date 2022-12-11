package com.kata.cinema.base.models.entity;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "per_seq")
    @SequenceGenerator(name = "per_seq",
            sequenceName = "per_sequence",
            initialValue = 1, allocationSize = 1000)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "height")
    private Double height;

    @Column(name = "date_birth")
    @Type(type = "org.hibernate.type.LocalDateType")
    private LocalDate dateBirth;

    @Column(name = "place_of_birth")
    private String placeBirth;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "original_name")
    private String originalName ;

    @Column(name = "original_last_name")
    private String originalLastName ;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "person_profession",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "profession_id"))
    private Set<Profession> professions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person that = (Person) o;
        return id.equals(that.id) && firstName.equals(that.firstName) && lastName.equals(that.lastName) &&
                height.equals(that.height) && dateBirth.equals(that.dateBirth) && placeBirth.equals(that.placeBirth);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
