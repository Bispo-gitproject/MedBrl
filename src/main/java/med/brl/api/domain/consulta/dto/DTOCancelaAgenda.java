package med.brl.api.domain.consulta.dto;

import jakarta.validation.constraints.NotBlank;
import med.brl.api.domain.consulta.entities.ConsultaEntity;
import med.brl.api.domain.consulta.enums.Motivo;

import java.time.LocalDateTime;

public record DTOCancelaAgenda(

        @NotBlank
        Long id,

        Long idMedico,

        Long idPaciente,

        LocalDateTime data,

        @NotBlank
        Motivo motivo) {

}
