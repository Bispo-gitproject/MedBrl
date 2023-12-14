package med.brl.api.domain.users.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import med.brl.api.domain.users.dto.DTOCadastroUsuario;
import med.brl.api.domain.users.dto.DTODetalhaUsuario;
import med.brl.api.domain.users.repository.UsuariosRepository;
import med.brl.api.infra.service.TokenService;
import med.brl.api.domain.users.dto.DTOAutenticação;
import med.brl.api.infra.security.DTOtokenJWT;
import med.brl.api.domain.users.entities.UsuariosEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @PostMapping
    public ResponseEntity login(@RequestBody @Validated DTOAutenticação dtoAutenticação){

        var authenticationToken = new UsernamePasswordAuthenticationToken(dtoAutenticação.usuario(), dtoAutenticação.senha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((UsuariosEntity) authentication.getPrincipal());

        return ResponseEntity.ok(new DTOtokenJWT(tokenJWT));

    }

    @PostMapping("cadastro")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity usuario(@RequestBody @Validated DTOCadastroUsuario dados){

        var senha = dados.pass();
        String senhaCodificada = passwordEncoder.encode(senha);
        System.out.println("Senha codificada: " + senhaCodificada);

        var usuario = new UsuariosEntity(dados,senhaCodificada);

        usuariosRepository.save(usuario);

        System.out.println(usuario);
        return ResponseEntity.ok(new DTODetalhaUsuario(usuario));

    }
}
