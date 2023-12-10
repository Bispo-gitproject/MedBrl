package med.brl.api.domain.medico.dto;

import jakarta.validation.constraints.NotNull;
import med.brl.api.domain.endereco.dto.DTOEndereco;

public record DTOAtualizaoMedico(

        @NotNull
        Long id,

        String nome,

        String telefone,

        DTOEndereco endereco) {

}
