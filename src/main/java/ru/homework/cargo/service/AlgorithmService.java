package ru.homework.cargo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.cargo.dto.CargoStartPositionDto;
import ru.homework.cargo.type.AlgorithmType;
import ru.homework.cargo.util.Transformer;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlgorithmService {
    private final LoadingParcelToTruckService loadingParcelToTruckService;
    private final CargoService cargoService;
    private final TruckService truckService;

    public List<char[][]> algorithmLoadTruck(int height, int width, int truckCount,
                                             AlgorithmType algorithmType,
                                             List<String> parcel) {
        return switch (algorithmType) {
            case UNIFORM -> runAlgorithmUniform(height, width, truckCount, parcel);
            case MAXIMAL -> runAlgorithmMaximal(height, width, truckCount, parcel);
            case CONSISTENT -> runAlgorithmConsistent(height, width, truckCount, parcel);

        };
    }

    private List<char[][]> runAlgorithmUniform(int height, int width, int truckCount, List<String> parcels) {
        List<char[][]> trucks = truckService.createTrucks(height, width, truckCount);
        int truckIndex = 0;
        for (String parcel : parcels) {
            char[][] parcelChar = Transformer.stringToMatrix(parcel);
            CargoStartPositionDto positionDto = cargoService.returnStartPosition(parcelChar, trucks.get(truckIndex % trucks.size()));
            loadingParcelToTruckService.loadParcelToTruck(parcelChar, trucks.get(truckIndex % trucks.size()), positionDto);
            truckIndex++;
        }
        return trucks;
    }

    private List<char[][]> runAlgorithmMaximal(int height, int width, int truckCount, List<String> parcels) {
        List<char[][]> trucks = truckService.createTrucks(height, width, truckCount);
        int truckIndex = 0;
        for (String parcel : parcels) {
            char[][] parcelChar = Transformer.stringToMatrix(parcel);
            CargoStartPositionDto positionDto = cargoService.returnStartPosition(parcelChar, trucks.get(truckIndex % trucks.size()));
            if (positionDto.isFullTruck()) {
                truckIndex++;
                positionDto = cargoService.returnStartPosition(parcelChar, trucks.get(truckIndex % trucks.size()));
            }
            loadingParcelToTruckService.loadParcelToTruck(parcelChar, trucks.get(truckIndex % trucks.size()), positionDto);
        }
        return trucks;
    }

    private List<char[][]> runAlgorithmConsistent(int height, int width, int truckCount, List<String> parcels) {
        List<char[][]> trucks = truckService.createTrucks(height, width, truckCount);
        int truckIndex = 0;
        for (String parcel : parcels) {
            char[][] parcelChar = Transformer.stringToMatrix(parcel);
            CargoStartPositionDto positionDto = cargoService.returnStartPosition(parcelChar, trucks.get(truckIndex % trucks.size()));
            loadingParcelToTruckService.loadParcelToTruck(parcelChar, trucks.get(truckIndex % trucks.size()), positionDto);
            truckIndex++;
        }
        return trucks;
    }
}
