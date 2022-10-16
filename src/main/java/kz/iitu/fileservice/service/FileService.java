package kz.iitu.fileservice.service;

import kz.iitu.fileservice.model.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    FileEntity uploadFile(MultipartFile file) throws IOException;

    byte[] downloadFile(String id);

    FileEntity fileData(String id);
}
