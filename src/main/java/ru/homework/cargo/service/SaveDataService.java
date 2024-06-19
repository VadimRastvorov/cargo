package ru.homework.cargo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.dto.domain.ParcelTypeDto;
import ru.homework.cargo.dto.telegram.SaveDataTypeDto;
import ru.homework.cargo.service.jpa.CarcaseTypeDataService;
import ru.homework.cargo.service.jpa.ParcelTypeDataService;
import ru.homework.cargo.type.SaveDataType;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaveDataService {
    private final CarcaseTypeDataService carcaseTypeDataService;
    private final ParcelTypeDataService parcelTypeDataService;
    // todo фабрика
    public String saveToDataBase(SaveDataTypeDto saveDataTypeDto) {
        log.info("метод saveToDataBase: {}", saveDataTypeDto);
        return switch (SaveDataType.get(saveDataTypeDto.getType())) {
            case CARCASE -> saveToCarcaseType(saveDataTypeDto.getCode(),
                    saveDataTypeDto.getTitle(),
                    saveDataTypeDto.getWidth(),
                    saveDataTypeDto.getHeight());
            case PARCEL -> saveToParcelType(saveDataTypeDto.getCode(),
                    saveDataTypeDto.getTitle(),
                    saveDataTypeDto.getParcel());
        };
    }
    // todo маппер инпут параметров
    private String saveToCarcaseType(CarcaseType carcaseType) {
        return carcaseTypeRepository.save(carcaseType);
    }

    private String saveToParcelType(String code, String title, String parcel) {
        return parcelTypeDataService.saveData(ParcelTypeDto.builder()
                .code(code)
                .title(title)
                .parcel(parcel)
                .build()).toString();
    }
}
