package med.brl.api.domain.consulta.services;

import med.brl.api.domain.consulta.dto.DTOCancelaAgenda;
import med.brl.api.domain.consulta.dto.DTODetalhaCancela;
import med.brl.api.domain.consulta.entities.ConsultaEntity;
import med.brl.api.domain.consulta.enums.Motivo;
import med.brl.api.domain.consulta.repository.ConsultaRepository;
import med.brl.api.domain.medico.repository.MedicoRepository;
import med.brl.api.domain.paciente.repository.PacienteRepository;
import med.brl.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancelarAgendasService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public DTODetalhaCancela cancelamento(DTOCancelaAgenda dados){

        if (dados.id() != null && !consultaRepository.existsById(dados.id())){
            throw new ValidacaoException("Consulta não encontrada");
        }

        var medico = medicoRepository.getReferenceById(dados.idMedico());
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());

        var cancelaConsulta = new ConsultaEntity(dados.id(), medico, paciente, dados.data(), true, dados.motivo());

        consultaRepository.save(cancelaConsulta);

        return new DTODetalhaCancela(cancelaConsulta);

    }
}
