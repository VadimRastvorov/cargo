package ru.homework.cargo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.homework.cargo.dto.domain.ParcelTypeDto;
import ru.homework.cargo.entity.ParcelType;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ParcelTypeMapper {
    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    ParcelType toEntity(ParcelTypeDto source);

    ParcelTypeDto toDto(ParcelType source);
}
