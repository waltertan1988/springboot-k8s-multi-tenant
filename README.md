# springboot-multi-tenant
一个基于SpringBoot2.x + SpringDataJpa + Hibernate + MySQL，采用“数据库隔离方式”的多租户应用。   
在该应用下，不同租户的数据库互相独立，且每个租户可以根据不同业务场景拥有属于自己的多个业务数据库，且各个数据库可以完成各自的事务操作。
