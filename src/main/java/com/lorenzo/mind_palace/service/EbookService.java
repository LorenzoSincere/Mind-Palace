package com.lorenzo.mind_palace.service;

import com.lorenzo.mind_palace.entity.Demo;
import com.lorenzo.mind_palace.entity.Ebook;
import com.lorenzo.mind_palace.entity.EbookExample;
import com.lorenzo.mind_palace.mapper.DemoMapper;
import com.lorenzo.mind_palace.mapper.EbookMapper;
import com.lorenzo.mind_palace.request.EbookReq;
import com.lorenzo.mind_palace.response.EbookResp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lorenzo
 * @date 2022/03/14 18:54
 **/
@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%" + req.getName() + "%");
        List<Ebook> ebooksList = ebookMapper.selectByExample(ebookExample);

        List<EbookResp> respList = new ArrayList<>();
        for (Ebook ebook : ebooksList) {
            EbookResp ebookResp = new EbookResp();
            BeanUtils.copyProperties(ebook, ebookResp);
            respList.add(ebookResp);
        }
        return respList;
    }
}
