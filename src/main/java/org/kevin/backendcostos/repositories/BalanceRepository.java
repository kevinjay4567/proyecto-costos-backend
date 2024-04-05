package org.kevin.backendcostos.repositories;

import org.kevin.backendcostos.entities.BalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<BalanceEntity, Long> {
}
