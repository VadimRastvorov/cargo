package ru.homework.cargo.mapper;

import org.springframework.stereotype.Component;
import ru.homework.cargo.entity.json.CargoJson;
import ru.homework.cargo.entity.json.TruckListJson;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TruckListJsonMapper {
    private final static char REPLACEMENT_SYMBOL = '\u0000';
    private final static char SUBSTITUTE_SYMBOL = ' ';

    public TruckListJson trucksToTrucksJson(List<char[][]> trucks) {
        return TruckListJson.builder()
                .truckList(
                        trucks.stream()
                                .map(this::convertCharArrayToStringList)
                                .map(cargo -> CargoJson.builder()
                                        .cargo(cargo)
                                        .build())
                                .collect(Collectors.toList()))
                .build();
    }

    private List<String> convertCharArrayToStringList(char[][] charArray) {
        return Arrays.stream(charArray)
                .map(chars -> new String(chars).replace(REPLACEMENT_SYMBOL, SUBSTITUTE_SYMBOL))
                .collect(Collectors.toList());
    }
}
