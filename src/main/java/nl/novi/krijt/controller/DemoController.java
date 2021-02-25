package nl.novi.krijt.controller;

import nl.novi.krijt.domain.Demo;
import nl.novi.krijt.domain.UploadData;
import nl.novi.krijt.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/files")
public class DemoController {

    @Autowired
    DemoService demoService;

    @PostMapping("/uploads")
    public void uploadFile(@RequestParam("file") MultipartFile file,
                           Principal principal,
                           @RequestParam("message") String message,
                           @RequestParam("name") String name,
                           @RequestParam("email") String email) throws IllegalStateException, IOException {
        demoService.uploadDemoToDir(file, principal, name, email, message);
    }

    @GetMapping(value = "/uploads")
    public ResponseEntity<Object> getAllUploads() {
        List<Demo> demos = demoService.getAllDemos();
        return new ResponseEntity<>(demos, HttpStatus.OK);
    }

    @GetMapping(value = "/uploads/{id}")
    public ResponseEntity<Object> getUploadById(@PathVariable("id") long id) {
        Demo demo = demoService.getDemoById(id);
        return new ResponseEntity<>(demo, HttpStatus.OK);
    }
}

