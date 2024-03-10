package org.kevin.backendcostos.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name="files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonProperty("file_name")
    @Column(name = "name", nullable = false)
    private String fileName;

    @JsonProperty("total_registers")
    @Column(name = "total_registers", nullable = false)
    private long totalRegisters;
    //private List<String> columnsName;
}
