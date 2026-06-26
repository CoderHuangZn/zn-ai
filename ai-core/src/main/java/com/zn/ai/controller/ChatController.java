package com.zn.ai.controller;

import com.zn.ai.service.IChatHistoryInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;


@RequiredArgsConstructor
@RestController
@RequestMapping("/ai")
public class ChatController {

    private final ChatClient chatClient;

    private final IChatHistoryInfoService chatHistoryInfoService;

    @GetMapping("/chat")
    public String chat(@RequestParam(name = "userId") Integer userId,
                       @RequestParam(name = "message") String message,
                       @RequestParam(name = "chatId") String chatId) {

        // 保存会话历史
        chatHistoryInfoService.save(userId, chatId, "chat");

        // 请求大模型
        return chatClient
                .prompt()
                .user(message)
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, chatId))
                .call()
                .content();
    }

//    @PostMapping(value = "/chat", produces = "text/html;charset=utf-8")
//    public Flux<String> chat(String prompt, String chatId) {
//        return chatClient
//                .prompt()
//                .user(prompt)
//                // 环绕增强
//                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, chatId))
//                .stream()
//                .content();
//    }


}
