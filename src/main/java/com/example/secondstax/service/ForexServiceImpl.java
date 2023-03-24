package com.example.secondstax.service;

import com.example.secondstax.model.ExchangeRate;
import com.example.secondstax.model.Forex;
import com.example.secondstax.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public abstract class ForexServiceImpl implements ForexService {

    private final ForexRepository forexRepository;

    @Autowired
    public ForexServiceImpl(ForexRepository forexRepository) {
        this.forexRepository = forexRepository;
    }

    @Override
    public List<Forex> getAllForex() {
        return forexRepository.findAll();
    }


    @Override
    public List<Forex> getBestOffer(String baseCurrency, String counterCurrency, Double amount) {
        List<Forex> bestOffers = new ArrayList<>();
        List<Forex> forexList = forexRepository.findByBaseCurrencyAndCounterCurrencyOrderByExchangeRateAsc(baseCurrency, counterCurrency);
        for (Forex forex : forexList) {
            Double exchangeRate = forex.getExchangeRate();
            Double totalAmount = amount * exchangeRate;
            forex.setAmount(amount);
            forex.setTotalAmount(totalAmount);
            bestOffers.add(forex);
        }
        return bestOffers;
    }

    @Override
    public ExchangeRate getExchangeRate(String baseCurrency, String quoteCurrency, String provider) {
        return null;
    }
}


