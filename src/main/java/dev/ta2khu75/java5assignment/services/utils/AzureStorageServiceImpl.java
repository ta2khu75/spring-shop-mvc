package dev.ta2khu75.java5assignment.services.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import dev.ta2khu75.java5assignment.services.AzureStorageService;
import dev.ta2khu75.java5assignment.utils.FileUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AzureStorageServiceImpl implements AzureStorageService {
    private final ResourceLoader resourceLoader;

    private Resource getResource(String containerName, String blobName) {
        return resourceLoader.getResource(String.format("azure-blob://%s/%s", containerName, blobName));
    }

    @Override
    public String writeBlobFile(String containerName, MultipartFile blobFile) throws IOException {
        String blobName = String.format("%s.%s", UUID.randomUUID().toString(), FileUtil.getFileExtension(blobFile));
        try (OutputStream os = ((WritableResource) this.getResource(containerName, blobName)).getOutputStream()) {
            os.write(blobFile.getBytes());
            return blobName;
        }
    }

    @Override
    public String readBlobFile(String containerName, String blobFile) throws IOException {
        return StreamUtils.copyToString(
                this.getResource(containerName, blobFile).getInputStream(),
                Charset.defaultCharset());
    }

    // public static String extractBase64String(String dataUrl) {
    //     // Tách URL dự liệu bằng dấu phẩy
    //     String[] parts = dataUrl.split(",");

    //     // Phần base64 là phần thứ hai
    //     if (parts.length == 2) {
    //         return parts[1];
    //     } else {
    //         throw new IllegalArgumentException("URL dữ liệu không hợp lệ: không tìm thấy phần base64.");
    //     }
    // }
}
