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
@EqualsAndHashCode
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "n_seq")
    @SequenceGenerator(name = "n_seq",
            sequenceName = "n_sequence",
            initialValue = 1, allocationSize = 5000)
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "title")
    private String title;

    @Column(name = "html_body")
    private String htmlBody;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_moderate")
    private Boolean isModerate = false;

}
