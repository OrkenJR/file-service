package kz.iitu.fileservice.repository;

import kz.iitu.fileservice.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface FileRepository extends JpaRepository<FileEntity, String> {
    @Transactional
    FileEntity findFileById(String id);
}
