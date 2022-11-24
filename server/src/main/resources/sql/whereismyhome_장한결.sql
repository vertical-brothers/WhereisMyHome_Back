# 아파트명으로 아파트 거래정보 검색 가능
# HouseDealDTO
select hd.no, hd.dealAmount, hd.dealYear, hd.dealMonth, hd.dealDay, hd.area
		      , hd.floor, hd.aptCode, hi.apartmentName as aptName, d.dongName
		      ,hi.roadName, hi.roadNamebonbun
		  from housedeal as hd
		  join houseinfo as hi on hd.aptCode=hi.aptCode
		  join dongcode d on hi.dongCode = d.dongCode
		  where hi.dongCode = 1111010100
		    and hi.apartmentName like concat('%', '청운', '%')
		    and hd.dealYear = '2020'
		  order by d.dongName, hi.apartmentName;
          
# 아파트코드로 거래정보 검색
select no, dealAmount, dealYear, dealMonth, dealDay, area, floor, cancelDealType, aptCode
from housedeal
where aptCode='11110000000002'
order by dealYear desc;

-- select * from houseinfo limit 1,10;
-- select * from housedeal limit 1,10;
-- select * from dongcode limit 1,10;

# HouseDTO 기본 틀
select aptCode, buildYear, roadName, roadNameBonbun, dong, dongCode,  apartmentName, lng, lat from houseinfo limit 1,10;

# HouseDTO 아파트코드별 검색
select aptCode, buildYear, roadName, roadNameBonbun, dong, dongCode,  apartmentName, lng, lat 
from houseinfo
where aptCode='11110000000002';

# HouseDTO 동코드검색
select aptCode, buildYear, 
		roadName, roadNameBonbun, dong, dongCode,  
		apartmentName, lng, lat 
		from houseinfo
		where dongCode='1111011500';

# HouseDTO 동 이름 검색
select aptCode, buildYear, 
		roadName, roadNameBonbun, dong, dongCode,  
		apartmentName, lng, lat 
		from houseinfo
		where dong like concat('%', '사직', '%');
        
# 아이디 중복체크
select count(*) from members
where user_id='myid';

# apartmentreview table 생성
-- -----------------------------------------------------
-- Table mydb.apartmentreview

DROP TABLE IF EXISTS apartmentreview;

CREATE TABLE IF NOT EXISTS myhome.apartmentreview (
  id INT NOT NULL auto_increment,
  aptCode BIGINT NOT NULL,
  user_id VARCHAR(16) NOT NULL,
  subject VARCHAR(45) NOT NULL,
  content VARCHAR(45) NOT NULL,
  date VARCHAR(45) NOT NULL,
  star1 VARCHAR(45) NOT NULL,
  star2 VARCHAR(45) NULL,
  star3 VARCHAR(45) NULL,
  PRIMARY KEY (id),
  INDEX fk_apartmentreview_houseinfo_idx (aptCode ASC) VISIBLE,
  INDEX fk_apartmentreview_members1_idx (user_id ASC) VISIBLE,
  CONSTRAINT fk_apartmentreview_houseinfo
    FOREIGN KEY (aptCode)
    REFERENCES myhome.houseinfo (aptCode)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_apartmentreview_members1
    FOREIGN KEY (user_id)
    REFERENCES myhome.members (user_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

# 검색 로그
# log_id : 로그별 pk
# log_date : 로깅된 날짜
# category : 검색 카테고리
# keyword : 검색 키워드

DROP TABLE IF EXISTS search_log;

CREATE TABLE IF NOT EXISTS `myhome`.`search_log` ( 
  `log_id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `log_date` DATETIME(6) NOT NULL,
  `category` varchar(2048) NOT NULL,
  `keyword` varchar(2048) NOT NULL,
  PRIMARY KEY(`log_id`, `log_date`),
  INDEX `daily_log-log_id` (`log_id`)  
) ENGINE=INNODB;

INSERT INTO search_log(log_date,category,keyword) VALUES(now(),'dongName','광화');

desc search_log;
select * from search_log;

# 로그 
select keyword, category ,count(*) as searchCount from search_log group by keyword order by searchCount desc;

select keyword, category, count(*) as searchCount from search_log where DATE(log_date) = DATE(NOW()) group by keyword order by searchCount desc;







