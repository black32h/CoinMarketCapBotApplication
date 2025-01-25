package com.example.coinmarketcapbotapplication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@Component
// Клас для взаємодії з Telegram Bot API
public class CoinMarketCapTelegramBot extends TelegramLongPollingBot {

    @Value("${telegram.bot.username}")
    private String botUsername;  // Ім'я бота, зчитується з конфігурації

    @Value("${telegram.bot.token}")
    private String botToken;  // Токен бота, зчитується з конфігурації

    // Конструктор
    public CoinMarketCapTelegramBot() {
    }

    // Повертає ім'я бота
    @Override
    public String getBotUsername() {
        return botUsername;
    }

    // Повертає токен бота
    @Override
    public String getBotToken() {
        return botToken;
    }

    // Метод для відправлення повідомлень в чат
    public void sendMessage(String вашChatId, String message) throws TelegramApiException, InterruptedException {
        final int MAX_MESSAGE_LENGTH = 4096;  // Максимальна довжина повідомлення

        // Якщо повідомлення занадто довге, розбиваємо його на частини
        if (message.length() > MAX_MESSAGE_LENGTH) {
            int chunkSize = MAX_MESSAGE_LENGTH;
            for (int i = 0; i < message.length(); i += chunkSize) {
                String chunk = message.substring(i, Math.min(i + chunkSize, message.length()));
                sendMessage(new SendMessage(вашChatId, chunk));  // Відправляємо кожну частину
            }
        } else {
            sendMessage(new SendMessage(вашChatId, message));  // Відправляємо коротке повідомлення
        }
    }

    // Допоміжний метод для відправлення повідомлення
    private void sendMessage(SendMessage sendMessage) throws TelegramApiException, InterruptedException {
        try {
            execute(sendMessage);  // Виконуємо запит на відправлення повідомлення
        } catch (TelegramApiRequestException e) {
            // Якщо отримуємо помилку з кодом 429 (занадто багато запитів)
            if (e.getErrorCode() == 429) {
                // Витягування часу затримки (retry_after) з помилки
                String retryAfter = e.getParameters().getRetryAfter().toString();
                long delay = Long.parseLong(retryAfter);  // Перетворюємо на число
                System.out.println("Rate limit reached. Retrying after " + delay + " seconds.");
                Thread.sleep(delay * 5000);  // Чекаємо перед повторною спробою (затримка у мілісекундах)
                execute(sendMessage);  // Повторюємо відправлення повідомлення
            } else {
                throw e;  // Якщо інша помилка, кидаємо її
            }
        }
    }

    // Метод для отримання оновлень (не використовується в даному коді)
    @Override
    public void onUpdateReceived(Update update) {
    }
}
