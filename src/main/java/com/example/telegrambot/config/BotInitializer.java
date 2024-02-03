package com.example.telegrambot.config;

import com.example.telegrambot.services.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class BotInitializer {

  private final ExchangeService exchangeService;

  @Autowired
  public BotInitializer(ExchangeService exchangeService) {
    this.exchangeService = exchangeService;
  }

  @EventListener({ContextRefreshedEvent.class})
  public void init() throws TelegramApiException {
    TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);

    botsApi.registerBot(exchangeService);
  }
}
