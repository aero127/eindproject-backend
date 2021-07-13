package nl.mtbrental.eindproject.service;

import nl.mtbrental.eindproject.dto.UploadRequestDto;
import nl.mtbrental.eindproject.dto.UploadResponseDto;
import nl.mtbrental.eindproject.exceptions.FileStorageException;
import nl.mtbrental.eindproject.exceptions.RecordNotFoundException;
import nl.mtbrental.eindproject.model.Upload;
import nl.mtbrental.eindproject.repository.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;

@Service
public class UploadServiceImpl implements UploadService {

    @Value("${app.upload.dir:${user.home}}")
    private String uploadDirectory;  // relative to root
    private final Path uploads = Paths.get("uploads");

    @Autowired
    private UploadRepository repository;

    @Override
    public void init() {
        try {
            Files.createDirectory(uploads);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public Iterable<Upload> getFiles() {
        return repository.findAll();
    }

    public long uploadFile(UploadRequestDto uploadDto) {

        MultipartFile file = uploadDto.getFile();

        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        Path copyLocation = this.uploads.resolve(file.getOriginalFilename());

        try {
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new FileStorageException("Could not store file " + originalFilename + ". Please try again!");
        }

        Upload newFileToStore = new Upload();
        newFileToStore.setFileName(originalFilename);
        newFileToStore.setLocation(copyLocation.toString());
//hier iets toevoegen voor uploaded by ?
        Upload saved = repository.save(newFileToStore);

        return saved.getId();
    }

    @Override
    public void deleteFile(long id) {
        Optional<Upload> stored = repository.findById(id);

        if (stored.isPresent()) {
            String filename = stored.get().getFileName();
            Path location = this.uploads.resolve(filename);
            try {
                Files.deleteIfExists(location);
            }
            catch (IOException ex) {
                throw new RuntimeException("File not found");
            }

            repository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public UploadResponseDto getFileById(long id) {
        Optional<Upload> stored = repository.findById(id);

        if (stored.isPresent()) {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand("download").toUri();

            UploadResponseDto responseDto = new UploadResponseDto();
            responseDto.setFileName(stored.get().getFileName());
//            responseDto.setTitle(stored.get().getTitle());
//            responseDto.setDescription(stored.get().getDescription());
//            responseDto.setMediaType(stored.get().getMediaType());
            responseDto.setDownloadUri(uri.toString());
            return responseDto;
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public boolean fileExistsById(long id) {
        return repository.existsById(id);
    }

    @Override
    public Resource downloadFile(long id) {
        Optional<Upload> stored = repository.findById(id);

        if (stored.isPresent()) {
            String filename = stored.get().getFileName();
            Path path = this.uploads.resolve(filename);

            Resource resource = null;
            try {
                resource = new UrlResource(path .toUri());
                return resource;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else {
            throw new RecordNotFoundException();
        }

        return null;
    }

}