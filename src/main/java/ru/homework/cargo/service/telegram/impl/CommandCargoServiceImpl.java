package ru.homework.cargo.service.telegram.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.telegram.Parameters;
import ru.homework.cargo.service.CommandService;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommandCargoServiceImpl implements CommandService {
    private final static String DEFAULT_TEXT_RETURN = "команда была переписана, логика еще не реализованна";

    @Override
    public String invoke(Parameters parameters) {
        return DEFAULT_TEXT_RETURN;
    }
}
