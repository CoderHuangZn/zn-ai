package com.zn.ai.controller;

import cn.hutool.core.collection.CollUtil;
import com.zn.ai.entity.vo.ChatMessageVO;
import com.zn.ai.service.IChatHistoryInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ai/history")
public class ChatHistoryController {

    private final IChatHistoryInfoService chatHistoryInfoService;

    private final ChatMemory chatMemory;

    @GetMapping("/{type}")
    public List<String> getChatIds(@PathVariable("type") String type, @RequestParam("userId") Integer userId) {
        return chatHistoryInfoService.getHistoryChatIds(userId, type);
    }

    @GetMapping("/detail/{chatId}")
    public List<ChatMessageVO> getChatHistory(@PathVariable("chatId") String chatId) {
        List<Message> messages = chatMemory.get(chatId);
        if (CollUtil.isEmpty(messages)) {
            return List.of();
        }
        return messages.stream().map(ChatMessageVO::new).toList();
    }

}
