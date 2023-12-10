package med.brl.api.domain.medico.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.brl.api.domain.medico.dto.DTOAtualizaoMedico;
import med.brl.api.domain.medico.dto.DTOCadastroMedico;
import med.brl.api.domain.medico.dto.DTODetalhaMedico;
import med.brl.api.domain.medico.dto.DTOListaMedicos;
import med.brl.api.domain.medico.entities.MedicoEntity;
import med.brl.api.domain.medico.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {


    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastraMedico(@RequestBody @Valid DTOCadastroMedico dtocadastroMedicos, UriComponentsBuilder uriBuilder){

        var medico = new MedicoEntity(dtocadastroMedicos);

        medicoRepository.save(medico);

        var uri = uriBuilder.path("/medico/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DTODetalhaMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DTOListaMedicos>> listarMedicos(Pageable paginacao){
        var page = medicoRepository.findAllByAtivoTrue(paginacao).map(DTOListaMedicos::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarMedico(@RequestBody @Valid DTOAtualizaoMedico dtoAtualizaMedico){
        var medicoEntity = medicoRepository.getReferenceById(dtoAtualizaMedico.id());
        medicoEntity.atualizarInformacoes(dtoAtualizaMedico);
        return ResponseEntity.ok(new DTODetalhaMedico(medicoEntity));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity excluirMedico(@PathVariable Long id){
        var medicoEntity = medicoRepository.getReferenceById(id);
        medicoEntity.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    @Transactional
    public ResponseEntity detalhaMedico(@PathVariable Long id){
        var medicoEntity = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DTODetalhaMedico(medicoEntity));
    }

}
