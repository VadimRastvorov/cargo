package ru.homework.cargo.entity.telegram;

import lombok.Builder;
import lombok.Getter;
import ru.homework.cargo.type.TelegramCommandType;

import java.util.Map;

@Builder
@Getter
//todo почему final?
public class Arguments {
    private final TelegramCommandType telegramCommandType;
    private final Map<String, String> parameters; //todo можно сделать отдельным объектом с нормальными названиями
}
