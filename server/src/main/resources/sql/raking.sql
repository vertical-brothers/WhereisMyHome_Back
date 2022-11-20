-- token 컬럼 추가 
USE myhome;
ALTER TABLE `members` ADD `token` VARCHAR(1000) NULL DEFAULT NULL;


select * from houseinfo;

-- 로그테이블 추가

CREATE TABLE `star_log` (
  `log_id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `log_date` DATETIME(6) NOT NULL,
  `aptcode` bigint DEFAULT NULL,
  PRIMARY KEY(`log_id`, `log_date`),
  INDEX `daily_log-log_id` (`log_id`),
  FOREIGN KEY (`aptcode`) REFERENCES `houseinfo` (`aptcode`)
) ENGINE=INNODB;
 

--  insert into star_log (`log_date`,`aptcode`) values (now(), 11110000000004);
--  insert into star_log (`log_date`,`aptcode`) values (now(), 11215000000082);