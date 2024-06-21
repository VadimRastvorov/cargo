package ru.homework.cargo.service.telegram.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.telegram.SavingType;
import ru.homework.cargo.mapper.SavingTypeMapper;
import ru.homework.cargo.service.savingType.SavingTypeFactory;
import ru.homework.cargo.service.savingType.SavingTypesService;
import ru.homework.cargo.service.telegram.CommandService;
import ru.homework.cargo.type.SaveDataType;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommandSaveServiceImpl implements CommandService {
    private final SavingTypeFactory savingTypeFactory;
    private final SavingTypeMapper savingTypeMapper;

    @Override
    public String invoke(Map<String, String> parameters) {
        SavingType savingType = savingTypeMapper.mapToEntity(parameters);
        SavingTypesService savingTypesService = savingTypeFactory.saveTypeData(SaveDataType.get(savingType.getType()));
        return savingTypesService.save(savingType);
    }
}
