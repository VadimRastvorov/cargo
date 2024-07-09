package ru.homework.cargo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
//todo почему final?
public class ParcelTypeDto {
    private final String title;
    private final String code;
    private final String parcel;
}
