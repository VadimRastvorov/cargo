package ru.homework.cargo.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CargoJson {
    private final List<String> cargo;
}
