package com.kata.cinema.base.models.enums;

import lombok.Getter;

import java.util.Random;

@Getter
public enum RedactorStatus {

    ACTIVE("ACTIVE"),
    RESOLVED("RESOLVED");

    @Getter
    private final String translation;

    RedactorStatus(String translation) {
        this.translation = translation;
    }

    public static RedactorStatus generateRandomRedactorStatus() {
        return RedactorStatus.values()[new Random().nextInt(RedactorStatus.values().length)];
    }

    @Override
    public String toString() {
        return "RedactorStatus{" +
                "translation='" + translation + '\'' +
                '}';
    }
}
