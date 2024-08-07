package ru.homework.cargo.service.telegram.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.TruckLoad;
import ru.homework.cargo.entity.jpa.CarcaseType;
import ru.homework.cargo.entity.jpa.CargoReport;
import ru.homework.cargo.entity.telegram.Parameters;
import ru.homework.cargo.exception.CustomException;
import ru.homework.cargo.mapper.TruckListJsonMapper;
import ru.homework.cargo.mapper.TruckLoadMapper;
import ru.homework.cargo.mapper.jpa.CargoReportMapper;
import ru.homework.cargo.repository.CarcaseTypeRepository;
import ru.homework.cargo.repository.CargoReportRepository;
import ru.homework.cargo.service.*;
import ru.homework.cargo.service.builderImage.BuilderImageTelegramDecorator;
import ru.homework.cargo.type.AlgorithmType;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommandLoadServiceImpl implements CommandService {
    private final BuilderImageService builderImageService;
    private final JsonConvertService jsonConvertService;
    private final CargoReportRepository cargoReportRepository;
    private final CargoReportMapper cargoReportMapper;
    private final AlgorithmFactory algorithmFactory;
    private final TruckListJsonMapper trucksToTrucksJson;
    private final ParcelService parcelService;
    private final CarcaseTypeRepository carcaseTypeRepository;
    private final TruckLoadMapper truckLoadMapper;

    @Override
    public String invoke(Parameters parameters) {
        List<String> parcels = findParcels(parameters.getParcelsTitle());
        List<CarcaseType> carcaseTypes = findCarcaseTypes(parameters.getTruckTitle());
        AlgorithmService algorithmService = getAlgorithmService(parameters.getAlgorithmName());
        List<char[][]> trucks = algorithmService.invoke(getTruckLoad(parameters, carcaseTypes.get(0), parcels));
        String buildImageString = buildImageString(trucks);
        String cargoJson = convertTrucksToJson(trucks);
        CargoReport cargoReport = saveCargoReport(parameters, cargoJson, buildImageString);
        log.info("метод loadTrucksService cargoReportDto: {}", cargoReport);
        return buildImageString;
    }

    private TruckLoad getTruckLoad(Parameters parameters, CarcaseType carcaseType, List<String> parcels) {
        return truckLoadMapper.toEntity(parameters, carcaseType, parcels);
    }

    private AlgorithmService getAlgorithmService(String algorithmName) {
        return algorithmFactory.getAlgorithmTruck(AlgorithmType.get(algorithmName));
    }

    private List<String> findParcels(String parcel) {
        List<String> parcels = parcelService.splitParcels(parcel);
        if (parcels.isEmpty()) {
            throw new CustomException("В БД не найдена посылка: " + parcels);
        }
        return parcels;
    }

    private List<CarcaseType> findCarcaseTypes(String truckTitle) {
        List<CarcaseType> carcaseTypes = carcaseTypeRepository.findByTitleContainsIgnoreCaseOrderByIdAsc(truckTitle);
        if (carcaseTypes.isEmpty()) {
            throw new CustomException("В БД не найден автомобиль: " + truckTitle);
        }
        return carcaseTypes;
    }

    private String buildImageString(List<char[][]> trucks) {
        return new BuilderImageTelegramDecorator(builderImageService).buildImageString(trucks);
    }

    private String convertTrucksToJson(List<char[][]> trucks) {
        return jsonConvertService.truckListJsonToJsonString(trucksToTrucksJson.trucksToTrucksJson(trucks));
    }

    private CargoReport saveCargoReport(Parameters parameters, String cargoJson, String buildImageString) {
        return cargoReportRepository.save(cargoReportMapper.loadTruckToEntity(parameters, cargoJson, buildImageString));
    }
}
