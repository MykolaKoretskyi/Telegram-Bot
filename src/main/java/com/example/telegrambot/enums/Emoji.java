package com.example.telegrambot.enums;

public enum Emoji {

  BLUSH(":blush:"),
  SMILE(":smile:"),
  WINK_EYE(":stuck_out_tonque_winking_eye:");

  private String emoji;

  Emoji(String emoji) {
    this.emoji = emoji;
  }

  public String getEmoji() {
    return emoji;
  }

}
