package med.brl.api.domain.medico.repository;

import med.brl.api.domain.medico.entities.MedicoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {
    Page<MedicoEntity> findAllByAtivoTrue(Pageable paginacao);
}
