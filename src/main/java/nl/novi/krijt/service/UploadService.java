package nl.novi.krijt.service;

import nl.novi.krijt.domain.Upload;

import java.util.List;

public interface UploadService {

    List<Upload> getAllUploads();

    Upload getUploadById(long id);

    void deleteUpload(long id);

    long saveUpload(Upload upload);

    void updateUpload(long id, Upload upload);
}
