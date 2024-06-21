package ru.homework.cargo.mapper;

import org.springframework.stereotype.Component;
import ru.homework.cargo.entity.CargoPosition;

@Component
public class CargoPositionMapper {
    public CargoPosition toEntity(char[][] parcel, int height, int width, boolean fullTruck) {
        return CargoPosition.builder()
                .height(height)
                .width(width)
                .fullTruck(fullTruck)
                .parcel(parcel)
                .build();
    }
}
