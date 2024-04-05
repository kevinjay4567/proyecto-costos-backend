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
public class SubcuentaEntity {

    @Id
    private Long id;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "agrupacion_id")
    private Long agrupacion_id;

    @ManyToOne()
    @JoinColumn(name = "agrupacion_id", insertable = false, updatable = false)
    private AgrupacionEntity agrupacion;
}
