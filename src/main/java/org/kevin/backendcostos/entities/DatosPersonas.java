package org.kevin.backendcostos.entities;

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
@Entity(name = "datos_personas")
public class DatosPersonas {

    @Id
    private Long id;

    private String sede;

    private String vinculacion;

    private String actividad;

    private String facultad;

    private String programa;

    private double promedio;

    private String nivel;

    private int anho;

}
