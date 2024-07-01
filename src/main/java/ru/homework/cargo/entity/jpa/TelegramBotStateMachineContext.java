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
@Table(name = "state_machine", schema = "cargo")
public class TelegramBotStateMachineContext {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "chat_id")
    private String chatId;
    private String state;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
