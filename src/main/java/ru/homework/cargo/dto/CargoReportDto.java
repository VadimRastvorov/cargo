package ru.homework.cargo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
//todo почему final?
public class CargoReportDto {
    private final String cargoJson;
    private final String cargo;
    private final String truck;
    private final String parcels;
    private final long truckCount;
    private final String algorithm;
}
