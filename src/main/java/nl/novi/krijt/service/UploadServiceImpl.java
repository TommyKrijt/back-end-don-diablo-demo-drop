package nl.novi.krijt.service;

import nl.novi.krijt.domain.Demo;
import nl.novi.krijt.domain.Upload;
import nl.novi.krijt.domain.User;
import nl.novi.krijt.exception.DatabaseErrorException;
import nl.novi.krijt.exception.RecordNotFoundException;
import nl.novi.krijt.repository.UploadRepository;
import nl.novi.krijt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    UploadRepository uploadRepository;

    @Override
    public List<Upload> getAllUploads() {
        return uploadRepository.findAll();
    }

    @Override
    public Upload getUploadById(long id) {
        if (uploadRepository.existsById(id)) {
            return uploadRepository.findById(id).orElse(null);
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void deleteUpload(long id) {
        if (uploadRepository.existsById(id)) {
            uploadRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public long saveUpload(Upload upload) {
        Upload newUpload = uploadRepository.save(upload);
        return newUpload.getId();
    }

    @Override
    public void updateUpload(long id, Upload upload) {
        if (uploadRepository.existsById(id)) {
            try {
                Upload existingUpload = uploadRepository.findById(id).orElse(null);
                existingUpload.setArtist_name(upload.getArtist_name());
                existingUpload.setEmail(upload.getEmail());
                existingUpload.setSong_name(upload.getSong_name());
                existingUpload.setUpload_file(upload.getUpload_file());
                existingUpload.setMessage(upload.getMessage());
                uploadRepository.save(existingUpload);
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
