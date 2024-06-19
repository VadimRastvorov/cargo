package ru.homework.cargo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.homework.cargo.dto.domain.RequestDto;
import ru.homework.cargo.entity.Request;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RequestMapper {
    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    Request toEntity(RequestDto source);

    RequestDto toDto(Request source);
}
