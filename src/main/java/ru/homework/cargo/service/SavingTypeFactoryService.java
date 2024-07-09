package ru.homework.cargo.service;

import ru.homework.cargo.type.SaveDataType;
//todo обычно Factory в конце - SavingTypeFactory
public interface SavingTypeFactoryService {
    SavingTypesService saveTypeData(SaveDataType savingType);
}
