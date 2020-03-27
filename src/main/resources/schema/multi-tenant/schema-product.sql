
CREATE DATABASE `tenant_a_product` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
CREATE DATABASE `tenant_b_product` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

# 在数据库 tenant_a_product 和 tenant_a_product 上创建以下表
CREATE TABLE `product_spu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `spu_seq` varchar(255) COLLATE utf8_bin NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `created_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_spu_seq` (`spu_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;