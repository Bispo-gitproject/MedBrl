package med.brl.api.domain.paciente.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.brl.api.domain.paciente.dto.DTOAtualizaoPaciente;
import med.brl.api.domain.paciente.dto.DTOCadastraPaciente;
import med.brl.api.domain.paciente.dto.DTODetalhePaciente;
import med.brl.api.domain.paciente.dto.DTOListaPaciente;
import med.brl.api.domain.paciente.entity.PacienteEntity;
import med.brl.api.domain.paciente.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/paciente")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadstraPaciente(@RequestBody @Valid DTOCadastraPaciente dtoCadastraPaciente, UriComponentsBuilder uriBuilder){

        var paciente = new PacienteEntity(dtoCadastraPaciente);

        pacienteRepository.save(paciente);

        var uri = uriBuilder.path("/paciente/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DTODetalhePaciente(paciente));

    }

    @GetMapping
    public ResponseEntity<Page<DTOListaPaciente>> listaPaciente(Pageable paginacao){
        var page = pacienteRepository.findAllByAtivoTrue(paginacao).map(DTOListaPaciente::new);
        return ResponseEntity.ok(page);
   }

   @PutMapping
   @Transactional
   public ResponseEntity<DTODetalhePaciente> atualizaPacientes(@RequestBody @Valid DTOAtualizaoPaciente dtoAtualizaPaciente){

        var paciente = pacienteRepository.getReferenceById(dtoAtualizaPaciente.id());

        paciente.atualizaPaciente(dtoAtualizaPaciente);

        return ResponseEntity.ok(new DTODetalhePaciente(paciente));

   }

   @DeleteMapping("{id}")
   @Transactional
   public ResponseEntity excluirPaciente(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.excluir(id);
        return ResponseEntity.noContent().build();
  }

  @GetMapping("{id}")
  public ResponseEntity detalhaPaciente(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DTODetalhePaciente(paciente));
  }
}
