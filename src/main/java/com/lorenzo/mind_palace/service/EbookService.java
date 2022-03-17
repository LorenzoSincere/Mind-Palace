package com.lorenzo.mind_palace.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lorenzo.mind_palace.entity.Demo;
import com.lorenzo.mind_palace.entity.Ebook;
import com.lorenzo.mind_palace.entity.EbookExample;
import com.lorenzo.mind_palace.mapper.DemoMapper;
import com.lorenzo.mind_palace.mapper.EbookMapper;
import com.lorenzo.mind_palace.request.EbookReq;
import com.lorenzo.mind_palace.response.EbookResp;
import com.lorenzo.mind_palace.util.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lorenzo
 * @date 2022/03/14 18:54
 **/
@Service
public class EbookService {

    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);

    @Resource
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if(!ObjectUtils.isEmpty(req.getName())) {
            criteria.andNameLike("%" + req.getName() + "%");
        }
        PageHelper.startPage(1,5);
        List<Ebook> ebooksList = ebookMapper.selectByExample(ebookExample);

        PageInfo<Ebook> pageInfo = new PageInfo<>();
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总行数：{}", pageInfo.getPages());

        // List<EbookResp> respList = new ArrayList<>();
        // for (Ebook ebook : ebooksList) {
            // EbookResp ebookResp = new EbookResp();
            // BeanUtils.copyProperties(ebook, ebookResp);
            // 遍历对象复制
            // EbookResp ebookResp = CopyUtil.copy(ebook, EbookResp.class);
            // respList.add(ebookResp);
        // }

        // 列表复制
        List<EbookResp> respList = CopyUtil.copyList(ebooksList, EbookResp.class);
        return respList;
    }
}
