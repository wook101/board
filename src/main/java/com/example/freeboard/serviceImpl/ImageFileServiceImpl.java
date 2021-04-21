package com.example.freeboard.serviceImpl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.freeboard.service.ImageFileService;

@Service
public class ImageFileServiceImpl implements ImageFileService {

    // 파일 업로드
    @Override
    public int upload(MultipartFile file) {
        String extension = regex(file.getContentType());
        Integer hashCode = file.hashCode();
        String path = filePath + hashCode + extension;
        try (FileOutputStream fos = new FileOutputStream(path);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             InputStream is = file.getInputStream();
             BufferedInputStream bis = new BufferedInputStream(is);

        ) {
            int readCount = 0;
            byte[] buffer = new byte[1024];
            while ((readCount = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, readCount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashCode;
    }

    // 정규표현식 (파일 확장자)
    @Override
    public String regex(String text) {
        String regex = "\\w+[/.]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        if (m.find()) {
            return "." + text.replace(m.group(), "");
        }
        return null;
    }

}
