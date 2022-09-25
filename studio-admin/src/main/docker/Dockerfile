FROM openjdk:8
MAINTAINER changlu 939974883@qq.com
VOLUME /tmp
ADD studio-admin-1.0.0.jar /app.jar
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
EXPOSE 8999
ENTRYPOINT ["java","-jar", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8666" ,"app.jar"]
