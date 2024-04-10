package org.kevin.backendcostos.services.PTA;

import org.kevin.backendcostos.entities.PTAEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PTAService {

    List<PTAEntity> getAll();

    ResponseEntity<String> store(MultipartFile file);
}
