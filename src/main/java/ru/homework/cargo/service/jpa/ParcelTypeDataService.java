package ru.homework.cargo.service.jpa;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.cargo.dto.jpa.ParcelTypeDto;
import ru.homework.cargo.mapper.ParcelTypeMapper;
import ru.homework.cargo.repository.ParcelTypeRepository;
import ru.homework.cargo.repository.entity.ParcelType;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParcelTypeDataService {
    private final ParcelTypeRepository parcelTypeRepository;
    private final ParcelTypeMapper parcelTypeMapper;

    public ParcelTypeDto saveData(ParcelTypeDto parcelTypeDto) {
        ParcelType parcelType = parcelTypeRepository.save(parcelTypeMapper.toEntity(parcelTypeDto));
        return parcelTypeMapper.toDto(parcelType);
    }

    public List<ParcelTypeDto> findAllData() {
        return parcelTypeRepository.findAllByOrderByIdAsc().stream()
                .map(parcelTypeMapper::toDto)
                .toList();
    }

    public List<ParcelTypeDto> findDataTitle(String title) {
        return parcelTypeRepository.findByTitleContainsIgnoreCaseOrderByIdAsc(title).stream()
                .map(parcelTypeMapper::toDto)
                .toList();
    }

    public ParcelTypeDto findDataById(Long id) {
        ParcelType parcelType = parcelTypeRepository.findById(id).orElseThrow(() -> new NotFoundException("Тип посылки не найден"));
        return parcelTypeMapper.toDto(parcelType);
    }
}
