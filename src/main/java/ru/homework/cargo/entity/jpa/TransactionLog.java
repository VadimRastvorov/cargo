package ru.homework.cargo.entity.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "transaction_log", schema = "cargo")
public class TransactionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String response;
    private String request;
    private String source;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
