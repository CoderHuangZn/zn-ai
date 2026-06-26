package com.zn.ai.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import com.zn.ai.handler.StringListTypeHandler;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 游戏信息实体类
 */
@Getter
@Setter
@TableName(value = "game_info", autoResultMap = true)
public class GameInfoEntity {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 游戏名称
     */
    @TableField("game_name")
    private String gameName;

    /**
     * 封面图片
     */
    @TableField("cover_image")
    private String coverImage;

    /**
     * 开发商
     */
    @TableField("developer")
    private String developer;

    /**
     * 游戏标签，存储为 JSON 数组，对应 Java List<String>
     */
    @TableField(value = "tags", typeHandler = StringListTypeHandler.class)
    private List<String> tags;

    /**
     * 游戏平台，存储为 JSON 数组，对应 Java List<String>
     */
    @TableField(value = "platforms", typeHandler = StringListTypeHandler.class)
    private List<String> platforms;

    /**
     * 游戏简介
     */
    @TableField("description")
    private String description;

    /**
     * 发行日期
     */
    @TableField("release_date")
    private LocalDate releaseDate;

    /**
     * 售价(元)
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createdTime;

    /**
     * 修改时间
     */
    @TableField(value = "updated_time")
    private LocalDateTime updatedTime;

}