package ru.homework.cargo.util;

import ru.homework.cargo.entity.json.CargoJson;
import ru.homework.cargo.entity.CargoPosition;
import ru.homework.cargo.entity.json.TruckListJson;
import ru.homework.cargo.entity.TruckLoad;
import ru.homework.cargo.entity.jpa.CarcaseType;
import ru.homework.cargo.entity.telegram.TelegramLoadTruck;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Transformer {
    private final static char REPLACEMENT_CHARACTER = '\u0000';
    private final static char PLACEHOLDER_SYMBOL = ' ';
    private final static String SPLIT_SYMBOL = ",";

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
        return Arrays.stream(parcel.split(SPLIT_SYMBOL))
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
                .map(x -> new String(x).replace(REPLACEMENT_CHARACTER, PLACEHOLDER_SYMBOL))
                .collect(Collectors.toList());
    }

    public static CargoPosition createCargoStartPosition(char[][] parcel, int height, int width, boolean fullTruck) {
        return CargoPosition.builder()
                .height(height)
                .width(width)
                .fullTruck(fullTruck)
                .parcel(parcel)
                .build();
    }

    public static TruckLoad createTruckLoad(TelegramLoadTruck telegramLoadTruck, CarcaseType carcaseType, List<String> parcelList)  {
        return TruckLoad.builder()
                .truckCount(telegramLoadTruck.getTruckCount())
                .parcels(parcelList)
                .width((int) carcaseType.getWidth())
                .height((int) carcaseType.getHeight())
                .build();
    }
}
