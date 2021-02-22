package nl.novi.krijt.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.File;

@Entity
@Table(name = "app_file")
public class FileUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    private String filename;
    File file;

    public FileUpload(String filename, java.io.File file) {
        this.filename = filename;
        this.file = file;
    }

    public FileUpload() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public java.io.File getFile() {
        return file;
    }

    public void setFile(java.io.File upload) {
        this.file = upload;
    }
}
