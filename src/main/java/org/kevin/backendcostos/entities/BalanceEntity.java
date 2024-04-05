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
public class BalanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "anho")
    private int anho;

    @Column(name = "valor")
    private int valor;

    private Long concepto_id;

    private Long subcuenta_id;

    private Long sede_id;

    private Long empresa_id;

    @ManyToOne()
    @JoinColumn(name = "concepto_id", insertable = false, updatable = false)
    private ConceptoEntity concepto;

    @ManyToOne()
    @JoinColumn(name = "subcuenta_id", insertable = false, updatable = false)
    private SubcuentaEntity subcuenta;

    @ManyToOne()
    @JoinColumn(name = "sede_id", insertable = false, updatable = false)
    private SedeEntity sede;

    @ManyToOne()
    @JoinColumn(name = "empresa_id", insertable = false, updatable = false)
    private EmpresaEntity empresa;
}
