/*
 Navicat Premium Data Transfer

 Source Server         : break-tx
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : 106.53.155.82:3306
 Source Schema         : breakblog

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 25/08/2021 02:34:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `del_flag` bit(1) NULL DEFAULT NULL COMMENT '删除标识',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `blog_title` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '博客标题',
  `blog_subtitle` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '博客副标题',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字',
  `email` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `about` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '关于我',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, b'0', '2019-05-18 00:00:00', NULL, 'http://www.twhuang.me:9001/img/logo.e9b9f2c6.png', 'admin', 'breakblog', 'BreakBlog', 'You still have lots more to work on!', 'Break', 'tw.huang@foxmail.com', '15219652018', '<p>Hello World!</p>\r\n');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `del_flag` bit(1) NULL DEFAULT NULL COMMENT '删除标识',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, b'0', '2019-05-18 00:00:00', NULL, 'Default');
INSERT INTO `category` VALUES (2, b'0', '2019-05-18 00:00:00', NULL, 'Python');
INSERT INTO `category` VALUES (3, b'0', '2019-05-18 00:00:00', NULL, 'Linux');
INSERT INTO `category` VALUES (4, b'0', '2019-05-18 00:00:00', NULL, '随笔');
INSERT INTO `category` VALUES (5, b'0', '2019-05-18 00:00:00', NULL, '生活');
INSERT INTO `category` VALUES (6, b'0', '2019-05-18 00:00:00', NULL, '爬虫');
INSERT INTO `category` VALUES (7, b'0', '2019-05-18 00:00:00', NULL, '看书');
INSERT INTO `category` VALUES (8, b'0', '2019-05-18 00:00:00', NULL, 'Java');
INSERT INTO `category` VALUES (9, b'0', '2021-04-23 01:40:00', NULL, 'test');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `del_flag` bit(1) NULL DEFAULT NULL COMMENT '删除标识',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论人',
  `email` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件地址',
  `site` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网站地址',
  `body` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评论内容',
  `reviewed` tinyint(1) NULL DEFAULT 0 COMMENT '已读',
  `timestamp` datetime(0) NULL DEFAULT NULL COMMENT '评论时间',
  `replied_id` int(11) NULL DEFAULT NULL COMMENT '回复评论ID',
  `post_id` int(11) NULL DEFAULT NULL COMMENT '文章ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `replied_id`(`replied_id`) USING BTREE,
  INDEX `post_id`(`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, b'0', '2019-05-18 00:00:00', '2021-04-26 08:45:35', 'tw.huang', 'tw.huang@foxmail.com', 'http://www.breakblog.me/', '测试评论系统~', 1, '2019-11-20 14:20:19', NULL, 1);
INSERT INTO `comment` VALUES (2, b'0', '2019-05-18 00:00:00', '2021-04-23 06:12:22', 'vv', 'vv@vv.com', '', 'vv', 1, '2020-03-05 19:58:00', NULL, 15);
INSERT INTO `comment` VALUES (4, b'0', '2019-05-18 00:00:00', '2021-03-10 09:51:46', 'test11', 'tw.huang@foxmail.com', 'http://www.breakblog.me/', '测试评论系统~', 1, '2020-03-05 19:58:00', NULL, 15);
INSERT INTO `comment` VALUES (5, b'0', '2019-05-18 00:00:00', NULL, 'test11', 'tw.huang@foxmail.com', 'http://www.breakblog.me/', '测试评论系统~', 1, '2020-03-05 19:58:00', 4, 15);
INSERT INTO `comment` VALUES (6, b'0', '2019-05-18 00:00:00', NULL, 'test11', 'tw.huang@foxmail.com', 'http://www.breakblog.me/', '测试评论系统~', 1, '2020-03-05 19:58:00', 4, 15);
INSERT INTO `comment` VALUES (13, b'0', '2019-05-18 00:00:00', NULL, 'twhuang', 'tw.huang@foxmail.com', NULL, 'test', 1, '2020-03-24 22:14:56', NULL, 15);
INSERT INTO `comment` VALUES (14, b'0', '2019-05-18 00:00:00', NULL, 'twhuang', 'tw.huang@foxmail.com', NULL, 'test', 1, '2020-03-24 22:14:58', NULL, 15);
INSERT INTO `comment` VALUES (15, b'0', '2019-05-18 00:00:00', NULL, 'twhuang', 'tw.huang@foxmail.com', NULL, 'test', 1, '2020-03-24 22:15:01', NULL, 15);
INSERT INTO `comment` VALUES (16, b'0', '2019-05-18 00:00:00', NULL, 'twhuang', 'tw.huang@foxmail.com', NULL, 'test', 1, '2020-03-24 22:15:04', NULL, 15);
INSERT INTO `comment` VALUES (17, b'0', '2019-05-18 00:00:00', NULL, 'twhuang', 'tw.huang@foxmail.com', NULL, 'test', 1, '2020-03-24 22:15:08', NULL, 15);
INSERT INTO `comment` VALUES (18, b'0', '2019-05-18 00:00:00', NULL, 'twhuang', 'tw.huang@foxmail.com', NULL, '1', 1, '2020-03-24 22:15:10', NULL, 15);
INSERT INTO `comment` VALUES (19, b'0', '2019-05-18 00:00:00', NULL, 'twhuang', 'tw.huang@foxmail.com', NULL, '2', 1, '2020-03-24 22:15:12', NULL, 15);
INSERT INTO `comment` VALUES (20, b'0', '2019-05-18 00:00:00', NULL, 'twhuang', 'tw.huang@foxmail.com', NULL, '3', 1, '2020-03-24 22:15:14', NULL, 15);
INSERT INTO `comment` VALUES (21, b'0', '2019-05-18 00:00:00', NULL, 'twhuang', 'tw.huang@foxmail.com', NULL, '4', 1, '2020-03-24 22:15:16', NULL, 15);
INSERT INTO `comment` VALUES (22, b'0', '2019-05-18 00:00:00', NULL, 'twhuang', 'tw.huang@foxmail.com', NULL, '5', 1, '2020-03-24 22:15:19', NULL, 15);
INSERT INTO `comment` VALUES (23, b'0', '2019-05-18 00:00:00', NULL, 'twhuang', 'tw.huang@foxmail.com', NULL, '6', 1, '2020-03-24 22:15:22', NULL, 15);
INSERT INTO `comment` VALUES (24, b'0', '2019-05-18 00:00:00', NULL, 'twhuang', 'tw.huang@foxmail.com', NULL, '7', 1, '2020-03-24 22:15:25', NULL, 15);
INSERT INTO `comment` VALUES (25, b'0', '2019-05-18 00:00:00', NULL, 'twhuang', 'tw.huang@foxmail.com', NULL, '8', 1, '2020-03-24 22:15:27', NULL, 15);
INSERT INTO `comment` VALUES (26, b'0', '2019-05-18 00:00:00', NULL, 'twhuang', 'tw.huang@foxmail.com', NULL, '9', 1, '2020-03-24 22:15:30', NULL, 15);
INSERT INTO `comment` VALUES (27, b'0', '2019-05-18 00:00:00', NULL, 'twhuang', 'tw.huang@foxmail.com', NULL, '10', 1, '2020-03-24 22:15:34', NULL, 15);
INSERT INTO `comment` VALUES (28, b'0', '2019-05-18 00:00:00', NULL, 'twhuang', 'tw.huang@foxmail.com', NULL, '11', 1, '2020-03-24 22:15:38', NULL, 15);
INSERT INTO `comment` VALUES (29, b'0', '2019-05-18 00:00:00', NULL, 'twhuang', 'tw.huang@foxmail.com', NULL, '12', 1, '2020-03-24 22:15:40', NULL, 15);
INSERT INTO `comment` VALUES (30, b'0', '2019-05-18 00:00:00', NULL, 'twhuang', 'tw.huang@foxmail.com', NULL, '13', 1, '2020-03-24 22:15:43', NULL, 15);
INSERT INTO `comment` VALUES (31, b'0', '2019-05-18 00:00:00', NULL, 'twhuang', 'tw.huang@foxmail.com', NULL, '14', 1, '2020-03-24 22:15:46', NULL, 15);
INSERT INTO `comment` VALUES (32, b'0', '2019-05-18 00:00:00', NULL, 'twhuang', 'tw.huang@foxmail.com', NULL, '15', 1, '2020-03-24 22:15:49', NULL, 15);
INSERT INTO `comment` VALUES (33, b'0', '2019-05-18 00:00:00', NULL, '黄天文', 'tw.huang@foxmail.com', 'http://www.baidu.com', '真逗啊。嘻嘻嘻嘻嘻', 0, '2020-03-26 07:39:35', NULL, 15);
INSERT INTO `comment` VALUES (34, b'0', '2019-05-18 00:00:00', NULL, '黄天文', 'tw.huang@foxmail.com', 'http://www.baidu.com', '是是是', 0, '2020-03-26 07:53:14', NULL, 12);
INSERT INTO `comment` VALUES (35, b'0', '2019-05-18 00:00:00', NULL, 'twhuang', 'tw.huang@foxmail.com', NULL, '试试上', 1, '2020-03-26 07:53:34', NULL, 12);
INSERT INTO `comment` VALUES (36, b'0', '2019-05-18 00:00:00', NULL, 'twhuang', 'tw.huang@foxmail.com', NULL, '反反复复方法', 1, '2020-03-26 07:53:53', 34, 12);
INSERT INTO `comment` VALUES (37, b'0', '2019-05-18 00:00:00', NULL, 'twhuang', 'tw.huang@foxmail.com', NULL, '哈哈哈', 1, '2020-03-26 07:54:11', 35, 12);
INSERT INTO `comment` VALUES (38, b'0', '2021-08-24 18:32:31', NULL, 'demo', 'wybhdxfx@yeah.net', '', 'demo', 0, '2021-08-24 18:32:31', 33, 15);

-- ----------------------------
-- Table structure for file_data
-- ----------------------------
DROP TABLE IF EXISTS `file_data`;
CREATE TABLE `file_data`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `del_flag` bit(1) NULL DEFAULT NULL COMMENT '删除标识',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原文件名',
  `uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'UUID',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件类型',
  `size` bigint(10) NULL DEFAULT NULL COMMENT '文件大小',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件链接',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_data
-- ----------------------------
INSERT INTO `file_data` VALUES (1, b'0', '2021-03-18 12:12:16', NULL, '电视剧.jpg', 'd3cae5bced7247f2bda1ee212adabad6', 'image/jpeg', 1208269, 'http://www.twhuang.me:9010/image/d3cae5bced7247f2bda1ee212adabad6.jpg');
INSERT INTO `file_data` VALUES (2, b'0', '2021-03-18 12:16:03', NULL, '老家奶奶.jpg', 'dcf21cab06214f93abecee12389344ef', 'image/jpeg', 2206255, 'http://www.twhuang.me:9010/image/dcf21cab06214f93abecee12389344ef.jpg');
INSERT INTO `file_data` VALUES (3, b'0', '2021-03-18 12:18:40', NULL, 'cisco局域网.jpg', '130c3a3087734f618d6ace04d1859a0b', 'image/jpeg', 316327, 'http://www.twhuang.me:9010/image/130c3a3087734f618d6ace04d1859a0b.jpg');
INSERT INTO `file_data` VALUES (4, b'0', '2021-03-22 09:56:47', NULL, '1.jpg', 'a9eeee825d37441abf3e0f31ca44d30f', 'image/jpeg', 23210, 'http://www.twhuang.me:9010/image/a9eeee825d37441abf3e0f31ca44d30f.jpg');
INSERT INTO `file_data` VALUES (5, b'0', '2021-04-23 08:28:40', NULL, 'avatar.jpeg', '8242b666b01d4e3badb97bf943860c0d', 'image/jpeg', 48010, 'http://www.twhuang.me:9010/image/8242b666b01d4e3badb97bf943860c0d.jpeg');
INSERT INTO `file_data` VALUES (6, b'0', '2021-04-23 08:50:07', NULL, 'avatar.jpeg', 'cd1555ae7e534379a4bd7beadec059d3', 'image/jpeg', 48010, 'http://www.twhuang.me:9010/image/cd1555ae7e534379a4bd7beadec059d3.jpeg');
INSERT INTO `file_data` VALUES (7, b'0', '2021-06-02 06:22:42', NULL, 'avatar.jpeg', '19bc2636e3874d4e8c3d5147949eaa69', 'image/jpeg', 48010, 'http://www.twhuang.me:9010/image/19bc2636e3874d4e8c3d5147949eaa69.jpeg');
INSERT INTO `file_data` VALUES (8, b'0', '2021-06-02 06:27:14', NULL, 'QQ截图20210410113024.png', '3cc1f6a6f4d74e5fa2edcf052f98c00e', 'image/png', 71506, 'http://www.twhuang.me:9010/demo/3cc1f6a6f4d74e5fa2edcf052f98c00e.png');
INSERT INTO `file_data` VALUES (9, b'0', '2021-06-02 06:34:30', NULL, 'QQ截图20210522164154.png', '5d0fe9142578400a94950797a4bd75e0', 'image/png', 72611, 'http://www.twhuang.me:9010/demo/5d0fe9142578400a94950797a4bd75e0.png');
INSERT INTO `file_data` VALUES (10, b'0', '2021-06-02 06:36:23', NULL, 'QQ截图20210410113024.png', 'f7330d5bec3d440f9933e586c301716e', 'image/png', 71506, 'http://www.twhuang.me:9010/demo/f7330d5bec3d440f9933e586c301716e.png');

-- ----------------------------
-- Table structure for link
-- ----------------------------
DROP TABLE IF EXISTS `link`;
CREATE TABLE `link`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `del_flag` bit(1) NULL DEFAULT NULL COMMENT '删除标识',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '友链名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址url',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of link
-- ----------------------------
INSERT INTO `link` VALUES (1, b'0', '2019-05-18 00:00:00', '2021-04-22 09:06:02', 'GitHub', 'https://github.com/tw-huang');
INSERT INTO `link` VALUES (2, b'0', '2019-05-18 00:00:00', NULL, 'OsChina', 'https://my.oschina.net/iswybhdxfx');
INSERT INTO `link` VALUES (3, b'1', '2021-04-22 09:08:45', '2021-04-22 09:09:05', 'hhhsss', 'http://localhost:9001/#/links');

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `del_flag` bit(1) NULL DEFAULT NULL COMMENT '删除标识',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `title` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `subtitle` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '副标题',
  `body` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `timestamp` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `can_comment` tinyint(1) NULL DEFAULT 1 COMMENT '开放评论',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '分类ID',
  `page_view` int(11) NULL DEFAULT 0 COMMENT '浏览量',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `category_id`(`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES (1, b'0', '2019-05-18 00:00:00', NULL, 'breakblog.me 博客初版v1.0上线啦！', 'Hello，折腾了好一段日子，本博客breakblog.me暂定稿上线了，以后再慢慢修改升级！！！', '<p><strong><code>Hello World !</code></strong></p>\n\n', '2019-11-19 14:44:59', 1, 1, 120);
INSERT INTO `post` VALUES (2, b'0', '2019-05-18 00:00:00', NULL, 'Git 的基本知识和使用方法', 'Git作为程序员必备工具之一，必须学会，记录一下。', '<h2>安装与配置</h2>\n\n\n\n<p>安装后的基本配置</p>\n\n\n\n<p><code>shell git config --global user.name &quot;git_name&quot; git config --global user.email &quot;email@example.com&quot; </code></p>\n\n\n\n<h3>版本库</h3>\n\n\n\n<p>初始化git仓库，使用<code>git init</code>命令<br />\n\n添加文件到git仓库，创建快照：</p>\n\n\n\n<ol>\n\n	<li><code>git add &lt;file&gt;</code>，注意，可反复多次使用，添加多个文件</li>\n\n	<li><code>git commit -m &lt;message&gt;</code>，完成</li>\n\n</ol>\n\n\n\n<h2>时光穿梭机</h2>\n\n\n\n<ul>\n\n	<li>随时了解工作区的状态，使用<code>git status</code>命令</li>\n\n	<li>用<code>git diff</code>查看文件的修改</li>\n\n</ul>\n\n\n\n<h3>版本回退</h3>\n\n\n\n<ul>\n\n	<li>HEAD指向的版本就是当前的版本，使用<code>git reset --hard commit_id</code> 切换版本</li>\n\n	<li>回到之前的版本，使用<code>git log</code> 查看已commit提交的历史版本，按 q 退出</li>\n\n	<li>回来现在的版本，使用<code>git reflog</code>查看所有commit命令历史版本</li>\n\n</ul>\n\n\n\n<h3>撤销修改</h3>\n\n\n\n<ol>\n\n	<li>没有<code>git add</code>，使用<code>git checkout -- file</code></li>\n\n	<li>已经<code>git add</code>，先<code>git reset HEAD &lt;file&gt;</code>回到 1.再按 1. 操作</li>\n\n	<li>已经<code>git commit</code>,请用<code>git reset</code>回退版本</li>\n\n</ol>\n\n\n\n<h3>删除文件</h3>\n\n\n\n<p>删除文件也是一个修改的操作，先删除文件，然后再使用<code>git rm &lt;file&gt;</code>,然后再<code>git commit</code></p>\n\n\n\n<h2>远程仓库</h2>\n\n\n\n<ul>\n\n	<li>使用<code>ssh-keygen -t rsa -C &quot;youremail@example.com&quot;</code>创建SSH Key</li>\n\n	<li>默认在用户家目录下创建一个隐藏的.ssh目录(即<code>~/.ssh</code>)</li>\n\n	<li><code>~/.ssh</code>目录下产生私钥 <em>id_rsa</em> 文件和 <em>id_rsa.pub</em> 公钥文件</li>\n\n</ul>\n\n\n\n<h3>创建关联远程库</h3>\n\n\n\n<ol>\n\n	<li>先在github或者gitee上创建一个空仓库</li>\n\n	<li>在本地要关联的仓库上运行命令<code>git remote add origin git@&lt;server-name:path&gt;/&lt;repo_name&gt;.git</code></li>\n\n	<li>使用<code>git push -u ogigin master</code>，推送本地仓库所有内容推送到远程库上</li>\n\n</ol>\n\n\n\n<h3>从远程克隆库</h3>\n\n\n\n<p>使用<code>git clone git@&lt;server-name:path&gt;/&lt;repo_name&gt;.git</code> <strong>ps:也可以使用https协议，但是比较慢</strong></p>\n\n\n\n<h2>分支管理</h2>\n\n\n\n<h3>创建与合并分支</h3>\n\n\n\n<ul>\n\n	<li>查看分支<code>git branch</code></li>\n\n	<li>创建分支<code>git branch &lt;name&gt;</code></li>\n\n	<li>切换分支<code>git checkout &lt;name&gt;</code>或者新版命令<code>git switch &lt;name&gt;</code></li>\n\n	<li>创建+切换分支<code>git checkout -b &lt;name&gt;</code>或者新版命令<code>git switch -c &lt;name&gt;</code></li>\n\n	<li>合并某个分支到当前分支<code>git merge &lt;name&gt;</code> <strong>使用<code>Fast forward</code>模式</strong></li>\n\n	<li>删除分支<code>git branch -d &lt;name&gt;</code></li>\n\n</ul>\n\n\n\n<h3>解决冲突</h3>\n\n\n\n<ul>\n\n	<li>当git无法自动合并分支时，必须先手动解决冲突，然后再提交，合并完成。</li>\n\n	<li>使用<code>git log --graph --pretty=oneline --abbrev-commit</code> 查看合并分支图</li>\n\n</ul>\n\n\n\n<h3>分支管理策略</h3>\n\n\n\n<ul>\n\n	<li>\n\n	<p>实际工作开发分支图：![alt 开发图](&quot;https://static.liaoxuefeng.com/files/attachments/919023260793600/0&quot;)</p>\n\n	</li>\n\n	<li>\n\n	<p>使用<code>git merge --no-ff -m &quot;&lt;message&gt;&quot; dev</code>普通模式合并，合并后有历史分支，能看到曾经做过合并</p>\n\n	</li>\n\n</ul>\n\n\n\n<h3>Bug分支</h3>\n\n\n\n<ul>\n\n	<li>使用<code>git stash</code>保存工作现场，然后再去修复bug，修复后，再使用<code>git stash pop</code>回到工作现场</li>\n\n	<li>使用<code>git cherry-pick &lt;commit&gt;</code>命令，把bug提交的修改&lsquo;复制&rsquo;到当前分支，避免重复劳动</li>\n\n</ul>\n\n\n\n<h3>Feature分支</h3>\n\n\n\n<ul>\n\n	<li>开发一个新feature，最好建一个分支</li>\n\n	<li>要丢弃一个没有被合并过的分支，使用<code>git branch -D &lt;name&gt;</code>强制删除</li>\n\n</ul>\n\n\n\n<h3>多人协作</h3>\n\n\n\n<ul>\n\n	<li>查看远程库信息，使用<code>git remote -v</code></li>\n\n	<li>本地新建的分支如果不推送到远程，对其他人就是不可见的</li>\n\n	<li>从本地推送分支，使用<code>git push origin branch-name</code>，如果推送失败，先用<code>git pull</code>抓取远程的新提交</li>\n\n	<li>在本地创建和远程分支对应的分支，使用<code>git checkout -b branch-name origin/branch-name</code>，本地和远程分支的名称最好一致</li>\n\n	<li>建立本地分支和远程分支的关联，使用<code>git branch --set-upstream branch-name origin/branch-name</code></li>\n\n	<li>从远程抓取分支，使用<code>g</code>it pull`，如果有冲突，要先处理冲突</li>\n\n	<li>使用<code>git rebase</code>把本地未push的分叉提交历史整理成直线</li>\n\n</ul>\n\n\n\n<h2>标签管理</h2>\n\n\n\n<h3>创建标签</h3>\n\n\n\n<ul>\n\n	<li>命令<code>git tag &lt;tagname&gt;</code>创建一个标签，默认为<code>HEAD</code>，也可以指定一个<code>commit &lt;id&gt;</code></li>\n\n	<li>命令<code>git tag -a &lt;tagname&gt; - m &quot;text...&quot;</code>指定标签信息</li>\n\n	<li>命令<code>git show &lt;tagname&gt;</code>查看标签的说明文字</li>\n\n	<li>命令<code>git tag</code>查看所有标签</li>\n\n</ul>\n\n\n\n<h3>操作标签</h3>\n\n\n\n<ul>\n\n	<li>删除标签<code>git tag -d &lt;tagname&gt;</code></li>\n\n	<li>推送本地某个标签到远程<code>git push origin &lt;tagname&gt;</code></li>\n\n	<li>推送全部尚未推送到远程的本地标签<code>git push origin --tags</code></li>\n\n	<li>删除一个远程标签<code>git push origin :refs/tags/&lt;tagname&gt;</code></li>\n\n</ul>\n\n', '2019-11-19 14:48:26', 1, 1, 89);
INSERT INTO `post` VALUES (3, b'0', '2019-05-18 00:00:00', NULL, 'pip 升级所有的包', 'pip升级某个包：pip install --upgrade package ，但是升级所有的包还真不会，不会就点进来吧~', '<p>代码：<code>pip freeze --local | grep -v &#39;^-e&#39; | cut -d = -f 1 &nbsp;| xargs -n1 pip install -U</code></p>\n\n', '2019-11-19 14:56:25', 1, 2, 51);
INSERT INTO `post` VALUES (4, b'0', '2019-05-18 00:00:00', NULL, 'gunicorn 的基本命令与配置', 'Gunicorn是一个unix上被广泛使用的高性能的Python WSGI UNIX HTTP Server，和大多数的web框架兼容，并具有实现简单，轻量级，高性能等特点。\n\n本站也在使用gunicorn~', '<h2>基本命令</h2>\n\n\n\n<h3>1、开启</h3>\n\n\n\n<p>在项目的根目录下，输入下方的代码。</p>\n\n\n\n<p>第一个app指的是app.py文件，第二个指的是flask应用的名字。<br />\n\n<code>gunicorn -w 4 -b 0.0.0.0:8000 app:app</code></p>\n\n\n\n<h3>2、关闭</h3>\n\n\n\n<p>首先执行如下命令获取Gunicorn进程树：</p>\n\n\n\n<p><code>pstree -ap|grep gunicorn</code></p>\n\n\n\n<p>之后输入如下指令关闭进程：<br />\n\n<code>kill -9 12345</code></p>\n\n\n\n<p>ps：如出现：<code>pstree:command not found</code></p>\n\n\n\n<p>实际上在linux平台要安装psmisc包.<br />\n\n安装方式使用以下命令即可。<br />\n\n<code>yum install psmisc -y</code></p>\n\n\n\n<h3>3、重启</h3>\n\n\n\n<p>输入如下指令重启进程：<br />\n\n<code>kill -HUP 12345</code></p>\n\n\n\n<h2>配置文档</h2>\n\n\n\n<p>#&nbsp;gunicorn.conf</p>\n\n\n\n<p>#&nbsp;并行工作进程数</p>\n\n\n\n<p>workers&nbsp;=&nbsp;2</p>\n\n\n\n<p>#&nbsp;指定每个工作者的线程数</p>\n\n\n\n<p>threads&nbsp;=&nbsp;2</p>\n\n\n\n<p>#&nbsp;监听内网端口8000</p>\n\n\n\n<p>bind&nbsp;=&nbsp;&#39;127.0.0.1:8000&#39;</p>\n\n\n\n<p>#&nbsp;设置守护进程,将进程交给supervisor管理</p>\n\n\n\n<p>daemon&nbsp;=&nbsp;&#39;false&#39;</p>\n\n\n\n<p>#&nbsp;工作模式协程</p>\n\n\n\n<p>worker_class&nbsp;=&nbsp;&#39;gevent&#39;</p>\n\n\n\n<p>#&nbsp;设置最大并发量</p>\n\n\n\n<p>worker_connections&nbsp;=&nbsp;2000</p>\n\n\n\n<p>#&nbsp;设置进程文件目录</p>\n\n\n\n<p>pidfile&nbsp;=&nbsp;&#39;/var/run/gunicorn.pid&#39;</p>\n\n\n\n<p>#&nbsp;设置访问日志和错误信息日志路径</p>\n\n\n\n<p>accesslog&nbsp;=&nbsp;&#39;/var/log/gunicorn_acess.log&#39;</p>\n\n\n\n<p>errorlog&nbsp;=&nbsp;&#39;/var/log/gunicorn_error.log&#39;</p>\n\n\n\n<p>#&nbsp;设置日志记录水平</p>\n\n\n\n<p>loglevel&nbsp;=&nbsp;&#39;warning&#39;</p>\n\n\n\n<p>&nbsp;</p>\n\n', '2019-11-19 14:58:47', 1, 2, 114);
INSERT INTO `post` VALUES (5, b'0', '2019-05-18 00:00:00', NULL, '记录Centos7上安装配置各种软件服务的方法', '本文章目的：记录一些在生成centos7服务器上安装各种软件服务的方法，保持更新~~', '<h2>安装python3.6</h2>\n\n\n\n<p>step1.新建一个python文件夹并进入<br />\n\n<code>mkdir /usr/local/python3 &amp;&amp; cd /usr/local/python3</code></p>\n\n\n\n<p>step2.安装依赖包<br />\n\n<code>yum -y groupinstall &quot;Development tools&quot;</code><br />\n\n<code>yum -y install wget zlib-devel bzip2-devel openssl-devel ncurses-devel sqlite-devel readline-devel tk-devel gdbm-devel db4-devel libpcap-devel xz-devel</code></p>\n\n\n\n<p>step3.下载自己需要的python版本（速度可能较慢）<br />\n\n<code>wget https://www.python.org/ftp/python/3.6.8/Python-3.6.8.tar.xz</code></p>\n\n\n\n<p>step4.解压安装包并安装python3<br />\n\n<code>tar -xvJf &nbsp;Python-3.6.8.tar.xz</code><br />\n\n<code>cd Python-3.6.8</code><br />\n\n<code>./configure --prefix=/usr/local/python3</code><br />\n\n<code>make &amp;&amp; make install</code></p>\n\n\n\n<p>step5.创建软连接<br />\n\n<code>ln -s /usr/local/python3/bin/python3 /usr/bin/python3</code><br />\n\n<code>ln -s /usr/local/python3/bin/pip3 /usr/bin/pip3</code></p>\n\n\n\n<p>step6.验证是否正常安装好python3<br />\n\n<code>python3&nbsp;</code><br />\n\n<code>pip3</code></p>\n\n\n\n<h2>安装nginx1.16.1</h2>\n\n\n\n<p>step1.新建一个nginx文件夹并进入<br />\n\n<code>mkdir /usr/local/nginx &amp;&amp; cd /usr/local/nginx</code></p>\n\n\n\n<p>step2.下载自己需要的nginx版本（速度可能较慢）<br />\n\n<code>wget https://nginx.org/download/nginx-1.16.1.tar.gz</code></p>\n\n\n\n<p>step3.解压安装包并安装nginx<br />\n\n<code>tar -zxvf &nbsp;nginx-1.16.1.tar.gz</code><br />\n\n<code>cd nginx-1.16.1</code><br />\n\n<code>./configure --prefix=/usr/local/nginx</code><br />\n\n<code>make &amp;&amp; make install</code></p>\n\n\n\n<p>step4.常用命令<br />\n\n<code>$ /usr/local/nginx/sbin/nginx</code><br />\n\n<code>$ /usr/local/nginx/sbin/nginx -s reload</code><br />\n\n<code>$ /usr/local/nginx/sbin/nginx -s stop</code><br />\n\n<code>$ /usr/local/nginx/sbin/nginx -t</code><br />\n\n&nbsp;</p>\n\n', '2019-11-19 15:37:37', 1, 3, 107);
INSERT INTO `post` VALUES (6, b'0', '2019-05-18 00:00:00', NULL, 'vps一键脚本快速搭建ss 开启bbr加速', '科学上网搭梯子', '<h2>搭建ss</h2>\n\n\n\n<p>关于本脚本，来着秋水逸冰 一键安装 <code>Shadowsocks-Python， ShadowsocksR， Shadowsocks-Go， Shadowsocks-libev</code> 版（四选一）服务端</p>\n\n\n\n<h3>基本信息</h3>\n\n\n\n<ul>\n\n	<li>各版本的启动脚本及配置文件名不再重合</li>\n\n	<li>每次运行可安装一种版本</li>\n\n	<li>支持以多次运行来安装多个版本，且各个版本可以共存（注意端口号需设成不同）</li>\n\n	<li>若已安装多个版本，则卸载时也需多次运行（每次卸载一种）</li>\n\n</ul>\n\n\n\n<h3>默认配置</h3>\n\n\n\n<blockquote>\n\n<p>服务器端口：自己设定（如不设定，默认从 9000-19999 之间随机生成）<br />\n\n密码：自己设定（如不设定，默认为 teddysun.com）<br />\n\n加密方式：自己设定（如不设定，Python 和 libev 版默认为 aes-256-gcm，R 和 Go 版默认为 aes-256-cfb）<br />\n\n协议（protocol）：自己设定（如不设定，默认为 origin）（仅限 ShadowsocksR 版）<br />\n\n混淆（obfs）：自己设定（如不设定，默认为 plain）（仅限 ShadowsocksR 版）<br />\n\n备注：脚本默认创建单用户配置文件，如需配置多用户，请手动修改相应的配置文件后重启即可。</p>\n\n</blockquote>\n\n\n\n<h3>使用方法</h3>\n\n\n\n<ol>\n\n	<li>\n\n	<p>使用root用户登录，依次运行以下命令</p>\n\n\n\n	<p><code>shell wget --no-check-certificate -O shadowsocks-all.sh https://raw.githubusercontent.com/teddysun/shadowsocks_install/master/shadowsocks-all.sh chmod +x shadowsocks-all.sh ./shadowsocks-all.sh 2&gt;&amp;1 | tee shadowsocks-all.log </code></p>\n\n	</li>\n\n	<li>\n\n	<p>安装完成后，脚本提示如下</p>\n\n\n\n	<p><code>shell Congratulations, your_shadowsocks_version install completed! Your Server IP :your_server_ip Your Server Port :your_server_port Your Password :your_password Your Encryption Method:your_encryption_method Your QR Code: (For Shadowsocks Windows, OSX, Android and iOS clients) ss://your_encryption_method:your_password@your_server_ip:your_server_port Your QR Code has been saved as a PNG file path: your_path.png Welcome to visit:https://teddysun.com/486.html Enjoy it! </code></p>\n\n	</li>\n\n	<li>\n\n	<p>卸载方法</p>\n\n\n\n	<ul>\n\n		<li>已安装多个版本，则卸载时也需多次运行（每次卸载一种）</li>\n\n		<li>\n\n		<p>使用root用户登录，运行以下命令：<br />\n\n		<code>./shadowsocks-all.sh uninstall</code></p>\n\n		</li>\n\n	</ul>\n\n	</li>\n\n	<li>\n\n	<p>启动脚本<br />\n\n	启动脚本后面的参数含义，从左至右依次为：启动，停止，重启，查看状态。</p>\n\n\n\n	<ul>\n\n		<li>\n\n		<p>Shadowsocks-Python 版：<br />\n\n		<code>/etc/init.d/shadowsocks-python start | stop | restart | status</code></p>\n\n		</li>\n\n		<li>ShadowsocksR 版：<br />\n\n		<code>/etc/init.d/shadowsocks-r start | stop | restart | status</code></li>\n\n		<li>Shadowsocks-Go 版：<br />\n\n		<code>/etc/init.d/shadowsocks-go start | stop | restart | status</code></li>\n\n		<li>\n\n		<p>Shadowsocks-libev 版：<br />\n\n		<code>/etc/init.d/shadowsocks-libev start | stop | restart | status</code></p>\n\n		</li>\n\n	</ul>\n\n	</li>\n\n	<li>\n\n	<p>各版本默认配置文件</p>\n\n\n\n	<ul>\n\n		<li>\n\n		<p>Shadowsocks-Python 版：<br />\n\n		<code>/etc/shadowsocks-python/config.json</code></p>\n\n		</li>\n\n		<li>ShadowsocksR 版：<br />\n\n		<code>/etc/shadowsocks-r/config.json</code></li>\n\n		<li>Shadowsocks-Go 版：<br />\n\n		<code>/etc/shadowsocks-go/config.json</code></li>\n\n		<li>Shadowsocks-libev 版：<br />\n\n		<code>/etc/shadowsocks-libev/config.json</code></li>\n\n	</ul>\n\n	</li>\n\n</ol>\n\n\n\n<h3>客户端下载</h3>\n\n\n\n<ul>\n\n	<li>\n\n	<p>常规版 Windows 客户端<br />\n\n	<code>https://github.com/shadowsocks/shadowsocks-windows/releases</code></p>\n\n	</li>\n\n	<li>\n\n	<p>ShadowsocksR 版 Windows 客户端<br />\n\n	<code>https://github.com/shadowsocksrr/shadowsocksr-csharp/releases</code></p>\n\n	</li>\n\n</ul>\n\n\n\n<h2>BBR加速</h2>\n\n\n\n<h3>安装</h3>\n\n\n\n<p>通过脚本一键升级内核并安装BBR加速<br />\n\n先切换到<code>root</code>用户. 否则运行某些命令会提示无权限<br />\n\n依次运行以下命令</p>\n\n\n\n<p><code>shell yum install -y wget wget &ndash;no-check-certificate https://github.com/teddysun/across/raw/master/bbr.sh chmod +x bbr.sh ./bbr.sh </code></p>\n\n\n\n<p>安装完成后，脚本会提示需要重启VPS，输入<code>y</code>回车重启vps<br />\n\nVPS重启可能需要几分钟, 耐心等待一下<br />\n\n重启完成后，重新连接VPS</p>\n\n\n\n<h3>验证</h3>\n\n\n\n<p>现在验证一下是否成功安装了最新内核并开启BBR<br />\n\n输入以下命令：<code>uname -r</code><br />\n\n查看内核版本，如果返回值含有4.13或以上版本, 就表示OK了</p>\n\n\n\n<p><code>sysctl net.ipv4.tcp_available_congestion_control</code><br />\n\n返回值一般为：<br />\n\n<code>net.ipv4.tcp_available_congestion_control = bbr cubic reno</code></p>\n\n\n\n<p><code>sysctl net.ipv4.tcp_congestion_control</code><br />\n\n返回值一般为：<br />\n\n<code>net.ipv4.tcp_congestion_control = bbr</code></p>\n\n\n\n<p><code>sysctl net.core.default_qdisc</code><br />\n\n返回值一般为：<br />\n\n<code>net.core.default_qdisc = fq</code></p>\n\n\n\n<p><code>lsmod | grep bbr</code><br />\n\n返回值有：<br />\n\n<code>tcp_bbr</code><br />\n\n模块即说明bbr已启动</p>\n\n', '2019-11-19 15:41:28', 1, 3, 175);
INSERT INTO `post` VALUES (7, b'0', '2019-05-18 00:00:00', NULL, '11.19 随笔', '一时裸辞一时爽，一直裸辞一直爽？', '<p>2019.11.19，今天正式提出申请辞职，明天提交辞职申请表。终于有勇气离职啦~ 嘴上虽然已经提了不少次了，之前考虑到各种情况，没有勇气去面对辞职后没收入的窘况。想了想，今年干了啥事情？我第一时间想到的是考完了驾照，没有其他了，有点失败。</p>\n\n\n\n<p>一切推到重来吧，勇敢去面对新的生活！！！</p>\n\n\n\n<p>&nbsp;</p>\n\n', '2019-11-19 15:52:10', 1, 4, 99);
INSERT INTO `post` VALUES (8, b'0', '2019-05-18 00:00:00', NULL, '11.20 随笔', '前端经常用到的一些图标集，flask的一些功能库', '<h2>开源图标</h2>\n\n\n\n<p>程序中经常用到的各种图标，开源的图标集有很多，例如：</p>\n\n\n\n<ul>\n\n	<li>\n\n	<p><a href=\"https://fontawesome.com\">Font Awesome</a></p>\n\n	</li>\n\n	<li>\n\n	<p><a href=\"https://material.io/icons\">Material Design Icons</a></p>\n\n	</li>\n\n	<li>\n\n	<p><a href=\"https://octicons.github.com\">Octicons</a></p>\n\n	</li>\n\n	<li>\n\n	<p><a href=\"https://useiconic.com/open\">Iconic</a></p>\n\n	</li>\n\n</ul>\n\n\n\n<h2>Flask的一些库</h2>\n\n\n\n<ul>\n\n	<li>\n\n	<p>Flask-Whooshee 实现全文搜索</p>\n\n	</li>\n\n	<li>\n\n	<p>Flask-OAuthlib 实现第三方登录</p>\n\n	</li>\n\n	<li>\n\n	<p>Flask-Avatars 处理用户头像</p>\n\n	</li>\n\n	<li>\n\n	<p>Flask-Dropzone 优化文件上传</p>\n\n	</li>\n\n	<li>\n\n	<p>Flask-Babel：</p>\n\n\n\n	<ul>\n\n		<li>Babel 是为web程序实现国际化和本地化的python工具集</li>\n\n	</ul>\n\n	</li>\n\n</ul>\n\n\n\n<h2>Api 资源的序列化</h2>\n\n\n\n<p>在传统的Flask Web程序中，我们使用Jinja2来把数据渲染到模板中，然后返回渲染后的html数据；而在Web API中,我们则需要将数据按照设计好的模式封装为JSON数据并返回，这个过程被称为响应的格式化，或是响应封装，也被称为资源的序列化(Serialization)。<br />\n\n对于我们来说，序列化就是把数据库模型对象转换成JSON数据，相对的，反序列化就是把JSON数据转换成数据库模型对象。</p>\n\n', '2019-11-20 09:57:40', 1, 4, 94);
INSERT INTO `post` VALUES (9, b'0', '2019-05-18 00:00:00', '2021-03-18 12:13:30', '爬取广播电视备案公示信息', '话说去年一位大学和我挺好的女同学拜托我帮她做一件事情，她最近的工作被安排去国家广播电视备案公示网站搜集整理资料，手动录入官方公示出来的电视剧备案信息，得做成excel表格。她觉得这种工作太费劲了，无脑去网站复制，然后到excel中粘贴，还得谨慎录入生怕录入错误信息。得知我大学搞过爬虫，就来拜托我啦~~', '<p>简单问清楚具体情况，了解她的需求，其实也挺简单的，难点是第一次使用<code>openpyxl</code>这个处理excel库，没事，谷歌一下查一下资料，嘿嘿，还挺简单的，毕竟python这个胶水语言，拿来就用。</p>\n<p>代码就不放了，一百行不到，而且现在看回代码，写得是什么鬼？？是我写的吗~</p>\n<p>详情看图吧：<img src=\"http://www.twhuang.me:9010/image/d3cae5bced7247f2bda1ee212adabad6.jpg\" alt=\"\" width=\"1186\" height=\"3858\" /></p>', '2019-11-20 10:44:58', 1, 6, 166);
INSERT INTO `post` VALUES (10, b'0', '2019-05-18 00:00:00', NULL, '《SQL必知必会》笔记', '最近复习一下SQL，找了本《SQL必知必会》高清PDF，花两三天上班时间过一遍，做了一些笔记。', '<h2>SQL</h2>\n\n\n\n<p><strong>sql(structured query language 结构化查询语句)是一种专门用来于数据库沟通的语言</strong><br />\n\n表中的如何列都可以定义为主键，只要满足以下条件：</p>\n\n\n\n<ul>\n\n	<li>任意两列都不具有相同的主键值</li>\n\n	<li>每一行都必须具有一个主键值</li>\n\n	<li>主键列中的值不允许修改或更新</li>\n\n	<li>主键值不能重用(表中删除后，不能赋给以后的新行)</li>\n\n</ul>\n\n\n\n<h2>数据表示</h2>\n\n\n\n<p>SQL语句一般返回原始的、无格式的数据，数据的格式化是表示问题，不是检索问题，一般在显示数据的应用程序中规定格式化</p>\n\n\n\n<h2>排序检索数据</h2>\n\n\n\n<p>仅在多个行中具有相同的prod<em>price值时才对产品按prod</em>name进行排序<br />\n\n<code>ORDER BY</code>子句位于语句的最后</p>\n\n\n\n<p><code>sql SELECT prod_id,prod_price,prod_name FROM Products ORDER BY prod_price; </code></p>\n\n\n\n<p>按列位置排序(下面例子的3即是prod_name)，指定排序方向，默认为升序(ASC),降序为(DESC)</p>\n\n\n\n<p><code>sql SELECT prod_id,prod_price,prod_name FROM Products ORDER BY prod_price DESC,3; </code></p>\n\n\n\n<h2>过滤数据</h2>\n\n\n\n<p>WHERE字句可以包含任意数目的AND和OR操作符，允许两者结合以进行复杂，高级的过滤。<br />\n\nAND优先级比OR优先级更高，在WHERE子句中应使用括号进行分组</p>\n\n\n\n<p><code>sql SELECT prod_name,prod_price FROM Products WHERE (vend_id = &#39;DLL01&#39; OR vend_id = &#39;BRS01&#39;) AND prod_price &gt;=10; </code></p>\n\n\n\n<h2>通配符过滤</h2>\n\n\n\n<ul>\n\n	<li>百分号<code>%</code>通配符，表示任何字符出现任意次数</li>\n\n	<li>下划线<code>_</code>通配符，只匹配单个字符</li>\n\n</ul>\n\n\n\n<h2>计算字段</h2>\n\n\n\n<p>常用聚集函数：用来汇总数据</p>\n\n\n\n<ul>\n\n	<li>AVG()返回某列的平均值</li>\n\n	<li>COUNT()返回某列的行数</li>\n\n	<li>MAX()返回某列的最大值</li>\n\n	<li>MIN()返回某列的最小值</li>\n\n	<li>SUN()返回某列的值之和</li>\n\n</ul>\n\n\n\n<h2>分组数据</h2>\n\n\n\n<p>使用<code>HAVING</code>子句过滤分组</p>\n\n\n\n<p><code>sql SELECT cust_id,COUNT(*) AS orders FROM Orders GROUP BY cust_id HAVING COUNT(*) &gt;= 2; </code></p>\n\n\n\n<h2>使用子查询</h2>\n\n\n\n<p>作为子查询的SELECT语句只能查询单个列</p>\n\n\n\n<p><code>sql SELECT cust_id FROM orders WHERE order_num IN (SELECT order_num FROM orderitems WHERE prod_id=&#39;RGAN01&#39;); </code></p>\n\n\n\n<h2>联结表</h2>\n\n\n\n<p><code>sql SELECT vend_name,prod_name,prod_price FROM Vendors,Products WHERE Vendors.vend_id = Products.vend_id; </code></p>\n\n\n\n<p>由没有联结条件的表关系返回的结果为笛卡尔积(即上面例子去掉<code>WHERE</code>子句)要保证所有联结都有WHERE子句<br />\n\n有时，返回笛卡尔积的联结，也称叉联结</p>\n\n\n\n<ul>\n\n	<li>自联结</li>\n\n	<li>自然联结</li>\n\n	<li>外联结\n\n	<ul>\n\n		<li>右外联结</li>\n\n		<li>左外联结</li>\n\n		<li>全外联结</li>\n\n	</ul>\n\n	</li>\n\n</ul>\n\n\n\n<p>左外联结合右外联结之间的唯一差别是所关联的表的顺序，换句话说，调整FROM或WHERE子句中表的顺序，左外联结可以转换为右外联结</p>\n\n\n\n<h2>组合查询</h2>\n\n\n\n<p>使用很简单，每条SELECT语句之间放上关键词<code>UNION</code>,<code>ORDER BY</code>子句排序所有SELECT语句返回的所有结果</p>\n\n\n\n<p><span class=\"marker\"><code>sql SELECT cust_name,cust_contact,cust_email FROM Customers WHERE cust_state IN (&#39;IL&#39;,&#39;IN&#39;,&#39;MI&#39;) UNION SELECT cust_name,cust_contact,cust_email FROM Customers WHERE cust_name = &#39;Fun4All&#39; ORDER BY cust_name,cust_contact; </code></span></p>\n\n\n\n<h2>插入数据</h2>\n\n\n\n<p><code>INSERT</code>通常只插入一行，<code>INSERT SELECT</code>是个例外，可以用一条<code>INSERT</code>插入多行</p>\n\n\n\n<h3>从一个表复制到另一个表</h3>\n\n\n\n<p><code>SELECT INTO</code>导入数据在已有表，<code>INSERT SELECT</code>导出数据到新表<br />\n\n<code>SELECT * INTO　CustCopy FROM Customers;</code><br />\n\nsqlites使用: <code>create table newTb as select * from oldTb;</code>复制表</p>\n\n\n\n<h2>更新和和删除数据</h2>\n\n\n\n<h3>更新数据</h3>\n\n\n\n<ul>\n\n	<li>更新表中的特定行\n\n	<ul>\n\n		<li>单列 <code>UPDATE Customers SET cust_email = &#39;kim@thetoystore.com&#39; WHERE cust_id = &#39;1000000005&#39;;</code></li>\n\n		<li>多列 <code>UPDATE Customers SET cust_email = &#39;kim@thetoystore.com&#39;,cust_contact = &#39;Sam Roberts&#39; WHERE cust_id = &#39;1000000005&#39;;</code></li>\n\n	</ul>\n\n	</li>\n\n	<li>更新表中的所有行\n\n	<ul>\n\n		<li>不指定条件即可 <code>UPDATE Customers SET cust_email = &#39;kim@thetoystore.com&#39;</code></li>\n\n	</ul>\n\n	</li>\n\n</ul>\n\n\n\n<h3>删除数据</h3>\n\n\n\n<ul>\n\n	<li>\n\n	<p>从表中删除特定行<code>DELETE FROM Customers WHERE cust_id = &#39;1000000005&#39;;</code></p>\n\n	</li>\n\n	<li>\n\n	<p>从表中删除所有行<code>DELETE FROM Customers;</code></p>\n\n	</li>\n\n</ul>\n\n\n\n<h2>创建和操作表</h2>\n\n\n\n<p><code>CREATE TABLE</code>创建新表<br />\n\n<code>ALTER TABLE</code>更改表列<br />\n\n<code>DROP TABLE</code>删除一个表</p>\n\n\n\n<h3>创建表</h3>\n\n\n\n<p><code>DEFAULT</code>默认值，<code>NOT NULL</code></p>\n\n\n\n<p><code>sql CREATE TABLE Newtab( tab_id CHAR(10) PRIMARY KEY NOT NULL, tab_name CHAR(25) NOT NULL, address CHAR(255), country CHAR(10) DEFAULT &#39;CN&#39; ); </code></p>\n\n\n\n<h3>更新表</h3>\n\n\n\n<p>增加列：<code>ALTER TABLE Vendors ADD vend_phone CHAR(20);</code><br />\n\n删除列：<code>ALTER TABLE Vendors DROP COLLUMN vend_phone;</code></p>\n\n\n\n<h3>删除表</h3>\n\n\n\n<p><code>DROP TABLE Custnewtab;</code></p>\n\n\n\n<h2>视图</h2>\n\n\n\n<p>视图为虚拟的表，视图包含的不是数据而是根据需要检索数据的查询。<br />\n\n视图提供了一种封装SELECT语句的层次，可用来简化数据处理，重新格式化或保护基础数据<br />\n\n为什么要使用视图？</p>\n\n\n\n<ul>\n\n	<li>重用SQL语句</li>\n\n	<li>简化复杂的SQL操作</li>\n\n	<li>使用表的一部分而不是整个表</li>\n\n	<li>保护数据</li>\n\n	<li>更改数据格式和表示</li>\n\n</ul>\n\n', '2019-11-21 08:15:41', 1, 7, 110);
INSERT INTO `post` VALUES (11, b'0', '2019-05-18 00:00:00', '2021-03-18 12:16:12', '永别了，亲爱的奶奶~', '天气冷了，冬天来了。', '<p><img src=\"http://www.twhuang.me:9010/image/dcf21cab06214f93abecee12389344ef.jpg\" alt=\"nainai\" width=\"3968\" height=\"2976\" /></p>', '2019-12-04 07:24:31', 1, 5, 162);
INSERT INTO `post` VALUES (12, b'0', '2019-05-18 00:00:00', '2021-03-18 12:17:19', 'breakblog.me 博客升级https啦~', '今天上班摸鱼，闲来无事，就把博客升级Https，用的是阿里云免费一年DV类型的SSL证书，申请SSL证书和服务端nginx配置也还挺简单。', '<p>过程就不详细写了，阿里云官网文档还很详细，点赞~&nbsp;<a href=\"https://help.aliyun.com/product/28533.html\" target=\"_blank\" rel=\"noopener\">点我打开</a></p>\n<p>&nbsp;</p>', '2019-12-05 09:45:34', 1, 1, 168);
INSERT INTO `post` VALUES (13, b'0', '2019-05-18 00:00:00', '2021-03-18 12:18:49', '模拟中小企业局域网组建', '大学计算机课程有那么多，其中我印象比较深刻的是网络课程，那位女老师讲课极其生动易懂，我也比较有兴趣。今天分享一下我的大一网络课程期末考的一次模拟实验。', '<h2>中小企业局域网组建</h2>\n<h3>网络拓扑图</h3>\n<p>软件用的是：Cisco Packet Tracer Student -Version:6.2.0 如下图：</p>\n<p><img src=\"http://www.twhuang.me:9010/image/130c3a3087734f618d6ace04d1859a0b.jpg\" alt=\"\" /></p>\n<h2>涵盖知识</h2>\n<p>这一实验独立完成下来，收货还是蛮多的。</p>\n<ul>\n<li>\n<p>开启安全验证、telnet远程登录</p>\n</li>\n<li>\n<p>交换机间链路聚合端口聚合</p>\n</li>\n<li>\n<p>跨vlan跨网段通讯</p>\n</li>\n<li>\n<p>静态路由配置</p>\n</li>\n<li>\n<p>动态OSPF配置</p>\n</li>\n<li>\n<p>NAT网络地址转换</p>\n</li>\n<li>\n<p>配置vlan的dhcp服务</p>\n</li>\n<li>\n<p>设置DNS、WEB服务</p>\n</li>\n</ul>', '2019-12-12 08:56:46', 1, 1, 126);
INSERT INTO `post` VALUES (14, b'0', '2019-05-18 00:00:00', NULL, 'java中的对象初始化块、静态初始化块和构造器方法执行顺序 ', '最近看《Java核心技术：一》里面的4.6节对象构造谈到一些对象构造的执行顺序，第一遍看还没好好掌握，网上查了些资料，结合里面说到的要点，恍然大悟。', '<p>书中的代码例子：</p>\n\n<p>\n\n<code>import java.util.*;<br />\n\n&nbsp;<br />\n\n/**<br />\n\n&nbsp;* This program demonstrates object construction.<br />\n\n&nbsp;* @version 1.01 2004-02-19<br />\n\n&nbsp;* @author Cay Horstmann<br />\n\n&nbsp;*/<br />\n\npublic class ConstructorTest<br />\n\n{<br />\n\n&nbsp; &nbsp;public static void main(String[] args)<br />\n\n&nbsp; &nbsp;{<br />\n\n&nbsp; &nbsp; &nbsp; // fill the staff array with three Employee objects<br />\n\n&nbsp; &nbsp; &nbsp; Employee[] staff = new Employee[3];<br />\n\n&nbsp;<br />\n\n&nbsp; &nbsp; &nbsp; staff[0] = new Employee(&quot;Harry&quot;, 40000);<br />\n\n&nbsp; &nbsp; &nbsp; staff[1] = new Employee(60000);<br />\n\n&nbsp; &nbsp; &nbsp; staff[2] = new Employee();<br />\n\n&nbsp;<br />\n\n&nbsp; &nbsp; &nbsp; // print out information about all Employee objects<br />\n\n&nbsp; &nbsp; &nbsp; for (Employee e : staff)<br />\n\n&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;System.out.println(&quot;name=&quot; + e.getName() + &quot;,id=&quot; + e.getId() + &quot;,salary=&quot;<br />\n\n&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;+ e.getSalary());<br />\n\n&nbsp; &nbsp;}<br />\n\n}<br />\n\n&nbsp;<br />\n\nclass Employee<br />\n\n{<br />\n\n&nbsp; &nbsp;private static int nextId;<br />\n\n&nbsp;<br />\n\n&nbsp; &nbsp;private int id;<br />\n\n&nbsp; &nbsp;private String name = &quot;&quot;; // instance field initialization<br />\n\n&nbsp; &nbsp;private double salary;<br />\n\n&nbsp;&nbsp;<br />\n\n&nbsp; &nbsp;// static initialization block<br />\n\n&nbsp; &nbsp;static<br />\n\n&nbsp; &nbsp;{<br />\n\n&nbsp; &nbsp; &nbsp; Random generator = new Random();<br />\n\n&nbsp; &nbsp; &nbsp; // set nextId to a random number between 0 and 9999<br />\n\n&nbsp; &nbsp; &nbsp; nextId = generator.nextInt(10000);<br />\n\n&nbsp; &nbsp;}<br />\n\n&nbsp;<br />\n\n&nbsp; &nbsp;// object initialization block<br />\n\n&nbsp; &nbsp;{<br />\n\n&nbsp; &nbsp; &nbsp; id = nextId;<br />\n\n&nbsp; &nbsp; &nbsp; nextId++;<br />\n\n&nbsp; &nbsp;}<br />\n\n&nbsp;<br />\n\n&nbsp; &nbsp;// three overloaded constructors<br />\n\n&nbsp; &nbsp;public Employee(String n, double s)<br />\n\n&nbsp; &nbsp;{<br />\n\n&nbsp; &nbsp; &nbsp; name = n;<br />\n\n&nbsp; &nbsp; &nbsp; salary = s;<br />\n\n&nbsp; &nbsp;}<br />\n\n&nbsp;<br />\n\n&nbsp; &nbsp;public Employee(double s)<br />\n\n&nbsp; &nbsp;{<br />\n\n&nbsp; &nbsp; &nbsp; // calls the Employee(String, double) constructor<br />\n\n&nbsp; &nbsp; &nbsp; this(&quot;Employee #&quot; + nextId, s);<br />\n\n&nbsp; &nbsp;}<br />\n\n&nbsp;<br />\n\n&nbsp; &nbsp;// the default constructor<br />\n\n&nbsp; &nbsp;public Employee()<br />\n\n&nbsp; &nbsp;{<br />\n\n&nbsp; &nbsp; &nbsp; // name initialized to &quot;&quot;--see above<br />\n\n&nbsp; &nbsp; &nbsp; // salary not explicitly set--initialized to 0<br />\n\n&nbsp; &nbsp; &nbsp; // id initialized in initialization block<br />\n\n&nbsp; &nbsp;}<br />\n\n&nbsp;<br />\n\n&nbsp; &nbsp;public String getName()<br />\n\n&nbsp; &nbsp;{<br />\n\n&nbsp; &nbsp; &nbsp; return name;<br />\n\n&nbsp; &nbsp;}<br />\n\n&nbsp;<br />\n\n&nbsp; &nbsp;public double getSalary()<br />\n\n&nbsp; &nbsp;{<br />\n\n&nbsp; &nbsp; &nbsp; return salary;<br />\n\n&nbsp; &nbsp;}<br />\n\n&nbsp;<br />\n\n&nbsp; &nbsp;public int getId()<br />\n\n&nbsp; &nbsp;{<br />\n\n&nbsp; &nbsp; &nbsp; return id;<br />\n\n&nbsp; &nbsp;}<br />\n\n}</code><br />\n\n&nbsp;</p>\n\n\n\n<p>在Java中,有两种初始化块:静态初始化块和非静态初始化块。它们都是定义在类中，用大括号{}括起来，静态代码块在大括号外还要加上static关键字。</p>\n\n\n\n<p>非静态初始化块（构造代码块）：<br />\n\n作用：给对象进行初始化。对象一建立就运行，且优先于构造函数的运行。<br />\n\n与构造函数的区别：非静态初始化块给所有对象进行统一初始化，构造函数只给对应对象初始化。<br />\n\n应用：将所有构造函数共性的东西定义在构造代码块中。</p>\n\n\n\n<p>静态初始化块：<br />\n\n作用：给类进行初始化。随着类的加载而执行，且只执行一次<br />\n\n与构造代码块的区别：<br />\n\n1）构造代码块用于初始化对象，每创建一个对象就会被执行一次；静态代码块用于初始化类，随着类的加载而执行，不管创建几个对象，都只执行一次。<br />\n\n2）静态代码块优先于构造代码块的执行<br />\n\n3）都定义在类中，一个带static关键字，一个不带static</p>\n\n\n\n<p>构造器、非静态初始化块、静态代码块都是用于初始化，三者的执行顺序依次是：静态代码块&gt;构造代码块&gt;构造器。<br />\n\n其实初始化块就是构造器的补充，初始化块是不能接收任何参数的，定义的一些所有对象共有的属性、方法等内容时就可以用初始化块初始化了。</p>\n\n\n\n<p>静态初始化块的作用就是当JVM在装载类时，你想让它做一些事情，那么，就可以用静态初始化块。这几者的执行顺序是：</p>\n\n\n\n<p>（JVM在装载类时）先装载类的静态成员，再执行静态初始化块（同样，当一个类有继承自某类时，则会先装载该父类，那么，父类的装载或执行顺序，也都如句子所述）。</p>\n\n\n\n<p>（在创建类的实例时）先执行实例初始化块，再执行构造方法；但对于一棵继承树中，会先调用父类的构造方法，那么其执行顺序也如句子所述。<br />\n\n&nbsp;</p>\n\n', '2020-01-14 17:15:28', 1, 8, 166);
INSERT INTO `post` VALUES (15, b'0', '2019-05-18 00:00:00', '2021-07-28 05:51:11', '2020.3.5 随笔', '2020年来，这是新年的第一篇文章，有时候真的很懒，博客一直没能按时更新，可能也觉得没啥人看，没动力写下去。回头想想，自己也不去交换友链，不推广博客，这也是我的问题。今晚有心情，就写写近况吧~', '<p>今年应该是我记忆中，新年节日气氛最没氛围的，最主要的还是新冠病毒的原因，我这情况是这样的，农村里都没人出门，探亲、聚会都没了。</p>\n<p>我呢，带了一台女朋友不用的dell旧笔记本和一本spring实战回家看书学习，回到家里，书也就看了一两章，笔记本也没打开过几次。过年前还帮女朋友升级了电脑，加了内存，加了固态，想着过年回家好好学习，结果，呵呵~~</p>\n<p>还好，本来想着年后三四月份出去找份java后端工作，但是最近发生这么严重的疫情，恐怕也要推迟了，情况看看再说吧！这两三个月来，一直有学习java，从java基础se，到servlet，spring，也有刷牛客上面的java基础题目，现在大概刷了一半题库，准确率只有6成左右，好好加油吧。</p>\n<p>项目的话，用servlet+jsp重构了现在用的flask写的博客，我知道这些技术比较落后了，外面的公司都不用了，但是servlet肯定必须写好，毕竟springmvc底层也是servlet嘛。</p>\n<p>看书和看b站上面的黑马视频，学spring、springmvc和mybatis相关技术。所以最近用spring+springmvc+mybatis (ssm)+jsp&nbsp;又重构了现在的breakblog博客，项目也基本写完了。迟点把最近的写的两个项目放github上面。</p>\n<p>下一步干嘛？当然学springboot啦，springmvc毕竟也算了解了皮毛，学springboot也不妨碍深入理解spring吧，jsp是不想再用了，今晚去优化了一下现在的博客，发现还是flask的jinjia2模板爽啊，哈哈哈哈哈。springboot推荐的thymeleaf模板引擎也得学学，下个项目打算用springboot&nbsp;加thymeleaf模板引擎再写一遍这个博客吧，然后写完之后，就是前后端分离的事情了。大前端的浪潮下，vue怎么也得学学吧，幸好学java之前有接触过，难不倒我的，springboot+vue前后端分离搞起。搞着这些，换个高难度的项目试试，从头搞起，肯定学到不少的。</p>\n<p><span style=\"color: #f1c40f;\">最后，还是好好把java基础学好，多线程，jvm虚拟机，底层这些要了解呀，精通不要求，基本怎么也得会吧。好吧，半夜四点多了。该睡觉了~再见！</span></p>', '2020-03-04 20:19:35', 1, 4, 514);
INSERT INTO `post` VALUES (16, b'1', '2021-01-13 06:33:27', '2021-03-09 08:09:37', 'tttt', 'tttt', '<p>tttsss</p>', '2021-01-13 06:33:27', 1, 1, 7);
INSERT INTO `post` VALUES (17, b'1', '2021-04-23 01:40:36', '2021-04-23 01:42:32', 'ttttt', 'fsdfsadfdsaf', '<p>asfsadfsadfsdafsdfsdf2222</p>', '2021-04-23 01:40:36', 1, 9, 0);

SET FOREIGN_KEY_CHECKS = 1;