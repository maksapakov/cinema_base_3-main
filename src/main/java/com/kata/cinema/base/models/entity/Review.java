package com.kata.cinema.base.models.entity;

import com.kata.cinema.base.models.enums.RedactorStatus;
import com.kata.cinema.base.models.enums.TypeReview;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.*;

@Entity
@Table(name = "reviews")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq")
  @SequenceGenerator(name = "review_seq",
          sequenceName = "review_sequence",
          initialValue = 1, allocationSize = 100)
  private Long id;

  @Column(nullable = false, name = "type_review")
  @Enumerated(EnumType.STRING)
  private TypeReview typeReview;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private LocalDate date;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "movie_id")
  private Movie movie;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "redactor_status")
  @Enumerated(value = EnumType.STRING)
  private RedactorStatus redactorStatus;

  @Column(name = "is_moderate")
  private Boolean isModerate = false;
}


