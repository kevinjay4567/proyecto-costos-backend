package org.kevin.backendcostos.services.balance;

import org.kevin.backendcostos.entities.BalanceEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BalanceService {

    List<BalanceEntity> getAll();
    ResponseEntity<?> store(MultipartFile file) throws IOException;
}
