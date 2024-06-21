package ru.homework.cargo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuilderImageService {
    private final static String CARCASS_SYMBOL = "+";
    private final static String NEW_LINE_SYMBOL = "\n";
    private final static String EMPTY_SYMBOL = " ";

    public String buildImageString(char[][] truck) {
        log.info("метод printStringFromCharTruck: {}", Arrays.deepToString(truck));
        StringBuilder stringBuilder = new StringBuilder();
        appendTopBorder(stringBuilder, truck);
        appendTruckContent(stringBuilder, truck);
        appendBottomBorder(stringBuilder, truck);
        return stringBuilder.toString();
    }

    public String buildImageString(List<char[][]> trucks) {
        log.info("метод printStringFromListCharTrucks: {}", trucks);
        return trucks.stream().map(this::buildImageString)
                .collect(Collectors.joining(NEW_LINE_SYMBOL));
    }

    private void appendTopBorder(StringBuilder stringBuilder, char[][] truck) {
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(CARCASS_SYMBOL);
        IntStream.range(0, truck[0].length)
                .forEach(x -> stringBuilder.append(EMPTY_SYMBOL));
        stringBuilder.append(CARCASS_SYMBOL);
        stringBuilder.append(System.lineSeparator());
    }

    private void appendTruckContent(StringBuilder stringBuilder, char[][] truck) {
        for (int i = truck.length - 1; i >= 0; i--) {
            stringBuilder.append(CARCASS_SYMBOL);
            for (int j = 0; j < truck[0].length; j++) {
                stringBuilder.append(truck[i][j] == 0 ? EMPTY_SYMBOL : truck[i][j]);
            }
            stringBuilder.append(CARCASS_SYMBOL);
            stringBuilder.append(System.lineSeparator());
        }
    }

    private void appendBottomBorder(StringBuilder stringBuilder, char[][] truck) {
        stringBuilder.append(CARCASS_SYMBOL);
        IntStream.range(0, truck[0].length)
                .forEach(x -> stringBuilder.append(CARCASS_SYMBOL));
        stringBuilder.append(CARCASS_SYMBOL);
    }
}
