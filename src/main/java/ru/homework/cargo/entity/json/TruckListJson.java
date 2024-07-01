package ru.homework.cargo.entity.json;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class TruckListJson {
    private final List<CargoJson> truckList;
}
