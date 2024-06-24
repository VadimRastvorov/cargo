package ru.homework.cargo.service;

import java.util.Map;

public interface CommandService {
    String invoke(Map<String, String> parameters);
}
