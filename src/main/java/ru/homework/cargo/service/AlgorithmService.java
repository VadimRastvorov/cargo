package ru.homework.cargo.service;

import ru.homework.cargo.entity.TruckLoad;

import java.util.List;

public interface AlgorithmService {
    List<char[][]> invoke(TruckLoad truckLoad);
}
