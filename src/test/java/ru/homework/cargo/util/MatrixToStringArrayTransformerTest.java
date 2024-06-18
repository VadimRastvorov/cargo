package ru.homework.cargo.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatrixToStringArrayTransformerTest {

    @Test
    void transform() {
        MatrixToStringArrayTransformer matrixToStringArrayTransformer = new MatrixToStringArrayTransformer();
        char[][] charsLocal = new char[3][6];
        charsLocal[0] = " в   в ".toCharArray();
        charsLocal[1] = "вввввв".toCharArray();
        charsLocal[2] = " в      ".toCharArray();
        List<String> stringListLocal = Arrays.asList(" в   в ","вввввв"," в      ");
        List<String> stringList = matrixToStringArrayTransformer.transform(charsLocal);
        assertTrue(stringList.equals(stringListLocal));
    }
}