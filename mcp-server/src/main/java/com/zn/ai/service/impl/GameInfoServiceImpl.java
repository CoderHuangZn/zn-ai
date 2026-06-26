package com.zn.ai.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zn.ai.entity.cmd.QueryGameCmd;
import com.zn.ai.entity.po.GameInfoEntity;
import com.zn.ai.entity.vo.GameInfoVO;
import com.zn.ai.mapper.GameInfoMapper;
import com.zn.ai.service.IGameInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameInfoServiceImpl extends ServiceImpl<GameInfoMapper, GameInfoEntity> implements IGameInfoService {

    @Override
    public List<GameInfoVO> queryGame(QueryGameCmd cmd) {
        // 查询条件构建
        LambdaQueryWrapper<GameInfoEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(cmd.getGameName()), GameInfoEntity::getGameName, cmd.getGameName());
        hasAnyTag(wrapper, cmd.getTags().toArray(String[]::new));
        hasAnyPlatform(wrapper, cmd.getPlatforms());

        List<GameInfoEntity> list = this.list(wrapper);
        if (CollUtil.isNotEmpty(list)) {
            return BeanUtil.copyToList(list, GameInfoVO.class);
        }
        return List.of();
    }

    private void hasAnyTag(LambdaQueryWrapper<GameInfoEntity> wrapper, String... tags) {
        if (tags.length == 0) return;
        wrapper.and(w -> {
            for (int i = 0; i < tags.length; i++) {
                if (i > 0) w.or();
                w.apply("JSON_CONTAINS(tags, {0})", "\"" + tags[i] + "\"");
            }
        });
    }

    private void hasAnyPlatform(LambdaQueryWrapper<GameInfoEntity> wrapper, List<String> platformsList) {
        if (CollUtil.isEmpty(platformsList)) return;
        String platformJsonArray = JSONObject.toJSONString(platformsList);
        wrapper.apply("JSON_OVERLAPS(platforms, {0})", platformJsonArray);
    }

}