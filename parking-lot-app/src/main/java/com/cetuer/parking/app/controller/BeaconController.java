package com.cetuer.parking.app.controller;

import com.cetuer.parking.app.domain.BeaconDevice;
import com.cetuer.parking.app.service.BeaconService;
import com.cetuer.parking.common.core.domain.ResultData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *  信标操作
 *
 * @author Cetuer
 * @date 2022/3/6 12:09
 */
@RestController
@RequestMapping("/beacon")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BeaconController {

    private final BeaconService beaconService;

    /**
     * 获取所有信标
     * @return 信标列表
     */
    @GetMapping("/list")
    public ResultData<List<BeaconDevice>> list() {
        return ResultData.success(beaconService.selectAll());
    }
}
