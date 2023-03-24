package com.example.secondstax.service;

import com.example.secondstax.model.*;

import java.util.List;

public interface ForexService {
    List<Forex> getAllForex();

    List<Forex> getBestOffer(String baseCurrency, String counterCurrency, Double amount);

    ExchangeRate getExchangeRate(String baseCurrency, String quoteCurrency, String provider);

    List<Trader> getAllTraders();

    Trader getTraderById(long id);

    List<Provider> getProviders();

    List<ProviderRate> getProviderRates();

    Forex purchaseForex(Forex forex);

    ExchangeRate getExchangeRate(Forex forex);

    List<Purchase> getPurchaseHistory();
}
