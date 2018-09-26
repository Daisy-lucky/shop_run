package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileService {

    public void file(byte[] file, String path) {
        File f = new File(path);


        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            System.out.println("文件路径" + path);
            FileOutputStream out = new FileOutputStream(path);

            out.write(file);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("文件撞见错误");
        } catch (IOException e) {
            System.out.println("写入异常");
        }

    }
}
