package com.kata.cinema.base.models.dto.request;

import com.kata.cinema.base.models.enums.RedactorStatus;
import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class RedactorCommentRequestDto {

    private String comment;

    @NotNull
    private RedactorStatus redactorStatus;

}

