# Spring Boot MongoDB Example

使用 Docker 安装 MongoDB

1.拉取镜像

```bash
$ docker pull mongo:latest
```

2.运行

```bash
docker run -d -p 27017:27017 --name mongo mongo
```

application.yml

```yaml
server:
  port: 8003

spring:
  data:
    mongodb:
      host: localhost
      port: 27017
      database: spring-boot-examples
logging:
  level:
    org.springframework.data.mongodb.core: debug

```
