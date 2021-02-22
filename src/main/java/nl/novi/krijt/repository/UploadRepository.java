package nl.novi.krijt.repository;


import nl.novi.krijt.domain.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadRepository extends JpaRepository<FileUpload, Long> {
}
