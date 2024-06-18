package ru.homework.cargo.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
