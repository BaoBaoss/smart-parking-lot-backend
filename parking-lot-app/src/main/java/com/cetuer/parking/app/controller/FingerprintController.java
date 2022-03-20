package com.cetuer.parking.app.controller;

import com.cetuer.parking.app.service.FingerprintService;
import com.cetuer.parking.common.core.domain.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 指纹操作
 *
 * @author zhangqb
 * @date 2022/3/19 16:09
 */
@Api(tags = "指纹操作")
@RestController
@RequestMapping("/fingerprint")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FingerprintController {

    private final FingerprintService fingerprintService;

    @ApiOperation("指纹收集")
    @PostMapping("/collect")
    public ResultData<Void> collectFingerprint(@ApiParam(name = "指纹信息", required = true) @RequestBody Map<String, Map<String, Double>> fingerprint) {
        fingerprintService.collectFingerprint(fingerprint);
        return ResultData.success();
    }
}
