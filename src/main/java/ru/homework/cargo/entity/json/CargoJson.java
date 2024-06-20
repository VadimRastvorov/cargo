package ru.homework.cargo.entity.json;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CargoJson {
    private final List<String> cargo;
}
