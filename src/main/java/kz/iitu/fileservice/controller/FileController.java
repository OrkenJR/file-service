package kz.iitu.fileservice.controller;

import kz.iitu.cfaslib.controller.AbstractWrapper;
import kz.iitu.fileservice.model.FileEntity;
import kz.iitu.fileservice.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FileController extends AbstractWrapper {
    private final FileService fileService;

    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public FileEntity uploadFile(@RequestParam MultipartFile file) throws IOException {
        return fileService.uploadFile(file);
    }

    @GetMapping(value = "/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam String fileId) {
        FileEntity fileEntity = fileService.fileData(fileId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", fileEntity.getFileType());
        headers.add("Content-Disposition", "attachment; filename=\"" + fileEntity.getFileName() + "\"");
        return new ResponseEntity<>(fileEntity.getFileData(), headers, HttpStatus.OK);
    }

}
