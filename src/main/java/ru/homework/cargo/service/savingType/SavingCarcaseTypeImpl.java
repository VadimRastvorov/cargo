package ru.homework.cargo.service.savingType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.cargo.entity.telegram.SavingType;
import ru.homework.cargo.mapper.jpa.CarcaseTypeMapper;
import ru.homework.cargo.repository.CarcaseTypeRepository;
import ru.homework.cargo.service.SavingTypesService;

@Slf4j
@RequiredArgsConstructor
@Service
public class SavingCarcaseTypeImpl implements SavingTypesService {
    private final CarcaseTypeRepository carcaseTypeRepository;
    private final CarcaseTypeMapper carcaseTypeMapper;

    @Override
    public String save(SavingType savingType) {
        return carcaseTypeRepository.save(carcaseTypeMapper.fromSaveDataTypeToEntity(savingType)).toString();
    }
}
