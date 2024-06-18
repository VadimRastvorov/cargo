package ru.homework.cargo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CargoStartPositionDto {
    private final int height;
    private final int width;
    private final boolean fullTruck;
    private final char[][] parcel;
}

