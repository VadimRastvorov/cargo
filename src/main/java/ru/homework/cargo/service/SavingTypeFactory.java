package ru.homework.cargo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.service.savingType.SavingCarcaseType;
import ru.homework.cargo.service.savingType.SavingParcelType;
import ru.homework.cargo.type.SaveDataType;

@Slf4j
@Service
@RequiredArgsConstructor
public class SavingTypeFactory {
    SavingParcelType savingParcelType;
    SavingCarcaseType savingCarcaseType;

    public SavingTypesService saveTypeData(SaveDataType savingType) {
        log.info("метод saveToDataBase: {}", savingType);
        return switch (savingType) {
            case CARCASE -> savingCarcaseType;
            case PARCEL -> savingParcelType;
        };
    }
}
