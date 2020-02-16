CREATE TABLE `fund_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_bin NOT NULL,
  `account_type` varchar(255) COLLATE utf8_bin NOT NULL,
  `balance_amount` decimal(10,2) NOT NULL DEFAULT '0.00',
  `created_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username_accountType` (`username`,`account_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
insert into `fund_account` (`username`, `account_type`, `balance_amount`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) values('0009785','1','0.00','*ADMIN',NOW(),'*ADMIN',NOW());
insert into `fund_account` (`username`, `account_type`, `balance_amount`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) values('walter','1','0.00','*ADMIN',NOW(),'*ADMIN',NOW());


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