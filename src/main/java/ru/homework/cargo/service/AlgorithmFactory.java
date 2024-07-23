package ru.homework.cargo.service;

import ru.homework.cargo.type.AlgorithmType;

public interface AlgorithmFactory {
    AlgorithmService getAlgorithmTruck(AlgorithmType algorithmType);
}
