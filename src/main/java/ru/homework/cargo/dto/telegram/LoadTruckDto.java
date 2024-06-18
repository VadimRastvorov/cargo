package ru.homework.cargo.dto.telegram;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoadTruckDto {
    private final String truckTitle;
    private final int truckCount;
    private final String algorithmName;
    private final String parcel;
}
