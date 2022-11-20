-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `myhome` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `myhome` ;

-- -----------------------------------------------------
DROP TABLE IF EXISTS `myhome`.`members` ;

CREATE TABLE IF NOT EXISTS `myhome`.`members` (
  `user_id` VARCHAR(16) NOT NULL,
  `user_name` VARCHAR(20) NOT NULL,
  `user_password` VARCHAR(32) NOT NULL,
  `user_email` VARCHAR(40) NULL DEFAULT NULL,
  `user_phone` VARCHAR(20) NULL DEFAULT NULL,
  `user_role` VARCHAR(16) NOT NULL,
  `user_del` INT DEFAULT 0,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

insert into `myhome`.`members` (user_id, user_name, user_password, user_email, user_phone, user_role)
values     ('admin', '관리자', '1234', 'admin@google.com', '010-1234-5678', 'admin'),
	('ssafy', '김싸피', '1234', 'ssafy@ssafy.com', '010-1234-5678', 'member');
    
update members set user_password=md5(user_password);

-- -----------------------------------------------------
DROP TABLE IF EXISTS `myhome`.`board` ;

CREATE TABLE IF NOT EXISTS `myhome`.`board` (
  `article_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(16) NULL DEFAULT NULL,
  `subject` VARCHAR(100) NULL DEFAULT NULL,
  `content` VARCHAR(2000) NULL DEFAULT NULL,
  `hit` INT NULL DEFAULT 0,
  `register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_no`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
DROP TABLE IF EXISTS `myhome`.`star` ;

CREATE TABLE IF NOT EXISTS `myhome`.`star` (
	`star_no` INT NOT NULL AUTO_INCREMENT,
  `dongCode` varchar(10) NOT NULL,
  `dealYM` varchar(8) NOT NULL,
  `user_id` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`star_no`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


    
commit;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;