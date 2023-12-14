package med.brl.api.domain.users.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.brl.api.domain.users.enums.Cargo;
import med.brl.api.domain.users.enums.Permissao;
import med.brl.api.domain.endereco.entities.EnderecoEntity;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record DTOCadastroUsuario(

        @NotBlank
        String nome,

        @NotNull
        @Past
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataNascimento,

        @CPF
        String cpf,

        @NotBlank
        String telefone,

        @Email
        String email,

        @NotNull
        @Valid
        EnderecoEntity endereco,

        @NotNull
        Cargo cargo,

        @NotNull
        Permissao permissao,

        @NotBlank
        String users,

        @Size(min = 8, max = 20, message = "A senha deve ter entre 8 e 20 caracteres")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Senha inválida")
        String pass

) {
}
