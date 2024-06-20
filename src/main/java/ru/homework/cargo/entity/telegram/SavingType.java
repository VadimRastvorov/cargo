package ru.homework.cargo.entity.telegram;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SavingType {
    private final String code;
    private final String title;
    private final long width;
    private final long height;
    private final String parcel;
    private final String type;
}
