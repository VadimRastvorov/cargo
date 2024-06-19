package ru.homework.cargo.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TransformerTest {

    @Test
    void shouldTransformMatrixToArrayString() {
        char[][] matrix = {
                {'I', 't', 'e', 'm', '1'},
                {'I', 't', 'e', 'm', '2'}
        };
        List<String> expectedList = Arrays.asList("Item1", "Item2");
        List<String> result = Transformer.matrixToArrayString(matrix);
        assertEquals(expectedList.size(), result.size());
        assertArrayEquals(expectedList.toArray(), result.toArray());
    }

    @Test
    void shouldTransformArrayStringToMatrix() {
        List<String> stringList = Arrays.asList("Item1", "Item2");
        char[][] expectedMatrix = {
                {'I', 't', 'e', 'm', '1'},
                {'I', 't', 'e', 'm', '2'}
        };
        char[][] result = Transformer.arrayStringToMatrix(stringList);
        assertArrayEquals(expectedMatrix, result);
    }

    @Test
    void shouldTransformStringToMatrix() {
        String parcel = "Item1,Item2";
        char[][] expectedMatrix = {
                {'I', 't', 'e', 'm', '1'},
                {'I', 't', 'e', 'm', '2'}
        };
        char[][] result = Transformer.stringToMatrix(parcel);
        assertArrayEquals(expectedMatrix, result);
    }
}