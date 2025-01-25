package com.example.coinmarketcapbotapplication;

import lombok.Getter;
import lombok.Setter;

// Клас для зберігання параметрів відповіді, зокрема часу затримки при помилці 429
@Getter
@Setter
public class CustomResponseParameters {
    private String retryAfter;  // Час затримки у секундах, після якого можна повторити запит
}
