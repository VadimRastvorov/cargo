package ru.homework.cargo.service.json;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.json.TruckListJson;
import ru.homework.cargo.service.JsonConvertService;

@Service
@RequiredArgsConstructor
public class JsonConvertServiceImpl implements JsonConvertService {
    private final Gson gson;

    @Override
    public TruckListJson jsonStringToTruckListJson(String json) {
        return gson.fromJson(json, TruckListJson.class);
    }

    @Override
    public String truckListJsonToJsonString(TruckListJson truckListJson) {
        return gson.toJson(truckListJson);
    }
}
