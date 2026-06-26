package com.zn.ai.tool;

import com.zn.ai.common.response.Result;
import com.zn.ai.entity.cmd.QueryGameCmd;
import com.zn.ai.entity.cmd.QueryUserGameWishCmd;
import com.zn.ai.entity.cmd.SaveUserGameWishCmd;
import com.zn.ai.entity.vo.GameInfoVO;
import com.zn.ai.entity.vo.UserGameWishVO;
import com.zn.ai.service.IGameInfoService;
import com.zn.ai.service.IUserGameWishService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GameHelpTool {

    private final IGameInfoService gameInfoService;

    private final IUserGameWishService userGameWishService;

    /*
     * @McpTool 是专门为“模型上下文协议”（MCP）设计的注解，用于在MCP服务器中注册工具，并提供MCP协议特有的高级功能.
     * 使用该注解,直接能将该工具暴露给MCP服务,不需要向ToolCallbackProvider注册了
     */
    @McpTool(name = "searchGameInfo", description = "根据输入条件,查询符合条件的游戏信息")
    public Result<List<GameInfoVO>> queryGame(
            @McpToolParam(description = "游戏名称", required = false) String gameName,
            @McpToolParam(description = "游戏标签,如：动作/冒险,多个采用集合的方式传递", required = false) List<String> tags,
            @McpToolParam(description = "游戏平台,如：PC/PS5,多个采用集合的方式传递", required = false) List<String> platforms
    ) {
        QueryGameCmd queryGameCmd = new QueryGameCmd();
        queryGameCmd.setGameName(gameName);
        queryGameCmd.setTags(tags);
        queryGameCmd.setPlatforms(platforms);
        return Result.success(gameInfoService.queryGame(queryGameCmd));
    }

    @McpTool(name = "addUserGameWish", description = "添加用户游戏心愿单,响应为操作结果.true为操作成功,false为操作失败")
    public Result<Boolean> addUserGameWish(@McpToolParam(description = "用户名称") String userName,
                                           @McpToolParam(description = "游戏ID,对应系统中游戏信息中的ID") Integer gameId,
                                           @McpToolParam(description = "游戏名称") String gameName,
                                           @McpToolParam(description = "联系电话") String contactPhone,
                                           @McpToolParam(description = "备注信息", required = false) String remark) {

        SaveUserGameWishCmd cmd = new SaveUserGameWishCmd();
        cmd.setUserName(userName).setGameId(gameId).setGameName(gameName).setContactPhone(contactPhone).setRemark(remark);
        return Result.success(userGameWishService.addUserGameWish(cmd));
    }

    @McpTool(name = "getUserGameWishList", description = "根据用户信息,查询用户游戏心愿单数据")
    public Result<List<UserGameWishVO>> getUserGameWishList(@McpToolParam(description = "用户名称") String userName,
                                                            @McpToolParam(description = "联系电话", required = false) String contactPhone) {

        QueryUserGameWishCmd cmd = new QueryUserGameWishCmd();
        cmd.setUserName(userName);
        cmd.setContactPhone(contactPhone);
        return Result.success(userGameWishService.getUserGameWishList(cmd));
    }


}
