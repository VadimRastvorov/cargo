package ru.homework.cargo.service.json;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import ru.homework.cargo.entity.json.CargoJson;
import ru.homework.cargo.entity.json.TruckListJson;
import ru.homework.cargo.service.JsonConvertService;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class JsonConvertServiceTest {

    @Test
    void shouldConvertJsonStringToTruckListJson() {
        Gson gson = new Gson();

        String jsonString = "{\"truckList\":[{\"cargo\":[\"Item1\",\"Item2\"]}]}";
        TruckListJson expectedTruckListJson = TruckListJson.builder()
                .truckList(Collections.singletonList(CargoJson.builder().cargo(Arrays.asList("Item1", "Item2")).build()))
                .build();

        gson.fromJson(jsonString, TruckListJson.class);

        JsonConvertService jsonConvertService = new JsonConvertServiceImpl(gson);
        TruckListJson truckListJson = jsonConvertService.jsonStringToTruckListJson(jsonString);

        assertThat(truckListJson).isEqualTo(expectedTruckListJson);
    }

    @Test
    void shouldConvertTruckListJsonToJsonString() {
        Gson gson = new Gson();

        TruckListJson truckListJson = TruckListJson.builder()
                .truckList(Collections.singletonList(CargoJson.builder().cargo(Arrays.asList("Item1", "Item2")).build()))
                .build();
        String expectedJsonString = "{\"truckList\":[{\"cargo\":[\"Item1\",\"Item2\"]}]}";

        gson.toJson(truckListJson);

        JsonConvertService jsonConvertService = new JsonConvertServiceImpl(gson);
        String jsonString = jsonConvertService.truckListJsonToJsonString(truckListJson);

        assertThat(jsonString).isEqualTo(expectedJsonString);
    }
}