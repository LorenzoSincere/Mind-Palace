package com.lorenzo.mind_palace.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lorenzo.mind_palace.entity.Doc;
import com.lorenzo.mind_palace.entity.DocExample;
import com.lorenzo.mind_palace.mapper.DocMapper;
import com.lorenzo.mind_palace.request.DocQueryReq;
import com.lorenzo.mind_palace.request.DocSaveReq;
import com.lorenzo.mind_palace.response.DocQueryResp;
import com.lorenzo.mind_palace.response.PageResp;
import com.lorenzo.mind_palace.util.CopyUtil;
import com.lorenzo.mind_palace.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lorenzo
 * @date 2022/03/18 18:08
 **/
@Service
public class DocService {

    private static final Logger LOG = LoggerFactory.getLogger(DocService.class);

    @Resource
    private DocMapper docMapper;

    @Resource
    private SnowFlake snowFlake;

    public List<DocQueryResp> all() {
        DocExample docExample = new DocExample();
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
        if(ObjectUtils.isEmpty(req.getId())) {
            // id为空新增电子书
            doc.setId(snowFlake.nextId());
            docMapper.insert(doc);
        } else {
            // 更新电子书
            docMapper.updateByPrimaryKey(doc);
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
}
