package ru.homework.cargo.dto.telegram;

import lombok.Builder;
import lombok.Getter;
import ru.homework.cargo.type.TelegramCommandType;

import java.util.Map;

@Builder
@Getter
public class ArgumentsDto {
    private final TelegramCommandType telegramCommandType;
    private final Map<String, String> parameters;
}
