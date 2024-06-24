package ru.homework.cargo.service;

import ru.homework.cargo.entity.CargoPosition;

public interface CargoPositionService {
    CargoPosition createCargoPosition(char[][] parcel, char[][] truck);
}
