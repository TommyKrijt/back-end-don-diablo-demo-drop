package nl.novi.krijt.service;

import nl.novi.krijt.domain.Demo;
import nl.novi.krijt.domain.User;
import nl.novi.krijt.exception.DatabaseErrorException;
import nl.novi.krijt.exception.RecordNotFoundException;
import nl.novi.krijt.payload.response.DemoResponse;
import nl.novi.krijt.repository.DemoRepository;
import nl.novi.krijt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class DemoServiceImpl implements DemoService{

    @Autowired
    private DemoRepository demoRepository;

    @Autowired
    private UserRepository userRepository;

    public static String uploadDir = System.getProperty("user.dir") + "/fileUploads/";

    @Override
    public ResponseEntity<DemoResponse> uploadDemoToDir(MultipartFile file, Principal principal, String name, String email, String message) throws IOException {
        file.transferTo(new File(uploadDir + file.getOriginalFilename()));

        long currentUserId = ((UserDetailsImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
        Optional<User> optionalUser = userRepository.findById(currentUserId);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/files/uploads/download/")
                .path(file.getOriginalFilename())
                .toUriString();

        Demo demo = new Demo();

        demo.setName(name);
        demo.setEmail(email);
        demo.setDemo(file.getOriginalFilename());
        demo.setMessage(message);
        demo.setUser(optionalUser.get());
        demo.setDownloadUrl(fileDownloadUri);
        demo.setUploadDir(uploadDir + file.getOriginalFilename());

        demoRepository.save(demo).getId();
        return ResponseEntity.ok(new DemoResponse("Demo uploaded successfully!"));
    }

    @Override
    public List<Demo> getAllDemos() {
        return demoRepository.findAll();
    }

    @Override
    public Demo getDemoById(long id) {
        if (demoRepository.existsById(id)) {
            return demoRepository.findById(id).orElse(null);
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public List<Demo> getAllDemosForUser(Principal principal) {
        Long userId = ((UserDetailsImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
        List<Demo> projects = demoRepository.findAllByUserId(userId);
        return projects;
    }

    @Override
    public ResponseEntity<DemoResponse> updateDemo(long id, String feedback) {
        if (demoRepository.existsById(id)) {
            try {
                Demo existingDemo = demoRepository.findById(id).orElse(null);
                existingDemo.setFeedback(feedback);
                demoRepository.save(existingDemo);
            }
            catch (Exception ex) {
                throw new DatabaseErrorException();
            }
        }
        else {
            throw new RecordNotFoundException();
        }
        return ResponseEntity.ok(new DemoResponse("Feedback is updated!"));
    }
}

