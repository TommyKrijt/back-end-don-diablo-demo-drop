package nl.novi.krijt.service;

import nl.novi.krijt.domain.UploadData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface UploadDataService {

    List<UploadData> getAllUploadDatas();

    UploadData getUploadById(long id);

    void deleteUpload(long id);

    public long createUpload(UploadData upload, Principal principal, MultipartFile file, String name, String email, String songName, String message) throws IOException;

    void updateUpload(long id, UploadData upload);
}
