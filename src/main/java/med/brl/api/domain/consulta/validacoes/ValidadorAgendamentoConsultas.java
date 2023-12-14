package med.brl.api.domain.consulta.validacoes;

import med.brl.api.domain.consulta.dto.DTOAgendaConsulta;

public interface ValidadorAgendamentoConsultas {

    void validar(DTOAgendaConsulta dados);
}
