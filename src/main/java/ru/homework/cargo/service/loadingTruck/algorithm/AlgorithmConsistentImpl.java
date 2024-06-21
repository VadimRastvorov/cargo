package ru.homework.cargo.service.loadingTruck.algorithm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.CargoPosition;
import ru.homework.cargo.entity.TruckLoad;
import ru.homework.cargo.service.loadingTruck.AlgorithmService;
import ru.homework.cargo.service.loadingTruck.CargoPositionService;
import ru.homework.cargo.service.loadingTruck.LoadingParcelToTruckService;
import ru.homework.cargo.service.loadingTruck.TruckService;
import ru.homework.cargo.util.Transformer;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AlgorithmConsistentImpl implements AlgorithmService {
    private final LoadingParcelToTruckService loadingParcelToTruckService;
    private final CargoPositionService cargoPositionService;
    private final TruckService truckService;

    public List<char[][]> invoke(TruckLoad truckLoad) {
        List<char[][]> trucks = truckService.createTrucks(truckLoad);
        int truckIndex = 0;
        for (String parcel : truckLoad.getParcels()) {
            char[][] parcelChar = Transformer.stringToMatrix(parcel);
            CargoPosition cargoPosition = cargoPositionService.createCargoPosition(parcelChar, trucks.get(truckIndex % trucks.size()));
            loadingParcelToTruckService.loadParcelToTruck(parcelChar, trucks.get(truckIndex % trucks.size()), cargoPosition);
            truckIndex++;
        }
        return trucks;
    }
}
