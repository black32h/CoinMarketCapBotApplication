package com.example.coinmarketcapbotapplication;

import com.example.coinmarketcapbotapplication.CoinMarketCapTelegramBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramBotConfig {

    // Бін для створення і налаштування Telegram бота
    @Bean
    public CoinMarketCapTelegramBot coinMarketCapTelegramBot() {
        return new CoinMarketCapTelegramBot();
    }

    // Бін для налаштування TelegramBotsApi та реєстрації бота
    @Bean
    public TelegramBotsApi telegramBotsApi(CoinMarketCapTelegramBot bot) throws TelegramApiException {
        // Створення нового екземпляра TelegramBotsApi для реєстрації бота
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(bot);  // Реєстрація бота у API
        return botsApi;
    }
}
