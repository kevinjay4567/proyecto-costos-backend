package org.kevin.backendcostos.repositories;

import org.kevin.backendcostos.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> { }
