use myhome;

CREATE TABLE IF NOT EXISTS `myhome`.`login_log` ( 
  `log_id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `log_date` DATETIME(6) NOT NULL,
  PRIMARY KEY(`log_id`, `log_date`),
  INDEX `daily_log-log_id` (`log_id`)  
) ENGINE=INNODB;