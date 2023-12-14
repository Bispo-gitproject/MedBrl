package med.brl.api.domain.consulta.validacoes;

import med.brl.api.domain.consulta.dto.DTOAgendaConsulta;
import med.brl.api.domain.consulta.repository.ConsultaRepository;
import med.brl.api.domain.paciente.repository.PacienteRepository;
import med.brl.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.stream.events.DTD;

@Component
public class ValidaPacienteConsultaDia implements ValidadorAgendamentoConsultas{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DTOAgendaConsulta dados){
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacienteComConsultaDia = consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario,ultimoHorario);
        if (pacienteComConsultaDia){
            throw new ValidacaoException("Paciente já possue consulta agendada para esta data");
        }
    }
}
