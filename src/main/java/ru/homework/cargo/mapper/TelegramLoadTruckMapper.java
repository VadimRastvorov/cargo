package ru.homework.cargo.mapper;

import org.springframework.stereotype.Component;
import ru.homework.cargo.entity.telegram.TelegramLoadTruck;

import java.util.Map;
import java.util.Optional;

@Component
public class TelegramLoadTruckMapper {
    private final static String TRUCK_TITLE_START = "в";
    private final static String TRUCK_COUNT_START = "машин";
    private final static String ALGORITHM_COUNT_START = "алгоритм";
    private final static String ALGORITHM_PARCEL_START = "посылки";

    public TelegramLoadTruck mapToLoadTruck(Map<String, String> parameters) {
        return TelegramLoadTruck.builder()
                .truckTitle(parameters.get(TRUCK_TITLE_START))
                .truckCount(Integer.parseInt(Optional.ofNullable(parameters.get(TRUCK_COUNT_START)).orElse("0")))
                .algorithmName(parameters.get(ALGORITHM_COUNT_START))
                .parcel(parameters.get(ALGORITHM_PARCEL_START))
                .build();
    }
}
