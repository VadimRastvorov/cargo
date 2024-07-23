package ru.homework.cargo.mapper;

import org.springframework.stereotype.Component;
import ru.homework.cargo.entity.TruckLoad;
import ru.homework.cargo.entity.jpa.CarcaseType;
import ru.homework.cargo.entity.telegram.Parameters;

import java.util.List;

@Component
public class TruckLoadMapper {
    public TruckLoad toEntity(Parameters parameters, CarcaseType carcaseType, List<String> parcelList) {
        return TruckLoad.builder()
                .truckCount(parameters.getTruckCount())
                .parcels(parcelList)
                .width((int) carcaseType.getWidth())
                .height((int) carcaseType.getHeight())
                .build();
    }
}
