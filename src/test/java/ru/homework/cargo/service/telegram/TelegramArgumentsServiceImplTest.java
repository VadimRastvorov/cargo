package ru.homework.cargo.service.telegram;

import org.junit.jupiter.api.Test;
import ru.homework.cargo.entity.telegram.Arguments;
import ru.homework.cargo.entity.telegram.SavingType;
import ru.homework.cargo.entity.telegram.TelegramLoadTruck;
import ru.homework.cargo.mapper.SavingTypeMapper;
import ru.homework.cargo.mapper.TelegramLoadTruckMapper;
import ru.homework.cargo.service.TelegramArgumentsService;
import ru.homework.cargo.type.TelegramCommandType;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class TelegramArgumentsServiceImplTest {

    TelegramArgumentsService telegramArgumentsService = new TelegramArgumentsServiceImpl();
    TelegramLoadTruckMapper telegramLoadTruckMapper = new TelegramLoadTruckMapper();
    SavingTypeMapper savingTypeMapper = new SavingTypeMapper();

    @Test
    void shouldCreateTelegramArguments() {
        String message = "/Загрузить -в Газель 6 на 6 -машин 2 -алгоритм Равномерный -посылки Торшер,Диван,Велосипед";

        Arguments arguments = telegramArgumentsService.createTelegramArguments(message);

        assertThat(arguments.getTelegramCommandType()).isEqualTo(TelegramCommandType.LOAD);
        Map<String, String> parameters = arguments.getParameters();
        assertThat(parameters).containsEntry("в", "Газель 6 на 6");
        assertThat(parameters).containsEntry("машин", "2");
        assertThat(parameters).containsEntry("алгоритм", "Равномерный");
        assertThat(parameters).containsEntry("посылки", "Торшер,Диван,Велосипед");
    }

    @Test
    void shouldCreateLoadTruckDto() {
        Map<String, String> parameters = Map.of(
                "в", "Газель 6 на 6",
                "машин", "2",
                "алгоритм", "Равномерный",
                "посылки", "Торшер,Диван,Велосипед"
        );

        TelegramLoadTruck telegramLoadTruck = telegramLoadTruckMapper.mapToLoadTruck(parameters);

        assertThat(telegramLoadTruck.getTruckTitle()).isEqualTo("Газель 6 на 6");
        assertThat(telegramLoadTruck.getTruckCount()).isEqualTo(2);
        assertThat(telegramLoadTruck.getAlgorithmName()).isEqualTo("Равномерный");
        assertThat(telegramLoadTruck.getParcel()).isEqualTo("Торшер,Диван,Велосипед");
    }

    @Test
    void shouldCreateSaveDataTypeDto() {
        Map<String, String> parameters = Map.of(
                "title", "Шкаф",
                "width", "3",
                "height", "6",
                "parcel", "333,333,333,333,333,333",
                "code", "Ш",
                "type", "Мебель"
        );

        SavingType savingType = savingTypeMapper.mapToEntity(parameters);

        assertThat(savingType.getTitle()).isEqualTo("Шкаф");
        assertThat(savingType.getWidth()).isEqualTo(3);
        assertThat(savingType.getHeight()).isEqualTo(6);
        assertThat(savingType.getParcel()).isEqualTo("333,333,333,333,333,333");
        assertThat(savingType.getCode()).isEqualTo("Ш");
        assertThat(savingType.getType()).isEqualTo("Мебель");
    }
}