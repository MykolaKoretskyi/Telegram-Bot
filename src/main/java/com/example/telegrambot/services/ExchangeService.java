package com.example.telegrambot.services;


import com.example.telegrambot.config.BotConfig;
import com.example.telegrambot.enums.Emoji;
import com.example.telegrambot.exeptions.BotException;
import com.example.telegrambot.utils.KeyboardCurrency;
import com.example.telegrambot.utils.KeyboardStart;
import com.vdurmont.emoji.EmojiParser;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class ExchangeService extends TelegramLongPollingBot {

  private final BotConfig botConfig;

  @Autowired
  public ExchangeService(BotConfig botConfig) {
    super(botConfig.getBotToken());
    this.botConfig = botConfig;
  }


  @SneakyThrows
  @Override
  public void onUpdateReceived(Update update) {

    if (update.hasMessage() && update.getMessage().hasText()) {

      String message = update.getMessage().getText();
      long chatId = update.getMessage().getChatId();
      checkMessage(update, message, chatId);
      createStartButtons(chatId);

    } else if (update.hasCallbackQuery()) {

      String callbackData = update.getCallbackQuery().getData();
      long chatId = update.getCallbackQuery().getMessage().getChatId();
      String dataMessage = update.getCallbackQuery().getData();
      startService(callbackData, chatId, dataMessage);
    }
  }


  private void startService(String callbackData, long chatId, String dataMessage) {

    if (dataMessage.equals("START")) {
      handleRequest(chatId);
    } else {
      ICurrencyService service = new CurrencyJSONService();
      String message = service.getResponse(callbackData);
      sendMessage(chatId, message);
    }
  }


  private void checkMessage(Update update, String message, long chatId) {

    String info = "This bot informs the user about the "
        + "current exchange rate from the National Bank of Ukraine. ";

    if (message.equals("/start")) {
      startCommand(chatId, info + update.getMessage().getChat().getFirstName());
    } else {
      sendMessage(chatId, info);
    }
  }


  private void createStartButtons(long chatId) {
    SendMessage message = new SendMessage();
    message.setChatId(chatId);
    message.setText("Start working with the service !");

    InlineKeyboardMarkup markup = new KeyboardStart().createKeyboardMarkup();
    message.setReplyMarkup(markup);
    try {
      execute(message);
    } catch (TelegramApiException e) {
      throw new BotException(e.getMessage());
    }
  }


  private void handleRequest(long chatId) {

    SendMessage message = new SendMessage();
    message.setChatId(chatId);
    message.setText("Course on the date: " + new CurrencyJSONService().getDate());

    InlineKeyboardMarkup markup = new KeyboardCurrency().createKeyboardMarkup();
    message.setReplyMarkup(markup);
    try {
      execute(message);
    } catch (TelegramApiException e) {
      throw new BotException(e.getMessage());
    }
  }


  private void sendMessage(long chatId, String message) {
    SendMessage messageInfo = new SendMessage();
    messageInfo.setChatId(chatId);
    messageInfo.setText(message);
    try {
      execute(messageInfo);
    } catch (TelegramApiException e) {
      throw new BotException(e.getMessage());
    }
  }


  private void startCommand(long chatId, String firstName) {
    String answer = Emoji.SMILE.getEmoji() + firstName + " hello. Nice to meet you";
    String response = EmojiParser.parseToUnicode(answer);
    sendMessage(chatId, response);
  }

  @Override
  public String getBotUsername() {
    return botConfig.getBotName();
  }
}
