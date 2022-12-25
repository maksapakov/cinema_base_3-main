package com.kata.cinema.base.models.entity;

import com.kata.cinema.base.models.enums.RedactorStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "redactor_comment")
public class RedactorComment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    private News news;

    @Column (name = "comment")
    private String comment;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    @Column(name = "redactor_status")
    @Enumerated (value = EnumType.STRING)
    private RedactorStatus redactorStatus;

    @Override
    public String toString() {
        return "RedactorComment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", redactorStatus=" + redactorStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RedactorComment that = (RedactorComment) o;
        return Objects.equals(id, that.id) && Objects.equals(comment, that.comment) && redactorStatus == that.redactorStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comment, redactorStatus);
    }
}
