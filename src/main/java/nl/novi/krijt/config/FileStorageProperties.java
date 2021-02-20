package nl.novi.krijt.config;

public class FileStorageProperties {
    private String UploadDir;

    public FileStorageProperties(String uploadDir) {
        UploadDir = uploadDir;
    }

    public String getUploadDir() {
        return UploadDir;
    }

    public void setUploadDir(String uploadDir) {
        UploadDir = uploadDir;
    }
}
