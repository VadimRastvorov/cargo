package ru.homework.cargo.dto.jpa; //todo переименуй jpa на dto или domain

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
