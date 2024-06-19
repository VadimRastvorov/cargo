package ru.homework.cargo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.homework.cargo.dto.jpa.CargoReportDto;
import ru.homework.cargo.repository.entity.CargoReport;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CargoReportMapper {
    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    CargoReport toEntity(CargoReportDto source);

    CargoReportDto toDto(CargoReport source);
}
