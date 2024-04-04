package org.kevin.backendcostos.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "subcuenta")
public class Subcuenta {

    @Id
    private Long id;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne()
    @JoinColumn(name = "agrupacion")
    private Agrupacion agrupacion;
}
