package ru.homework.cargo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.jpa.CarcaseType;
import ru.homework.cargo.entity.jpa.CargoReport;
import ru.homework.cargo.entity.telegram.TelegramLoadTruck;
import ru.homework.cargo.exception.CustomException;
import ru.homework.cargo.mapper.jpa.CargoReportMapper;
import ru.homework.cargo.repository.CarcaseTypeRepository;
import ru.homework.cargo.repository.CargoReportRepository;
import ru.homework.cargo.service.json.JsonConvertService;
import ru.homework.cargo.type.AlgorithmType;
import ru.homework.cargo.util.Transformer;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProcessLoadingTruckService {
    private final CargoReportRepository cargoReportRepository;
    private final CarcaseTypeRepository carcaseTypeRepository;
    private final BuilderImageService builderImageService;
    private final JsonConvertService jsonConvertService;
    private final AlgorithmFactory algorithmFactory;
    private final ParcelService parcelService;
    private final CargoReportMapper cargoReportMapper;

    public String loadTrucks(TelegramLoadTruck telegramLoadTruck) {
        log.info("метод loadTrucksService telegramLoadTruck: {}", telegramLoadTruck);
        List<String> parcelList = parcelService.getParcelList(telegramLoadTruck.getParcel());
        List<CarcaseType> carcaseTypeList =
                carcaseTypeRepository.findByTitleContainsIgnoreCaseOrderByIdAsc(telegramLoadTruck.getTruckTitle());
        validateCarcaseTypeDtoList(carcaseTypeList, telegramLoadTruck.getTruckTitle());
        AlgorithmService algorithmService =
                algorithmFactory.algorithmLoadTruck(AlgorithmType.get(telegramLoadTruck.getAlgorithmName()));
        List<char[][]> trucks =
                algorithmService.invoke(Transformer.createTruckLoad(telegramLoadTruck, carcaseTypeList.get(0), parcelList));
        String buildImageString = builderImageService.buildImageString(trucks);
        String cargoJson = jsonConvertService.truckListJsonToJsonString(Transformer.trucksToTrucksJson(trucks));
        CargoReport cargoReport =
                cargoReportRepository.save(cargoReportMapper.loadTruckToEntity(telegramLoadTruck, cargoJson, buildImageString));
        log.info("метод loadTrucksService cargoReportDto: {}", cargoReport);
        return cargoReport.getCargo();
    }

    private void validateCarcaseTypeDtoList(List<CarcaseType> carcaseTypeList, String truckTitle) {
        if (carcaseTypeList.isEmpty()) {
            throw new CustomException("В БД не найден автомобиль: " + truckTitle);
        }
    }
}
