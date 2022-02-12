package com.cetuer.parking.file.controller;

import com.cetuer.parking.common.core.domain.ResultData;
import com.cetuer.parking.common.core.enums.ResultCode;
import com.cetuer.parking.common.core.exception.ServiceException;
import com.cetuer.parking.file.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 文件操作
 *
 * @author Cetuer
 * @date 2022/2/12 15:30
 */
@Slf4j
@Api(tags = "文件操作")
@RestController
@Validated
@RequestMapping("/file")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FileController {
    private final FileService fileService;

    /**
     * 文件上传
     *
     * @param file 文件
     * @return 文件访问地址
     */
    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public ResultData<String> upload(MultipartFile file) {
        try {
            return ResultData.success(fileService.upload(file));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传文件失败：{}", e.getMessage());
            throw new ServiceException(ResultCode.FILE_UPLOAD_ERROR);
        }
    }

    /**
     * 删除文件
     * @param filename 文件名
     * @return 无
     */
    @ApiOperation("文件删除")
    @DeleteMapping("/delete")
    public ResultData<Void> delete(@Valid @NotBlank(message = "文件名不能为空") String filename) {
        try {
            fileService.delete(filename);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除文件失败：{}", e.getMessage());
            throw new ServiceException(ResultCode.FILE_DELETE_ERROR);
        }
        return ResultData.success();
    }
}
