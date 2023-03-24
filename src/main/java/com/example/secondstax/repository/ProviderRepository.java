package com.example.secondstax.repository;

import com.example.secondstax.model.Provider;

import java.util.List;
import java.util.Optional;

public interface ProviderRepository {
    List<Provider> findAll();

    Optional<Object> findById(Long id);

    void save(Provider provider);
}
