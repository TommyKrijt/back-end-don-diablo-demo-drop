package nl.novi.krijt.service;

import nl.novi.krijt.domain.Upload;
import nl.novi.krijt.exception.DatabaseErrorException;
import nl.novi.krijt.exception.RecordNotFoundException;
import nl.novi.krijt.repository.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
                existingUpload.setName(upload.getName());
                existingUpload.setEmail(upload.getEmail());
                existingUpload.setUploadName(upload.getUploadName());
                existingUpload.setUploadFile(upload.getUploadFile());
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
