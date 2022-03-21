package com.lorenzo.mind_palace.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.lorenzo.mind_palace.entity.Content;
import com.lorenzo.mind_palace.entity.Doc;
import com.lorenzo.mind_palace.entity.DocExample;
import com.lorenzo.mind_palace.mapper.ContentMapper;
import com.lorenzo.mind_palace.mapper.DocMapper;
import com.lorenzo.mind_palace.mapper.DocMapperCount;
import com.lorenzo.mind_palace.request.DocQueryReq;
import com.lorenzo.mind_palace.request.DocSaveReq;
import com.lorenzo.mind_palace.response.DocQueryResp;
import com.lorenzo.mind_palace.response.PageResp;
import com.lorenzo.mind_palace.util.CopyUtil;
import com.lorenzo.mind_palace.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lorenzo
 * @date 2022/03/19 20:22
 **/
@Service
public class DocService {

    private static final Logger LOG = LoggerFactory.getLogger(DocService.class);

    @Resource
    private DocMapper docMapper;

    @Resource
    private DocMapperCount docMapperCount;

    @Resource
    private SnowFlake snowFlake;

    @Resource
    private ContentMapper contentMapper;

    public List<DocQueryResp> all(Long ebookId) {
        DocExample docExample = new DocExample();
        docExample.createCriteria().andEbookIdEqualTo(ebookId);
        docExample.setOrderByClause("sort asc");
        List<Doc> docList = docMapper.selectByExample(docExample);

        // 列表复制
        List<DocQueryResp> list = CopyUtil.copyList(docList, DocQueryResp.class);

        return list;
    }

    public PageResp<DocQueryResp> list(DocQueryReq req) {
        DocExample docExample = new DocExample();
        docExample.setOrderByClause("sort asc");
        DocExample.Criteria criteria = docExample.createCriteria();
        PageHelper.startPage(req.getPage(),req.getSize());
        List<Doc> docsList = docMapper.selectByExample(docExample);

        PageInfo<Doc> pageInfo = new PageInfo<>(docsList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总行数：{}", pageInfo.getPages());

        // List<DocResp> respList = new ArrayList<>();
        // for (Doc doc : docsList) {
            // DocResp docResp = new DocResp();
            // BeanUtils.copyProperties(doc, docResp);
            // 遍历对象复制
            // DocResp docResp = CopyUtil.copy(doc, DocResp.class);
            // respList.add(docResp);
        // }

        // 列表复制
        List<DocQueryResp> respList = CopyUtil.copyList(docsList, DocQueryResp.class);
        PageResp<DocQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(respList);
        return pageResp;
    }

    /**
     * 保存
     */
    public void save(DocSaveReq req) {
        Doc doc = CopyUtil.copy(req, Doc.class);
        Content content = CopyUtil.copy(req, Content.class);
        if(ObjectUtils.isEmpty(req.getId())) {
            // id为空新增
            doc.setId(snowFlake.nextId());
            doc.setViewCount(0);
            doc.setVoteCount(0);
            docMapper.insert(doc);
            content.setId(doc.getId());
            contentMapper.insert(content);
        } else {
            // 更新
            docMapper.updateByPrimaryKey(doc);
            int count = contentMapper.updateByPrimaryKeyWithBLOBs(content);
            if (count == 0) {
                contentMapper.insert(content);
            }
        }
    }

    /**
     * 删除
     */
    public void delete(Long id) {
        docMapper.deleteByPrimaryKey(id);
    }
    public void delete(List<String> idStr) {
        DocExample docExample = new DocExample();
        DocExample.Criteria criteria = docExample.createCriteria();
        criteria.andIdIn(idStr);
        docMapper.deleteByExample(docExample);
    }

    public String findContent(Long id) {
        Content content = contentMapper.selectByPrimaryKey(id);
        // 文档阅读数+1
        docMapperCount.increaseViewCount(id);
        if (ObjectUtils.isEmpty(content)) {
            return "";
        } else {
            return content.getContent();
        }
    }

    /**
     * 点赞
     */
    public void vote(Long id) {
        docMapperCount.increaseVoteCount(id);
    }
}
