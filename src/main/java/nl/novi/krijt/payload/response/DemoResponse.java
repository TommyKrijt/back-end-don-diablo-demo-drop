package nl.novi.krijt.payload.response;

import nl.novi.krijt.domain.User;

public class DemoResponse {

    private String name;
    private long size;
    private long id;


    public DemoResponse(String name, long size, long id) {
        this.name = name;
        this.size = size;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

}
