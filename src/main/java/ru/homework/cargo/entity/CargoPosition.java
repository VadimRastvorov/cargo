package ru.homework.cargo.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CargoPosition {
    private final int height;
    private final int width;
    private final boolean fullTruck;
    private final char[][] parcel;
}

