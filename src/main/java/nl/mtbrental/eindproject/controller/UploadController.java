package nl.mtbrental.eindproject.controller;


import nl.mtbrental.eindproject.dto.UploadRequestDto;
import nl.mtbrental.eindproject.dto.UploadResponseDto;
import nl.mtbrental.eindproject.model.Upload;
import nl.mtbrental.eindproject.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/upload")
@CrossOrigin
public class UploadController {

    @Autowired
    UploadService uploadService;

    @GetMapping("/files")
    public ResponseEntity<Object> getFiles() {
        Iterable<Upload> files = uploadService.getFiles();
        return ResponseEntity.ok().body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<Object> getFileInfo(@PathVariable long id) {
        UploadResponseDto response = uploadService.getFileById(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/files/{id}/download")
    public ResponseEntity downloadFile(@PathVariable long id) {
        Resource resource = uploadService.downloadFile(id);
        String mediaType = "application/octet-stream";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mediaType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping(value = "/files",
//        consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<Object> uploadFile(UploadRequestDto uploadDto) {
        long newId = uploadService.uploadFile(uploadDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.noContent().build();
      //  return ResponseEntity.ok("het uploaden is gelukt!");
       //  return ResponseEntity.created(location).body(location);
    }

    @DeleteMapping("/files/{id}")
    public ResponseEntity<Object> deleteFile(@PathVariable long id) {
        uploadService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }


}
