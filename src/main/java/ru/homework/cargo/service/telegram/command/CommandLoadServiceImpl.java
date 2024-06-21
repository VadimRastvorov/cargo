package ru.homework.cargo.service.telegram.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.jpa.CargoReport;
import ru.homework.cargo.entity.telegram.TelegramLoadTruck;
import ru.homework.cargo.mapper.TelegramLoadTruckMapper;
import ru.homework.cargo.mapper.TruckListJsonMapper;
import ru.homework.cargo.mapper.jpa.CargoReportMapper;
import ru.homework.cargo.repository.CargoReportRepository;
import ru.homework.cargo.service.BuilderImageService;
import ru.homework.cargo.service.json.JsonConvertService;
import ru.homework.cargo.service.loadingTruck.AlgorithmFactory;
import ru.homework.cargo.service.loadingTruck.AlgorithmService;
import ru.homework.cargo.service.telegram.CommandService;
import ru.homework.cargo.service.telegram.CreatingTruckLoadService;
import ru.homework.cargo.type.AlgorithmType;
import ru.homework.cargo.util.Transformer;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommandLoadServiceImpl implements CommandService {
    private final static String TELEGRAM_BOT_FORMAT_SYMBOL = "```";
    private final TelegramLoadTruckMapper telegramLoadTruckMapper;
    private final CreatingTruckLoadService creatingTruckLoadService;
    private final BuilderImageService builderImageService;
    private final JsonConvertService jsonConvertService;
    private final CargoReportRepository cargoReportRepository;
    private final CargoReportMapper cargoReportMapper;
    private final AlgorithmFactory algorithmFactory;
    private final TruckListJsonMapper trucksToTrucksJson;

    @Override
    public String invoke(Map<String, String> parameters) {
        TelegramLoadTruck telegramLoadTruck = telegramLoadTruckMapper.mapToLoadTruck(parameters);

        AlgorithmService algorithmService =
                algorithmFactory.algorithmLoadTruck(AlgorithmType.get(telegramLoadTruck.getAlgorithmName()));

        List<char[][]> trucks = algorithmService.invoke(creatingTruckLoadService.createTruckLoad(telegramLoadTruck));

        String buildImageString = builderImageService.buildImageString(trucks);
        String cargoJson = jsonConvertService.truckListJsonToJsonString(trucksToTrucksJson.trucksToTrucksJson(trucks));
        CargoReport cargoReport =
                cargoReportRepository.save(cargoReportMapper.loadTruckToEntity(telegramLoadTruck, cargoJson, buildImageString));
        log.info("метод loadTrucksService cargoReportDto: {}", cargoReport);

        return formatImageString(buildImageString);
    }

    private String formatImageString(String buildImageString) {
        return TELEGRAM_BOT_FORMAT_SYMBOL + buildImageString + TELEGRAM_BOT_FORMAT_SYMBOL;
    }
}
