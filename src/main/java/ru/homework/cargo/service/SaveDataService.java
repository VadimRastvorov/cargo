package ru.homework.cargo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.dto.jpa.CarcaseTypeDto;
import ru.homework.cargo.dto.jpa.ParcelTypeDto;
import ru.homework.cargo.dto.telegram.SaveDataTypeDto;
import ru.homework.cargo.service.jpa.CarcaseTypeDataService;
import ru.homework.cargo.service.jpa.ParcelTypeDataService;
import ru.homework.cargo.type.SaveDataType;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaveDataService { //todo сделать общий интерфейс
    private final CarcaseTypeDataService carcaseTypeDataService;
    private final ParcelTypeDataService parcelTypeDataService;

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

    private String saveToCarcaseType(String code, String title, long width, long height) {
        // /save -type carcase -code Г9 -title Газель 9 на 9 -width 9 -height 9
        //todo комментарии убрать, можно сделать read.me файл
        return carcaseTypeDataService.saveData(CarcaseTypeDto.builder()
                .code(code)
                .title(title)
                .width(width)
                .height(height)
                .build()).toString();
    }

    private String saveToParcelType(String code, String title, String parcel) {
        // /save -type parcel -code Г -title Гитара в жестком кейсе -parcel ГГГГ
        //todo комментарии убрать, можно сделать read.me файл
        return parcelTypeDataService.saveData(ParcelTypeDto.builder()
                .code(code)
                .title(title)
                .parcel(parcel)
                .build()).toString();
    }
}
