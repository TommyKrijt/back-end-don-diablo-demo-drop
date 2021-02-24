package nl.novi.krijt.service;

import nl.novi.krijt.domain.UploadData;
import nl.novi.krijt.domain.User;
import nl.novi.krijt.exception.DatabaseErrorException;
import nl.novi.krijt.exception.RecordNotFoundException;
import nl.novi.krijt.repository.UploadDataRepository;
import nl.novi.krijt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class UploadDataServiceImpl implements UploadDataService {

    @Autowired
    UploadDataRepository uploadDataRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UploadData> getAllUploadDatas() {
        return uploadDataRepository.findAll();
    }

    @Override
    public UploadData getUploadById(long id) {
        if (uploadDataRepository.existsById(id)) {
            return uploadDataRepository.findById(id).orElse(null);
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void deleteUpload(long id) {
        if (uploadDataRepository.existsById(id)) {
            uploadDataRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public long createUpload(UploadData upload, Principal principal) {
        long currentUserId = ((UserDetailsImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
        Optional<User> optionalUser = userRepository.findById(currentUserId);

        upload.setUser(optionalUser.get());
        return uploadDataRepository.save(upload).getId();
    }

    @Override
    public void updateUpload(long id, UploadData upload) {
        if (uploadDataRepository.existsById(id)) {
            try {
                UploadData existingUpload = uploadDataRepository.findById(id).orElse(null);
                existingUpload.setName(upload.getName());
                existingUpload.setEmail(upload.getEmail());
                existingUpload.setSongName(upload.getSongName());
                existingUpload.setMessage(upload.getMessage());
                uploadDataRepository.save(existingUpload);
            }
            catch (Exception ex) {
                throw new DatabaseErrorException();
            }
        }
        else {
            throw new RecordNotFoundException();
        }
    }
}
