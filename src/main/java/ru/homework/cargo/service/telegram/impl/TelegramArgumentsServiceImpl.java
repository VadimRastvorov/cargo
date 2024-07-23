package ru.homework.cargo.service.telegram.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.telegram.Arguments;
import ru.homework.cargo.mapper.TelegramParametersMapper;
import ru.homework.cargo.service.telegram.TelegramArgumentsService;
import ru.homework.cargo.type.TelegramCommandType;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramArgumentsServiceImpl implements TelegramArgumentsService {
    private final static String SPLIT_CHAR = "-";
    private final static char SPACE_CHAR = ' ';
    private final static int SKIP_DEFAULT = 1;
    private final static int START_POSITION_COMMAND = 0;
    private final static int START_POSITION_SUBSTRING = 0;

    private final TelegramParametersMapper telegramParametersMapper;

    public Arguments createTelegramArguments(String message) {
        log.info("метод createTelegramArguments: {}", message);
        String[] args = message.split(SPLIT_CHAR);
        String command = args[START_POSITION_COMMAND].toLowerCase().trim();
        Map<String, String> parameters =
                Arrays.stream(args)
                        .skip(SKIP_DEFAULT)
                        .collect(Collectors
                                .toMap(s -> s.substring(START_POSITION_SUBSTRING, s.indexOf(SPACE_CHAR)).trim(),
                                        s -> s.substring(s.indexOf(SPACE_CHAR)).trim()));


        return Arguments.builder()
                .telegramCommandType(TelegramCommandType.get(command))
                .parameters(telegramParametersMapper.mapToEntity(parameters))
                .build();
    }
}
