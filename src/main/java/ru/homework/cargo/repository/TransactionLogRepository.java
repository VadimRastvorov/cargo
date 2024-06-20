package ru.homework.cargo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.cargo.entity.jpa.TransactionLog;

public interface TransactionLogRepository extends JpaRepository<TransactionLog, Long> {
}
