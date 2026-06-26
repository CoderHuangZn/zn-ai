package com.zn.ai.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 会话历史记录
 */
@Getter
@Setter
@TableName(value = "chat_history_info")
public class ChatHistoryInfo {

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 会话ID
     */
    private String chatId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 会话类型
     */
    private String type;

    /**
     * 创建时间
     */
    private Date createTime;

}
