package med.brl.api.domain.paciente.repository;

import med.brl.api.domain.paciente.entity.PacienteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {
    Page<PacienteEntity> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
           select p.ativo
           from Paciente p
           where p.id = :idPaciente
           """)
    Boolean findAtivoById(Long idPaciente);

}
