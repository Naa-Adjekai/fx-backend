package com.example.secondstax.controller;

import com.example.secondstax.model.*;
import com.example.secondstax.service.ForexService;
import com.example.secondstax.service.TraderService;
import com.example.secondstax.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/forex")
public class ForexController {

    private final ForexService forexService;
    private final TraderService traderService;

    @Autowired
    public ForexController(ForexService forexService, TraderService traderService) {
        this.forexService = forexService;
        this.traderService = traderService;
    }

    // Endpoint for create a new trader
    @PostMapping("/traders")
    public ResponseEntity<Trader> createTrader(@RequestBody Trader trader) {
        Trader newTrader = traderService.createTrader(trader);
        return new ResponseEntity<>(newTrader, HttpStatus.CREATED);
    }


    @GetMapping("/traders")
    public ResponseEntity<List<Trader>> getAllTraders() {
        List<Trader> traders = forexService.getAllTraders();
        if (traders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(traders, HttpStatus.OK);
    }

    @GetMapping("/traders/{id}")
    public ResponseEntity<?> getTraderById(@PathVariable("id") long id) {
        Trader trader = forexService.getTraderById(id);
        if (trader == null) {
            return new ResponseEntity<>("Trader not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trader, HttpStatus.OK);
    }

    //Endpoint for trader to make a request to purchase Forex
    @PostMapping("/forex")
    public ResponseEntity<ExchangeRate> requestForex(@RequestBody Forex forex) {
        ExchangeRate exchangeRate = forexService.getExchangeRate(forex);
        return new ResponseEntity<>(exchangeRate, HttpStatus.OK);
    }


    // Endpoint for getting exchange rates for a currency pair
    @GetMapping("/exchange")
    public ResponseEntity<ExchangeRate> getExchangeRate(@RequestParam String fromCurrency, @RequestParam String toCurrency) {
        String Provider = new String();
        ExchangeRate exchangeRate = forexService.getExchangeRate(fromCurrency, toCurrency, Provider);
        return new ResponseEntity<>(exchangeRate, HttpStatus.OK);
    }

    // Endpoint for getting available forex providers
    @GetMapping("/providers")
    public ResponseEntity<List<Provider>> getProviders() {
        List<Provider> providers = forexService.getProviders();
        if (providers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(providers, HttpStatus.OK);
    }

    // Endpoint for selecting a forex provider
    @PutMapping("/traders/{id}/providers/{providerId}")
    public ResponseEntity<Trader> selectPreferredProvider(@PathVariable Long id, @PathVariable Long providerId) {
        Trader trader = traderService.selectPreferredProvider(id, providerId);
        return new ResponseEntity<>(trader, HttpStatus.OK);
    }


    // Endpoint for getting forex provider rates
    @GetMapping("/provider-rates")
    public ResponseEntity<List<ProviderRate>> getProviderRates() {
        List<ProviderRate> providerRates = forexService.getProviderRates();
        return new ResponseEntity<>(providerRates, HttpStatus.OK);
    }

    //Endpoint for exchange rate
    @GetMapping("/exchange-rate")
    public ExchangeRate getExchangeRate(@RequestParam String fromCurrency,
                                        @RequestParam String toCurrency,
                                        @RequestParam String provider) {
        return forexService.getExchangeRate(fromCurrency, toCurrency, provider);
    }

    //Endpoint for best offers
    @PostMapping("/best-offer")
    public ResponseEntity<ProviderRate> getBestOffer(@RequestBody Forex forex) {
        ProviderRate bestOffer = ProviderService.getBestOffer(forex);
        if (bestOffer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bestOffer, HttpStatus.OK);
    }

    //Endpoint for purchasing Forex
    @PostMapping("/purchase")
    public ResponseEntity<Forex> purchaseForex(@RequestBody Forex forex) {
        Forex purchasedForex = forexService.purchaseForex(forex);
        return ResponseEntity.ok(purchasedForex);
    }

    //Endpoint for past purchase
    @GetMapping("/purchases")
    public List<Purchase> getPurchaseHistory() {
        return forexService.getPurchaseHistory();
    }
}

// TODO: 3/24/2023 notes(The provider rate is the rate at which
//  a Forex provider is willing to sell a particular currency,
//  while the exchange rate is the rate at which two currencies can be exchanged for each other.
//  The provider rate is typically higher than the exchange rate, as the provider seeks to make a profit on the sale of the currency.
//  The exchange rate is determined by the market forces of supply and demand,
//  and can fluctuate based on a variety of factors, such as economic conditions, political events, and global trade. )
