package ru.homework.cargo.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class TruckLoad {
    private int height;
    private int width;
    private int truckCount;
    private List<String> parcels;
}
