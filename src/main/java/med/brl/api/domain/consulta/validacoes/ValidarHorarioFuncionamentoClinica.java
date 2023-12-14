package med.brl.api.domain.consulta.validacoes;

import med.brl.api.domain.consulta.dto.DTOAgendaConsulta;
import med.brl.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidarHorarioFuncionamentoClinica implements ValidadorAgendamentoConsultas{

    public void validar(DTOAgendaConsulta dados){

        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesAberturaClinica = dataConsulta.getHour() < 7;
        var depoisEncerramentoClinica = dataConsulta.getHour() > 18;

        if (domingo || antesAberturaClinica || depoisEncerramentoClinica){
            throw new ValidacaoException("Consulta fora do horario de atendimento");
        }
    }
}
