package med.brl.api.domain.users.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.brl.api.domain.users.dto.DTOCadastroUsuario;
import med.brl.api.domain.endereco.entities.EnderecoEntity;
import med.brl.api.domain.users.enums.Cargo;
import med.brl.api.domain.users.enums.Permissao;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity(name = "Usuarios")
@Table(name = "usuarios")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsuariosEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDate data;

    private String cpf;

    private String telefone;

    private String email;

    @Embedded
    private EnderecoEntity endereco;

    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    @Enumerated(EnumType.STRING)
    private Permissao permissao;

    private String usuario;

    private String senha;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE.USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UsuariosEntity(DTOCadastroUsuario dados,String senha){
        this.nome = dados.nome();
        this.data = dados.dataNascimento();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.endereco = dados.endereco();
        this.cargo = dados.cargo();
        this.permissao = dados.permissao();
        this.usuario = dados.users();
        this.senha = senha;
    }

}
