package ru.homework.cargo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ParcelTypeDto {
    private final String title;
    private final String code;
    private final String parcel;
}
