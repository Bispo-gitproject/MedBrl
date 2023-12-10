package med.brl.api.domain.medico.dto;

import med.brl.api.domain.endereco.entities.EnderecoEntity;
import med.brl.api.domain.medico.entities.MedicoEntity;
import med.brl.api.domain.medico.enums.Especialidade;

public record DTODetalhaMedico(Long id, String nome, String email,String telefone, String crm, Especialidade especialidade, EnderecoEntity endereco) {
    public DTODetalhaMedico(MedicoEntity medicoEntity) {
        this(medicoEntity.getId(),
             medicoEntity.getNome(),
             medicoEntity.getEmail(),
             medicoEntity.getTelefone(),
             medicoEntity.getCrm(),
             medicoEntity.getEspecialidade(),
             medicoEntity.getEndereco());
    }
}
