package ru.homework.cargo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.cargo.repository.entity.Response;

public interface ResponseRepository extends JpaRepository<Response, Long> {
}
