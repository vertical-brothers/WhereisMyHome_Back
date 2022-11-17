use myhome;

CREATE TABLE `parkinfo` (
  `parkName` varchar(40) NOT NULL,
  `gugunName` varchar(30) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phoneNum` varchar(50) DEFAULT NULL,
  `lng` varchar(30) DEFAULT NULL,
  `lnt` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;