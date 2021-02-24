package nl.novi.krijt.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

public interface DemoService {
    void uploadDemoToDir(MultipartFile file, Principal principal) throws IOException;
}
