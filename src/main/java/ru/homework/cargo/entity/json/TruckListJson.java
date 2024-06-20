package ru.homework.cargo.entity.json;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class TruckListJson {
    private final List<CargoJson> truckList;
}
