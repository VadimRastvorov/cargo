package ru.homework.cargo.service;

import ru.homework.cargo.entity.CargoPosition;

public interface LoadingParcelToTruckService {
    void loadParcelToTruck(char[][] parcel, char[][] truck, CargoPosition position);
}
