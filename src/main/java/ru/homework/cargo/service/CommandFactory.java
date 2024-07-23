package ru.homework.cargo.service;

import ru.homework.cargo.type.TelegramCommandType;

public interface CommandFactory {
    CommandService invokeCommand(TelegramCommandType telegramCommandType);
}
