
CREATE DATABASE `multi_tenant_base` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

USE `multi_tenant_base`;

# 用户表
CREATE TABLE `acl_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_bin NOT NULL,
  `password` varchar(255) COLLATE utf8_bin NOT NULL,
  `user_real_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `mobile` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `gender` varchar(1) COLLATE utf8_bin NOT NULL,
  `is_expired` bit(1) NOT NULL,
  `is_locked` bit(1) NOT NULL,
  `is_password_expired` bit(1) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `created_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_51bvuyvihefoh4kp5syh2jpi4` (`username`),
  UNIQUE KEY `UK_51bvuyvihefoh4kp5syh2jpi5` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
insert into `acl_user` (`id`, `username`, `password`, `user_real_name`, `mobile`, `gender`, `is_expired`, `is_locked`, `is_password_expired`, `is_enabled`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) values(null,'0009785','$2a$10$8r2tGdMtSLfEHbYV/3ZmE.90ivYb7h1y7TyHy86l9mDADMA89SH2O','walter.tan','13123456789','M','','','','','*ADMIN',now(),'*ADMIN',now());
insert into `acl_user` (`id`, `username`, `password`, `user_real_name`, `mobile`, `gender`, `is_expired`, `is_locked`, `is_password_expired`, `is_enabled`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) values(null,'walter','$2a$10$8r2tGdMtSLfEHbYV/3ZmE.90ivYb7h1y7TyHy86l9mDADMA89SH2O','waltertan','13987654321','M','','','','','*ADMIN',now(),'*ADMIN',now());


# 租户表
CREATE TABLE `acl_tenant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenant_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `tenant_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `is_enabled` bit(1) DEFAULT b'0',
  `created_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenantId` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
insert into `acl_tenant` (`tenant_id`, `tenant_name`, `is_enabled`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) values('A','A租户','','*ADMIN','2020-02-15 20:09:21','*ANONYMOUS','2020-02-15 12:23:44');
insert into `acl_tenant` (`tenant_id`, `tenant_name`, `is_enabled`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) values('B','B租户','','*ADMIN','2020-02-15 20:09:49','*ANONYMOUS','2020-02-15 12:23:44');


# 用户租户表
CREATE TABLE `acl_user_tenant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_bin NOT NULL,
  `tenant_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `created_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username_tenantId` (`username`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
insert into `acl_user_tenant` (`username`, `tenant_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) values('0009785','A','*ADMIN','2020-02-15 20:11:25','*ADMIN','2020-02-15 20:11:31');
insert into `acl_user_tenant` (`username`, `tenant_id`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) values('walter','B','*ADMIN','2020-02-15 20:11:27','*ADMIN','2020-02-15 20:11:34');


# 租户数据源表
CREATE TABLE `acl_tenant_datasource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenant_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `datasource_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `driver_class` varchar(255) COLLATE utf8_bin NOT NULL,
  `jdbc_url` varchar(1000) COLLATE utf8_bin NOT NULL,
  `db_username` varchar(255) COLLATE utf8_bin NOT NULL,
  `db_password` varchar(255) COLLATE utf8_bin NOT NULL,
  `created_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_datasource` (`tenant_id`,`datasource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
insert into `acl_tenant_datasource` (`tenant_id`, `datasource_id`, `driver_class`, `jdbc_url`, `db_username`, `db_password`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) values('A','fund','com.mysql.cj.jdbc.Driver','jdbc:mysql://10.1.218.157/tenant_a_fund?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8','root','','*ADMIN','2020-02-15 23:45:51','*ADMIN','2020-02-15 23:46:00');
insert into `acl_tenant_datasource` (`tenant_id`, `datasource_id`, `driver_class`, `jdbc_url`, `db_username`, `db_password`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) values('B','fund','com.mysql.cj.jdbc.Driver','jdbc:mysql://10.1.218.157/tenant_b_fund?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8','root','','*ADMIN','2020-02-15 23:45:57','*ADMIN','2020-02-15 23:46:06');
insert into `acl_tenant_datasource` (`tenant_id`, `datasource_id`, `driver_class`, `jdbc_url`, `db_username`, `db_password`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) values('A','product','com.mysql.cj.jdbc.Driver','jdbc:mysql://10.1.218.157/tenant_a_product?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8','root','','*ADMIN','2020-02-15 23:45:51','*ADMIN','2020-02-15 23:46:00');
insert into `acl_tenant_datasource` (`tenant_id`, `datasource_id`, `driver_class`, `jdbc_url`, `db_username`, `db_password`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) values('B','product','com.mysql.cj.jdbc.Driver','jdbc:mysql://10.1.218.157/tenant_b_product?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8','root','','*ADMIN','2020-02-15 23:45:57','*ADMIN','2020-02-15 23:46:06');

