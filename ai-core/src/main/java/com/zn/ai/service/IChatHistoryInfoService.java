package com.zn.ai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zn.ai.entity.po.ChatHistoryInfo;

import java.util.List;

/**
 * 会话历史记录 Service
 */
public interface IChatHistoryInfoService extends IService<ChatHistoryInfo> {

    /**
     * 保存会话历史记录
     *
     * @param userId 用户ID
     * @param chatId 会话ID
     * @param type   业务类型
     */
    void save(Integer userId, String chatId, String type);

    /**
     * 获取会话历史ID列表
     *
     * @param userId 用户ID
     * @param type   业务类型
     * @return 会话ID列表
     */
    List<String> getHistoryChatIds(Integer userId, String type);

    /**
     * 移除会话历史
     *
     * @param userId 用户ID
     * @param chatId 会话ID
     */
    void removeChatHistory(Integer userId, String chatId);

}
