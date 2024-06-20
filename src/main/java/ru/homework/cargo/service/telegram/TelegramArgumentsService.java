package ru.homework.cargo.service.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.telegram.Arguments;
import ru.homework.cargo.entity.telegram.SavingType;
import ru.homework.cargo.entity.telegram.TelegramLoadTruck;
import ru.homework.cargo.type.TelegramCommandType;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TelegramArgumentsService {
    public Arguments createTelegramArguments(String message) {
        log.info("метод createTelegramArguments: {}", message);
        String[] args = message.split("-");
        String command = args[0].toLowerCase().trim();
        Map<String, String> parameters =
                Arrays.stream(args)
                        .skip(1)
                        .collect(Collectors
                                .toMap(s -> s.substring(0, s.indexOf(' ')).trim(),
                                        s -> s.substring(s.indexOf(' ')).trim()));

        return Arguments.builder()
                .telegramCommandType(TelegramCommandType.get(command))
                .parameters(parameters)
                .build();
    }

    public TelegramLoadTruck createLoadTruck(Map<String, String> parameters) {
        log.info("метод createLoadTruck: {}", parameters.toString());
        return TelegramLoadTruck.builder()
                .truckTitle(parameters.get("в"))
                .truckCount(Integer.parseInt(Optional.ofNullable(parameters.get("машин")).orElse("0")))
                .algorithmName(parameters.get("алгоритм"))
                .parcel(parameters.get("посылки"))
                .build();
    }

    public SavingType saveDataType(Map<String, String> parameters) {
        log.info("метод saveDataType: {}", parameters.toString());
        return SavingType.builder()
                .title(parameters.get("title"))
                .width(Long.parseLong(Optional.ofNullable(parameters.get("width")).orElse("0")))
                .height(Long.parseLong(Optional.ofNullable(parameters.get("height")).orElse("0")))
                .parcel(parameters.get("parcel"))
                .code(parameters.get("code"))
                .type(parameters.get("type"))
                .build();
    }
}
