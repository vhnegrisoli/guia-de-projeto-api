package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.model;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.dto.ConsultaRequest;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.dto.UsuarioAutenticado;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.model.Usuario;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model.InscricaoImobiliaria;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static org.springframework.util.ObjectUtils.isEmpty;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "FK_INSCRICAO_IMOBILIARIA")
    private InscricaoImobiliaria inscricaoImobiliaria;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "FK_USUARIO")
    private Usuario usuario;

    @JsonIgnore
    public boolean isNovoCadastro() {
        return isEmpty(id);
    }

    public static Consulta of(ConsultaRequest request, UsuarioAutenticado usuario) {
        var consulta = new Consulta();
        BeanUtils.copyProperties(request, consulta);
        consulta.setUsuario(new Usuario(usuario.getId()));
        consulta.setDataConsulta(LocalDateTime.now());
        return consulta;
    }
}
