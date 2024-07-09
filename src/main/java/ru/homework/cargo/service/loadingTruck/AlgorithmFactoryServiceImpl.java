package ru.homework.cargo.service.loadingTruck;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.cargo.service.AlgorithmFactoryService;
import ru.homework.cargo.service.AlgorithmService;
import ru.homework.cargo.type.AlgorithmType;

@Service
@RequiredArgsConstructor
//todo это фабрикка а не сервис
public class AlgorithmFactoryServiceImpl implements AlgorithmFactoryService {
    private final AlgorithmUniformImpl algorithmUniformImpl;
    private final AlgorithmMaximalImpl algorithmMaximalImpl;
    private final AlgorithmConsistentImpl algorithmConsistentImpl;
    //todo методы долдны содержать глагол
    public AlgorithmService algorithmLoadTruck(AlgorithmType algorithmType) {
        return switch (algorithmType) {
            case UNIFORM -> algorithmUniformImpl;
            case MAXIMAL -> algorithmMaximalImpl;
            case CONSISTENT -> algorithmConsistentImpl;

        };
    }
}
