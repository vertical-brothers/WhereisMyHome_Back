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
order by dealYear, dealMonth;

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




