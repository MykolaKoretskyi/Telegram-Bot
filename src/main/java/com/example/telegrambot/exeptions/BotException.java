package com.example.telegrambot.exeptions;


public class BotException extends RuntimeException{
    public BotException(String errorMessage){
      super(errorMessage);
    }
}
