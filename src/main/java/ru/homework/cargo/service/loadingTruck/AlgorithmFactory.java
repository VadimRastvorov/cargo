package ru.homework.cargo.service.loadingTruck;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.cargo.service.loadingTruck.algorithm.AlgorithmConsistentImpl;
import ru.homework.cargo.service.loadingTruck.algorithm.AlgorithmMaximalImpl;
import ru.homework.cargo.service.loadingTruck.algorithm.AlgorithmUniformImpl;
import ru.homework.cargo.type.AlgorithmType;

@Service
@RequiredArgsConstructor
public class AlgorithmFactory {
    private final AlgorithmUniformImpl algorithmUniformImpl;
    private final AlgorithmMaximalImpl algorithmMaximalImpl;
    private final AlgorithmConsistentImpl algorithmConsistentImpl;

    public AlgorithmService algorithmLoadTruck(AlgorithmType algorithmType) {
        return switch (algorithmType) {
            case UNIFORM -> algorithmUniformImpl;
            case MAXIMAL -> algorithmMaximalImpl;
            case CONSISTENT -> algorithmConsistentImpl;

        };
    }
}
