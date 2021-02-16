package nl.novi.krijt.controller;

import nl.novi.krijt.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class FileUploadController {
    @Autowired
    FileUploadService fileUploadService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes) {
        fileUploadService.uploadFile(file);

        redirectAttributes.addFlashAttribute( "message",
                "You successfullu uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/";
    }
}
