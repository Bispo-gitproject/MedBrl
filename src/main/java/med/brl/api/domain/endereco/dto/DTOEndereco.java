package med.brl.api.domain.endereco.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DTOEndereco(

        @NotBlank(message = "{logradouro.obrigatorio}")
        String logradouro,

        @NotBlank(message = "{bairro.obrigatorio}")
        String bairro,

        @NotBlank(message = "{cep.obrigatorio}")
        @Pattern(regexp = "\\d{8}",message = "{cepformato.obrigatorio}")
        String cep,

        @NotBlank(message = "{cidade.obrigatorio}")
        String cidade,

        @NotBlank(message = "{uf.obrigatorio}")
        String uf,

        String complemento,

        String numero) {
}
