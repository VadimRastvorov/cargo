package ru.homework.cargo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.cargo.repository.entity.ParcelType;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface ParcelTypeRepository extends JpaRepository<ParcelType, Long> {
    List<ParcelType> findAllByOrderByIdAsc();

    List<ParcelType> findByTitleContainsIgnoreCaseOrderByIdAsc(String title);

    Optional<ParcelType> findByCode(String code);
}
