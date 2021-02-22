package nl.novi.krijt.controller;

import nl.novi.krijt.domain.Upload;
import nl.novi.krijt.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UploadController {

    @Autowired
    UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @GetMapping(value = "/uploads")
    public ResponseEntity<Object> getAllUploadForms() {
        List<Upload> uploads = uploadService.getAllUploads();
        return new ResponseEntity<>(uploads, HttpStatus.OK);
    }

    @GetMapping(value = "upload/{id}")
    public ResponseEntity<Object> getUploadById(@PathVariable("id") long id) {
        Upload upload = uploadService.getUploadById(id);
        return new ResponseEntity<>(upload, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteUpload(@PathVariable("id") long id) {
        uploadService.deleteUpload(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<Object> saveUpload(@RequestBody Upload upload) {
        long newId = uploadService.saveUpload(upload);
        return new ResponseEntity<>(newId, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateUpload(@PathVariable("id") long id, @RequestBody Upload upload) {
        uploadService.updateUpload(id, upload);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
