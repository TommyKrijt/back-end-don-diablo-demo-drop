package nl.novi.krijt.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    File demoUpload;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Demo() {
    }

    public Demo(long id, String name, File demoUpload) {
        this.id = id;
        this.name = name;
        this.demoUpload = demoUpload;
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

    public File getDemoUpload() {
        return demoUpload;
    }

    public void setDemoUpload(File demoUpload) {
        this.demoUpload = demoUpload;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


