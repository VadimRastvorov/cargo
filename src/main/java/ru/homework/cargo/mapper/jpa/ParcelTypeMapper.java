package ru.homework.cargo.mapper.jpa;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.homework.cargo.dto.ParcelTypeDto;
import ru.homework.cargo.entity.jpa.ParcelType;
import ru.homework.cargo.entity.telegram.SavingType;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ParcelTypeMapper {
    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    ParcelType toEntity(ParcelTypeDto source);

    ParcelTypeDto toDto(ParcelType source);

    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    ParcelType fromSaveDataTypeToEntity(SavingType source);
}
