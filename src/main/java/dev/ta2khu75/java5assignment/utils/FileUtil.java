package dev.ta2khu75.java5assignment.utils;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
    private FileUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String getFileExtension(MultipartFile partFile) {
        String contentType = partFile.getContentType();
        if (contentType == null) {
            return null;
        }
        return contentType.split("/")[1];
    }
}
