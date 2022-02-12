package com.cetuer.parking.file.api.factory;

import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.enums.ResultCode;
import com.cetuer.parking.file.api.RemoteFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务降级处理
 *
 * @author Cetuer
 * @date 2022/2/12 20:53
 */
@Slf4j
@Component
public class RemoteFileFallbackFactory implements FallbackFactory<RemoteFileService> {
    @Override
    public RemoteFileService create(Throwable cause) {
        log.error("文件服务调用失败:{}", cause.getMessage());
        return new RemoteFileService() {
            @Override
            public ResultData<String> upload(MultipartFile file) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }

            @Override
            public ResultData<Void> delete(String filename) {
                return ResultData.fail(ResultCode.SERVICE_DEMOTION);
            }
        };
    }
}
