package ru.homework.cargo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.cargo.repository.entity.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
