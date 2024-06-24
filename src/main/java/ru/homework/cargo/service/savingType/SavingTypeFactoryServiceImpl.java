package ru.homework.cargo.service.savingType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.service.SavingTypeFactoryService;
import ru.homework.cargo.service.SavingTypesService;
import ru.homework.cargo.type.SaveDataType;

@Slf4j
@Service
@RequiredArgsConstructor
public class SavingTypeFactoryServiceImpl implements SavingTypeFactoryService {
    SavingParcelTypeImpl savingParcelTypeImpl;
    SavingCarcaseTypeImpl savingCarcaseTypeImpl;

    @Override
    public SavingTypesService saveTypeData(SaveDataType savingType) {
        log.info("метод saveToDataBase: {}", savingType);
        return switch (savingType) {
            case CARCASE -> savingCarcaseTypeImpl;
            case PARCEL -> savingParcelTypeImpl;
        };
    }
}
