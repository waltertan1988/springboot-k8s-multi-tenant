# springboot-multi-tenant
一个基于Kubernetes + SpringBoot2.x + SpringDataJpa + Hibernate + MySQL，采用“数据库隔离方式”的多租户应用。   
在该应用下，不同租户的数据库互相独立，且每个租户可以根据不同业务场景拥有属于自己的多个业务数据库，且各个数据库可以完成各自的事务操作。   
## 多租户应用架构设计图
![Pandao editor.md](https://github.com/waltertan1988/springboot-multi-tenant/blob/master/docs/charts/%E5%A4%9A%E7%A7%9F%E6%88%B7%E5%BA%94%E7%94%A8%E6%9E%B6%E6%9E%84%E8%AE%BE%E8%AE%A1%E5%9B%BE.jpg?raw=true "design.png")
## 多租户基础数据
[示例](https://github.com/waltertan1988/springboot-multi-tenant/tree/master/src/main/resources/schema)
## 部署到kubernetes上
前提：  
* kubernetes多节点集群
* master节点上安装了JDK1.8+、Git、Maven、镜像仓库（如registry、harbor）、Helm

步骤：   
* 从Github克隆本项目到master节点
* maven打包：
```
mvn clean package -Dmaven.test.skip=true
```
* 打包成功后进入target目录，执行以下构建docker镜像命令：
```
docker build -t waltertan1988/multi-tenant:latest --build-arg jarFile=multi-tenant-0.0.1-SNAPSHOT.jar --build-arg port=7081 . 
```
* 把镜像上传到镜像仓库（如无镜像仓库，可用docker save/load 命令把镜像传输到node节点）：
```
docker push <仓库ip>:<仓库端口>/waltertan1988/multi-tenant:latest
```
* 在master节点，进入项目工程的deploy目录，创建外部mysql的endpoint及service：
```
kubectl apply -f mysql-endpoint.yml
```
* 在master节点，进入项目工程的deploy目录，部署应用：
```
kubectl apply -f startup.yml
```
* 访问应用http://\<nodeIp\>:30081/ping
