package nl.novi.krijt.service;

import nl.novi.krijt.domain.Demo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface DemoService {
    ResponseEntity uploadDemoToDir(MultipartFile file, Principal principal, String name, String email, String message) throws IOException;
    List<Demo> getAllDemos();
    Demo getDemoById(long id);
    List<Demo> getAllDemosForUser(Principal principal);
    void updateDemo(long id, String feedback);
}
