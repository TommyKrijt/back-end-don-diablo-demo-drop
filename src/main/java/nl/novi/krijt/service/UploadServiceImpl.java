package nl.novi.krijt.service;

import nl.novi.krijt.repository.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class UploadServiceImpl implements UploadService{
    @Autowired
    private UploadRepository uploadRepository;

    public static String uploadDirectory = System.getProperty("user.dir") + "/fileUploads/";

    @Override
    public void uploadFile(MultipartFile file) throws IOException {
        file.transferTo(new File(uploadDirectory + file.getOriginalFilename()));
    }

    //    @Value("${app.upload.dir:${user.home}}")
//    public String uploadDir;
//
//    public void uploadFile(MultipartFile file) {
//
//        try {
//            Path copyLocation = Paths
//                    .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
//            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new FileStorageException("Could not store file " + file.getOriginalFilename()
//                    + ". Please try again!");
//        }
//    }

}
