package com.example.secondstax.service;


import com.example.secondstax.model.Forex;
import com.example.secondstax.model.Provider;
import com.example.secondstax.model.ProviderRate;

import java.util.List;

public interface ProviderService {
    static ProviderRate getBestOffer(Forex forex) {
        return null;
    }

    List<Provider> getAllProviders();
    Provider getProviderById(Long id);
    void addProvider(Provider provider);
    void updateProvider(Long id, Provider provider);
    void deleteProviderById(Long id);
    Provider getProviderWithLowestRate();
}

