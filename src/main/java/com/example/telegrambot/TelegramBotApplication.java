package com.example.telegrambot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This program is a Telegram bot that provides the user with the current
 * exchange rate against the hryvnia from the National Bank of Ukraine.
 */

@SpringBootApplication
public class TelegramBotApplication {

  public static void main(String[] args) {
    SpringApplication.run(TelegramBotApplication.class, args);
  }

}
