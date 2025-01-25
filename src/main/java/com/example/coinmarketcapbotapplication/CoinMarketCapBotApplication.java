package com.example.coinmarketcapbotapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class CoinMarketCapBotApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoinMarketCapBotApplication.class, args);
    }
}







