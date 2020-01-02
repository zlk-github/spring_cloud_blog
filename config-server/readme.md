## config统一配置中心

### 介绍

    配置中心主要用于为分布式系统中基础设施和微服务应用推广集中化的外部配置支持。
    分为服务端与客服端，其中服务端为分布式配置中心。配置中心就是通过抽象映射对
    微服务的配置文件进行集中管理。默认采用git存储配置信息。
    

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

**2.1** 启动类加注解

    @EnableDiscoveryClient 将服务注册到注册中心
    @EnableConfigServer  配置中心注解

**2.2** pom.xml引入
	
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
    
    

### 4.高可用注册中心（peer1,peer2互相为对方注册中心）

**4.1** application.properties

	# 服务名称
	spring.application.name=eureka-service
	
	# 端口
	server.port= 1001
	
	# 此应用为注册中心，false：不向注册中心注册自己。
	eureka.client.register-with-eureka=true
	# 注册中心职责是维护服务实例，false：不检索服务。
	eureka.client.fetch-registry=true
	
	#高可用注册中心
	eureka.client.serviceUrl.defaultZone=http://peer1:1111/eureka/;http://peer2:2222/eureka/
	
	# peer1、peer2为主机名称。
	# 可以直接使用主机ip，需要配置参数eureka.instance.preferIpAddressTrue=true,默认为flase。

