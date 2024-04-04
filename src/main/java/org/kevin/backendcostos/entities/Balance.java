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
@Entity(name = "balance")
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "anho")
    private int anho;

    @Column(name = "valor")
    private Long valor;

    @ManyToOne()
    @JoinColumn(name = "concepto")
    private Concepto concepto;

    @ManyToOne()
    @JoinColumn(name = "subcuenta")
    private Subcuenta subcuenta;

    @ManyToOne()
    @JoinColumn(name = "sede")
    private Sede sede;

    @ManyToOne()
    @JoinColumn(name = "empresa")
    private Empresa empresa;
}
