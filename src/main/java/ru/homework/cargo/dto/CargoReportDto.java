package ru.homework.cargo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CargoReportDto {
    private String cargoJson;
    private String cargo;
    private String truck;
    private String parcels;
    private long truckCount;
    private String algorithm;
}
