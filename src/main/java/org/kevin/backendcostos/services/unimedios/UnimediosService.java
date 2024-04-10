package org.kevin.backendcostos.services.unimedios;


import org.kevin.backendcostos.entities.UnimediosEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UnimediosService {

    List<UnimediosEntity> getAll();

    ResponseEntity<String> store(MultipartFile file);
}
