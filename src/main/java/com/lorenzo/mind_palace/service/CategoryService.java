package com.lorenzo.mind_palace.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lorenzo.mind_palace.entity.Category;
import com.lorenzo.mind_palace.entity.CategoryExample;
import com.lorenzo.mind_palace.mapper.CategoryMapper;
import com.lorenzo.mind_palace.request.CategoryQueryReq;
import com.lorenzo.mind_palace.request.CategorySaveReq;
import com.lorenzo.mind_palace.response.CategoryQueryResp;
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
public class CategoryService {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private SnowFlake snowFlake;

    public PageResp<CategoryQueryResp> list(CategoryQueryReq req) {
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        PageHelper.startPage(req.getPage(),req.getSize());
        List<Category> categorysList = categoryMapper.selectByExample(categoryExample);

        PageInfo<Category> pageInfo = new PageInfo<>(categorysList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总行数：{}", pageInfo.getPages());

        // List<CategoryResp> respList = new ArrayList<>();
        // for (Category category : categorysList) {
            // CategoryResp categoryResp = new CategoryResp();
            // BeanUtils.copyProperties(category, categoryResp);
            // 遍历对象复制
            // CategoryResp categoryResp = CopyUtil.copy(category, CategoryResp.class);
            // respList.add(categoryResp);
        // }

        // 列表复制
        List<CategoryQueryResp> respList = CopyUtil.copyList(categorysList, CategoryQueryResp.class);
        PageResp<CategoryQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(respList);
        return pageResp;
    }

    /**
     * 保存
     */
    public void save(CategorySaveReq req) {
        Category category = CopyUtil.copy(req, Category.class);
        if(ObjectUtils.isEmpty(req.getId())) {
            // id为空新增电子书
            snowFlake.nextId();
            categoryMapper.insert(category);
        } else {
            // 更新电子书
            categoryMapper.updateByPrimaryKey(category);
        }
    }

    /**
     * 删除
     */
    public void delete(Long id) {
        categoryMapper.deleteByPrimaryKey(id);
    }
}
