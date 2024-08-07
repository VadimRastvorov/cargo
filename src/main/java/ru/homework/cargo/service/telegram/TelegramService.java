package ru.homework.cargo.service.telegram;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.telegram.Arguments;
import ru.homework.cargo.service.CommandFactory;
import ru.homework.cargo.service.CommandService;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramService {
    private final TelegramArgumentsService telegramArgumentsService;
    private final CommandFactory commandFactory;

    public String telegramPrint(String messageText) {
        Arguments arguments = telegramArgumentsService.createTelegramArguments(messageText);
        CommandService commandService = commandFactory.invokeCommand(arguments.getTelegramCommandType());
        return commandService.invoke(arguments.getParameters());
    }
}