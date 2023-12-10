package med.brl.api.users.dto;

import jakarta.validation.constraints.NotBlank;

public record DTOAutenticação(

        @NotBlank(message = "{usuario.obrigatorio}")
        String usuario,

        @NotBlank(message = "{senha.obrigatorio}")
        String senha
) {
}
