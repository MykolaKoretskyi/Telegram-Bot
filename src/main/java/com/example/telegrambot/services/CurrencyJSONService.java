package com.example.telegrambot.services;

import com.example.telegrambot.model.CurrencyJSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("application.properties")
public class CurrencyJSONService implements ICurrencyService {

  private static String url;
  private List<CurrencyJSON> currencyJSONList = new ArrayList<>();


  @Value("${json.url}")
  public void setUrl(String url) {
    this.url = url;
  }


  @Override
  public String getResponse(String currency) {
    getCurrencyList();
    CurrencyJSON curr = getCurrencyJSON(currency);
    return curr != null ? curr.getName() + ": " +
        curr.getCurrency() + " rate is " + curr.getRate() : "ERROR !";
  }


  @Override
  public String getDate() {

    getCurrencyList();
    CurrencyJSON curr = getCurrencyJSON("USD");
    if (curr == null) {
      curr = getCurrencyJSON("EUR");
    }
    return curr != null ? curr.getDate() : "Not specified !";
  }


  @SneakyThrows
  private void getCurrencyList() {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(url))
        .GET()
        .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    String res = response.body();
    ObjectMapper mapper = new ObjectMapper();
    TypeReference<List<CurrencyJSON>> listType = new TypeReference<>() {
    };
    currencyJSONList = mapper.readValue(res, listType);
  }


  private CurrencyJSON getCurrencyJSON(String currency) {

    return currencyJSONList.stream()
        .filter(el -> el.getCurrency().equals(currency)).findAny().orElse(null);
  }

}
