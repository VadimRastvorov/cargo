package ru.homework.cargo.dto.jpa;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestDto {
    private final String message;
    private final String source;
}
