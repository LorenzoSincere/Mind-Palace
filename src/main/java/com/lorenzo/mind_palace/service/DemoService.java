package com.lorenzo.mind_palace.service;

import com.lorenzo.mind_palace.entity.Demo;
import com.lorenzo.mind_palace.mapper.DemoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lorenzo
 * @date 2022/03/14 18:54
 **/
@Service
public class DemoService {

    @Resource
    private DemoMapper demoMapper;

    public List<Demo> list() {
        return demoMapper.selectByExample(null);
    }
}
