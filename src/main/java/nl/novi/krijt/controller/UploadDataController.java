package nl.novi.krijt.controller;


import nl.novi.krijt.domain.UploadData;
import nl.novi.krijt.service.UploadDataService;
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

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/uploads")
public class UploadDataController {

    @Autowired
    UploadDataService uploadDataService;

    @GetMapping(value = "")
    public ResponseEntity<Object> getAllUploads() {
        List<UploadData> uploads = uploadDataService.getAllUploadDatas();
        return new ResponseEntity<>(uploads, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getUploadById(@PathVariable("id") long id) {
        UploadData upload = uploadDataService.getUploadById(id);
        return new ResponseEntity<>(upload, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteUpload(@PathVariable("id") long id) {
        uploadDataService.deleteUpload(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> saveUpload(@RequestBody UploadData upload, Principal principal) {
        long newId = uploadDataService.createUpload(upload, principal);
        return new ResponseEntity<>(newId, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateUpload(@PathVariable("id") long id, @RequestBody UploadData upload) {
        uploadDataService.updateUpload(id, upload);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
