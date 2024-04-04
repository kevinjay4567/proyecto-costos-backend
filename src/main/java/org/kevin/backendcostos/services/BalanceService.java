package org.kevin.backendcostos.services;

import lombok.RequiredArgsConstructor;
import org.kevin.backendcostos.entities.Balance;
import org.kevin.backendcostos.repositories.BalanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BalanceService {

    private final BalanceRepository balanceRepository;

    public List<Balance> getAll() {
        return balanceRepository.findAll();
    }
}
