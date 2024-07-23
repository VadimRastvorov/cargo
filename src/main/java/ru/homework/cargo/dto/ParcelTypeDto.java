package ru.homework.cargo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ParcelTypeDto {
    private String title;
    private String code;
    private String parcel;
}
