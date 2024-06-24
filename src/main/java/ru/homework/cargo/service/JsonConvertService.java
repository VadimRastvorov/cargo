package ru.homework.cargo.service;

import ru.homework.cargo.entity.json.TruckListJson;

public interface JsonConvertService {
    TruckListJson jsonStringToTruckListJson(String json);

    String truckListJsonToJsonString(TruckListJson truckListJson);
}
