package com.example.telegrambot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyJSON {

  @JsonProperty("txt")
  private String name;

  @JsonProperty("cc")
  private String currency;

  @JsonProperty("rate")
  private double rate;

  @JsonProperty("exchangedate")
  private String date;

}
