package com.zn.ai.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 用户游戏心愿单实体类
 */
@Getter
@Setter
@TableName(value = "user_game_wish")
public class UserGameWishEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 游戏ID
     */
    private Integer gameId;

    /**
     * 游戏名称
     */
    private String gameName;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 加入心愿单时间
     */
    private LocalDateTime wishTime;

    /**
     * 备注
     */
    private String remark;


}
