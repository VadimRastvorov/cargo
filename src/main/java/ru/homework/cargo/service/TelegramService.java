package ru.homework.cargo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.telegram.Arguments;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramService {
    private final TelegramArgumentsService telegramArgumentsService;
    private final CommandFactoryService commandFactoryService;

    public String telegramPrint(String messageText) {
        Arguments arguments = telegramArgumentsService.createTelegramArguments(messageText);
        CommandService commandService = commandFactoryService.invokeCommand(arguments.getTelegramCommandType());
        return commandService.invoke(arguments.getParameters());
    }
}