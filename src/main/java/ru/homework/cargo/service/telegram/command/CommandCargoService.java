package ru.homework.cargo.service.telegram.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.service.telegram.CommandService;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommandCargoService implements CommandService {
    private final static String DEFAULT_TEXT_RETURN = "команда была переписана, логика еще не реализованна";

    @Override
    public String invoke(Map<String, String> parameters) {
        return DEFAULT_TEXT_RETURN;
    }
}
