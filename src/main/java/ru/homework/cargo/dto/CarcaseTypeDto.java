package ru.homework.cargo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CarcaseTypeDto {
    private String title;
    private String code;
    private long width;
    private long height;
}
