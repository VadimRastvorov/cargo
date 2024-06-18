package ru.homework.cargo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.model.TelegramTruck;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrintLoadingTruck {

    public String printStringFromCharTruck(TelegramTruck truck) {
        log.info("метод printStringFromCharTruck: {}", truck.toString());
        StringBuilder stringBuilder = new StringBuilder();
        appendTopBorder(stringBuilder, truck);
        appendTruckContent(stringBuilder, truck);
        appendBottomBorder(stringBuilder, truck);
        return stringBuilder.toString();
    }

    public String printStringFromListCharTrucks(List<TelegramTruck> trucks) {
        log.info("метод printStringFromListCharTrucks: {}", trucks);
        return trucks.stream().map(this::printStringFromCharTruck)
                .collect(Collectors.joining("\n"));
    }

    private void appendTopBorder(StringBuilder stringBuilder, TelegramTruck truck) {
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("+");
        IntStream.range(0, truck.getWidth())
                .forEach(x -> stringBuilder.append(" "));
        stringBuilder.append("+");
        stringBuilder.append(System.lineSeparator());
    }

    private void appendTruckContent(StringBuilder stringBuilder, TelegramTruck truck) {
        for (int i = truck.getHeight() - 1; i >= 0; i--) {
            stringBuilder.append("+");
            for (int j = 0; j < truck.getWidth(); j++) {
                stringBuilder.append(truck.getTruck()[i][j] == 0 ? " " : truck.getTruck()[i][j]);
            }
            stringBuilder.append("+");
            stringBuilder.append(System.lineSeparator());
        }
    }

    private void appendBottomBorder(StringBuilder stringBuilder, TelegramTruck truck) {
        stringBuilder.append("+");
        IntStream.range(0, truck.getWidth())
                .forEach(x -> stringBuilder.append("+"));
        stringBuilder.append("+");
    }
}
