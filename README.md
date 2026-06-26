# zn-ai

> 氢锌 AI 智能服务平台 —— 基于 Spring AI 2.0 + MCP 协议的企业级 AI 应用

## 📋 项目简介

zn-ai 是一个基于 **Spring AI 2.0** 和 **MCP（Model Context Protocol）** 协议构建的 AI 的DEMO服务。项目采用多模块架构，实现了 AI 对话、角色扮演游戏、智能客服推荐等多种 AI 应用场景，并通过 MCP 协议实现 AI 模型与业务工具的解耦集成。

## 🏗️ 项目结构

```
zn-ai/
├── core/                       # 公共基础模块
│   └── src/main/java/com/zn/ai/common/
│       ├── response/           # 统一响应体 (Result<T>)
│       └── global/             # 全局异常处理 + 自定义异常
├── ai-core/                    # AI 核心服务 (端口: 8088)
│   └── src/main/java/com/zn/ai/
│       ├── config/             # ChatClient/MVC 配置
│       ├── constants/          # 系统提示词常量
│       ├── controller/         # REST API 控制器
│       ├── entity/             # 实体/VO
│       ├── mapper/             # MyBatis-Plus Mapper
│       ├── repository/         # 自定义 MongoDB 会话存储
│       └── service/            # 业务服务层
├── mcp-server/                 # MCP 工具服务 (端口: 8078)
│   └── src/main/java/com/zn/ai/
│       ├── config/             # MCP 工具注册配置
│       ├── entity/             # 实体/VO/CMD
│       ├── handler/            # MyBatis JSON 类型处理器
│       ├── mapper/             # MyBatis-Plus Mapper
│       ├── service/            # 游戏信息 & 心愿单服务
│       └── tool/               # MCP 工具定义 (@McpTool / @Tool)
├── db/                         # 数据库初始化脚本
│   ├── game_info.sql           # 游戏信息表
│   └── user_game_wish.sql      # 用户心愿单表
└── pom.xml                     # 父 POM (Maven 多模块)
```

## 🚀 技术栈

| 分类 | 技术 | 版本 | 说明 |
|------|------|------|------|
| **基础框架** | Spring Boot | 4.1.0 | 应用框架 |
| **JDK** | Java | 17 | 运行环境 |
| **AI 框架** | Spring AI | 2.0.0 | AI 集成框架 (OpenAI Starter + MCP) |
| **LLM** | DeepSeek V4 Pro | — | 大语言模型 (OpenAI 兼容协议) |
| **ORM** | MyBatis-Plus | 3.5.16 | MySQL 持久层 |
| **文档数据库** | MongoDB | — | AI 会话记忆存储 |
| **关系数据库** | MySQL | — | 业务数据存储 |
| **工具库** | Hutool | 5.8.32 | Java 工具集 |
| **JSON** | Fastjson2 | 2.0.61 | JSON 序列化 |
| **简化代码** | Lombok | 1.18.44 | 注解式代码生成 |
| **构建工具** | Maven | — | 多模块项目管理 |

### 关键依赖说明

- `spring-ai-starter-model-openai` — 通过 OpenAI 兼容协议对接 DeepSeek 模型
- `spring-ai-starter-mcp-client` — MCP 客户端，用于 ai-core 调用 mcp-server 的工具
- `spring-ai-starter-mcp-server-webmvc` — 基于 Spring WebMVC SSE 传输的 MCP 服务端
- `spring-ai-starter-model-chat-memory-repository-mongodb` — MongoDB 会话记忆存储

## 🔗 核心链路

### 1. 整体架构

