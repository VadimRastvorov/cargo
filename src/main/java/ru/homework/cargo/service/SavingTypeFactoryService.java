package ru.homework.cargo.service;

import ru.homework.cargo.type.SaveDataType;

public interface SavingTypeFactoryService {
    SavingTypesService saveTypeData(SaveDataType savingType);
}
