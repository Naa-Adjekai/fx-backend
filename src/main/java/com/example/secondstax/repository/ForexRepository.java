package com.example.secondstax.repository;

import com.example.secondstax.model.Forex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForexRepository extends JpaRepository<Forex, Long> {

    List<Forex> findAllByTraderId(Long traderId);

    List<Forex> findAllByProviderId(Long providerId);

    List<Forex> findAllByCurrencyFromAndCurrencyTo(String currencyFrom, String currencyTo);

    List<Forex> findByBaseCurrencyAndCounterCurrencyOrderByExchangeRateAsc(String baseCurrency, String counterCurrency);
}

