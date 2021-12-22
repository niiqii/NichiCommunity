## Nichi社区

### 创建伊始
1. Starting with Spring Initializr
* 用Spring Initializer 创建一个SpringMVC项目
* 选择Spring Web依赖
* 进入Spring 官方Guide手册,找到SpringMVC配置案例
* 导入thymeleaf Maven依赖
2. Create a Web Controller
* @Controller 将其设为controller对象
* @RequestMapper 设置请求页面
* @RequestParam(name="name") 设置请求对象
* String name, 设置Model对象
* addAttribute("name",name)设置传入对象的值
* return "home"
3. Add a Home Page
* [Spring文档](https://www.bilibili.com/video/BV1r4411r7au?p=8&t=322.6)
###脚本
```sql
create table USER
(
ID           INTEGER auto_increment,
ACCOUNT_ID   VARCHAR(100),
NAME         VARCHAR(60),
GMT_CREATE   LONG,
GMT_MODIFIED LONG,
TOKEN        CHAR(36),
constraint USER_PK
primary key (ID)
);
```
