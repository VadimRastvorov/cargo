package ru.homework.cargo.dto.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@JsonAutoDetect
public class TruckListJson {
    final List<CargoJson> truckList;
}