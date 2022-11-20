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
-- Table `myhome`.`apartmentreview`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myhome`.`apartmentreview` (
  `id` INT NOT NULL,
  `aptCode` BIGINT NOT NULL,
  `user_id` VARCHAR(16) NOT NULL,
  `subject` VARCHAR(45) NOT NULL,
  `content` VARCHAR(45) NOT NULL,
  `star1` VARCHAR(45) NOT NULL,
  `star2` VARCHAR(45) NULL,
  `star3` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_apartmentreview_houseinfo_idx` (`aptCode` ASC) VISIBLE,
  INDEX `fk_apartmentreview_members1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_apartmentreview_houseinfo`
    FOREIGN KEY (`aptCode`)
    REFERENCES `myhome`.`houseinfo` (`aptCode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_apartmentreview_members1`
    FOREIGN KEY (`user_id`)
    REFERENCES `myhome`.`members` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;





