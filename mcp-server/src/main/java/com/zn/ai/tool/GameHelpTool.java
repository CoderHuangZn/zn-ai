package com.zn.ai.tool;

import com.zn.ai.entity.cmd.QueryGameCmd;
import com.zn.ai.entity.vo.GameInfoVO;
import com.zn.ai.service.IGameInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GameHelpTool {

    private final IGameInfoService gameInfoService;

    /*
     * @McpTool 是专门为“模型上下文协议”（MCP）设计的注解，用于在MCP服务器中注册工具，并提供MCP协议特有的高级功能.
     * 使用该注解,直接能将该工具暴露给MCP服务,不需要向ToolCallbackProvider注册了
     */
    @McpTool(name = "searchGameInfo", description = "根据输入条件,查询符合条件的游戏信息")
    public List<GameInfoVO> queryGame(
            @McpToolParam(description = "游戏名称", required = false) String gameName,
            @McpToolParam(description = "游戏标签,如：动作/冒险,多个采用集合的方式传递", required = false) List<String> tags,
            @McpToolParam(description = "游戏平台,如：PC/PS5,多个采用集合的方式传递", required = false) List<String> platforms
    ) {
        QueryGameCmd queryGameCmd = new QueryGameCmd();
        queryGameCmd.setGameName(gameName);
        queryGameCmd.setTags(tags);
        queryGameCmd.setPlatforms(platforms);
        return gameInfoService.queryGame(queryGameCmd);
    }

}
