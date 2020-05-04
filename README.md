# springboot-multi-tenant
一个基于Kubernetes + SpringBoot2.x + Spring Data Jpa + Hibernate + MySQL，采用“数据库隔离方式”的多租户应用。   
在该应用下，不同租户的数据库互相独立，且每个租户可以根据不同业务场景拥有属于自己的多个业务数据库，且各个数据库可以完成各自的事务操作。   
## 多租户应用架构设计图
![Pandao editor.md](https://github.com/waltertan1988/springboot-multi-tenant/blob/master/docs/charts/%E5%A4%9A%E7%A7%9F%E6%88%B7%E5%BA%94%E7%94%A8%E6%9E%B6%E6%9E%84%E8%AE%BE%E8%AE%A1%E5%9B%BE.jpg?raw=true "design.png")
## 多租户基础数据
* 登录账号/密码：0009785/123456，walter/123456   
* 多租户ID：A, B   
* 详细的DDL及DML配置参考[这里](https://github.com/waltertan1988/springboot-multi-tenant/tree/master/src/main/resources/schema)
## 部署到kubernetes上
### 前提：  
* kubernetes多节点集群：   
```text
master: 192.168.2.200，该节点上安装了JDK1.8+、Git、Maven、镜像仓库（如docker-registry、harbor）
node1:  192.168.2.201
node2:  192.168.2.202
```
* k8s集群配置了Ingress，以NodePort=30080方式对外暴露Http服务。
* 配置外部的DNS域名k8s.walter.com，指向k8s集群的一个节点IP（如192.168.2.200），用于以Ingress方式访问k8s应用。   

### 步骤：   
1. 开启master节点上的docker-registry本地镜像仓库：   
```shell script
docker run -d -p 5000:5000 -v /work/docker_registry:/var/lib/registry --restart=always --name=docker-registry -e REGISTRY_STORAGE_DELETE_ENABLED=true registry:2
```
2. Git克隆本项目到master节点，用maven打包、构建并上传docker镜像：
```shell script
# 以下命令相当于同时执行了3步：
# 2.1 mvn clean package -Dmaven.test.skip=true
# 2.2 docker build -t 192.168.2.200:5000/multi-tenant:latest --build-arg jarFile=multi-tenant-0.0.1-SNAPSHOT.jar --build-arg port=7081 . 
# 2.3 docker push 192.168.2.200:5000/multi-tenant:latest
# docker-maven-plugin插件的使用参考：https://github.com/spotify/docker-maven-plugin/tree/master
#
# 如无镜像仓库，可先手工执行2.1和2.2步生成docker镜像，然后用docker save/load 命令把镜像传输到node节点
mvn clean package docker:build -DpushImageTag -Dmaven.test.skip=true
```
3. 在master节点，进入项目工程的deploy目录，部署应用及Ingress：
```shell script
kubectl apply -f app-multi-tenant.yml
kubectl apply -f k8s-ingress.yml
```
4. 访问应用：
* 4.1 以应用本身的Service的NodePort方式访问应用（端口为30080）：   
```text
检测 服务是否已启动
http://<nodeIp>:30081/multi-tenant/ping

返回 默认数据源的数据和多租户数据源数据（A）的并集
http://<nodeIp>:30081/multi-tenant/fund/listObject?tenantId=A

不同租户（A和B），同类型数据源（fund）的写事务回滚
http://<nodeIp>:30081/multi-tenant/fund/deposit?fail=true&tenantId=A
http://<nodeIp>:30081/multi-tenant/fund/deposit?fail=true&tenantId=B

不同租户（A和B），同类型数据源（fund）数据源的写事务提交
http://<nodeIp>:30081/multi-tenant/fund/deposit?fail=false&tenantId=A
http://<nodeIp>:30081/multi-tenant/fund/deposit?fail=false&tenantId=B

同一租户（A）不同数据源类型（product）的事务回滚
http://<nodeIp>:30081/multi-tenant/product/addProductSpu?fail=true&tenantId=A

同一租户（A）不同数据源类型（product）的事务提交
http://<nodeIp>:30081/multi-tenant/product/addProductSpu?fail=false&tenantId=A
```
* 4.2 以Ingress方式访问应用（端口为30080，即Ingress的NodePort）   
```text
检测 服务是否已启动
http://k8s.walter.com:30080/multi-tenant/ping
```
