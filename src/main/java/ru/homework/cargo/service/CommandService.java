package ru.homework.cargo.service;

import ru.homework.cargo.entity.telegram.Parameters;

public interface CommandService {
    String invoke(Parameters parameters);
}
