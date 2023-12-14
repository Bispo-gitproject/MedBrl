package med.brl.api.domain.users.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.brl.api.domain.endereco.entities.EnderecoEntity;
import med.brl.api.domain.users.entities.UsuariosEntity;
import med.brl.api.domain.users.enums.Cargo;
import med.brl.api.domain.users.enums.Permissao;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record DTODetalhaUsuario(

        Long id,

        String nome,

        LocalDate dataNascimento,

        String cpf,

        String telefone,

        String email,

        EnderecoEntity endereco,

        Cargo cargo,
        Permissao permissao,

        String users

) {
    public DTODetalhaUsuario(UsuariosEntity usuario) {
        this(usuario.getId(),
                usuario.getNome(),
                usuario.getData(),
                usuario.getCpf(),
                usuario.getTelefone(),
                usuario.getEmail(),
                usuario.getEndereco(),
                usuario.getCargo(),
                usuario.getPermissao(),
                usuario.getUsuario());
    }
}
