package com.cetuer.parking.file.api;

import com.cetuer.parking.common.core.constant.ServiceNameConstants;
import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.file.api.factory.RemoteFileFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * 远程调用文件服务
 *
 * @author Cetuer
 * @date 2022/2/12 20:44
 */
@FeignClient(contextId = "remoteFileService", value = ServiceNameConstants.FILE_SERVICE, fallbackFactory = RemoteFileFallbackFactory.class)
public interface RemoteFileService {
    /**
     * 上传文件
     * @param file 文件
     * @return 访问地址
     */
    @PostMapping(value = "/file/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResultData<String> upload(@RequestPart("file") MultipartFile file);

    /**
     * 删除文件
     * @param filename 文件名
     * @return 无
     */
    @DeleteMapping("/file/delete")
    ResultData<Void> delete(@RequestParam("filename") String filename);
}