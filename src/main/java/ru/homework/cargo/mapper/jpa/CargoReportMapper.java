package ru.homework.cargo.mapper.jpa;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.homework.cargo.dto.CargoReportDto;
import ru.homework.cargo.entity.jpa.CargoReport;
import ru.homework.cargo.entity.telegram.Parameters;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CargoReportMapper {
    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    CargoReport toEntity(CargoReportDto source);

    CargoReportDto toDto(CargoReport source);

    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "cargoJson", source = "cargoJson")
    @Mapping(target = "cargo", source = "image")
    @Mapping(target = "truck", source = "parameters.truckTitle")
    @Mapping(target = "parcels", source = "parameters.parcelsTitle")
    @Mapping(target = "truckCount", source = "parameters.truckCount")
    @Mapping(target = "algorithm", source = "parameters.algorithmName")
    CargoReport loadTruckToEntity(Parameters parameters, String cargoJson, String image);
}
