package ru.homework.cargo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.cargo.repository.entity.CargoReport;

public interface CargoReportRepository extends JpaRepository<CargoReport, Long> {
}
