# 基础镜像
FROM mysql:5.7
# 作者信息
MAINTAINER Cetuer
# 控制其它sql文件执行
COPY ./db/init/init.sql /docker-entrypoint-initdb.d/init.sql
# 需要执行的sql
COPY ./db/sql/*.sql /opt/sql/
