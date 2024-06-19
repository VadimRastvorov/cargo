package ru.homework.cargo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "carcase_type", schema = "cargo")
public class CarcaseType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String code;
    private long width;
    private long height;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
