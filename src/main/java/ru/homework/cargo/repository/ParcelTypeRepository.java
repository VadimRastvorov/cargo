package ru.homework.cargo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.cargo.entity.jpa.ParcelType;

import java.util.List;

@Transactional
@Repository
public interface ParcelTypeRepository extends JpaRepository<ParcelType, Long> {
    List<ParcelType> findAllByOrderByIdAsc();

    List<ParcelType> findByTitleContainsIgnoreCaseOrderByIdAsc(String title);
}
