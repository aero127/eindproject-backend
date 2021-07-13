package nl.mtbrental.eindproject.dto;

import lombok.Data;

@Data
public class UploadResponseDto {
    private String fileName;
//    private String mediaType;
    private String downloadUri;
}
