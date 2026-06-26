package com.zn.ai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zn.ai.entity.po.ChatHistoryInfo;
import org.springframework.stereotype.Repository;

/**
 * 会话历史记录 Mapper
 */
@Repository
public interface ChatHistoryInfoMapper extends BaseMapper<ChatHistoryInfo> {

}
