package ru.homework.cargo.service.telegram.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.telegram.SavingType;
import ru.homework.cargo.service.SavingTypeFactory;
import ru.homework.cargo.service.SavingTypesService;
import ru.homework.cargo.service.telegram.CommandService;
import ru.homework.cargo.service.telegram.TelegramArgumentsService;
import ru.homework.cargo.type.SaveDataType;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommandSaveService implements CommandService {
    private final SavingTypeFactory savingTypeFactory;
    private final TelegramArgumentsService telegramArgumentsService;

    @Override
    public String invoke(Map<String, String> parameters) {
        SavingType savingType = telegramArgumentsService.saveDataType(parameters);
        SavingTypesService savingTypesService = savingTypeFactory.saveTypeData(SaveDataType.get(savingType.getType()));
        return savingTypesService.save(savingType);
    }
}
