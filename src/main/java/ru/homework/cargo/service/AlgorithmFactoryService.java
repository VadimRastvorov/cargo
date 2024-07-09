package ru.homework.cargo.service;

import ru.homework.cargo.type.AlgorithmType;
//todo обычно Factory в конце - AlgorithmFactory
public interface AlgorithmFactoryService {
    //todo методы долдны содержать глагол
    AlgorithmService algorithmLoadTruck(AlgorithmType algorithmType);
}
