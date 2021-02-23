package nl.novi.krijt.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "app_upload")
public class Upload {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(columnDefinition = "serial")
    private long id;
    private String name;
    private String email;
    private String song;
    private String upload_file; // should be Demo Type?
    private String message;

    public Upload() {

    }

    public Upload(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Upload(String name, String song, String message) {
        this.name = name;
        this.song = song;
        this.message = message;
    }

    public Upload(String name, String song, String message, String upload_file) {
        this.name = name;
        this.song = song;
        this.upload_file = upload_file;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getUpload_file() {
        return upload_file;
    }

    public void setUpload_file(String upload_file) {
        this.upload_file = upload_file;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
