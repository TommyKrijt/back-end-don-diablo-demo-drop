package nl.novi.krijt.repository;


import nl.novi.krijt.domain.Demo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemoRepository extends JpaRepository<Demo, Long> {
}
