package ru.homework.cargo.service;

import ru.homework.cargo.type.AlgorithmType;

public interface AlgorithmFactoryService {
    AlgorithmService algorithmLoadTruck(AlgorithmType algorithmType);
}
