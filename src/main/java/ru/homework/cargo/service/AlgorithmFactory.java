package ru.homework.cargo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.cargo.service.algorithm.AlgorithmConsistent;
import ru.homework.cargo.service.algorithm.AlgorithmMaximal;
import ru.homework.cargo.service.algorithm.AlgorithmUniform;
import ru.homework.cargo.type.AlgorithmType;

@Service
@RequiredArgsConstructor
public class AlgorithmFactory {
    private final AlgorithmUniform algorithmUniform;
    private final AlgorithmMaximal algorithmMaximal;
    private final AlgorithmConsistent algorithmConsistent;

    public AlgorithmService algorithmLoadTruck(AlgorithmType algorithmType) {
        return switch (algorithmType) {
            case UNIFORM -> algorithmUniform;
            case MAXIMAL -> algorithmMaximal;
            case CONSISTENT -> algorithmConsistent;

        };
    }
}
