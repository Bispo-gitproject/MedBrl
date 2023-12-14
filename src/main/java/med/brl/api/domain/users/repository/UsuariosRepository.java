package med.brl.api.domain.users.repository;

import med.brl.api.domain.users.entities.UsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<UsuariosEntity, Long> {

    UserDetails findByUsuario(String usuario);

}
