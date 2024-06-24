package ru.homework.cargo.service.telegram;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.service.CommandFactoryService;
import ru.homework.cargo.service.CommandService;
import ru.homework.cargo.type.TelegramCommandType;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommandFactoryServiceImpl implements CommandFactoryService {
    private final CommandSaveServiceImpl commandSaveServiceImpl;
    private final CommandCargoServiceImpl commandCargoServiceImpl;
    private final CommandStartServiceImpl commandStartServiceImpl;
    private final CommandLoadServiceImpl commandLoadServiceImpl;

    public CommandService invokeCommand(TelegramCommandType telegramCommandType) {
        return switch (telegramCommandType) {
            case START -> commandStartServiceImpl;
            case LOAD -> commandLoadServiceImpl;
            case CARGO -> commandCargoServiceImpl;
            case SAVE -> commandSaveServiceImpl;
        };
    }
}
