package ru.homework.cargo.service.telegram.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.telegram.SavingType;
import ru.homework.cargo.mapper.SavingTypeMapper;
import ru.homework.cargo.service.SavingTypeFactoryService;
import ru.homework.cargo.service.SavingTypesService;
import ru.homework.cargo.service.CommandService;
import ru.homework.cargo.type.SaveDataType;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommandSaveServiceImpl implements CommandService {
    private final SavingTypeFactoryService savingTypeFactory;
    private final SavingTypeMapper savingTypeMapper;

    @Override
    public String invoke(Map<String, String> parameters) {
        SavingType savingType = savingTypeMapper.mapToEntity(parameters);
        SavingTypesService savingTypesService = savingTypeFactory.saveTypeData(SaveDataType.get(savingType.getType()));
        return savingTypesService.save(savingType);
    }
}
