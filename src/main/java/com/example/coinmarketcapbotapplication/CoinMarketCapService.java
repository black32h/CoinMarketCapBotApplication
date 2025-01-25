package com.example.coinmarketcapbotapplication;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
// Цей клас відповідає за отримання даних від CoinMarketCap API
public class CoinMarketCapService {

    private final RestTemplate restTemplate = new RestTemplate();  // Об'єкт для виконання HTTP-запитів
    private final CoinMarketCapProperties properties;  // Інжектимо налаштування для API

    // Конструктор для ініціалізації класу з налаштуваннями
    public CoinMarketCapService(CoinMarketCapProperties properties) {
        this.properties = properties;
    }

    // Метод для отримання списку криптовалют
    public String getCryptocurrencies() {
        String url = properties.getUrl();  // Отримуємо URL з налаштувань

        // Додавання заголовків з API-ключем для авторизації
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CMC_PRO_API_KEY", properties.getKey());  // Встановлюємо API-ключ у заголовок

        // Створення HTTP-запиту з заголовками
        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);

        // Виконуємо GET-запит до API і повертаємо відповідь як рядок
        return restTemplate.exchange(url, org.springframework.http.HttpMethod.GET, entity, String.class).getBody();
    }
}
