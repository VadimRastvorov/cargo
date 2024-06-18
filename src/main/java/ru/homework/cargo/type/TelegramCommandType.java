package ru.homework.cargo.type;

import java.util.Arrays;

public enum TelegramCommandType {
    START("/start"),
    LOAD("/load"),
    CARGO("/cargo"),
    ЗАГРУЗИТЬ("/загрузить"),
    SAVE("/save");
    private final String command;

    TelegramCommandType(String command) {
        this.command = command;
    }

    public static TelegramCommandType get(String command) {
        return Arrays.stream(TelegramCommandType.values())
                .filter(telegramCommandType -> telegramCommandType.command.equals(command))
                .findFirst().orElse(START);
    }
}
