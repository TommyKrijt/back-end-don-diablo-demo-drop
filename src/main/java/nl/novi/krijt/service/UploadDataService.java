package nl.novi.krijt.service;

import nl.novi.krijt.domain.UploadData;

import java.security.Principal;
import java.util.List;

public interface UploadDataService {

    List<UploadData> getAllUploadDatas();

    UploadData getUploadById(long id);

    void deleteUpload(long id);

    long createUpload(UploadData upload, Principal principal);

    void updateUpload(long id, UploadData upload);
}
