package com.lorenzo.mind_palace.service;

import com.lorenzo.mind_palace.entity.Demo;
import com.lorenzo.mind_palace.entity.Ebook;
import com.lorenzo.mind_palace.mapper.DemoMapper;
import com.lorenzo.mind_palace.mapper.EbookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lorenzo
 * @date 2022/03/14 18:54
 **/
@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    public List<Ebook> list() {
        return ebookMapper.selectByExample(null);
    }
}
