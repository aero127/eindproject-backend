package com.mtbrental.eindproject.repositories;

import com.mtbrental.eindproject.model.UploadFile;
import org.springframework.data.repository.CrudRepository;

public interface UploadRepository extends CrudRepository<UploadFile, Long> {
}
