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
COPY ./jar/parking-lot-app.jar /home/parking/parking-lot-app.jar
# 暴露端口
EXPOSE 9093
# 启动服务
ENTRYPOINT ["java", "-Xms64m", "-Xmx128m", "-jar", "parking-lot-app.jar"]