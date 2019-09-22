package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.enums.EZonaResidencial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ZONA_RESIDENCIAL")
public class ZonaResidencial {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "CODIGO")
    @Enumerated(EnumType.STRING)
    private EZonaResidencial codigo;

    @Column(name = "ZONA_RESIDENCIAL")
    private String zonaResidencial;
}
