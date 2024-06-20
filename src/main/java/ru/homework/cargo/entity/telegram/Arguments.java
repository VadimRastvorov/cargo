package ru.homework.cargo.entity.telegram;

import lombok.Builder;
import lombok.Getter;
import ru.homework.cargo.type.TelegramCommandType;

import java.util.Map;

@Builder
@Getter
public class Arguments {
    private final TelegramCommandType telegramCommandType;
    private final Map<String, String> parameters;
}
