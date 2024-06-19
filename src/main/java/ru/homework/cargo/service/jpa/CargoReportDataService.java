package ru.homework.cargo.service.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.cargo.dto.domain.CargoReportDto;
import ru.homework.cargo.mapper.CargoReportMapper;
import ru.homework.cargo.repository.CargoReportRepository;
import ru.homework.cargo.entity.CargoReport;

@Service
@RequiredArgsConstructor
public class CargoReportDataService {
    private final CargoReportRepository cargoReportRepository;
    private final CargoReportMapper cargoReportMapper;

    public CargoReportDto saveData(CargoReportDto cargoReportDto) {
        CargoReport cargoReport = cargoReportRepository.save(cargoReportMapper.toEntity(cargoReportDto));
        return cargoReportMapper.toDto(cargoReport);
    }
}
