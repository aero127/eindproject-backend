package nl.mtbrental.eindproject.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadRequestDto {
    private MultipartFile file;
}
