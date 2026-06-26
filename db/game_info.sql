/*
 Navicat Premium Dump SQL

 Source Server         : 本地MYSQL
 Source Server Type    : MySQL
 Source Server Version : 80046 (8.0.46)
 Source Host           : localhost:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 80046 (8.0.46)
 File Encoding         : 65001

 Date: 26/06/2026 10:50:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for game_info
-- ----------------------------
DROP TABLE IF EXISTS `game_info`;
CREATE TABLE `game_info`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `game_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '游戏名称',
  `cover_image` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '游戏封面图URL或路径',
  `developer` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '游戏开发商',
  `tags` json NULL COMMENT '游戏标签（JSON数组，如[\"动作\",\"冒险\"]）',
  `platforms` json NULL COMMENT '游戏平台（JSON数组，如[\"PC\",\"PS5\"]）',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '游戏简介（长文本）',
  `release_date` date NULL DEFAULT NULL COMMENT '发行时间（日期）',
  `price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '游戏价格（单位：元，两位小数）',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `updated_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '游戏信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of game_info
-- ----------------------------
INSERT INTO `game_info` VALUES (1, '黑神话：悟空', 'https://img.example.com/blackmyth_wukong.jpg', '游戏科学', '[\"动作\", \"角色扮演\", \"神话\"]', '[\"PC\", \"PS5\", \"Xbox Series X\"]', '改编自中国经典小说《西游记》的国产3A动作角色扮演游戏。玩家将扮演\"天命人\"，为探寻昔日传说的真相，踏上一条充满危险与惊奇的西游之路。', '2024-08-20', 268.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (2, 'GTA5', 'https://img.example.com/gta5.jpg', 'Rockstar Games', '[\"动作\", \"开放世界\", \"犯罪\"]', '[\"PC\", \"PS4\", \"PS5\", \"Xbox One\", \"Xbox Series X\"]', '一款围绕犯罪为主题的开放式动作冒险游戏。玩家在洛圣都这座阳光明媚的都市中，扮演三位不同的主角，交织出一段关于金钱、权力与背叛的史诗故事。', '2013-09-17', 119.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (3, '塞尔达传说：旷野之息', 'https://img.example.com/zelda_botw.jpg', '任天堂', '[\"动作\", \"冒险\", \"开放世界\"]', '[\"Nintendo Switch\", \"Wii U\"]', '《塞尔达传说》主系列第19作。故事发生在海拉鲁王国灭亡100年后，主角林克在地下遗迹苏醒，追寻着不可思议的声音开始了冒险之旅。', '2017-03-03', 59.99, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (4, '塞尔达传说：王国之泪', 'https://img.example.com/zelda_totk.jpg', '任天堂', '[\"动作\", \"冒险\", \"开放世界\"]', '[\"Nintendo Switch\"]', '《塞尔达传说：旷野之息》的续作。林克将与塞尔达公主一同踏上新的冒险，探索海拉鲁大陆上空的神秘空岛与地底深处的未知世界。', '2023-05-12', 69.99, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (5, '赛博朋克2077', 'https://img.example.com/cyberpunk2077.jpg', 'CD Projekt RED', '[\"角色扮演\", \"开放世界\", \"科幻\"]', '[\"PC\", \"PS4\", \"PS5\", \"Xbox One\", \"Xbox Series X\"]', '舞台位于大都会夜之城，是一款开放世界动作冒险角色扮演游戏。玩家扮演一位赛博朋克雇佣兵，身陷绝地求生、不成功便成仁的险境。', '2020-12-10', 298.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (6, '荒野大镖客：救赎2', 'https://img.example.com/rdr2.jpg', 'Rockstar Games', '[\"动作\", \"开放世界\", \"西部\"]', '[\"PC\", \"PS4\", \"Xbox One\"]', '美国1899年。亚瑟·摩根和范德林德帮众是一群逃亡在外的亡命之徒，必须在广袤蛮荒的美国腹地上四处劫掠、挣扎求生。', '2018-10-26', 279.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (7, '巫师3：狂猎', 'https://img.example.com/witcher3.jpg', 'CD Projekt RED', '[\"角色扮演\", \"开放世界\", \"奇幻\"]', '[\"PC\", \"PS4\", \"PS5\", \"Xbox One\", \"Xbox Series X\", \"Nintendo Switch\"]', '著名小说改编的开放世界角色扮演游戏。玩家扮演猎魔人杰洛特，在战火纷飞的北方王国中寻找预言之子，踏上一段关于命运与抉择的史诗旅程。', '2015-05-19', 149.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (8, '艾尔登法环', 'https://img.example.com/eldenring.jpg', 'FromSoftware', '[\"动作\", \"角色扮演\", \"黑暗奇幻\"]', '[\"PC\", \"PS4\", \"PS5\", \"Xbox One\", \"Xbox Series X\"]', '由《黑暗之魂》系列创作者宫崎英高与《冰与火之歌》作者乔治·R·R·马丁联手打造。玩家将穿越广阔的奇幻大陆，寻找艾尔登法环的碎片，成为新的艾尔登之王。', '2022-02-25', 298.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (9, '博德之门3', 'https://img.example.com/bg3.jpg', 'Larian Studios', '[\"角色扮演\", \"策略\", \"奇幻\"]', '[\"PC\", \"PS5\", \"Xbox Series X\"]', '基于D&D 5E规则的角色扮演游戏。玩家被一种寄生虫感染，为了寻找解药而踏上旅程，在费伦大陆上展开一段充满选择与后果的史诗冒险。', '2023-08-03', 298.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (10, '战神：诸神黄昏', 'https://img.example.com/gow_ragnarok.jpg', 'Santa Monica Studio', '[\"动作\", \"冒险\", \"北欧神话\"]', '[\"PS4\", \"PS5\"]', '《战神》（2018）的续作。奎托斯与阿特柔斯父子将面对北欧神话中\"诸神黄昏\"的预言，在九界之间展开最后的征途。', '2022-11-09', 468.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (11, '最后生还者 第一部', 'https://img.example.com/tlou1.jpg', 'Naughty Dog', '[\"动作\", \"冒险\", \"生存\", \"丧尸\"]', '[\"PC\", \"PS4\", \"PS5\"]', '在丧尸病毒爆发的末日世界中，走私者乔尔受雇将14岁少女艾莉护送穿越美国。在这场横跨美国的艰难旅程中，两人逐渐建立起超越生死的羁绊。', '2013-06-14', 398.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (12, '对马岛之魂', 'https://img.example.com/ghostoftsushima.jpg', 'Sucker Punch Productions', '[\"动作\", \"冒险\", \"武士\", \"开放世界\"]', '[\"PC\", \"PS4\", \"PS5\"]', '1274年，蒙古帝国入侵日本对马岛。武士境井仁为了保卫家乡，不得不放弃武士道传统，化身为\"战鬼\"，以非常规手段对抗强大的蒙古军队。', '2020-07-17', 398.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (13, '地平线：西之绝境', 'https://img.example.com/horizon_fw.jpg', 'Guerrilla Games', '[\"动作\", \"角色扮演\", \"开放世界\", \"科幻\"]', '[\"PC\", \"PS4\", \"PS5\"]', '《地平线：零之曙光》的续作。女猎人埃洛伊继续在由机械兽统治的末日世界中探索，前往西方禁地寻找拯救地球生态系统的关键。', '2022-02-18', 468.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (14, '死亡搁浅', 'https://img.example.com/deathstranding.jpg', 'Kojima Productions', '[\"动作\", \"冒险\", \"科幻\"]', '[\"PC\", \"PS4\", \"PS5\"]', '在经历\"死亡搁浅\"事件后的破碎世界中，快递员山姆·布里吉斯必须穿越被危险生物\"BT\"侵扰的荒原，重新连接孤立的人类聚居地。', '2019-11-08', 298.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (15, '只狼：影逝二度', 'https://img.example.com/sekiro.jpg', 'FromSoftware', '[\"动作\", \"冒险\", \"武士\", \"忍者\"]', '[\"PC\", \"PS4\", \"Xbox One\"]', '在16世纪末的日本战国时代，一名独臂忍者\"只狼\"为了拯救被掳走的少主，在生死边缘与强大的敌人展开殊死搏斗。', '2019-03-22', 268.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (16, '黑暗之魂3', 'https://img.example.com/darksouls3.jpg', 'FromSoftware', '[\"动作\", \"角色扮演\", \"黑暗奇幻\"]', '[\"PC\", \"PS4\", \"Xbox One\"]', '《黑暗之魂》系列的最终章。在火之时代即将终结的世界中，玩家扮演\"无火的余灰\"，踏上寻找薪王、延续火焰的黑暗征途。', '2016-03-24', 268.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (17, '生化危机4 重制版', 'https://img.example.com/re4.jpg', 'Capcom', '[\"动作\", \"恐怖\", \"生存\"]', '[\"PC\", \"PS4\", \"PS5\", \"Xbox Series X\"]', '经典生存恐怖游戏《生化危机4》的完全重制版。特工里昂·肯尼迪奉命营救被绑架的总统女儿，深入西班牙偏远的村庄，与疯狂的村民和恐怖的寄生虫感染者作战。', '2023-03-24', 348.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (18, '怪物猎人：崛起', 'https://img.example.com/mhrise.jpg', 'Capcom', '[\"动作\", \"角色扮演\", \"合作\"]', '[\"PC\", \"Nintendo Switch\", \"PS4\", \"PS5\", \"Xbox One\", \"Xbox Series X\"]', '在充满日式风情的\"炎火村\"中，猎人利用\"翔虫\"在空中自由翱翔，狩猎各种巨大的怪物，守护村庄的安宁。', '2021-03-26', 308.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (19, '最终幻想7 重制版', 'https://img.example.com/ff7r.jpg', 'Square Enix', '[\"角色扮演\", \"动作\", \"奇幻\"]', '[\"PC\", \"PS4\", \"PS5\"]', '经典RPG《最终幻想7》的完全重制版。雇佣兵克劳德与反神罗组织\"雪崩\"联手，对抗企图榨干星球生命力的巨型企业神罗公司。', '2020-04-10', 446.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (20, '女神异闻录5 皇家版', 'https://img.example.com/p5r.jpg', 'Atlus', '[\"角色扮演\", \"校园\", \"奇幻\"]', '[\"PC\", \"PS4\", \"PS5\", \"Nintendo Switch\", \"Xbox Series X\"]', '白天是普通高中生，夜晚化身为\"怪盗团\"的一员，潜入扭曲的欲望宫殿，盗取邪恶之人的\"秘宝\"，让恶人悔改。', '2019-10-31', 308.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (21, '任天堂明星大乱斗 特别版', 'https://img.example.com/smash.jpg', '任天堂', '[\"格斗\", \"休闲\", \"多人\"]', '[\"Nintendo Switch\"]', '汇聚了任天堂史上最全角色的格斗游戏。从马里奥到林克，从皮卡丘到卡比，众多经典角色在同一舞台上一决高下。', '2018-12-07', 429.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (22, '超级马里奥：奥德赛', 'https://img.example.com/mario_odyssey.jpg', '任天堂', '[\"动作\", \"冒险\", \"平台跳跃\"]', '[\"Nintendo Switch\"]', '马里奥乘坐飞行帽\"奥德赛号\"环游世界，在各个风格迥异的国度中冒险，利用帽子\"凯比\"的神奇能力附身各种物体和敌人。', '2017-10-27', 429.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (23, '集合啦！动物森友会', 'https://img.example.com/animalcrossing.jpg', '任天堂', '[\"模拟\", \"休闲\", \"生活\"]', '[\"Nintendo Switch\"]', '玩家移居到一座无人岛上，与可爱的动物邻居一起生活，钓鱼、捕虫、装修房屋、设计岛屿，享受悠闲的慢生活。', '2020-03-20', 429.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (24, '宝可梦 朱／紫', 'https://img.example.com/pokemon_scvi.jpg', '任天堂', '[\"角色扮演\", \"冒险\", \"收集\"]', '[\"Nintendo Switch\"]', '《宝可梦》系列首次采用完全开放世界设计的作品。玩家在帕底亚地区自由探索，邂逅全新的宝可梦，展开属于自己的冒险故事。', '2022-11-18', 429.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (25, '霍格沃茨之遗', 'https://img.example.com/hogwartslegacy.jpg', 'Avalanche Software', '[\"动作\", \"角色扮演\", \"奇幻\", \"开放世界\"]', '[\"PC\", \"PS4\", \"PS5\", \"Xbox One\", \"Xbox Series X\", \"Nintendo Switch\"]', '背景设定在《哈利·波特》小说之前。玩家扮演一名霍格沃茨的五年级新生，掌握强大的古老魔法，揭开隐藏在魔法世界背后的秘密。', '2023-02-10', 298.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (26, '星空', 'https://img.example.com/starfield.jpg', 'Bethesda Game Studios', '[\"角色扮演\", \"科幻\", \"开放世界\"]', '[\"PC\", \"Xbox Series X\"]', 'Bethesda 25年来首个全新宇宙。玩家在广袤的星空中自由探索，扮演一名太空探险家，在银河系的各个角落书写属于自己的传奇。', '2023-09-06', 298.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (27, '暗黑破坏神4', 'https://img.example.com/diablo4.jpg', 'Blizzard Entertainment', '[\"动作\", \"角色扮演\", \"暗黑奇幻\"]', '[\"PC\", \"PS4\", \"PS5\", \"Xbox One\", \"Xbox Series X\"]', '暴雪娱乐经典ARPG系列最新作。在黑暗的庇护之地，玩家选择五大职业之一，对抗地狱魔王莉莉丝及其恶魔大军。', '2023-06-06', 429.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (28, '守望先锋2', 'https://img.example.com/overwatch2.jpg', 'Blizzard Entertainment', '[\"射击\", \"竞技\", \"多人\"]', '[\"PC\", \"PS4\", \"PS5\", \"Xbox One\", \"Xbox Series X\", \"Nintendo Switch\"]', '免费游玩的团队射击游戏。玩家从众多英雄中选择角色，在5v5的快节奏对战中展开激烈交锋，争夺胜利。', '2022-10-04', 0.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (29, '我的世界', 'https://img.example.com/minecraft.jpg', 'Mojang Studios', '[\"沙盒\", \"创造\", \"冒险\"]', '[\"PC\", \"PS4\", \"PS5\", \"Xbox One\", \"Xbox Series X\", \"Nintendo Switch\", \"手机\"]', '风靡全球的沙盒游戏。玩家在一个由方块构成的无限世界中自由探索、建造、合成与生存，发挥无限的创造力。', '2011-11-18', 165.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (30, '泰拉瑞亚', 'https://img.example.com/terraria.jpg', 'Re-Logic', '[\"沙盒\", \"创造\", \"冒险\", \"动作\"]', '[\"PC\", \"PS4\", \"Xbox One\", \"Nintendo Switch\", \"手机\"]', '2D沙盒冒险游戏。玩家在随机生成的世界中挖掘、建造、战斗，击败各种Boss，探索地底深处的秘密。', '2011-05-16', 36.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (31, '星露谷物语', 'https://img.example.com/stardewvalley.jpg', 'ConcernedApe', '[\"模拟\", \"农场\", \"休闲\"]', '[\"PC\", \"PS4\", \"Xbox One\", \"Nintendo Switch\", \"手机\"]', '继承了爷爷留下的农场，玩家从城市搬到了星露谷，开始了一段远离喧嚣的田园生活——种田、养动物、采矿、钓鱼、恋爱。', '2016-02-26', 48.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (32, '哈迪斯', 'https://img.example.com/hades.jpg', 'Supergiant Games', '[\"动作\", \"Roguelike\", \"希腊神话\"]', '[\"PC\", \"PS4\", \"PS5\", \"Xbox One\", \"Xbox Series X\", \"Nintendo Switch\"]', '希腊神话背景的Roguelike动作游戏。冥王之子扎格列欧斯试图逃离冥界，在一次次死亡与重生中变得更强，最终揭开家族的秘密。', '2020-09-17', 80.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (33, '空洞骑士', 'https://img.example.com/hollowknight.jpg', 'Team Cherry', '[\"动作\", \"冒险\", \"平台跳跃\", \"类银河战士恶魔城\"]', '[\"PC\", \"PS4\", \"Xbox One\", \"Nintendo Switch\"]', '在破败的昆虫王国\"圣巢\"中，一位沉默的骑士探索地下遗迹，与强大的敌人战斗，揭开王国衰败的真相。', '2017-02-24', 48.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (34, '茶杯头', 'https://img.example.com/cuphead.jpg', 'StudioMDHR', '[\"动作\", \"射击\", \"平台跳跃\", \"复古\"]', '[\"PC\", \"PS4\", \"Xbox One\", \"Nintendo Switch\"]', '以1930年代老动画风格为特色的横版卷轴射击游戏。茶杯头兄弟俩与魔鬼打赌输了，必须在三天内讨回所有欠债者的灵魂。', '2017-09-29', 68.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (35, '蔚蓝', 'https://img.example.com/celeste.jpg', 'Extremely OK Games', '[\"平台跳跃\", \"冒险\", \"像素\"]', '[\"PC\", \"PS4\", \"Xbox One\", \"Nintendo Switch\"]', '女孩玛德琳攀登险峻的蔚蓝山，在克服重重难关的过程中，直面内心的恐惧与焦虑，完成一场自我救赎的旅程。', '2018-01-25', 68.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (36, '奥日与黑暗森林', 'https://img.example.com/ori.jpg', 'Moon Studios', '[\"动作\", \"冒险\", \"平台跳跃\", \"类银河战士恶魔城\"]', '[\"PC\", \"Xbox One\", \"Nintendo Switch\"]', '在美丽的精灵森林中，小精灵奥日为了拯救濒临死亡的森林，踏上了一段充满危险与感动的冒险之旅。', '2015-03-11', 68.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (37, '传送门2', 'https://img.example.com/portal2.jpg', 'Valve', '[\"解谜\", \"第一人称\", \"科幻\"]', '[\"PC\", \"PS4\", \"Xbox One\"]', '经典第一人称解谜游戏。玩家利用\"传送门枪\"在墙壁上开启蓝色和橙色的传送门，通过精巧的空间逻辑破解重重谜题。', '2011-04-19', 42.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (38, '半条命2', 'https://img.example.com/halflife2.jpg', 'Valve', '[\"射击\", \"第一人称\", \"科幻\"]', '[\"PC\", \"Xbox\"]', '划时代的第一人称射击游戏。物理学家戈登·弗里曼在联合军统治的反乌托邦世界中，领导人类反抗外星侵略者。', '2004-11-16', 42.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (39, '生化奇兵：无限', 'https://img.example.com/bioshock_infinite.jpg', 'Irrational Games', '[\"射击\", \"第一人称\", \"科幻\", \"蒸汽朋克\"]', '[\"PC\", \"PS4\", \"Xbox One\"]', '在漂浮于云端的哥伦比亚空中之城，私家侦探布克·德威特受雇解救神秘女孩伊丽莎白，却揭开了一段关于平行宇宙的惊天秘密。', '2013-03-26', 99.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (40, '耻辱2', 'https://img.example.com/dishonored2.jpg', 'Arkane Studios', '[\"动作\", \"潜行\", \"第一人称\", \"蒸汽朋克\"]', '[\"PC\", \"PS4\", \"Xbox One\"]', '在充满阴谋与超自然力量的蒸汽朋克世界中，玩家选择扮演艾米丽或科尔沃，利用独特的技能与能力，夺回被篡夺的王位。', '2016-11-11', 129.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (41, '掠食', 'https://img.example.com/prey.jpg', 'Arkane Studios', '[\"射击\", \"第一人称\", \"科幻\", \"恐怖\"]', '[\"PC\", \"PS4\", \"Xbox One\"]', '在太空站\"塔洛斯一号\"上，玩家扮演一名科学家，被卷入外星生物\"风暴异魔\"的袭击中，必须利用一切资源生存下来。', '2017-05-05', 129.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (42, '控制', 'https://img.example.com/control.jpg', 'Remedy Entertainment', '[\"动作\", \"冒险\", \"超自然\"]', '[\"PC\", \"PS4\", \"PS5\", \"Xbox One\", \"Xbox Series X\"]', '女主角杰西·法登来到纽约的秘密政府机构\"联邦控制局\"，在充满超自然现象的太古屋中寻找失踪的弟弟，并逐步掌握局长的力量。', '2019-08-27', 198.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (43, '心灵杀手2', 'https://img.example.com/alanwake2.jpg', 'Remedy Entertainment', '[\"动作\", \"恐怖\", \"生存\"]', '[\"PC\", \"PS5\", \"Xbox Series X\"]', '畅销书作家艾伦·韦克被困在黑暗之地13年后，与FBI探员萨加·安德森联手，在现实与噩梦交错的世界中对抗黑暗势力。', '2023-10-27', 200.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (44, '死亡空间 重制版', 'https://img.example.com/deadspace.jpg', 'EA Motive', '[\"动作\", \"恐怖\", \"科幻\", \"生存\"]', '[\"PC\", \"PS5\", \"Xbox Series X\"]', '经典科幻恐怖游戏《死亡空间》的完全重制版。工程师艾萨克·克拉克登上采矿飞船\"石村号\"，在狭小的太空船中与恐怖的外星生物\"尸变体\"搏斗。', '2023-01-27', 248.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (45, '木卫四协议', 'https://img.example.com/callistoprotocol.jpg', 'Striking Distance Studios', '[\"动作\", \"恐怖\", \"科幻\", \"生存\"]', '[\"PC\", \"PS4\", \"PS5\", \"Xbox One\", \"Xbox Series X\"]', '在木星死寂卫星\"木卫四\"上的黑铁监狱中，囚犯雅各布·李必须面对变异为恐怖怪物的囚犯们，在绝境中寻求生存。', '2022-12-02', 298.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (46, '原子之心', 'https://img.example.com/atomicheart.jpg', 'Mundfish', '[\"射击\", \"第一人称\", \"科幻\", \"苏联\"]', '[\"PC\", \"PS4\", \"PS5\", \"Xbox One\", \"Xbox Series X\"]', '在架空的苏联世界，机器人起义爆发。特工P-3被派往神秘的\"3826号设施\"，阻止失控的机器人系统毁灭人类。', '2023-02-21', 199.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (47, '卧龙：苍天陨落', 'https://img.example.com/wolong.jpg', 'Team NINJA', '[\"动作\", \"角色扮演\", \"历史\", \"奇幻\"]', '[\"PC\", \"PS4\", \"PS5\", \"Xbox One\", \"Xbox Series X\"]', '以东汉末年为背景的黑暗奇幻动作游戏。玩家扮演一名无名士兵，在妖魔横行的乱世中，与三国名将们并肩作战，对抗邪魔。', '2023-03-03', 298.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (48, '匹诺曹的谎言', 'https://img.example.com/liesofp.jpg', 'Neowiz Games', '[\"动作\", \"角色扮演\", \"黑暗奇幻\"]', '[\"PC\", \"PS4\", \"PS5\", \"Xbox One\", \"Xbox Series X\"]', '改编自经典童话《木偶奇遇记》的暗黑魂系动作游戏。人偶匹诺曹在充满疯狂与恐惧的克拉特城中，寻找成为真正人类的途径。', '2023-09-19', 298.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (49, '遗迹2', 'https://img.example.com/remnant2.jpg', 'Gunfire Games', '[\"射击\", \"动作\", \"角色扮演\", \"科幻\"]', '[\"PC\", \"PS5\", \"Xbox Series X\"]', '《遗迹：灰烬重生》的续作。人类幸存者穿梭于多个平行世界，对抗来自异次元的邪恶势力，阻止世界被\"根蔓\"彻底吞噬。', '2023-07-25', 198.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');
INSERT INTO `game_info` VALUES (50, '装甲核心6', 'https://img.example.com/armoredcore6.jpg', 'FromSoftware', '[\"动作\", \"射击\", \"机甲\", \"科幻\"]', '[\"PC\", \"PS4\", \"PS5\", \"Xbox One\", \"Xbox Series X\"]', 'FromSoftware经典机甲动作系列最新作。玩家驾驶高度自定义的机甲\"AC\"，在战火纷飞的未来世界中执行各种高危任务。', '2023-08-25', 298.00, '2026-06-25 16:56:29', '2026-06-25 16:56:29');

SET FOREIGN_KEY_CHECKS = 1;
