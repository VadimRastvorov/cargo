package ru.homework.cargo.service.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.cargo.dto.jpa.CarcaseTypeDto;
import ru.homework.cargo.repository.CarcaseTypeRepository;
import ru.homework.cargo.repository.entity.CarcaseType;
import ru.homework.cargo.mapper.CarcaseTypeMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarcaseTypeDataService {
    private final CarcaseTypeRepository carcaseTypeRepository;
    private final CarcaseTypeMapper carcaseTypeMapper;

    public CarcaseTypeDto saveData(CarcaseTypeDto carcaseTypeDto) {
        CarcaseType carcaseType = carcaseTypeRepository.save(carcaseTypeMapper.toEntity(carcaseTypeDto));
        return carcaseTypeMapper.toDto(carcaseType);
    }

    public List<CarcaseTypeDto> findAllData() {
        List<CarcaseType> carcaseTypes = carcaseTypeRepository.findAllByOrderByIdAsc();
        return carcaseTypeRepository.findAllByOrderByIdAsc().stream()
                .map(carcaseTypeMapper::toDto)
                .toList();
    }

    public List<CarcaseTypeDto> findDataTitle(String title) {
        return carcaseTypeRepository.findByTitleContainsIgnoreCaseOrderByIdAsc(title).stream()
                .map(carcaseTypeMapper::toDto)
                .toList();
    }
}
