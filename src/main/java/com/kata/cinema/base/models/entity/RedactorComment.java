package com.kata.cinema.base.models.entity;

import com.kata.cinema.base.models.enums.RedactorStatus;

import javax.persistence.*;

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

    @Enumerated (value = EnumType.STRING)
    private RedactorStatus redactorStatus;

}
