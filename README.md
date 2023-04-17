# guyunwu-backend
安卓项目-古韵屋-后端
## 环境
Java 7+
## 前端
[guyunwu](https://github.com/Uzemiu/guyunwu)
## 数据库
mysql:latest，推荐使用docker  
运行 `/src/main/resources/guyunwu.sql`，建表并插入初始数据
## 配置
将 `/src/main/resources/application.properties` 中和数据库有关的配置（url、username、password）替换为自己的数据库  
如：
```properties
spring.datasource.url = jdbc:mysql://localhost:3306/guyunwu?serverTimezone=Asia/Shanghai
spring.datasource.username = root
spring.datasource.password = 123456
```
## 部署
执行如下命令获得Jar包：
```bash
mvn package
```
假定打包完Jar包为 `guyunwu-0.0.1-SNAPSHOT.jar`  
服务器端运行：  
```bash
java -jar guyunwu-0.0.1-SNAPSHOT.jar
```
同时开启对应端口（默认8080
