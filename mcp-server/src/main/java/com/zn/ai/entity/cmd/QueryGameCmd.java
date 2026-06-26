package com.zn.ai.entity.cmd;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QueryGameCmd {

    /**
     * 游戏名称
     */
    private String gameName;


    /**
     * 游戏标签
     */
    private List<String> tags;

    /**
     * 平台
     */
    private List<String> platforms;

}
