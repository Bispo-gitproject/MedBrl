package med.brl.api.domain.consulta.validacoes;

import med.brl.api.domain.consulta.dto.DTOAgendaConsulta;
import med.brl.api.domain.medico.repository.MedicoRepository;
import med.brl.api.domain.paciente.repository.PacienteRepository;
import med.brl.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaPacienteAtivo implements ValidadorAgendamentoConsultas{

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DTOAgendaConsulta dados){

        if (dados.idPaciente() == null){
            return;
        }

        var idPaciente = dados.idPaciente();
        var pacienteAtivo = pacienteRepository.findAtivoById(idPaciente);

        if (!pacienteAtivo){
            throw new ValidacaoException("Paciente inativado");
        }
    }
}
