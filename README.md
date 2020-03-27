# breakblog-springboot

本项目使用：springboot + mybatis + Thymeleaf 技术重构我的个人博客 BreakBlog(Flask 实现)，前端页面基本一样。页面布局和功能主要参考李辉的：[Blueblog](https://github.com/greyli/bluelog) 项目，感谢！
博客主要实现了：

- 前台
  - 主页(文章列表)
  - 文章(文章详细页面)
  - 文章分类
  - 评论列表
  - 发表评论
  - 回复评论
- 后台
  - 管理文章
    - 创建
    - 更新
    - 删除
  - 管理评论
    - 状态
    - 删除
  - 管理分类
    - 创建
    - 更新
    - 删除
  - 管理外链(个人或友链)
  - 博客资料设置
    - 博客名称
    - 个性副标题
    - 作者

功能具体实现请看我的博客地址：[BreakBlog](https://github.com/tw-huang/breakblog)

## 使用说明

1. 创建 mysql 数据库
2. 导入 mysql/ 目录下的五张张数据表
3. 修改 src/main/resources/application.yml 连接数据库信息
4. 运行测试，后台默认管理员：**admin** 密码：**breakblog**

## 界面截图

![alt 首页](https://i.loli.net/2020/03/27/hzkGWMZFOu32i6B.jpg)
![alt 文章](https://i.loli.net/2020/03/27/pl12hsrAnSOjDzw.jpg)
![alt 文章管理](https://i.loli.net/2020/03/27/pIQuScEfy9zlmAX.jpg)
![alt 编写文章](https://i.loli.net/2020/03/27/ZxPm3AaTuJgFQVB.jpg)
![alt 评论管理](https://i.loli.net/2020/03/27/6GPILk9YVg8hJCF.jpg)
