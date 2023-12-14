package med.brl.api.domain.consulta.services;

import med.brl.api.domain.consulta.dto.DTOAgendaConsulta;
import med.brl.api.domain.consulta.dto.DTODetalheConsulta;
import med.brl.api.domain.consulta.entities.ConsultaEntity;
import med.brl.api.domain.consulta.repository.ConsultaRepository;
import med.brl.api.domain.consulta.validacoes.ValidadorAgendamentoConsultas;
import med.brl.api.domain.medico.entities.MedicoEntity;
import med.brl.api.domain.medico.repository.MedicoRepository;
import med.brl.api.domain.paciente.repository.PacienteRepository;
import med.brl.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultasService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoConsultas> validador;

    public DTODetalheConsulta agendar(DTOAgendaConsulta dados){

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("id do médico informado não encontrado");
        }
        if (!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("id do paciente informado não encontrado");
        }

        validador.forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);
        if (medico == null){
            throw new ValidacaoException("Não há medicos disponiveis para esta data");
        }

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());

        var consulta = new ConsultaEntity(null,medico, paciente, dados.data(),false,null);

        consultaRepository.save(consulta);

        return new DTODetalheConsulta(consulta);
    }

    private MedicoEntity escolherMedico(DTOAgendaConsulta dados) {

        if (dados.idMedico()!=null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade()==null){
            throw new ValidacaoException("especialidade obrigatorio, caso não há a preferencia de médico");
        }

        return medicoRepository.escolheMedicoAliatorioDataHora(dados.especialidade(),dados.data());
    }
}
