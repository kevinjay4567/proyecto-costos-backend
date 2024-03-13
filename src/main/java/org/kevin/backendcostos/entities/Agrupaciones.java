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
@Entity(name = "agrupaciones")
public class Agrupaciones {

    @Id
    private Long subcuenta;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "agrupacion", nullable = false)
    private String agrupacion;
}
