package ru.homework.cargo.mapper.jpa;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.homework.cargo.dto.CarcaseTypeDto;
import ru.homework.cargo.entity.jpa.CarcaseType;
import ru.homework.cargo.entity.telegram.SavingType;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarcaseTypeMapper {
    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    CarcaseType toEntity(CarcaseTypeDto source);

    CarcaseTypeDto toDto(CarcaseType source);

    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    CarcaseType fromSaveDataTypeToEntity(SavingType source);


}
