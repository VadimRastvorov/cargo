package ru.homework.cargo.dto.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseDto {
    private final String message;
    private final String source;
}
