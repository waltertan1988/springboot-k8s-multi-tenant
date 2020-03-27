

CREATE DATABASE `tenant_a_fund` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
CREATE DATABASE `tenant_b_fund` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

# 在数据库 tenant_a_fund 和 tenant_b_fund 上创建以下表
CREATE TABLE `fund_account_bill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_bin NOT NULL,
  `account_type` varchar(255) COLLATE utf8_bin NOT NULL,
  `bill_code` varchar(255) COLLATE utf8_bin NOT NULL,
  `before_balance_amount` decimal(10,2) NOT NULL DEFAULT '0.00',
  `transfer_amount` decimal(10,2) NOT NULL DEFAULT '0.00',
  `created_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;