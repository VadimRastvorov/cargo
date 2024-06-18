package ru.homework.cargo.service.json;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.homework.cargo.dto.TruckDto;
import ru.homework.cargo.dto.json.CargoJson;
import ru.homework.cargo.dto.json.TruckListJson;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class JsonConvertService {
    @Autowired
    private final Gson gson;

    public TruckListJson jsonStringToTruckListJson(String json) {
        return gson.fromJson(json, TruckListJson.class);
    }

    public String truckListJsonToJsonString(TruckListJson truckListJson) {
        return gson.toJson(truckListJson);
    }


    public TruckListJson trucksToTrucksJson(List<TruckDto> trucks) {
        List<CargoJson> cargoJsonList = trucks.stream()
                .map(x -> x.getCargoTruck())
                .map(x -> CargoJson.builder()
                        .cargo(convertToStringList(x))
                        .build())
                .toList();
        return TruckListJson.builder()
                .truckList(cargoJsonList)
                .build();
    }

    public List<TruckDto> truckListJsonToTrucks(TruckListJson truckListJson) {
        return truckListJson.getTruckList().stream()
                .map(x -> (createCargo(x)))
                .toList();
    }

    private static List<String> convertToStringList(char[][] charArray) {
        return IntStream.range(0, charArray.length)
                .mapToObj(i -> convertRowToString(charArray[i]))
                .collect(Collectors.toList());
    }

    private static String convertRowToString(char[] row) {
        int nonSpaceIndex = 0;
        while (nonSpaceIndex < row.length && row[nonSpaceIndex] == ' ') {
            nonSpaceIndex++;
        }
        return new String(row, nonSpaceIndex, row.length - nonSpaceIndex);
    }

    private TruckDto createCargo(CargoJson cargoJson) {
        char[][] cargoTruck = convertToCharArray(cargoJson.getCargo());
        return TruckDto.builder()
                .cargoTruck(cargoTruck)
                .build();
    }

    private static char[][] convertToCharArray(List<String> strings) {
        int maxLength = strings.stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);

        return strings.stream()
                .map(str -> {
                    char[] charArr = new char[maxLength];
                    Arrays.fill(charArr, ' ');
                    for (int i = 0; i < str.length(); i++) {
                        charArr[i] = str.charAt(i);
                    }
                    return charArr;
                })
                .toArray(char[][]::new);
    }
}
