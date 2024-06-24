package ru.homework.cargo.service;

import ru.homework.cargo.type.TelegramCommandType;

public interface CommandFactoryService {
    CommandService invokeCommand(TelegramCommandType telegramCommandType);
}
