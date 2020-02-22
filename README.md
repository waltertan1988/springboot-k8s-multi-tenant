# springboot-multi-tenant
一个基于SpringBoot2.x + SpringDataJpa + Hibernate + MySQL，采用“数据库隔离方式”的多租户应用。   
在该应用下，不同租户的数据库互相独立，且每个租户可以根据不同业务场景拥有属于自己的多个业务数据库，且各个数据库可以完成各自的事务操作。   
## 多租户应用架构设计图
![Pandao editor.md](https://github.com/waltertan1988/springboot-multi-tenant/blob/master/docs/charts/%E5%A4%9A%E7%A7%9F%E6%88%B7%E5%BA%94%E7%94%A8%E6%9E%B6%E6%9E%84%E8%AE%BE%E8%AE%A1%E5%9B%BE.jpg?raw=true "design.png")
## 多租户基础数据
[示例](https://github.com/waltertan1988/springboot-multi-tenant/tree/master/src/main/resources/schema)