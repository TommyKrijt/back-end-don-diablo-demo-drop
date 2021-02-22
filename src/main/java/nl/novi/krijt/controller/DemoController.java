package nl.novi.krijt.controller;

import nl.novi.krijt.domain.Demo;
import nl.novi.krijt.payload.response.DemoResponse;
import nl.novi.krijt.service.DemoStorageService;
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

    private final DemoStorageService storageService;

    @Autowired
    public DemoController(DemoStorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file, Principal principal) {
        try {
            storageService.saveDemo(file);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }

    @GetMapping
    public List<DemoResponse> list() {
        return storageService.getAllFiles()
                .stream()
                .map(this::mapToFileResponse)
                .collect(Collectors.toList());
    }

    private DemoResponse mapToFileResponse(Demo demoFiles) {
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/files/")
                .path(demoFiles.getId())
                .toUriString();
        DemoResponse fileResponse = new DemoResponse("","","",0,"","");
        fileResponse.setId(demoFiles.getId());
        fileResponse.setName(demoFiles.getName());
        fileResponse.setContenttype(demoFiles.getContentType());
        fileResponse.setSize(demoFiles.getSize());
        fileResponse.setUrl(downloadURL);

        return fileResponse;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getFile(@PathVariable String id) {
        Optional<Demo> demoFilesOptional = DemoStorageService.getFile(id);

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






    //    @Autowired
//    private UploadService uploadService;
//
//    @PostMapping("/upload")
//    public void uploadFile(MultipartFile file) throws IOException {
//        uploadService.uploadFile(file);
//    }


//    @PostMapping("/upload")
//    public String uploadFile(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
//        fileUploadService.uploadFile(file);
//
//        redirectAttributes.addFlashAttribute( "message",
//                "You successfullu uploaded " + file.getOriginalFilename() + "!");
//        return "redirect:/";
//    }

