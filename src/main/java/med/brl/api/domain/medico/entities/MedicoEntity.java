package med.brl.api.domain.medico.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.brl.api.domain.medico.dto.DTOAtualizaoMedico;
import med.brl.api.domain.medico.dto.DTOCadastroMedico;
import med.brl.api.domain.medico.enums.Especialidade;
import med.brl.api.domain.endereco.entities.EnderecoEntity;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class MedicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private EnderecoEntity endereco;

    private Boolean ativo;

    public MedicoEntity(DTOCadastroMedico cadastroMedicos) {
        this.nome = cadastroMedicos.nome();
        this.email = cadastroMedicos.email();
        this.telefone = cadastroMedicos.telefone();
        this.crm = cadastroMedicos.crm();
        this.especialidade = cadastroMedicos.especialidade();
        this.endereco = new EnderecoEntity(cadastroMedicos.endereco());
        this.ativo = true;

    }

    public void atualizarInformacoes(DTOAtualizaoMedico dtoAtualizaMedico) {
        if (dtoAtualizaMedico.nome() != null) {
            this.nome = dtoAtualizaMedico.nome();
        }
        if (dtoAtualizaMedico.telefone() != null) {
            this.telefone = dtoAtualizaMedico.telefone();
        }
        if (dtoAtualizaMedico.endereco() != null) {
            this.endereco.atualizarInformacoes(dtoAtualizaMedico.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }


}
