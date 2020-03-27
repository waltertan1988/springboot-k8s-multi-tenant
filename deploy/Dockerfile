FROM openjdk:8
MAINTAINER waltertan waltertan1988@163.com
ARG jarFile=multi-tenant-0.0.1-SNAPSHOT.jar
ARG containerPath=/work
ARG port=7081
ENV jarFile ${jarFile}
ENV containerPath ${containerPath}
ENV port ${port}
COPY ${jarFile} ${containerPath}/${jarFile}
EXPOSE ${port}
CMD java -jar ${containerPath}/${jarFile} --server.port=${port}