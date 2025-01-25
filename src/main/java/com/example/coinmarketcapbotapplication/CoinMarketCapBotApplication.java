package com.example.coinmarketcapbotapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableScheduling;

// Основний клас програми для запуску Spring Boot застосунку
@SpringBootApplication  // Вказує, що цей клас є точкою входу для Spring Boot застосунку
@EnableScheduling       // Дозволяє використання планувальника задач (для регулярного виконання задач)
public class CoinMarketCapBotApplication {

    // Головний метод для запуску програми
    public static void main(String[] args) {
        // Запуск Spring Boot застосунку
        SpringApplication.run(CoinMarketCapBotApplication.class, args);
    }
}






