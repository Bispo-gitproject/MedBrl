package med.brl.api.domain.paciente.dto;

import jakarta.validation.constraints.NotNull;
import med.brl.api.domain.endereco.dto.DTOEndereco;

public record DTOAtualizaoPaciente(
        @NotNull
        Long id,

        String nome,

        String telefone,

        DTOEndereco endereco) {
}
