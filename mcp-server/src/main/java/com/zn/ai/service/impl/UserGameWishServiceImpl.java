package com.zn.ai.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zn.ai.entity.cmd.QueryUserGameWishCmd;
import com.zn.ai.entity.cmd.SaveUserGameWishCmd;
import com.zn.ai.entity.po.UserGameWishEntity;
import com.zn.ai.entity.vo.UserGameWishVO;
import com.zn.ai.mapper.UserGameWishMapper;
import com.zn.ai.service.IUserGameWishService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserGameWishServiceImpl extends ServiceImpl<UserGameWishMapper, UserGameWishEntity> implements IUserGameWishService {


    @Override
    public boolean addUserGameWish(SaveUserGameWishCmd cmd) {
        UserGameWishEntity entity = BeanUtil.copyProperties(cmd, UserGameWishEntity.class);
        entity.setId(null);
        entity.setWishTime(LocalDateTime.now());
        return this.save(entity);
    }

    @Override
    public boolean editUserGameWish(SaveUserGameWishCmd cmd) {
        UserGameWishEntity entity = BeanUtil.copyProperties(cmd, UserGameWishEntity.class);
        UserGameWishEntity oriEntity = this.getById(entity.getId());
        if (ObjectUtil.isNull(oriEntity)) {
            return false;
        }
        return this.updateById(entity);
    }

    @Override
    public List<UserGameWishVO> getUserGameWishList(QueryUserGameWishCmd cmd) {
        LambdaQueryWrapper<UserGameWishEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(cmd.getUserName()), UserGameWishEntity::getUserName, cmd.getUserName());
        wrapper.eq(StrUtil.isNotBlank(cmd.getContactPhone()), UserGameWishEntity::getContactPhone, cmd.getContactPhone());
        wrapper.orderByDesc(UserGameWishEntity::getWishTime);

        return BeanUtil.copyToList(this.list(wrapper), UserGameWishVO.class);
    }

}