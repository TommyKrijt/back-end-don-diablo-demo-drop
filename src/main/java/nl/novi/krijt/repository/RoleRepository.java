package nl.novi.krijt.repository;

import nl.novi.krijt.domain.ERole;
import nl.novi.krijt.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);

}
