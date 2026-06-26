package com.zn.ai.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zn.ai.entity.po.ChatHistoryInfo;
import com.zn.ai.mapper.ChatHistoryInfoMapper;
import com.zn.ai.service.IChatHistoryInfoService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 会话历史记录 Service 实现
 */
@Service
public class ChatHistoryInfoServiceImpl extends ServiceImpl<ChatHistoryInfoMapper, ChatHistoryInfo> implements IChatHistoryInfoService {

    @Override
    public void save(Integer userId, String chatId, String type) {
        List<String> historyChatIds = getHistoryChatIds(userId, type);
        if (!historyChatIds.contains(chatId)) {
            ChatHistoryInfo historyInfo = new ChatHistoryInfo();
            historyInfo.setUserId(userId);
            historyInfo.setChatId(chatId);
            historyInfo.setType(type);
            historyInfo.setCreateTime(DateUtil.date());
            this.save(historyInfo);
        }
    }

    @Override
    public List<String> getHistoryChatIds(Integer userId, String type) {
        LambdaQueryWrapper<ChatHistoryInfo> queryWrapper = new LambdaQueryWrapper<ChatHistoryInfo>();
        queryWrapper.eq(ChatHistoryInfo::getUserId, userId);
        queryWrapper.eq(ChatHistoryInfo::getType, type);

        List<ChatHistoryInfo> historyList = this.list(queryWrapper);

        if (CollectionUtils.isEmpty(historyList)) {
            return List.of();
        } else {
            return historyList.stream().map(ChatHistoryInfo::getChatId).collect(Collectors.toList());
        }
    }

    @Override
    public void removeChatHistory(Integer userId, String chatId) {

    }

}
