package nl.novi.krijt.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    void fileStorageService();
    String storeFile(MultipartFile file);

//    void uploadFile(MultipartFile file) throws IOException;

}
