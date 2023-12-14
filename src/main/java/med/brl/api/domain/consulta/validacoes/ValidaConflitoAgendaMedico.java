package med.brl.api.domain.consulta.validacoes;

import med.brl.api.domain.consulta.dto.DTOAgendaConsulta;
import med.brl.api.domain.consulta.repository.ConsultaRepository;
import med.brl.api.domain.medico.repository.MedicoRepository;
import med.brl.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaConflitoAgendaMedico implements ValidadorAgendamentoConsultas{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DTOAgendaConsulta dados){
        var medicoPossuiConflito = consultaRepository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if (medicoPossuiConflito){
            throw new ValidacaoException("Medico possui outra consulta para este horario");
        }
    }
}
