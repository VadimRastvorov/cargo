package ru.homework.cargo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.cargo.repository.entity.CarcaseType;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CarcaseTypeRepository extends JpaRepository<CarcaseType, Long> {
    List<CarcaseType> findAllByOrderByIdAsc();

    Optional<CarcaseType> findByCode(String code);

    List<CarcaseType> findByTitleContainsIgnoreCaseOrderByIdAsc(String title);
}
