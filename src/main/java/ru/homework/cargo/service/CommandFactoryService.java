package ru.homework.cargo.service;

import ru.homework.cargo.type.TelegramCommandType;
//todo обычно Factory в конце: CommandFactory
public interface CommandFactoryService {
    //todo в фабрике мы не вызываем, а выбираем getCommand
    CommandService invokeCommand(TelegramCommandType telegramCommandType);
}
