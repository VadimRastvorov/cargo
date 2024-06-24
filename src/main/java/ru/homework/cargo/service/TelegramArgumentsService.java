package ru.homework.cargo.service;

import ru.homework.cargo.entity.telegram.Arguments;

public interface TelegramArgumentsService {
    Arguments createTelegramArguments(String message);
}
