package med.brl.api.domain.consulta.validacoes;

import med.brl.api.domain.consulta.dto.DTOAgendaConsulta;
import med.brl.api.domain.medico.entities.MedicoEntity;
import med.brl.api.domain.medico.repository.MedicoRepository;
import med.brl.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaMedicoAtivo implements ValidadorAgendamentoConsultas{

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DTOAgendaConsulta dados){

        if (dados.idMedico() == null){
            return;
        }

        var idMedico = dados.idMedico();
        var medicoAtivo = medicoRepository.findAtivoById(idMedico);

        if (!medicoAtivo){
            throw new ValidacaoException("Medico inativado");
        }
    }
}
