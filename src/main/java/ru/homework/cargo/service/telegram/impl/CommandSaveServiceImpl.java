package ru.homework.cargo.service.telegram.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.telegram.Parameters;
import ru.homework.cargo.service.CommandService;
import ru.homework.cargo.service.SavingTypeFactoryService;
import ru.homework.cargo.service.SavingTypesService;
import ru.homework.cargo.type.SaveDataType;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommandSaveServiceImpl implements CommandService {
    private final SavingTypeFactoryService savingTypeFactory;

    @Override
    public String invoke(Parameters parameters) {
        SavingTypesService savingTypesService = savingTypeFactory.saveTypeData(SaveDataType.get(parameters.getType()));
        return savingTypesService.save(parameters);
    }
}