```
┌──────────────────────────────────────────────────────────┐
│                      客户端 (Browser/App)                  │
└──────┬───────────────────────┬───────────────────────────┘
       │                       │
       ▼                       ▼
┌──────────────┐      ┌────────────────┐
│   ai-core    │      │   ai-core      │
│  ChatController  │      │ServiceChatController│
│  (普通对话)    │      │  (智能客服)      │
│  Port: 8088  │      │  Port: 8088    │
└──────┬───────┘      └───────┬────────┘
       │                      │
       │              ┌───────▼────────┐
       │              │   MCP Client   │
       │              │ (SyncMcpTool)  │
       │              └───────┬────────┘
       │                      │ SSE (Streamable HTTP)
       │              ┌───────▼────────┐
       │              │  mcp-server    │
       │              │  Port: 8078    │
       │              │  ┌──────────┐  │
       │              │  │GameHelp  │  │
       │              │  │  Tool    │  │
       │              │  ├──────────┤  │
       │              │  │MathTool  │  │
       │              │  └──────────┘  │
       │              └───────┬────────┘
       │                      │
       ▼                      ▼
┌──────────┐          ┌──────────┐
│ DeepSeek │          │  MySQL   │
│   API    │          │(业务数据) │
└──────────┘          └──────────┘
       │
       ▼
┌──────────┐
│ MongoDB  │
│(会话记忆) │
└──────────┘
```

### 2. 业务链路详解

#### 链路一：普通 AI 对话 (`/ai/chat`)

```
Client → ChatController.chat()
    → chatHistoryInfoService.save()   // MySQL 记录会话历史
    → chatClient.prompt().call()      // 调用 DeepSeek API
        ← ChatMemory (MongoDB)        // 注入历史会话上下文
    → 返回 AI 回复文本
```

#### 链路二：角色扮演游戏 (`/ai/game-chat`)

```
Client → GameChatController.chat()
    → gameChatClient.prompt().stream()   // 流式调用 DeepSeek API
        ← 系统提示词: PromptConstants.GAME_SYSTEM_PROMPT  // "哄女友大作战"
        ← ChatMemory (MongoDB)           // 多轮对话记忆
    → SSE 流式返回 (Flux<String>)        // text/html;charset=utf-8
```

游戏机制：
- 初始原谅值 20/100，用户输入作为"哄女友"剧情
- 5 级评分系统：激怒(-10) / 生气(-5) / 中立(0) / 开心(+5) / 感动(+10)
- 通关条件：原谅值 ≥ 100；失败条件：原谅值 ≤ 0

#### 链路三：智能客服 + MCP 工具调用 (`/server-ai/chat`)

```
Client → ServiceChatController.chat()
    → serviceChatClient.prompt()
        .tools(syncMcpToolCallbackProvider)  // 注入 MCP 工具
        .call()
    → DeepSeek API 决策是否调用工具
        ├── 需要工具 → MCP Client → mcp-server (8078) SSE
        │   ├── searchGameInfo      → MySQL JSON 查询游戏
        │   ├── addUserGameWish     → MySQL 写入心愿单
        │   └── getUserGameWishList → MySQL 查询心愿单
        └── 无需工具 → 直接返回 AI 回复
    → 返回最终回复文本
```

### 3. MCP 协议工具清单

| 工具名称 | 注解 | 位置 | 功能 |
|----------|------|------|------|
| `searchGameInfo` | `@McpTool` | mcp-server/GameHelpTool | 根据名称/标签/平台查询游戏 |
| `addUserGameWish` | `@McpTool` | mcp-server/GameHelpTool | 添加用户游戏心愿单 |
| `getUserGameWishList` | `@McpTool` | mcp-server/GameHelpTool | 查询用户心愿单列表 |
| `addNumber` | `@Tool` | mcp-server/MathTool | 两数相加 |
| `subtractNumber` | `@Tool` | mcp-server/MathTool | 两数相减 |
| `multiplyNumber` | `@Tool` | mcp-server/MathTool | 两数相乘 |
| `divideNumber` | `@Tool` | mcp-server/MathTool | 两数相除 |

### 4. 数据存储

| 存储 | 用途 | 关键配置 |
|------|------|----------|
| **MySQL** | 业务数据（游戏信息、心愿单、会话历史） | MyBatis-Plus ORM，JSON 字段类型处理器 |
| **MongoDB** | AI 对话会话记忆 | 窗口大小 20 条，TTL 24 小时自动过期 |

