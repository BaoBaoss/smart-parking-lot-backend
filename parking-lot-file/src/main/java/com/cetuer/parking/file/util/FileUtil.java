package com.cetuer.parking.file.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.util.IdUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件工具类
 *
 * @author Cetuer
 * @date 2022/2/12 15:47
 */
public class FileUtil {

    /**
     *  编码文件名
     * @param file 文件
     * @return 编码后的文件名
     * @throws IOException IO异常
     */
    public static String encodeFilename(MultipartFile file) throws IOException {
        String extension = FileTypeUtil.getType(file.getInputStream(), file.getName());
        return DateUtil.today() + "/" + IdUtil.fastUUID() + "." + extension;
    }
}
