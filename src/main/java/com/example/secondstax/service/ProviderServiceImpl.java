package com.example.secondstax.service;

import com.example.secondstax.model.Provider;
import com.example.secondstax.repository.ProviderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepository;

    public ProviderServiceImpl(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public List<Provider> getAllProviders() {

        return providerRepository.findAll();
    }

    @Override
    public Provider getProviderById(Long id) {

        return (Provider) providerRepository.findById(id).orElse(null);
    }

    @Override
    public void addProvider(Provider provider) {
        providerRepository.save(provider);
    }

    @Override
    public void updateProvider(Long id, Provider provider) {
        Provider existingProvider = (Provider) providerRepository.findById(id).orElse(null);
        if (existingProvider != null) {
            existingProvider.setName(provider.getName());
            existingProvider.setRate(provider.getExchangeRates());
            providerRepository.save(existingProvider);
        }

    }

    @Override
    public void deleteProviderById(Long id) {

    }

    @Override
    public Provider getProviderWithLowestRate() {
        return null;
    }
}



