package ru.homework.cargo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CarcaseTypeDto {
    private final String title;
    private final String code;
    private final long width;
    private final long height;
}
