package nl.novi.krijt.repository;


import nl.novi.krijt.domain.Demo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemoRepository extends JpaRepository<Demo, Long> {
    List<Demo> findAllByUserId(Long userId);
}
