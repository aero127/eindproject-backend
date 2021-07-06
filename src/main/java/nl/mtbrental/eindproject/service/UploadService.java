package nl.mtbrental.eindproject.service;

import nl.mtbrental.eindproject.dto.UploadRequestDto;
import nl.mtbrental.eindproject.dto.UploadResponseDto;
import nl.mtbrental.eindproject.model.UploadFile;
import org.springframework.core.io.Resource;

public interface UploadService {

    void init();
    Iterable<UploadFile> getFiles();
    UploadResponseDto getFileById(long id);
    boolean fileExistsById(long id);
    long uploadFile(UploadRequestDto uploadDto);
    void deleteFile(long id);
    Resource downloadFile(long id);

}
