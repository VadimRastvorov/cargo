package ru.homework.cargo.service;

import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.TruckLoad;

import java.util.List;

@Service
public interface AlgorithmService {
    List<char[][]> invoke(TruckLoad truckLoad);
}
