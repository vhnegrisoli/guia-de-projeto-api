package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.model.Usuario;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model.InscricaoImobiliaria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "CONSULTA")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "DATA_CONSULTA")
    @NotNull
    private LocalDateTime dataConsulta;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "FK_INSCRICAO_IMOBILIARIA")
    private InscricaoImobiliaria inscricaoImobiliaria;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "FK_USUARIO")
    private Usuario usuario;
}
