package com.zn.ai.config;

import com.zn.ai.tool.GameHelpTool;
import com.zn.ai.tool.MathTool;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToolRegisterConfiguration {

    /**
     * 将工具注入到mcp
     */
    @Bean
    public ToolCallbackProvider mcpTools(MathTool mathTool) {
        return MethodToolCallbackProvider.builder().toolObjects(mathTool).build();
    }

}
