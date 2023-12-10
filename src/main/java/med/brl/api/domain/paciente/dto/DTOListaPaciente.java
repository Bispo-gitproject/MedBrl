package med.brl.api.domain.paciente.dto;

import med.brl.api.domain.paciente.entity.PacienteEntity;

public record DTOListaPaciente(Long id, String nome, String email, String cpf) {

    public DTOListaPaciente(PacienteEntity paciente) {
        this(paciente.getId(),
                paciente.getNome(),paciente.getEmail(), paciente.getCpf());
    }
}
