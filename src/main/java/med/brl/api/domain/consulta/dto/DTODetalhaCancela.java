package med.brl.api.domain.consulta.dto;

import jakarta.validation.constraints.NotBlank;
import med.brl.api.domain.consulta.entities.ConsultaEntity;
import med.brl.api.domain.consulta.enums.Motivo;
import med.brl.api.domain.medico.entities.MedicoEntity;
import med.brl.api.domain.paciente.entity.PacienteEntity;

import java.time.LocalDateTime;

public record DTODetalhaCancela(Long id, MedicoEntity medico, PacienteEntity paciente, LocalDateTime data, Boolean ativo, Motivo motivo) {

    public DTODetalhaCancela(ConsultaEntity cancelaConsulta) {
        this(cancelaConsulta.getId(),
                cancelaConsulta.getMedico(),
                cancelaConsulta.getPaciente(),
                cancelaConsulta.getData(),
                cancelaConsulta.getAtivo(),
                cancelaConsulta.getMotivoCancelamento());
    }
}
