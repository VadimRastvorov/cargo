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
@Table(name = "req_msg", schema = "cargo")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private String source;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
