package ru.homework.cargo.type;

import java.util.Arrays;

public enum AlgorithmType {
    UNIFORM("Равномерный"),
    MAXIMAL("Максимальный"),
    CONSISTENT("Последовательный");
    private final String name;

    AlgorithmType(String name) {
        this.name = name;
    }
//todo не используется
    public static AlgorithmType get(String name) {
        return Arrays.stream(AlgorithmType.values())
                .filter(algorithmType -> algorithmType.name.equals(name))
                .findFirst().orElse(UNIFORM);
    }
}