## 🎯 功能特性

### 三大 AI 场景

1. **通用 AI 对话** — 基础 ChatClient，带 MongoDB 多轮会话记忆
2. **AI 角色扮演游戏** — "哄女友大作战"，流式输出，情感数值系统
3. **AI 智能客服** — MCP 工具集成，游戏推荐 + 心愿单管理

### 技术亮点

- ✅ **Spring AI 2.0 深度集成**：ChatClient、ChatMemory、MCP 全链路
- ✅ **MCP 协议工具解耦**：AI 模型通过 MCP 协议调用远程工具，ai-core 与 mcp-server 独立部署
- ✅ **双注解工具注册**：`@McpTool`（MCP 专属）+ `@Tool`（Spring AI 通用），按场景灵活选用
- ✅ **SSE 流式传输**：GameChat 使用 `Flux<String>` 实现流式对话输出
- ✅ **JSON 数据库查询**：MySQL JSON_CONTAINS / JSON_OVERLAPS 实现标签和平台的多值查询
- ✅ **统一响应体**：`Result<T>` 封装所有 API 响应，配合全局异常处理
- ✅ **自定 MyBatis 类型处理器**：`ListTypeHandler<T>` 实现 Java List ↔ MySQL JSON 自动映射

## ⚙️ 快速开始

### 环境要求

- JDK 17+
- Maven 3.8+
- MySQL 8.0+
- MongoDB 5.0+
- DeepSeek API Key

### 1. 初始化数据库

```bash
# 导入建表脚本
mysql -u root -p demo < db/game_info.sql
mysql -u root -p demo < db/user_game_wish.sql
```

### 2. 配置

分别修改两个模块的 `application.yml`：

**ai-core** (`ai-core/src/main/resources/application.yml`)：
- DeepSeek API Key、模型配置
- MCP Client 连接地址（指向 mcp-server）
- MySQL / MongoDB 连接信息

**mcp-server** (`mcp-server/src/main/resources/application.yml`)：
- MySQL 连接信息
- MCP Server 暴露配置

### 3. 启动服务

```bash
# 先启动 MCP 工具服务 (端口 8078)
cd mcp-server
mvn spring-boot:run

# 再启动 AI 核心服务 (端口 8088)
cd ai-core
mvn spring-boot:run
```

### 4. 接口测试

```bash
# 普通对话
curl "http://localhost:8088/ai/chat?userId=1&message=你好&chatId=test001"

# 角色扮演游戏 (流式)
curl "http://localhost:8088/ai/game-chat?message=我迟到了&chatId=game001"

# 智能客服 (含 MCP 工具)
curl "http://localhost:8088/server-ai/chat?message=推荐一款动作游戏&chatId=service001"

# 查询会话历史
curl "http://localhost:8088/ai/history/chat?userId=1"
```

## 📐 API 接口一览

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/ai/chat` | 普通 AI 对话 |
| GET | `/ai/game-chat` | 角色扮演游戏（SSE 流式） |
| GET | `/server-ai/chat` | 智能客服（MCP 工具集成） |
| GET | `/ai/history/{type}` | 查询用户会话历史 ID 列表 |
| GET | `/ai/history/detail/{chatId}` | 查询某会话的对话详情 |

## 📦 模块依赖关系

```
zn-ai (父 POM)
├── core          ← 公共基础库 (Result, 异常, 全局处理)
├── mcp-server    ← MCP 工具服务 (依赖 core)
└── ai-core       ← AI 核心服务 (依赖 core, 连接 mcp-server)
```

## 🧑‍💻 开发说明

- **提示词管理**：系统提示词统一在 `PromptConstants` 常量类管理，支持角色扮演和客服两种场景
- **MCP 模式**：当前使用 `sync` 同步模式 + `streamable` 协议 + SSE 传输
- **会话记忆窗口**：设置为 20 条消息，MongoDB TTL 为 24 小时
- **跨域**：`MvcConfiguration` 已配置全路径 CORS 允许
