package com.example.telegrambot.utils;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Component
public class KeyboardCurrency {

  private final String[] typesOfCurrency = {
      "USD", "EUR", "PLN", "CZK", "SEK", "BGN",
      "CHF", "AMD", "TRY", "RON", "AUD", "CAD",
      "AED", "CNY", "HKD", "HUF", "CNY", "DKK",
      "DOP", "GBP", "HKD", "INR", "IDR", "ILS"};

  public InlineKeyboardMarkup createKeyboardMarkup() {

    InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
    List<List<InlineKeyboardButton>> rows = new ArrayList<>();
    List<InlineKeyboardButton> rowButtons = new ArrayList<>();

    for (int i = 0; i < typesOfCurrency.length; i++) {

      if (i == 0 || i % 6 != 0) {
        createButton(rowButtons, i);
      } else {
        rows.add(rowButtons);
        rowButtons = new ArrayList<>();
        createButton(rowButtons, i);
      }
    }
    rows.add(rowButtons);
    keyboardMarkup.setKeyboard(rows);

    return keyboardMarkup;
  }

  private void createButton(List<InlineKeyboardButton> row, int i) {
    InlineKeyboardButton button = new InlineKeyboardButton();
    button.setText(typesOfCurrency[i]);
    button.setCallbackData(typesOfCurrency[i]);
    row.add(button);
  }

}
