package nl.novi.krijt.controller;

import nl.novi.krijt.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")

public class UploadController {

    @Autowired
    UploadService fileUploadService;

    @PostMapping("/upload")
    public void uploadFile(MultipartFile file) throws IOException {
        fileUploadService.uploadFile(file);
    }


//    @PostMapping("/upload")
//    public String uploadFile(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
//        fileUploadService.uploadFile(file);
//
//        redirectAttributes.addFlashAttribute( "message",
//                "You successfullu uploaded " + file.getOriginalFilename() + "!");
//        return "redirect:/";
//    }
}
