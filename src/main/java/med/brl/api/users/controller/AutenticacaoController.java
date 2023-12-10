package med.brl.api.users.controller;

import med.brl.api.infra.service.TokenService;
import med.brl.api.users.dto.DTOAutenticação;
import med.brl.api.infra.security.DTOtokenJWT;
import med.brl.api.users.entities.UsuariosEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    @PostMapping
    public ResponseEntity login(@RequestBody @Validated DTOAutenticação dtoAutenticação){

        var authenticationToken = new UsernamePasswordAuthenticationToken(dtoAutenticação.usuario(), dtoAutenticação.senha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((UsuariosEntity) authentication.getPrincipal());

        return ResponseEntity.ok(new DTOtokenJWT(tokenJWT));

    }
}
