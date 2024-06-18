package ru.homework.cargo.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CargoSizeDtoApplicationTests {
	@Test
	void contextLoads() {
		StringArrayToMatrixTransformer stringArrayToMatrixTransformer = new StringArrayToMatrixTransformer();
		char[][] chars = stringArrayToMatrixTransformer.transform(Arrays.asList(" в   в ","вввввв"," в      "));
		char[][] charsLocal = new char[3][6];
		charsLocal[0] = " в   в ".toCharArray();
		charsLocal[1] = "вввввв".toCharArray();
		charsLocal[2] = " в      ".toCharArray();
		assertTrue(Arrays.deepEquals(chars, charsLocal));
	}
}
