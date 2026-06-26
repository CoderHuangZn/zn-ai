package com.zn.ai.config;

import com.zn.ai.constants.PromptConstants;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.mongo.MongoChatMemoryRepository;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 对话客户端配置
 */
@Configuration
public class ChatConfiguration {

    /**
     * 配置基于MongoDB的会话记忆
     * SpringAI 自动配置会注入 MongoChatMemoryRepository(配置文件中有相应配置)
     */
    @Bean
    public ChatMemory chatMemory(MongoChatMemoryRepository chatMemoryRepository) {
        return MessageWindowChatMemory.builder()
                // 注入MongoDB实现
                .chatMemoryRepository(chatMemoryRepository)
                // 限制记忆窗口为20条
                .maxMessages(20)
                .build();
    }

    /**
     * 聊天客户端，集成 MongoDB 会话记忆
     */
    @Bean
    public ChatClient chatClient(OpenAiChatModel model, ChatMemory chatMemory) {
        return ChatClient
                // 设置模型,模型配置在yml中
                .builder(model)
                // 设置默认系统提示词
                .defaultSystem("你是一名专业的客服人员,昵称叫水狗。")
                // 配置日志 + mongodb会话记忆
                .defaultAdvisors(
                        new SimpleLoggerAdvisor(),
                        MessageChatMemoryAdvisor.builder(chatMemory).build()
                )
                .build();
    }

    /**
     * 游戏chatClient
     */
    @Bean
    public ChatClient gameChatClient(OpenAiChatModel model, ChatMemory chatMemory) {
        return ChatClient
                // 设置模型,模型配置在yml中
                .builder(model)
                // 设置默认系统提示词
                .defaultSystem(PromptConstants.GAME_SYSTEM_PROMPT)
                // 配置日志 + mongodb会话记忆
                .defaultAdvisors(
                        new SimpleLoggerAdvisor(),
                        MessageChatMemoryAdvisor.builder(chatMemory).build()
                )
                .build();
    }

    /**
     * 服务chatClient
     */
    @Bean
    public ChatClient serviceChatClient(OpenAiChatModel model, ChatMemory chatMemory, SyncMcpToolCallbackProvider mcpProvider) {
        return ChatClient
                // 设置模型,模型配置在yml中
                .builder(model)
                // 设置默认系统提示词
                .defaultSystem(PromptConstants.CUSTOMER_SERVICE_SYSTEM)
                // 配置日志 + mongodb会话记忆
                .defaultAdvisors(
                        new SimpleLoggerAdvisor(),
                        MessageChatMemoryAdvisor.builder(chatMemory).build()
                )
                //  方法在 Spring AI 2.0.0 中被标记为废弃，是因为框架对工具调用的整个架构进行了重构
                .defaultToolCallbacks(mcpProvider.getToolCallbacks())
                .build();
    }

    /*
     * 自定义chatMemory实现
     * @param customerMemoryRepository 自定义会话记忆实现实例,继承ChatMemoryRepository进行方法实现
     */
/*    @Bean
    public ChatMemory chatMemory(CustomerChatMemoryRepository customerMemoryRepository) {
        return MessageWindowChatMemory.builder()
                .chatMemoryRepository(customerMemoryRepository) // 注入你的自定义 Repository
                .maxMessages(20) // 设置窗口大小为30条消息，默认为20[reference:10]
                .build();
    }*/

}
