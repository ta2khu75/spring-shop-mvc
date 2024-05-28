package dev.ta2khu75.java5assignment.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface AzureStorageService {
    public String writeBlobFile(String containerName, MultipartFile file) throws IOException;
    public String readBlobFile(String containerName, String blobFile) throws IOException;
}
