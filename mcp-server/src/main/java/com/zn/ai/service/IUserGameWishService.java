package com.zn.ai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zn.ai.entity.cmd.QueryUserGameWishCmd;
import com.zn.ai.entity.cmd.SaveUserGameWishCmd;
import com.zn.ai.entity.po.UserGameWishEntity;
import com.zn.ai.entity.vo.UserGameWishVO;

import java.util.List;

public interface IUserGameWishService extends IService<UserGameWishEntity> {

    /**
     * 新增用户游戏心愿
     *
     * @param cmd 操作类
     * @return 操作结果
     */
    boolean addUserGameWish(SaveUserGameWishCmd cmd);

    /**
     * 修改用户游戏心愿
     *
     * @param cmd 操作类
     * @return 操作结果
     */
    boolean editUserGameWish(SaveUserGameWishCmd cmd);

    /**
     * 根据条件获取用户游戏心愿信息
     *
     * @param cmd 操作类
     * @return 游戏心愿信息
     */
    List<UserGameWishVO> getUserGameWishList(QueryUserGameWishCmd cmd);

}