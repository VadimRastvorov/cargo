package ru.homework.cargo.type;

import ru.homework.cargo.exception.CustomException;

import java.util.Arrays;

public enum SaveDataType {
    CARCASE("carcase"),
    PARCEL("parcel");
    private final String saveType;

    SaveDataType(String saveType) {
        this.saveType = saveType;
    }

    public static SaveDataType get(String saveType) {
        return Arrays.stream(SaveDataType.values())
                .filter(x -> x.saveType.equals(saveType))
                .findFirst().orElseThrow(() -> new CustomException("Ошибка Parameters"));
    }
}