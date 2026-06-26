package com.zn.ai.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class GameInfoVO {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 游戏名称
     */
    private String gameName;

    /**
     * 封面图片
     */
    private String coverImage;

    /**
     * 开发商
     */
    private String developer;

    /**
     * 游戏标签
     */
    private List<String> tags;

    /**
     * 游戏平台
     */
    private List<String> platforms;

    /**
     * 游戏简介
     */
    private String description;

    /**
     * 发行日期
     */
    private LocalDate releaseDate;

    /**
     * 售价(元)
     */
    private BigDecimal price;

}
