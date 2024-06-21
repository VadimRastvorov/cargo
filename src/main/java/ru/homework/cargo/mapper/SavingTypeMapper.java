package ru.homework.cargo.mapper;

import org.springframework.stereotype.Component;
import ru.homework.cargo.entity.telegram.SavingType;

import java.util.Map;
import java.util.Optional;

@Component
public class SavingTypeMapper {
    public SavingType mapToEntity(Map<String, String> parameters) {
        return SavingType.builder()
                .title(parameters.get("title"))
                .width(Long.parseLong(Optional.ofNullable(parameters.get("width")).orElse("0")))
                .height(Long.parseLong(Optional.ofNullable(parameters.get("height")).orElse("0")))
                .parcel(parameters.get("parcel"))
                .code(parameters.get("code"))
                .type(parameters.get("type"))
                .build();
    }
}
