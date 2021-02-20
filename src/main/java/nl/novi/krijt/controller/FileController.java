package nl.novi.krijt.controller;

import nl.novi.krijt.payload.response.UploadFileResponse;
import nl.novi.krijt.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/upload")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        return new UploadFileResponse(fileName, file.getContentType(), file.getSize());
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
}
