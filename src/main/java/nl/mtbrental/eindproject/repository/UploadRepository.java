package nl.mtbrental.eindproject.repository;

import nl.mtbrental.eindproject.model.UploadFile;
import org.springframework.data.repository.CrudRepository;

public interface UploadRepository extends CrudRepository<UploadFile, Long> {
}
