package nl.novi.krijt.controller;

import nl.novi.krijt.domain.Demo;
import nl.novi.krijt.payload.response.DemoResponse;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<DemoResponse> uploadFile(@RequestParam("file") MultipartFile file,
                                                   Principal principal,
                                                   @RequestParam("message") String message,
                                                   @RequestParam("name") String name,
                                                   @RequestParam("email") String email) throws IllegalStateException, IOException {

        return demoService.uploadDemoToDir(file, principal, email, name, message);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/uploads/all")
    public ResponseEntity<Object> getAllUploads() {
        List<Demo> demos = demoService.getAllDemos();
        return new ResponseEntity<>(demos, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/uploads/{id}")
    public ResponseEntity<Object> getUploadById(@PathVariable("id") long id) {
        Demo demo = demoService.getDemoById(id);
        return new ResponseEntity<>(demo, HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ADMIN')")
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
                .contentType(MediaType.parseMediaType("audio/mpeg"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(value = "/uploads")
    public ResponseEntity<Object> getAllDemosForUser(Principal principal) {
        List<Demo> projects = demoService.getAllDemosForUser(principal);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @PutMapping(value = "/uploads/{id}")
    public ResponseEntity<DemoResponse> updateUpload(@PathVariable("id") long id, @RequestParam("feedback") String feedback) {
        return demoService.updateDemo(id, feedback);
    }

}

