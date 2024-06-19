package ru.homework.cargo.service.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.config.CargoConfig;
import ru.homework.cargo.dto.TruckDto;
import ru.homework.cargo.dto.json.TruckListJson;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class JsonService {     //todo неиспользуемые сервисы убрать
    private final CargoConfig cargoConfig;
    private final JsonConvertService jsonConvertService;

    @SneakyThrows
    public void serialization(List<TruckDto> truck, String fileName) {
        log.info("запуск метода Serialization с truckV2s: '{}'", truck.stream()
                .map(TruckDto::getCargoTruck)
                .toList());
        TruckListJson truckListJson = jsonConvertService.trucksToTrucksJson(truck);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(getStringFileContent(fileName)), truckListJson);
    }

    public List<TruckDto> deSerialization(String fileName) throws IOException {
        log.info("запуск метода DeSerialization");
        ObjectMapper mapper = new ObjectMapper();
        String readString = Files.readString(Path.of(getStringFileContent(fileName)));
        TruckListJson truckListJsonRead = mapper.readValue(new StringReader(readString), TruckListJson.class);

        return jsonConvertService.truckListJsonToTrucks(truckListJsonRead);
    }

    private String getStringFileContent(String fileName) {
        return cargoConfig.getFileDirectory().concat("/".concat(fileName));
    }
}
