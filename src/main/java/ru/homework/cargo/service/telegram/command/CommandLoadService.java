package ru.homework.cargo.service.telegram.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.service.ProcessLoadingTruckService;
import ru.homework.cargo.service.telegram.CommandService;
import ru.homework.cargo.service.telegram.TelegramArgumentsService;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommandLoadService implements CommandService {
    private final ProcessLoadingTruckService processLoadingTruckService;
    private final TelegramArgumentsService telegramArgumentsService;

    @Override
    public String invoke(Map<String, String> parameters) {
        return processLoadingTruckService
                .loadTrucks(telegramArgumentsService
                        .createLoadTruck(parameters));
    }
}
