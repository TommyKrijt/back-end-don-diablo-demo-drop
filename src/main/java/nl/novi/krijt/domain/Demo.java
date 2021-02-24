package nl.novi.krijt.domain;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.File;

@Entity
@Table(name = "app_demo")
public class Demo {

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
    private File demo;

    public Demo() {
    }

    public Demo(long id, String name, File demo) {
        this.id = id;
        this.name = name;
        this.demo = demo;
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

    public File getDemo() {
        return demo;
    }

    public void setDemo(File demo) {
        this.demo = demo;
    }
}


