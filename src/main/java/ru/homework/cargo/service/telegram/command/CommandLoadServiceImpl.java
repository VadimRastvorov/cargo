package ru.homework.cargo.service.telegram.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.jpa.CarcaseType;
import ru.homework.cargo.entity.jpa.CargoReport;
import ru.homework.cargo.entity.telegram.TelegramLoadTruck;
import ru.homework.cargo.exception.CustomException;
import ru.homework.cargo.mapper.TelegramLoadTruckMapper;
import ru.homework.cargo.mapper.TruckListJsonMapper;
import ru.homework.cargo.mapper.TruckLoadMapper;
import ru.homework.cargo.mapper.jpa.CargoReportMapper;
import ru.homework.cargo.repository.CarcaseTypeRepository;
import ru.homework.cargo.repository.CargoReportRepository;
import ru.homework.cargo.service.BuilderImageService;
import ru.homework.cargo.service.ParcelService;
import ru.homework.cargo.service.json.JsonConvertService;
import ru.homework.cargo.service.loadingTruck.AlgorithmFactory;
import ru.homework.cargo.service.loadingTruck.AlgorithmService;
import ru.homework.cargo.service.telegram.CommandService;
import ru.homework.cargo.type.AlgorithmType;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommandLoadServiceImpl implements CommandService {
    private final static String TELEGRAM_BOT_FORMAT_SYMBOL = "```";

    private final TelegramLoadTruckMapper telegramLoadTruckMapper;
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
    public String invoke(Map<String, String> parameters) {
        TelegramLoadTruck telegramLoadTruck = loadTruckFromParameters(parameters);
        List<String> parcels = findParcels(telegramLoadTruck.getParcel());
        List<CarcaseType> carcaseTypes = findCarcaseTypes(telegramLoadTruck.getTruckTitle());

        AlgorithmService algorithmService = algorithmFactory.algorithmLoadTruck(AlgorithmType.get(telegramLoadTruck.getAlgorithmName()));
        List<char[][]> trucks = algorithmService.invoke(truckLoadMapper.toEntity(telegramLoadTruck, carcaseTypes.get(0), parcels));

        String buildImageString = buildImageString(trucks);
        String cargoJson = convertTrucksToJson(trucks);
        CargoReport cargoReport = saveCargoReport(telegramLoadTruck, cargoJson, buildImageString);
        log.info("метод loadTrucksService cargoReportDto: {}", cargoReport);

        return formatImageString(buildImageString);
    }

    private TelegramLoadTruck loadTruckFromParameters(Map<String, String> parameters) {
        return telegramLoadTruckMapper.mapToLoadTruck(parameters);
    }

    private List<String> findParcels(String parcel) {
        List<String> parcels = parcelService.findParcels(parcel);
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
        return builderImageService.buildImageString(trucks);
    }

    private String convertTrucksToJson(List<char[][]> trucks) {
        return jsonConvertService.truckListJsonToJsonString(trucksToTrucksJson.trucksToTrucksJson(trucks));
    }

    private CargoReport saveCargoReport(TelegramLoadTruck telegramLoadTruck, String cargoJson, String buildImageString) {
        return cargoReportRepository.save(cargoReportMapper.loadTruckToEntity(telegramLoadTruck, cargoJson, buildImageString));
    }

    private String formatImageString(String buildImageString) {
        return TELEGRAM_BOT_FORMAT_SYMBOL + buildImageString + TELEGRAM_BOT_FORMAT_SYMBOL;
    }
}
