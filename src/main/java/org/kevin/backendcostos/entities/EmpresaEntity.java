package org.kevin.backendcostos.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "empresa")
public class EmpresaEntity {

    @Id
    private Long id;

    @Column(name = "nombre")
    private String nombre;
}
