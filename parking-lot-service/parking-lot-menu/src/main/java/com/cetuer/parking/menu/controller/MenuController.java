package com.cetuer.parking.menu.controller;

import com.cetuer.parking.common.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统菜单Controller
 *
 * @author Cetuer
 * @date 2021/11/30 22:39
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @GetMapping("/test")
    public ResultData<String> test() {
        return ResultData.success("Hello World !");
    }
}
