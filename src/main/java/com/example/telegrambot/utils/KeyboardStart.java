package com.example.telegrambot.utils;

import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class KeyboardStart {

  public InlineKeyboardMarkup createKeyboardMarkup() {

    InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
    List<List<InlineKeyboardButton>> rows = new ArrayList<>();
    List<InlineKeyboardButton> rowButtons = new ArrayList<>();

    InlineKeyboardButton buttonStart = new InlineKeyboardButton();
    buttonStart.setText("START");
    buttonStart.setCallbackData("START");
    rowButtons.add(buttonStart);

    rows.add(rowButtons);
    keyboardMarkup.setKeyboard(rows);

    return keyboardMarkup;
  }

}
