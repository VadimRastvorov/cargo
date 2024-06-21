package ru.homework.cargo.service.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.telegram.Arguments;
import ru.homework.cargo.type.TelegramCommandType;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TelegramArgumentsService {
    private final static String SPLIT_CHAR = "-";
    private final static char SPACE_CHAR = ' ';

    public Arguments createTelegramArguments(String message) {
        log.info("метод createTelegramArguments: {}", message);
        String[] args = message.split(SPLIT_CHAR);
        String command = args[0].toLowerCase().trim();
        Map<String, String> parameters =
                Arrays.stream(args)
                        .skip(1)
                        .collect(Collectors
                                .toMap(s -> s.substring(0, s.indexOf(SPACE_CHAR)).trim(),
                                        s -> s.substring(s.indexOf(SPACE_CHAR)).trim()));

        return Arguments.builder()
                .telegramCommandType(TelegramCommandType.get(command))
                .parameters(parameters)
                .build();
    }


}
