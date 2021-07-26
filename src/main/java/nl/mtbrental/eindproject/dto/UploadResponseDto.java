package nl.mtbrental.eindproject.dto;

import lombok.Data;
import nl.mtbrental.eindproject.model.User;

@Data
public class UploadResponseDto {
    private String fileName;
//    private String mediaType;
    private String downloadUri;
}
