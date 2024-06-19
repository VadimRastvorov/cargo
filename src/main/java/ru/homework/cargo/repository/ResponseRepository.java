package ru.homework.cargo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.cargo.entity.Response;

public interface ResponseRepository extends JpaRepository<Response, Long> {
}
