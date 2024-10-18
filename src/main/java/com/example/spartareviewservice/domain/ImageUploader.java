package com.example.spartareviewservice.domain;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Component
public class ImageUploader {
    private static final String LOCATION_PATH = "C:\\Users\\cykei\\source";

    @SneakyThrows
    public String uploadImage(MultipartFile image){
        makeRootDirectory();
        String contentType = image.getContentType();
        if (!ObjectUtils.isEmpty(contentType) &&
                (contentType.contains("image/jpeg") || contentType.contains("image/png"))) {
            File file = new File(LOCATION_PATH, Objects.requireNonNull(image.getOriginalFilename()));
            image.transferTo(file);

            return file.getPath();
        }
        throw new IOException("이미지 업로드에 실패했습니다.");
    }

    private void makeRootDirectory() {
        File root = new File(LOCATION_PATH);
        if (!root.exists()) {
            if(root.mkdir()) System.out.println("이미지 디렉토리 생성"); //todo: log 로 변경
        }
    }
}
