package ru.homework.cargo.service.telegram;

import org.junit.jupiter.api.Test;
import ru.homework.cargo.dto.telegram.ArgumentsDto;
import ru.homework.cargo.dto.telegram.LoadTruckDto;
import ru.homework.cargo.dto.telegram.SaveDataTypeDto;
import ru.homework.cargo.type.TelegramCommandType;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class TelegramArgumentsServiceTest {

    private final TelegramArgumentsService telegramArgumentsService = new TelegramArgumentsService();

    @Test
    void shouldCreateTelegramArguments() {
        String message = "/Загрузить -в Газель 6 на 6 -машин 2 -алгоритм Равномерный -посылки Торшер,Диван,Велосипед";

        ArgumentsDto argumentsDto = telegramArgumentsService.createTelegramArguments(message);

        assertThat(argumentsDto.getTelegramCommandType()).isEqualTo(TelegramCommandType.ЗАГРУЗИТЬ);
        Map<String, String> parameters = argumentsDto.getParameters();
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

        LoadTruckDto loadTruckDto = telegramArgumentsService.createLoadTruck(parameters);

        assertThat(loadTruckDto.getTruckTitle()).isEqualTo("Газель 6 на 6");
        assertThat(loadTruckDto.getTruckCount()).isEqualTo(2);
        assertThat(loadTruckDto.getAlgorithmName()).isEqualTo("Равномерный");
        assertThat(loadTruckDto.getParcel()).isEqualTo("Торшер,Диван,Велосипед");
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

        SaveDataTypeDto saveDataTypeDto = telegramArgumentsService.saveDataType(parameters);

        assertThat(saveDataTypeDto.getTitle()).isEqualTo("Шкаф");
        assertThat(saveDataTypeDto.getWidth()).isEqualTo(3);
        assertThat(saveDataTypeDto.getHeight()).isEqualTo(6);
        assertThat(saveDataTypeDto.getParcel()).isEqualTo("333,333,333,333,333,333");
        assertThat(saveDataTypeDto.getCode()).isEqualTo("Ш");
        assertThat(saveDataTypeDto.getType()).isEqualTo("Мебель");
    }
}