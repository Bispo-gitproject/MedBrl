package med.brl.api.domain.paciente.dto;

import med.brl.api.domain.paciente.entity.PacienteEntity;
import med.brl.api.domain.endereco.entities.EnderecoEntity;

public record DTODetalhePaciente(Long id, String nome, String telefone, String email, String cpf, EnderecoEntity endereco) {
    public DTODetalhePaciente(PacienteEntity pacienteEntity) {
        this(pacienteEntity.getId(),
             pacienteEntity.getNome(),
             pacienteEntity.getTelefone(),
             pacienteEntity.getEmail(),
             pacienteEntity.getCpf(),
             pacienteEntity.getEndereco());
    }

}
