# eureka服务注册中心

# 版本说明
1.Spring Boot 2.1.10
2.Spring Cloud Finchley.RELEASE
3.JDK 1.8
4.MAVEN 3.3.9

# 1.peer1与peer2为域名，在本地测试加
hosts文件： 加入 127.0.0.1  peer1
                 127.0.0.1  peer2
                 
# 主要说明
1.启动类加@EnableEurekaServer注解   
2.pom.xml引入
    <!--euraka 2.0注册中心-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    </dependency>  
  
# 打包jar，启动peer1,peer2注册中心到注册中心集群（peer1,peer2互相为对方注册中心）
1.java -jar eureka-1.0.0-SNAPSHOT.jar  --spring.profiles.active=peer1
2.java -jar eureka-1.0.0-SNAPSHOT.jar  --spring.profiles.active=peer2
3.java -jar eureka-1.0.0-SNAPSHOT.jar
    
               