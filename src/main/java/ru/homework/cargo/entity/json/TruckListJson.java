package ru.homework.cargo.entity.json;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
//todo почему final?
public class TruckListJson {
    private final List<CargoJson> truckList;
}
