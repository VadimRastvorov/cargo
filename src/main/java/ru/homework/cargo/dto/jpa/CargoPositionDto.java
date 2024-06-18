package ru.homework.cargo.dto.jpa;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CargoPositionDto {
    private final int startHeight;
    private final int startWidth;
    private final boolean loadingNewCar;
    private final char[][] cargoTruck;
}
