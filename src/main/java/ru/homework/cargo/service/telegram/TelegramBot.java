package ru.homework.cargo.service.telegram;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.homework.cargo.config.telegram.BotConfig;
import ru.homework.cargo.dto.domain.RequestDto;
import ru.homework.cargo.dto.domain.ResponseDto;
import ru.homework.cargo.service.jpa.RequestDataService;
import ru.homework.cargo.service.jpa.ResponseDataService;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private final TelegramService telegramService;
    private final RequestDataService requestDataService;
    private final ResponseDataService responseDataService;

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
            requestDataService.saveData(RequestDto.builder().message(messageText).source("telegram").build());
            long chatId = update.getMessage().getChatId();
            log.info("chatId: '{}' messageText: '{}'", chatId, messageText);

            String messageTextOut;
            try {
                messageTextOut = telegramService.telegramPrint(messageText, update.getMessage().getChat().getFirstName());
            } catch (Exception ex) {
                messageTextOut = ex.getMessage();
            }
            sendMessage(chatId, (messageTextOut.isBlank()) ? "В ответ вернулась пустая строка" : messageTextOut);
        }
    }

    private void sendMessage(Long chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        try {
            responseDataService.saveData(ResponseDto.builder().message(textToSend).source("telegram").build());
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.info("TelegramApiException In Method sendMessage: '{}'", e);
            responseDataService.saveData(ResponseDto.builder().message("TelegramApiException In Method sendMessage: " + e.toString()).source("telegram").build());
        }
    }
}