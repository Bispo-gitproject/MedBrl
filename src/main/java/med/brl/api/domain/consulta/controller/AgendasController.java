package med.brl.api.domain.consulta.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.brl.api.domain.consulta.dto.DTOAgendaConsulta;
import med.brl.api.domain.consulta.dto.DTOCancelaAgenda;
import med.brl.api.domain.consulta.services.AgendaDeConsultasService;
import med.brl.api.domain.consulta.services.CancelarAgendasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class AgendasController {

    @Autowired
    private AgendaDeConsultasService agenda;
    @Autowired
    private CancelarAgendasService cancelarAgendasService;
    @PostMapping
    @Transactional
    public ResponseEntity agendarConsulta(@RequestBody @Valid DTOAgendaConsulta dtoAgendaConsulta, UriComponentsBuilder uriBuilder){

        var dto = agenda.agendar(dtoAgendaConsulta);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/cancelamento")
    @Validated
    public ResponseEntity cancelarAgenda(@RequestBody @Valid DTOCancelaAgenda dados){

        var dto = cancelarAgendasService.cancelamento(dados);

        return ResponseEntity.ok(dto);
    }
}
