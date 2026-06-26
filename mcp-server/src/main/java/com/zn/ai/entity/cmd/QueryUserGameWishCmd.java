package com.zn.ai.entity.cmd;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryUserGameWishCmd {

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 联系电话
     */
    private String contactPhone;

}
