package org.kevin.backendcostos.services.unimedios;

import lombok.RequiredArgsConstructor;
import org.kevin.backendcostos.entities.UnimediosEntity;
import org.kevin.backendcostos.repositories.UnimediosRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UnimediosServiceImpl implements UnimediosService{

    private final UnimediosRepository repository;

    @Override
    public List<UnimediosEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public ResponseEntity<String> store(MultipartFile file) {
        return ResponseEntity.ok("Unimedios stored");
    }
}
