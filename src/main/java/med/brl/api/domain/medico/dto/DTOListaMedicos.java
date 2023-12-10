package med.brl.api.domain.medico.dto;

import med.brl.api.domain.medico.entities.MedicoEntity;
import med.brl.api.domain.medico.enums.Especialidade;

public record DTOListaMedicos(Long id, String nome,String email,String crm,Especialidade especialidade) {

    public DTOListaMedicos(MedicoEntity medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}