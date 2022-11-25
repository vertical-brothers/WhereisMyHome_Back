-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema myhome
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema myhome
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `myhome` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `myhome` ;

-- -----------------------------------------------------
-- Table `myhome`.`dongcode`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myhome`.`dongcode` (
  `dongCode` VARCHAR(10) NOT NULL,
  `sidoName` VARCHAR(30) NULL DEFAULT NULL,
  `gugunName` VARCHAR(30) NULL DEFAULT NULL,
  `dongName` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`dongCode`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `myhome`.`houseinfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myhome`.`houseinfo` (
  `aptCode` BIGINT NOT NULL,
  `buildYear` INT NULL DEFAULT NULL,
  `roadName` VARCHAR(40) NULL DEFAULT NULL,
  `roadNameBonbun` VARCHAR(5) NULL DEFAULT NULL,
  `roadNameBubun` VARCHAR(5) NULL DEFAULT NULL,
  `roadNameSeq` VARCHAR(2) NULL DEFAULT NULL,
  `roadNameBasementCode` VARCHAR(1) NULL DEFAULT NULL,
  `roadNameCode` VARCHAR(7) NULL DEFAULT NULL,
  `dong` VARCHAR(40) NULL DEFAULT NULL,
  `bonbun` VARCHAR(4) NULL DEFAULT NULL,
  `bubun` VARCHAR(4) NULL DEFAULT NULL,
  `sigunguCode` VARCHAR(5) NULL DEFAULT NULL,
  `eubmyundongCode` VARCHAR(5) NULL DEFAULT NULL,
  `dongCode` VARCHAR(10) NULL DEFAULT NULL,
  `landCode` VARCHAR(1) NULL DEFAULT NULL,
  `apartmentName` VARCHAR(40) NULL DEFAULT NULL,
  `jibun` VARCHAR(10) NULL DEFAULT NULL,
  `lng` VARCHAR(30) NULL DEFAULT NULL,
  `lat` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`aptCode`),
  UNIQUE INDEX `UNIQUE` (`buildYear` ASC, `apartmentName` ASC, `jibun` ASC, `sigunguCode` ASC, `eubmyundongCode` ASC) INVISIBLE,
  INDEX `houseinfo_dongCode_dongcode_dongCode_fk_idx` (`dongCode` ASC) INVISIBLE,
  CONSTRAINT `houseinfo_dongCode_dongcode_dongCode_fk`
    FOREIGN KEY (`dongCode`)
    REFERENCES `myhome`.`dongcode` (`dongCode`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `myhome`.`members`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myhome`.`members` (
  `user_id` VARCHAR(16) NOT NULL,
  `user_name` VARCHAR(20) NOT NULL,
  `user_password` VARCHAR(32) NOT NULL,
  `user_email` VARCHAR(40) NULL DEFAULT NULL,
  `user_phone` VARCHAR(20) NULL DEFAULT NULL,
  `user_role` VARCHAR(16) NOT NULL,
  `user_del` INT NULL DEFAULT '0',
  `token` VARCHAR(1000) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `myhome`.`apartmentreview`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myhome`.`apartmentreview` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `aptCode` BIGINT NOT NULL,
  `user_id` VARCHAR(16) NOT NULL,
  `subject` VARCHAR(45) NOT NULL,
  `content` VARCHAR(45) NOT NULL,
  `date` VARCHAR(45) NOT NULL,
  `star1` VARCHAR(45) NOT NULL,
  `star2` VARCHAR(45) NULL DEFAULT NULL,
  `star3` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_apartmentreview_houseinfo_idx` (`aptCode` ASC) VISIBLE,
  INDEX `fk_apartmentreview_members1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_apartmentreview_houseinfo`
    FOREIGN KEY (`aptCode`)
    REFERENCES `myhome`.`houseinfo` (`aptCode`),
  CONSTRAINT `fk_apartmentreview_members1`
    FOREIGN KEY (`user_id`)
    REFERENCES `myhome`.`members` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `myhome`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myhome`.`board` (
  `article_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(16) NULL DEFAULT NULL,
  `subject` VARCHAR(100) NULL DEFAULT NULL,
  `content` VARCHAR(2000) NULL DEFAULT NULL,
  `hit` INT NULL DEFAULT '0',
  `register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_no`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `myhome`.`housedeal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myhome`.`housedeal` (
  `no` BIGINT NOT NULL,
  `dealAmount` VARCHAR(20) NULL DEFAULT NULL,
  `dealYear` INT NULL DEFAULT NULL,
  `dealMonth` INT NULL DEFAULT NULL,
  `dealDay` INT NULL DEFAULT NULL,
  `area` VARCHAR(20) NULL DEFAULT NULL,
  `floor` VARCHAR(4) NULL DEFAULT NULL,
  `cancelDealType` VARCHAR(1) NULL DEFAULT NULL,
  `aptCode` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`no`),
  INDEX `housedeal_aptCode_houseinfo_aptCode_fk_idx` (`aptCode` ASC) VISIBLE,
  CONSTRAINT `housedeal_aptCode_houseinfo_aptCode_fk`
    FOREIGN KEY (`aptCode`)
    REFERENCES `myhome`.`houseinfo` (`aptCode`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `myhome`.`login_log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myhome`.`login_log` (
  `log_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `log_date` DATETIME(6) NOT NULL,
  PRIMARY KEY (`log_id`, `log_date`),
  INDEX `daily_log-log_id` (`log_id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 35
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `myhome`.`parkinfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myhome`.`parkinfo` (
  `parkName` VARCHAR(40) NOT NULL,
  `gugunName` VARCHAR(30) NULL DEFAULT NULL,
  `address` VARCHAR(100) NULL DEFAULT NULL,
  `phoneNum` VARCHAR(50) NULL DEFAULT NULL,
  `lng` VARCHAR(30) NULL DEFAULT NULL,
  `lnt` VARCHAR(30) NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `myhome`.`search_log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myhome`.`search_log` (
  `log_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `log_date` DATETIME(6) NOT NULL,
  `category` VARCHAR(2048) NOT NULL,
  `keyword` VARCHAR(2048) NOT NULL,
  PRIMARY KEY (`log_id`, `log_date`),
  INDEX `daily_log-log_id` (`log_id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 37
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `myhome`.`star`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myhome`.`star` (
  `starno` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(16) NULL DEFAULT NULL,
  `apt_code` VARCHAR(16) NULL DEFAULT NULL,
  `road_name` VARCHAR(40) NULL DEFAULT NULL,
  `dong` VARCHAR(40) NULL DEFAULT NULL,
  `apartment_name` VARCHAR(40) NULL DEFAULT NULL,
  `lng` VARCHAR(30) NULL DEFAULT NULL,
  `lat` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`starno`))
ENGINE = InnoDB
AUTO_INCREMENT = 60
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `myhome`.`star_log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myhome`.`star_log` (
  `log_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `log_date` DATETIME(6) NOT NULL,
  `aptcode` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`log_id`, `log_date`),
  INDEX `daily_log-log_id` (`log_id` ASC) VISIBLE,
  INDEX `aptcode` (`aptcode` ASC) VISIBLE,
  CONSTRAINT `star_log_ibfk_1`
    FOREIGN KEY (`aptcode`)
    REFERENCES `myhome`.`houseinfo` (`aptCode`))
ENGINE = InnoDB
AUTO_INCREMENT = 38
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
