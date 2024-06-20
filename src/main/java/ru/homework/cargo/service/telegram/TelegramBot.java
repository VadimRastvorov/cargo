package ru.homework.cargo.service.telegram;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.homework.cargo.config.telegram.BotConfig;
import ru.homework.cargo.exception.CustomException;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final TelegramService telegramService;
    private final BotConfig botConfig;

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            log.info("chatId: '{}' messageText: '{}'", chatId, messageText);
            try {
                sendMessage(chatId, telegramService.telegramPrint(messageText));
            } catch (CustomException ex) {
                sendMessage(chatId, ex.getMessage());
            } catch (Exception ex) {
                log.info("TelegramApiException In Method onUpdateReceived: '{}'", ex.toString());
            }
        }
    }

    public void sendMessage(Long chatId, String textToSend) {
        try {
            execute(SendMessage.builder()
                    .chatId(chatId)
                    .text(textToSend)
                    .parseMode(ParseMode.MARKDOWN)
                    .build());
        } catch (TelegramApiException ex) {
            log.info("TelegramApiException In Method sendMessage: '{}'", ex.toString());
        }
    }
}