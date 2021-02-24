package nl.novi.krijt.repository;

import nl.novi.krijt.domain.UploadData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadDataRepository extends JpaRepository<UploadData, Long> {
}
