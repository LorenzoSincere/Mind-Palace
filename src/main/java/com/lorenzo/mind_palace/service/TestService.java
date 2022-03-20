package com.lorenzo.mind_palace.service;

import com.lorenzo.mind_palace.entity.Test;
import com.lorenzo.mind_palace.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lorenzo
 * @date 2022/03/14 18:54
 **/
@Service
public class TestService {

    @Resource
    private TestMapper testMapper;

    public List<Test> list() {
        return testMapper.list();
    }
}
