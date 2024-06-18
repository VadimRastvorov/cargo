package ru.homework.cargo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TruckDto {
    private char[][] cargoTruck;
}
