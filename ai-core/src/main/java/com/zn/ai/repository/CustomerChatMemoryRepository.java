package com.zn.ai.repository;

import com.zn.ai.entity.po.ChatMemoryDocument;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.messages.Message;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义会话记忆实现实例
 * 负责消息的实际存储与检索，是数据的搬运工
 */
//@Component
//public class CustomerChatMemoryRepository implements ChatMemoryRepository {
//
//    /**
//     * MongoDB操作模板
//     */
//    private final MongoTemplate mongoTemplate;
//
//    public CustomerChatMemoryRepository(MongoTemplate mongoTemplate) {
//        this.mongoTemplate = mongoTemplate;
//    }
//
//    @Override
//    public List<String> findConversationIds() {
//        // 查询所有不同的 conversationId
//        return mongoTemplate.findDistinct(new Query(), "conversationId",
//                ChatMemoryDocument.class, String.class);
//    }
//
//    @Override
//    public List<Message> findByConversationId(String conversationId) {
//        Query query = Query.query(Criteria.where("conversationId").is(conversationId));
//        // 假设 ChatMemoryDocument 包含 conversationId 和 messages 字段
//        ChatMemoryDocument doc = mongoTemplate.findOne(query, ChatMemoryDocument.class);
//        return doc != null ? doc.getMessages() : List.of();
//    }
//
//    @Override
//    public void saveAll(String conversationId, List<Message> messages) {
//        // 使用 upsert 操作：存在则更新，不存在则插入
//        Query query = Query.query(Criteria.where("conversationId").is(conversationId));
//        ChatMemoryDocument doc = new ChatMemoryDocument(conversationId, messages);
//        mongoTemplate.save(doc, "chat_memory"); // 保存到 chat_memory 集合
//    }
//
//    @Override
//    public void deleteByConversationId(String conversationId) {
//        Query query = Query.query(Criteria.where("conversationId").is(conversationId));
//        mongoTemplate.remove(query, ChatMemoryDocument.class);
//    }
//
//}
