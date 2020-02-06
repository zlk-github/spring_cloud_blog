## config统一配置中心

### 介绍

    Spring Cloud Config为服务端和客户端提供了分布式系统的外部化配置支持。配置服务器为各应用的所有环境提供了一个中心化的外部配置。
    它实现了对服务端和客户端对Spring Environment和PropertySource抽象的映射，所以它除了适用于Spring构建的应用程序，也可以在任何
    其他语言运行的应用程序中使用。作为一个应用可以通过部署管道来进行测试或者投入生产，我们可以分别为这些环境创建配置，并且在需要
    迁移环境的时候获取对应环境的配置来运行。
    
    配置服务器默认采用git来存储配置信息，这样就有助于对环境配置进行版本管理，并且可以通过git客户端工具来方便的管理和访问配置内容。
    当然他也提供本地化文件系统的存储方式。默认采用git存储配置信息。
    

### 版本说明

    1.Spring Boot 2.1.10
    2.Spring Cloud Finchley.RELEASE
    3.JDK 1.8
    4.MAVEN 3.3.9
    
    
### 1.peer1与peer2为域名，在本地测试加
    
hosts文件： 加入以下
  
    127.0.0.1  peer1
    127.0.0.1  peer2
        
### 2.主要说明

**3.1** pom.xml引入
	
	   <!--config 统一配置中心-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
        </dependency>
        <!--euraka 2.0(spring boot 2.0+)注册中心-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
        <!--加权限-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        
 **3.2** application.properties  
 
    # 服务名称
    spring.application.name=config-server
    
    # 端口
    server.port=2011
    
    encrypt.key=sang
    
    # 拦截url,输入上一步设置的用户名和密码后才能获取到，否则页面会报404
    spring.security.user.name=root_zzz
    spring.security.user.password=123456
    
    # 配置服务注册中心（高可用注册中心）
    eureka.client.serviceUrl.defaultZone=http://peer1:1111/eureka/,http://peer2:2222/eureka/
    
    # git仓库配置
    spring.cloud.config.server.git.uri=https://github.com/zlk-github/spring_cloud_blog
    spring.cloud.config.server.git.searchPaths=tree/dev-1.0.0/config-repo
    spring.cloud.config.server.git.username=username
    spring.cloud.config.server.git.password=password
    
 **注：** 
 
    Spring Cloud Config也提供本地存储配置的方式。我们只需要设置属性spring.profiles.active=native，
    Config Server会默认从应用的src/main/resource目录下检索配置文件。
    也可以通过spring.cloud.config.server.native.searchLocations=file:F:/properties/属性来指定配置文件的位置。
    虽然Spring Cloud Config提供了这样的功能，但是为了支持更好的管理内容和版本控制的功能，还是推荐使用git的方式。

 **3.3** 启动类加注解
 
     @EnableDiscoveryClient 将服务注册到注册中心
     @EnableConfigServer  开启Config Server     
  
### 3.加密保证git上配置安全（参考https://segmentfault.com/a/1190000011680775）

application.properties加入
    
    # 拦截url,输入上一步设置的用户名和密码后才能获取到，否则页面会报404
    spring.security.user.name=root_zzz
    spring.security.user.password=123456 

pom.xml引入（上面已引入，此处为说明）

    <!--加权限-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
  
  
**3.1** 下载并解压JCE
    
    下载：JAVA8 JCE 地址： java8 JCE下载地址（https://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html）
    上述链接下载解压后拷贝到 JDK/jre/lib/security 目录下覆盖文件 (whichever version of JRE/JDK x64/x86 you are using)

**3.2** 下载并解压JCE 
    
    

### 4.


