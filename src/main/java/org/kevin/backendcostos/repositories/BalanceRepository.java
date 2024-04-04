package org.kevin.backendcostos.repositories;

import org.kevin.backendcostos.entities.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance, Long> { }
