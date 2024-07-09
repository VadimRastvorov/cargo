package ru.homework.cargo.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
//todo почему final?
public class TruckLoad {
    private final int height;
    private final int width;
    private final int truckCount;
    private final List<String> parcels;
}
