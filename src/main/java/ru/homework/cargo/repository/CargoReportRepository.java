package ru.homework.cargo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.cargo.entity.jpa.CargoReport;

public interface CargoReportRepository extends JpaRepository<CargoReport, Long> {
}
