package ru.homework.cargo.service.telegram.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.telegram.Parameters;
import ru.homework.cargo.service.CommandService;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommandStartServiceImpl implements CommandService {
    private final static String DEFAULT_TEXT_RETURN = "Привет!" + "\n" + "пример ввода команды: load";

    @Override
    public String invoke(Parameters parameters) {
        return DEFAULT_TEXT_RETURN;
    }
}
