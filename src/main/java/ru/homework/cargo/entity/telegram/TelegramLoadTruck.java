package ru.homework.cargo.entity.telegram;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
//todo почему final?
public class TelegramLoadTruck {
    private final String truckTitle;
    private final int truckCount;
    private final String algorithmName;
    private final String parcel;
}
