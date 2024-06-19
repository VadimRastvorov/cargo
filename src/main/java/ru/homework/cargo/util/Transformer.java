package ru.homework.cargo.util;

import ru.homework.cargo.dto.json.CargoJson;
import ru.homework.cargo.dto.json.TruckListJson;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Transformer {
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
        return Arrays.stream(parcel.split(","))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    public static TruckListJson trucksToTrucksJson(List<char[][]> trucks) {
        List<CargoJson> cargoJsonList = trucks.stream()
                .map(Transformer::convertCharArrayToStringList)
                .map(cargo -> CargoJson.builder()
                        .cargo(cargo)
                        .build())
                .collect(Collectors.toList());

        return TruckListJson.builder()
                .truckList(cargoJsonList)
                .build();
    }
    private static List<String> convertCharArrayToStringList(char[][] charArray) {
        return Arrays.stream(charArray)
                .map(x->new String(x).replace('\u0000',' '))
                .collect(Collectors.toList());
    }
}
