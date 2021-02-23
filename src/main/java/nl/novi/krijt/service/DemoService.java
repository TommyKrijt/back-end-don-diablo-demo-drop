package nl.novi.krijt.service;

import nl.novi.krijt.domain.Demo;
import nl.novi.krijt.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class DemoService {

    private static DemoRepository demoFilesRepository;

    @Autowired
    public DemoService(DemoRepository demoFilesRepository) {
        this.demoFilesRepository = demoFilesRepository;
    }

//    public static String uploadDirectory = System.getProperty("user.dir") + "/FileUploads/";

    public void save(MultipartFile file) throws IOException {

        Path uploadDirectory = Paths.get(System.getProperty("user.dir") + "/FileUploads/");
        Files.copy(file.getInputStream(), uploadDirectory.resolve(file.getOriginalFilename()));

        Demo fileEntity = new Demo();
        fileEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileEntity.setContentType(file.getContentType());
//        fileEntity.setData(file.getBytes());
        fileEntity.setDirectory(uploadDirectory + "/" + file.getOriginalFilename());
        fileEntity.setSize(file.getSize());

        demoFilesRepository.save(fileEntity);
    }

    public static Optional<Demo> getFile(String id) {
        return demoFilesRepository.findById(id);
    }

    public List<Demo> getAllFiles() {
        return demoFilesRepository.findAll();
    }
}
