package ru.homework.cargo.service;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BuilderImageServiceTest {

    private final BuilderImageService builderImageService = new BuilderImageService();

    @Test
    void shouldBuildImageString() {
        char[][] truck = new char[][]{
                {'a', 'b', 'c', 'd'},
                {'e', 'f', 'g', 'h'},
                {'i', 'j', 'k', 'l'}
        };

        String truckString = builderImageService.buildImageString(truck);

        String expectedTruckString = "```\r\n+    +\r\n+ijkl+\r\n+efgh+\r\n+abcd+\r\n++++++```";
        assertThat(truckString).isEqualTo(expectedTruckString);
    }

    @Test
    void shouldBuildImageStringList() {
        char[][] truck = new char[][]{
                {'c', 'd'},
                {'a', 'b'}
        };

        char[][] truck2 = new char[][]{
                {'7', '8', '9'},
                {'4', '5', '6'},
                {'1', '2', '3'}
        };
        List<char[][]> trucks = Arrays.asList(truck, truck2);

        String trucksString = builderImageService.buildImageString(trucks);

        String expectedTrucksString = "```\r\n+  +\r\n+ab+\r\n+cd+\r\n++++```\n```\r\n+   +\r\n+123+\r\n+456+\r\n+789+\r\n+++++```";
        assertThat(trucksString).isEqualTo(expectedTrucksString);
    }
}