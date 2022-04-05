# 基础镜像
FROM openjdk:8-jre
# 作者信息
MAINTAINER Cetuer
# 挂载目录
VOLUME /home/parking
# 创建目录
RUN mkdir -p /home/parking
# 工作目录
WORKDIR /home/parking
# 复制jar
COPY ./jar/parking-lot-gateway-admin.jar /home/parking/parking-lot-gateway-admin.jar
# 暴露端口
EXPOSE 9090
# 启动服务
ENTRYPOINT ["java", "-Xms32m", "-Xmx64m", "-jar", "parking-lot-gateway-admin.jar"]