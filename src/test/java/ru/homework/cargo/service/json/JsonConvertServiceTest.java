package ru.homework.cargo.service.json;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.homework.cargo.dto.json.CargoJson;
import ru.homework.cargo.dto.json.TruckListJson;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JsonConvertServiceTest {

    @Mock
    private Gson gson;

    @InjectMocks
    private JsonConvertService jsonConvertService;

    @Test
    void shouldConvertJsonStringToTruckListJson() {
        String jsonString = "{\"truckList\":[{\"cargo\":[\"Item1\",\"Item2\"]}]}";
        TruckListJson expectedTruckListJson = TruckListJson.builder()
                .truckList(Collections.singletonList(CargoJson.builder().cargo(Arrays.asList("Item1", "Item2")).build()))
                .build();

        when(gson.fromJson(jsonString, TruckListJson.class)).thenReturn(expectedTruckListJson);

        TruckListJson truckListJson = jsonConvertService.jsonStringToTruckListJson(jsonString);

        assertThat(truckListJson).isEqualTo(expectedTruckListJson);
    }

    @Test
    void shouldConvertTruckListJsonToJsonString() {
        TruckListJson truckListJson = TruckListJson.builder()
                .truckList(Collections.singletonList(CargoJson.builder().cargo(Arrays.asList("Item1", "Item2")).build()))
                .build();
        String expectedJsonString = "{\"truckList\":[{\"cargo\":[\"Item1\",\"Item2\"]}]}";

        when(gson.toJson(truckListJson)).thenReturn(expectedJsonString);

        String jsonString = jsonConvertService.truckListJsonToJsonString(truckListJson);

        assertThat(jsonString).isEqualTo(expectedJsonString);
    }
}