package ru.homework.cargo.service.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.dto.telegram.ArgumentsDto;
import ru.homework.cargo.dto.telegram.LoadTruckDto;
import ru.homework.cargo.dto.telegram.SaveDataTypeDto;
import ru.homework.cargo.type.TelegramCommandType;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TelegramArgumentsService {
    public ArgumentsDto createTelegramArguments(String message) {
        log.info("метод createTelegramArguments: {}", message);
        String[] args = message.split("-");
        String command = args[0].toLowerCase().trim();
        Map<String, String> parameters =
                Arrays.stream(args)
                        .skip(1)
                        .collect(Collectors
                                .toMap(s -> s.substring(0, s.indexOf(' ')).trim(),
                                        s -> s.substring(s.indexOf(' '), s.length()).trim()));

        return ArgumentsDto.builder()
                .telegramCommandType(TelegramCommandType.get(command))
                .parameters(parameters)
                .build();
    }

    public LoadTruckDto createLoadTruck(Map<String, String> parameters) {
        log.info("метод createLoadTruck: {}", parameters.toString());
        // /Загрузить -в Газель 6 на 6 -машин 2 -алгоритм Равномерный -посылки Торшер,Диван,Велосипед
        return LoadTruckDto.builder()
                .truckTitle(parameters.get("в"))
                .truckCount(Integer.parseInt(Optional.ofNullable(parameters.get("машин")).orElse("0")))
                .algorithmName(parameters.get("алгоритм"))
                .parcel(parameters.get("посылки"))
                .build();
    }

    public SaveDataTypeDto saveDataType(Map<String, String> parameters) {
        log.info("метод saveDataType: {}", parameters.toString());
        return SaveDataTypeDto.builder()
                .title(parameters.get("title"))
                .width(Long.parseLong(Optional.ofNullable(parameters.get("width")).orElse("0")))
                .height(Long.parseLong(Optional.ofNullable(parameters.get("height")).orElse("0")))
                .parcel(parameters.get("parcel"))
                .code(parameters.get("code"))
                .type(parameters.get("type"))
                .build();
    }
}
