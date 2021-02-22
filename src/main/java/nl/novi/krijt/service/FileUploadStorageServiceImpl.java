package nl.novi.krijt.service;

import nl.novi.krijt.exception.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadStorageServiceImpl implements FileUploadStorageService {

    private Path uploadDirectory;

    @Autowired
    @Override
    public void fileStorageService() {
        this.uploadDirectory = Paths.get(System.getProperty("user.dir") + "/fileUploads/");

        try {
            Files.createDirectories(this.uploadDirectory);
        } catch (Exception exception) {
            throw new FileStorageException("Could not create directory.", exception);
        }
    }

    @Override
    public String storeFile(MultipartFile file) {
        //Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            Path targetLocation = this.uploadDirectory.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;

        } catch (IOException exception) {
            throw new FileStorageException("Could not store the following file: " + fileName + ". Please try again!",exception);
        }


    }

//    @Autowired
//    private UploadRepository fileRepository;
//
//    public static String uploadDirectory = System.getProperty("user.dir") + "/fileUploads/";
//
//    @Override
//    public void uploadFile(MultipartFile file) throws IOException {
//        file.transferTo(new File(uploadDirectory + file.getOriginalFilename()));
//    }

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
