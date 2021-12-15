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
* hello.html
