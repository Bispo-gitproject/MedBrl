package med.brl.api.domain.paciente.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.brl.api.domain.paciente.dto.DTOAtualizaoPaciente;
import med.brl.api.domain.endereco.entities.EnderecoEntity;
import med.brl.api.domain.paciente.dto.DTOCadastraPaciente;

@Entity(name = "Paciente")
@Table(name = "paciente")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String cpf;

    @Embedded
    private EnderecoEntity endereco;

    private Boolean ativo;

    public PacienteEntity(@Valid DTOCadastraPaciente dtoCadastraPaciente) {
        this.nome = dtoCadastraPaciente.nome();
        this.email = dtoCadastraPaciente.email();
        this.telefone = dtoCadastraPaciente.telefone();
        this.cpf = dtoCadastraPaciente.cpf();
        this.endereco = new EnderecoEntity(dtoCadastraPaciente.endereco());
    }

    public void atualizaPaciente(DTOAtualizaoPaciente dtoAtualizaPaciente) {

            if (dtoAtualizaPaciente.nome() != null) {
                this.nome = dtoAtualizaPaciente.nome();
            }
            if (dtoAtualizaPaciente.telefone() != null) {
                this.telefone = dtoAtualizaPaciente.telefone();
            }
            if (dtoAtualizaPaciente.endereco() != null) {
                this.endereco.atualizarInformacoes(dtoAtualizaPaciente.endereco());
            }

    }

    public void excluir(Long id) {
        this.ativo = false;
    }
}
