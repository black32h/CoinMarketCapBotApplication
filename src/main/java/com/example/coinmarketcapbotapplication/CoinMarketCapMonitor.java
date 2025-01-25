package com.example.coinmarketcapbotapplication;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
// Компонент, що моніторить нові монети з CoinMarketCap
class CoinMarketCapMonitor {

    private final CoinMarketCapService service;  // Сервіс для отримання даних з CoinMarketCap
    private final CoinMarketCapTelegramBot bot;  // Телеграм-бот для відправки повідомлень
    private final Set<String> sentCoins = new HashSet<>();  // Набір для відслідковування вже надісланих монет
    private final Set<String> allCoins = new HashSet<>();   // Набір для зберігання всіх монет

    // Конструктор, який приймає сервіси
    public CoinMarketCapMonitor(CoinMarketCapService service, CoinMarketCapTelegramBot bot) {
        this.service = service;
        this.bot = bot;
    }

    // Метод, який виконується через певні інтервали (кожні 2 години)
    @Scheduled(fixedRate = 7200000) // Час між запитами - 2 години
    public void checkNewCoins() throws TelegramApiException, InterruptedException {
        // Отримуємо список монет від сервісу
        String newCoins = service.getCryptocurrencies();

        // Перетворюємо великий список монет в окремі рядки
        List<String> coinList = List.of(newCoins.split("\n"));

        // Додаємо всі монети до загального списку
        allCoins.addAll(coinList);

        // Відправляємо всі монети користувачу
        String allCoinsMessage = String.join("\n", allCoins);
        bot.sendMessage("985667869", allCoinsMessage);  // Замість "ВАШ_CHAT_ID" використовуйте реальний chatId

        // Фільтруємо лише нові монети, яких ще не було відправлено
        List<String> newUnsentCoins = coinList.stream()
                .filter(coin -> !sentCoins.contains(coin))
                .collect(Collectors.toList());

        // Якщо нові монети є, відправляємо їх
        if (!newUnsentCoins.isEmpty()) {
            String newCoinsMessage = String.join("\n", newUnsentCoins);
            bot.sendMessage("985667869", newCoinsMessage);  // Замість "ВАШ_CHAT_ID" використовуйте реальний chatId

            // Додаємо нові монети до списку надісланих
            sentCoins.addAll(newUnsentCoins);
        }
    }
}
