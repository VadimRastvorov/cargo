package ru.homework.cargo.service.algorithm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.CargoPosition;
import ru.homework.cargo.entity.TruckLoad;
import ru.homework.cargo.service.AlgorithmService;
import ru.homework.cargo.service.CargoService;
import ru.homework.cargo.service.LoadingParcelToTruckService;
import ru.homework.cargo.service.TruckService;
import ru.homework.cargo.util.Transformer;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AlgorithmMaximal implements AlgorithmService {
    private final LoadingParcelToTruckService loadingParcelToTruckService;
    private final CargoService cargoService;
    private final TruckService truckService;

    public List<char[][]> invoke(TruckLoad truckLoad) {
        List<char[][]> trucks = truckService.createTrucks(truckLoad);
        int truckIndex = 0;
        for (String parcel : truckLoad.getParcels()) {
            char[][] parcelChar = Transformer.stringToMatrix(parcel);
            CargoPosition positionDto = cargoService.returnStartPosition(parcelChar, trucks.get(truckIndex % trucks.size()));
            if (positionDto.isFullTruck()) {
                truckIndex++;
                positionDto = cargoService.returnStartPosition(parcelChar, trucks.get(truckIndex % trucks.size()));
            }
            loadingParcelToTruckService.loadParcelToTruck(parcelChar, trucks.get(truckIndex % trucks.size()), positionDto);
        }
        return trucks;
    }
}
