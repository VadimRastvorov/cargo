package ru.homework.cargo.entity.telegram;

import lombok.Builder;
import lombok.Getter;
import ru.homework.cargo.type.TelegramCommandType;

@Builder
@Getter
public class Arguments {
    private TelegramCommandType telegramCommandType;
    private Parameters parameters;
}
