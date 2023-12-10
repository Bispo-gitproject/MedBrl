package med.brl.api.domain.paciente.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.brl.api.domain.endereco.dto.DTOEndereco;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.validation.annotation.Validated;

public record DTOCadastraPaciente(

        @NotBlank(message = "{nome.obrigatorio}")
        String nome,

        @NotBlank(message = "{email.obrigatorio}")
        String email,

        @NotBlank(message = "{telefone.obrigatorio}")
        String telefone,

        @CPF
        @NotBlank(message = "{cpf.obrigatorio}")
        String cpf,

        @NotNull(message = "{endereco.obrigatorio}")
        @Validated
        DTOEndereco endereco) {

}
