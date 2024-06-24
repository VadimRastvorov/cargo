package ru.homework.cargo.service;

import ru.homework.cargo.entity.TruckLoad;

import java.util.List;

public interface TruckService {
    List<char[][]> createTrucks(TruckLoad truckLoad);
}
