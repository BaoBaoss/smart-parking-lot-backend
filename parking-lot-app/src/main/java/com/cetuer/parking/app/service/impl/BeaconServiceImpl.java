package com.cetuer.parking.app.service.impl;

import com.cetuer.parking.app.domain.BeaconDevice;
import com.cetuer.parking.app.mapper.BeaconMapper;
import com.cetuer.parking.app.service.BeaconService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 信标操作服务实现
 *
 * @author Cetuer
 * @date 2022/3/6 18:03
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BeaconServiceImpl implements BeaconService {

    private final BeaconMapper beaconMapper;

    @Override
    public List<BeaconDevice> selectAll() {
        return beaconMapper.selectAll();
    }
}
