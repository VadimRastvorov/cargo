package ru.homework.cargo.mapper.jpa;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.homework.cargo.dto.TransactionLogDto;
import ru.homework.cargo.entity.jpa.TransactionLog;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionLogMapper {
    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    TransactionLog toEntity(TransactionLogDto source);

    TransactionLogDto toDto(TransactionLog source);
}
