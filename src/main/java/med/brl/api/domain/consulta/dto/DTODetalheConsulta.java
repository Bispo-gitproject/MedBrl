package med.brl.api.domain.consulta.dto;

import med.brl.api.domain.consulta.entities.ConsultaEntity;

import java.time.LocalDateTime;

public record DTODetalheConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
    public DTODetalheConsulta(ConsultaEntity consulta) {
        this(consulta.getId(),
                consulta.getMedico().getId(), consulta.getPaciente().getId(),consulta.getData());
    }
}
