package ru.homework.cargo.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CargoPosition {
    private int height;
    private int width;
    private boolean fullTruck;
    private char[][] parcel;
}

