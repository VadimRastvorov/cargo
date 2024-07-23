package ru.homework.cargo.service.loadingTruck;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.cargo.service.AlgorithmFactory;
import ru.homework.cargo.service.AlgorithmService;
import ru.homework.cargo.type.AlgorithmType;

@Service
@RequiredArgsConstructor
public class AlgorithmFactoryImpl implements AlgorithmFactory {
    private final AlgorithmUniformImpl algorithmUniformImpl;
    private final AlgorithmMaximalImpl algorithmMaximalImpl;
    private final AlgorithmConsistentImpl algorithmConsistentImpl;

    public AlgorithmService getAlgorithmTruck(AlgorithmType algorithmType) {
        return switch (algorithmType) {
            case UNIFORM -> algorithmUniformImpl;
            case MAXIMAL -> algorithmMaximalImpl;
            case CONSISTENT -> algorithmConsistentImpl;

        };
    }
}
