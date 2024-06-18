package ru.homework.cargo.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MatrixToStringArrayTransformer {
    public List<String> transform(char[][] matrix) {
        return IntStream.range(0, matrix.length)
                .mapToObj(i -> new String(matrix[i]))
                .collect(Collectors.toList());
    }
}
