package ru.homework.cargo.dto.telegram;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SaveDataTypeDto {
    private final String code;
    private final String title;
    private final long width;
    private final long height;
    private final String parcel;
    private final String type;
}
