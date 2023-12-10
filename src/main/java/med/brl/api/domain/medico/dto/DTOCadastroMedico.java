package med.brl.api.domain.medico.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.brl.api.domain.endereco.dto.DTOEndereco;
import med.brl.api.domain.medico.enums.Especialidade;
import org.springframework.validation.annotation.Validated;

public record DTOCadastroMedico(
        @NotBlank(message = "{nome.obrigatorio}")
        String nome,

        @NotBlank(message = "{email.obrigatorio}")
        @Email(message = "{email.invalido}")
        String email,

        @NotBlank(message = "{telefone.obrigatorio}")
        String telefone,

        @NotBlank(message = "{crm.obrigatorio}")
        @Pattern(regexp = "\\d{4,6}", message = "{crm.invalido}")
        String crm,

        @NotNull(message = "{especialidade.obrigatoria}")
        Especialidade especialidade,

        @NotNull(message = "{endereco.obrigatorio}")
        @Validated
        DTOEndereco endereco) {}