package com.zn.ai.controller;

import com.zn.ai.service.IChatHistoryInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/server-ai")
public class ServiceChatController {

    private final ChatClient serviceChatClient;

    private final SyncMcpToolCallbackProvider syncMcpToolCallbackProvider;

    @GetMapping("/chat")
    public String chat(
                       @RequestParam(name = "message") String message,
                       @RequestParam(name = "chatId") String chatId) {

        // 请求大模型
        return serviceChatClient
                .prompt()
                .user(message)
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, chatId))
                // 从 Spring AI 2.0.0 开始，tools() 方法已经不再存在于 ChatClient.Builder 上,而是被移到了 ChatClient.prompt() 的返回对象上。这是一种新的设计，用于在每次请求时灵活地指定工具，而不是在构建客户端时固定下来
                .tools(syncMcpToolCallbackProvider)
                .call()
                .content();
    }


}
