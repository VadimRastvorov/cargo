package ru.homework.cargo.service.telegram;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.dto.telegram.ArgumentsDto;
import ru.homework.cargo.service.LoadingTruckService;
import ru.homework.cargo.service.SaveDataService;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramService {
    private final TelegramArgumentsService telegramArgumentsService;
    private final SaveDataService saveDataService;
    private final LoadingTruckService loadingTruckService;

    public String telegramPrint(String messageText, String name) {

        ArgumentsDto argumentsDto = telegramArgumentsService.createTelegramArguments(messageText);

        return switch (argumentsDto.getTelegramCommandType()) {
            case START -> "Привет, " + name + "!" + "\n" +
                    "пример ввода команды: load";
            case LOAD -> loadingTruckService
                    .loadTrucksService(telegramArgumentsService
                            .createLoadTruck(argumentsDto.getParameters()));
            case CARGO -> "команда была переписана, логика еще не реализованна";
            case SAVE -> saveDataService
                    .saveToDataBase(telegramArgumentsService
                            .saveDataType(argumentsDto.getParameters()));
        };
    }
}
