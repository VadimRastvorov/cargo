package ru.homework.cargo.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Transformer {
    private final static String SPLIT_SYMBOL = ",";

    public static List<String> matrixToArrayString(char[][] matrix) {
        return Arrays.stream(matrix).map(String::new)
                .collect(Collectors.toList());
    }

    public static char[][] arrayStringToMatrix(List<String> stringList) {
        return stringList.stream()
                .map(String::toCharArray)
                .toList()
                .toArray(new char[stringList.size()][]);
    }

    public static char[][] stringToMatrix(String parcel) {
        return Arrays.stream(parcel.split(SPLIT_SYMBOL))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }
}
