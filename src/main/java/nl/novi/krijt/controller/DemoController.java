package nl.novi.krijt.controller;

import nl.novi.krijt.domain.Demo;
import nl.novi.krijt.payload.response.DemoResponse;
import nl.novi.krijt.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/files")
public class DemoController {

    private final DemoService demoService;

    @Autowired
    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @PostMapping
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file, Principal principal) {
        try {
            demoService.save(file);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }

    @GetMapping
    public List<DemoResponse> list() {
        return demoService.getAllFiles()
                .stream()
                .map(this::mapToFileResponse)
                .collect(Collectors.toList());
    }

    private DemoResponse mapToFileResponse(Demo demos) {
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/files/")
                .path(demos.getId())
                .toUriString();
        DemoResponse fileResponse = new DemoResponse("","","",0,"","");
        fileResponse.setId(demos.getId());
        fileResponse.setName(demos.getName());
        fileResponse.setContenttype(demos.getContentType());
        fileResponse.setSize(demos.getSize());
        fileResponse.setUrl(downloadURL);

        return fileResponse;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getFile(@PathVariable String id) {
        Optional<Demo> demoFilesOptional = DemoService.getFile(id);

        if (!demoFilesOptional.isPresent()) {
            return ResponseEntity.notFound()
                    .build();
        }

        Demo demoFiles = demoFilesOptional.get();
        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + demoFiles.getName() + "\"")
//                .contentType(MediaType.valueOf(demoFiles.getContentType()))
//                .body(demoFiles.getData());
                .body(demoFiles.getDirectory());
    }

}

