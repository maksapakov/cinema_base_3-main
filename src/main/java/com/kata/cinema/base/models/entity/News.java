package com.kata.cinema.base.models.entity;

import com.kata.cinema.base.models.enums.RedactorStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "news")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "n_seq")
    @SequenceGenerator(name = "n_seq",
            sequenceName = "n_sequence",
            initialValue = 1, allocationSize = 5000)
    private Long id;

    @Column(name = "data")
    private LocalDateTime date;

    @Column(name = "title")
    private String title;

    @Column(name = "html_body")
    private String htmlBody;

    @Enumerated(value = EnumType.STRING)
    private RedactorStatus redactorStatus;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_moderate")
    private Boolean isModerate = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News that = (News) o;
        return id.equals(that.id) && date.equals(that.date) && title.equals(that.title) && redactorStatus.equals(that.redactorStatus) && htmlBody.equals(that.htmlBody);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
