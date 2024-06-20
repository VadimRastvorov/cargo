package ru.homework.cargo.service.telegram;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.service.telegram.command.CommandCargoService;
import ru.homework.cargo.service.telegram.command.CommandLoadService;
import ru.homework.cargo.service.telegram.command.CommandSaveService;
import ru.homework.cargo.service.telegram.command.CommandStartService;
import ru.homework.cargo.type.TelegramCommandType;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommandFactoryService {
    private final CommandSaveService commandSaveService;
    private final CommandCargoService commandCargoService;
    private final CommandStartService commandStartService;
    private final CommandLoadService commandLoadService;

    public CommandService invokeCommand(TelegramCommandType telegramCommandType) {
        return switch (telegramCommandType) {
            case START -> commandStartService;
            case LOAD -> commandLoadService;
            case CARGO -> commandCargoService;
            case SAVE -> commandSaveService;
        };
    }
}
