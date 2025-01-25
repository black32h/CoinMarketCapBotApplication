package com.example.coinmarketcapbotapplication;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
// Цей клас відповідає за зчитування налаштувань з конфігурації (application.yml або application.properties)
@ConfigurationProperties(prefix = "coinmarketcap.api")  // Вказуємо префікс для налаштувань, що мають бути зчитані
public class CoinMarketCapProperties {

    private String url;  // URL API CoinMarketCap
    private String key;  // Ключ API для доступу до CoinMarketCap

    // Геттер для URL
    public String getUrl() {
        return url;
    }

    // Сеттер для URL
    public void setUrl(String url) {
        this.url = url;
    }

    // Геттер для ключа API
    public String getKey() {
        return key;
    }

    // Сеттер для ключа API
    public void setKey(String key) {
        this.key = key;
    }
}
