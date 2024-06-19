package ru.homework.cargo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.homework.cargo.dto.domain.ResponseDto;
import ru.homework.cargo.entity.Response;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResponseMapper {
    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    Response toEntity(ResponseDto source);

    ResponseDto toDto(Response source);
}
