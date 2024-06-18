package ru.homework.cargo.util;

import java.util.List;
import java.util.stream.Collectors;

public class StringArrayToMatrixTransformer {
    public char[][] transform(List<String> stringList) {
        return stringList.stream()
                .map(String::toCharArray)
                .collect(Collectors.toList())
                .toArray(new char[stringList.size()][]);
    }
}
