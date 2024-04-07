package org.kevin.backendcostos.services.datospersona;

import org.kevin.backendcostos.entities.DatosPersonaEntity;
import org.kevin.backendcostos.response.DatosPersonaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DatosPersonaService {

    List<DatosPersonaEntity> getAll();

    ResponseEntity<DatosPersonaResponse> store(MultipartFile file);
}
