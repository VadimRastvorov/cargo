package ru.homework.cargo.service.telegram;

import java.util.Map;

public interface CommandService {
    String invoke(Map<String, String> parameters);
}
