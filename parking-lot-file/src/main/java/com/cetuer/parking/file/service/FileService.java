package com.cetuer.parking.file.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传接口
 *
 * @author Cetuer
 * @date 2022/2/12 15:42
 */
public interface FileService {
    /**
     * 文件上传
     *
     * @param file 文件
     * @return 访问地址
     */
    String upload(MultipartFile file) throws Exception;

    /**
     * 删除文件
     * @param filename 文件名
     */
    void delete(String filename) throws Exception;
}
