package com.zn.ai.entity.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.ai.chat.messages.Message;

@Getter
@Setter
public class ChatMessageVO {

    private String role;

    private String content;

    public ChatMessageVO(Message message) {
        switch (message.getMessageType()) {
            case USER -> role = "user";
            case ASSISTANT -> role = "assistant";
            default -> role = "unknown";
        }
        this.content = message.getText();
    }

}
