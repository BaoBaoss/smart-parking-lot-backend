package com.cetuer.parking.file.service.impl;

import com.cetuer.parking.file.config.MinioConfig;
import com.cetuer.parking.file.service.FileService;
import com.cetuer.parking.file.util.FileUtil;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Minio文件存储
 *
 * @author Cetuer
 * @date 2022/2/12 15:44
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MinioFileServiceImpl implements FileService {
    private final MinioConfig minioConfig;
    private final MinioClient minioClient;

    @Override
    public String upload(MultipartFile file) throws Exception {
        String filename = FileUtil.encodeFilename(file);
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(minioConfig.getBucketName())
                .object(filename)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(file.getContentType())
                .build();
        minioClient.putObject(args);
        return minioConfig.getUrl() + "/" + minioConfig.getBucketName() + "/" + filename;
    }

    @Override
    public void delete(String filename) throws Exception {
        filename = filename.replace(minioConfig.getUrl() + "/" + minioConfig.getBucketName() + "/", "");
        RemoveObjectArgs args = RemoveObjectArgs.builder()
                .bucket(minioConfig.getBucketName())
                .object(filename).build();
        minioClient.removeObject(args);
    }
}
