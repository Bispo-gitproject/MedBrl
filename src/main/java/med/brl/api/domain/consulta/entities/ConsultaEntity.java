package med.brl.api.domain.consulta.entities;
// Trecho de código suprimido

import jakarta.persistence.*;
import lombok.*;
import med.brl.api.domain.consulta.dto.DTOAgendaConsulta;
import med.brl.api.domain.consulta.enums.Motivo;
import med.brl.api.domain.medico.entities.MedicoEntity;
import med.brl.api.domain.paciente.entity.PacienteEntity;

import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ConsultaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicos_id")
    private MedicoEntity medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private PacienteEntity paciente;

    private LocalDateTime data;

    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private Motivo motivoCancelamento;

    public void cancelar(Motivo motivo) {
        this.motivoCancelamento = motivo;
    }

}