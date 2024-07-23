package ru.homework.cargo.mapper;

import org.springframework.stereotype.Component;
import ru.homework.cargo.entity.telegram.Parameters;

import java.util.Map;
import java.util.Optional;

@Component
public class TelegramParametersMapper {
    private final static String TRUCK_TITLE_START = "в";
    private final static String TRUCK_COUNT_START = "машин";
    private final static String ALGORITHM_COUNT_START = "алгоритм";
    private final static String ALGORITHM_PARCEL_START = "посылки";

    public Parameters mapToEntity(Map<String, String> parameters) {

        return Parameters.builder()
                .title(parameters.get("title"))
                .width(Long.parseLong(Optional.ofNullable(parameters.get("width")).orElse("0")))
                .height(Long.parseLong(Optional.ofNullable(parameters.get("height")).orElse("0")))
                .parcel(parameters.get("parcel"))
                .code(parameters.get("code"))
                .type(parameters.get("type"))
                .truckTitle(parameters.get(TRUCK_TITLE_START))
                .truckCount(Integer.parseInt(Optional.ofNullable(parameters.get(TRUCK_COUNT_START)).orElse("0")))
                .algorithmName(parameters.get(ALGORITHM_COUNT_START))
                .parcelsTitle(parameters.get(ALGORITHM_PARCEL_START))
                .build();
    }
}
