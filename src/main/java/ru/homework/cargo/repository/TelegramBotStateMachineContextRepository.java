package ru.homework.cargo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.cargo.entity.jpa.TelegramBotStateMachineContext;

import java.util.Optional;

public interface TelegramBotStateMachineContextRepository extends JpaRepository<TelegramBotStateMachineContext, Long> {
    Optional<TelegramBotStateMachineContext> findByChatId(String chatId);
}
