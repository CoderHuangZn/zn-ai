package com.zn.ai.controller;

import com.zn.ai.service.IChatHistoryInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/server-ai")
public class ServiceChatController {

    private final ChatClient serviceChatClient;

    @GetMapping("/chat")
    public String chat(
                       @RequestParam(name = "message") String message,
                       @RequestParam(name = "chatId") String chatId) {

        // 请求大模型
        return serviceChatClient
                .prompt()
                .user(message)
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, chatId))
                .call()
                .content();
    }


}
