package nl.novi.krijt.payload.response;

public class DemoResponse {

    private String name;
    private String url;
    private String type;
    private long size;
    private String id;
    private String contentType;

    public DemoResponse(String name, String url, String type, long size, String id, String contentType) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
        this.id = id;
        this.contentType = contentType;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContenttype() {
        return contentType;
    }

    public void setContenttype(String contenttype) {
        this.contentType = contenttype;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}