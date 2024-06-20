package ru.homework.cargo.service.json;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.json.TruckListJson;

@Service
@RequiredArgsConstructor
public class JsonConvertService {
    private final Gson gson;

    public TruckListJson jsonStringToTruckListJson(String json) {
        return gson.fromJson(json, TruckListJson.class);
    }

    public String truckListJsonToJsonString(TruckListJson truckListJson) {
        return gson.toJson(truckListJson);
    }
}
