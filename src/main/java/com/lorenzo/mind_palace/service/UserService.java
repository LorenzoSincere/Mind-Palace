package com.lorenzo.mind_palace.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lorenzo.mind_palace.entity.User;
import com.lorenzo.mind_palace.entity.UserExample;
import com.lorenzo.mind_palace.exception.BusinessException;
import com.lorenzo.mind_palace.exception.BusinessExceptionCode;
import com.lorenzo.mind_palace.mapper.UserMapper;
import com.lorenzo.mind_palace.request.UserQueryReq;
import com.lorenzo.mind_palace.request.UserSaveReq;
import com.lorenzo.mind_palace.response.UserQueryResp;
import com.lorenzo.mind_palace.response.PageResp;
import com.lorenzo.mind_palace.util.CopyUtil;
import com.lorenzo.mind_palace.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @author lorenzo
 * @date 2022/03/20 16:29
 **/
@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserMapper userMapper;

    @Resource
    private SnowFlake snowFlake;

    public PageResp<UserQueryResp> list(UserQueryReq req) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if(!ObjectUtils.isEmpty(req.getLoginName())) {
            criteria.andNameLike("%" + req.getLoginName() + "%");
        }
        PageHelper.startPage(req.getPage(),req.getSize());
        List<User> usersList = userMapper.selectByExample(userExample);

        PageInfo<User> pageInfo = new PageInfo<>(usersList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总行数：{}", pageInfo.getPages());

        // List<UserResp> respList = new ArrayList<>();
        // for (User user : usersList) {
            // UserResp userResp = new UserResp();
            // BeanUtils.copyProperties(user, userResp);
            // 遍历对象复制
            // UserResp userResp = CopyUtil.copy(user, UserResp.class);
            // respList.add(userResp);
        // }

        // 列表复制
        List<UserQueryResp> respList = CopyUtil.copyList(usersList, UserQueryResp.class);
        PageResp<UserQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(respList);
        return pageResp;
    }

    /**
     * 保存
     */
    public void save(UserSaveReq req) {
        User user = CopyUtil.copy(req, User.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            User userDB = selectByLoginName(req.getLoginName());
            if (ObjectUtils.isEmpty(userDB)) {
                // 新增
                user.setId(snowFlake.nextId());
                userMapper.insert(user);
            } else {
                // 用户名已存在
                throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
            }
        } else {
            // 更新，防攻击
            user.setLoginName(null);
            user.setPassword(null);
            userMapper.updateByPrimaryKeySelective(user);
        }
    }

    /**
     * 删除
     */
    public void delete(Long id) {
        userMapper.deleteByPrimaryKey(id);
    }

    public User selectByLoginName(String loginName) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andLoginNameEqualTo(loginName);
        List<User> usersList = userMapper.selectByExample(userExample);
        if(CollectionUtils.isEmpty(usersList)){
            return null;
        } else {
           usersList.get(0);
        }
    }
}
