package com.cetuer.parking.file.aspect;

import cn.hutool.core.util.ObjectUtil;
import com.cetuer.parking.common.core.enums.ResultCode;
import com.cetuer.parking.common.core.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传切面
 *
 * @author Cetuer
 * @date 2022/2/12 16:27
 */
@Slf4j
@Aspect
@Component
public class FileUploadAspect {

    /**
     * 文件大小 50M
     */
    public static final long FILE_MAX_SIZE = 50 * 1024 * 1024;

    /**
     * 文件名最大长度 100
     */
    public static final int FILE_NAME_LENGTH = 100;

    /**
     * 切入点
     */
    @Pointcut("execution(public String com.cetuer.parking.file.service.FileService.upload(org.springframework.web.multipart.MultipartFile))")
    public void pointcut() {
    }

    /**
     * 检验文件合法性
     * @param file 文件
     */
    @Before(value = "pointcut() && args(file)")
    public void checkFile(MultipartFile file) {
        String filename = file.getOriginalFilename();
        if(ObjectUtil.isNull(filename)) {
            log.error("文件上传失败，文件名为空");
            throw new ServiceException(ResultCode.FILE_UPLOAD_ERROR);
        }
        int fileNameLength = filename.length();
        if (fileNameLength > FILE_NAME_LENGTH) {
            throw new ServiceException(ResultCode.FILE_NAME_LENGTH_ERROR);
        }
        if (file.getSize() > FILE_MAX_SIZE) {
            throw new ServiceException(ResultCode.FILE_SIZE_LIMIT_ERROR);
        }
    }
}
