package ru.homework.cargo.mapper;

import org.springframework.stereotype.Component;
import ru.homework.cargo.entity.TruckLoad;
import ru.homework.cargo.entity.jpa.CarcaseType;
import ru.homework.cargo.entity.telegram.TelegramLoadTruck;

import java.util.List;

@Component
public class TruckLoadMapper {
    public TruckLoad toEntity(TelegramLoadTruck telegramLoadTruck, CarcaseType carcaseType, List<String> parcelList) {
        return TruckLoad.builder()
                .truckCount(telegramLoadTruck.getTruckCount())
                .parcels(parcelList)
                .width((int) carcaseType.getWidth())
                .height((int) carcaseType.getHeight())
                .build();
    }
}
