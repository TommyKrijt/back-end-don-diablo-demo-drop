package nl.novi.krijt.controller;

import nl.novi.krijt.domain.Demo;
import nl.novi.krijt.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/files")
public class DemoController {

    @Autowired
    DemoService demoService;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/uploads")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             Principal principal,
                                             @RequestParam("message") String message,
                                             @RequestParam("name") String name,
                                             @RequestParam("email") String email) throws IllegalStateException, IOException {
        demoService.uploadDemoToDir(file, principal, name, email, message);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/files/uploads/download/")
                .path(file.getOriginalFilename())
                .toUriString();
        return ResponseEntity.ok(fileDownloadUri);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(value = "/uploads")
    public ResponseEntity<Object> getAllUploads() {
        List<Demo> demos = demoService.getAllDemos();
        return new ResponseEntity<>(demos, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(value = "/uploads/{id}")
    public ResponseEntity<Object> getUploadById(@PathVariable("id") long id) {
        Demo demo = demoService.getDemoById(id);
        return new ResponseEntity<>(demo, HttpStatus.OK);
    }

    @GetMapping("uploads/download/{fileName}")
    public ResponseEntity downloadFileFromLocal(@PathVariable String fileName) {
        Path path = Paths.get( System.getProperty("user.dir") + "/fileUploads/" + fileName);
        UrlResource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}

