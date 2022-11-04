# sbvadmin
一个基于spring boot的后端管理系统

演示地址：[http://118.31.68.110:8081/index.html](http://118.31.68.110:8081/index.html)
账号：root
密码：123

前端代码仓库：[sbvadmin-vben](https://github.com/billyshen26/sbvadmin-vben)

# 知识点
## 获取springboot应用的版本号
在application.properties中加入
`application.version = @project.version@`
这里虽然我们用的是.properties,但不能用${}，[参考这里](https://blog.csdn.net/qq_34730511/article/details/119380836)
