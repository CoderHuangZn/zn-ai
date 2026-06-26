package com.zn.ai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zn.ai.entity.cmd.QueryGameCmd;
import com.zn.ai.entity.po.GameInfoEntity;
import com.zn.ai.entity.vo.GameInfoVO;

import java.util.List;

public interface IGameInfoService extends IService<GameInfoEntity> {

    List<GameInfoVO> queryGame(QueryGameCmd cmd);

}