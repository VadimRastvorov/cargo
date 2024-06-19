package ru.homework.cargo.repository.entity; //todo вынеси entity из repository

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor //todo можно заменить на @RequiredArgsConstructor
@AllArgsConstructor //todo можно заменить на @RequiredArgsConstructor
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
