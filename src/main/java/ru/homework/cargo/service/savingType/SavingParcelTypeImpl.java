package ru.homework.cargo.service.savingType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.telegram.Parameters;
import ru.homework.cargo.mapper.jpa.ParcelTypeMapper;
import ru.homework.cargo.repository.ParcelTypeRepository;
import ru.homework.cargo.service.SavingTypesService;

@Slf4j
@RequiredArgsConstructor
@Service
public class SavingParcelTypeImpl implements SavingTypesService {
    private final ParcelTypeRepository parcelTypeRepository;
    private final ParcelTypeMapper parcelTypeMapper;

    @Override
    public String save(Parameters savingType) {
        return parcelTypeRepository.save(parcelTypeMapper.fromSaveDataTypeToEntity(savingType)).toString();
    }
}
