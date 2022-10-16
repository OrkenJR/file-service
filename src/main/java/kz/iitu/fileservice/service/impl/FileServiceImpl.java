package kz.iitu.fileservice.service.impl;

import kz.iitu.fileservice.model.FileEntity;
import kz.iitu.fileservice.repository.FileRepository;
import kz.iitu.fileservice.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Override
    public FileEntity uploadFile(MultipartFile file) throws IOException {
        return fileRepository.save(FileEntity.builder()
                .fileName(file.getOriginalFilename())
                .fileData(file.getBytes())
                .fileType(file.getContentType())
                .build());
    }

    @Override
    public byte[] downloadFile(String id) {
        return fileRepository.findFileById(id).getFileData();
    }

    @Override
    public FileEntity fileData(String id) {
        return fileRepository.findFileById(id);
    }
}
