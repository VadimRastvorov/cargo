package ru.homework.cargo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.homework.cargo.dto.jpa.CarcaseTypeDto;
import ru.homework.cargo.repository.entity.CarcaseType;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarcaseTypeMapper {
    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    CarcaseType toEntity(CarcaseTypeDto source);
    CarcaseTypeDto toDto(CarcaseType source);
}
