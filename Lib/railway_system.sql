CREATE DATABASE IF NOT EXISTS `railway_system` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
use `railway_system`;

DROP TABLE IF EXISTS `trips`;
CREATE TABLE `trips` (
  `ID` int NOT NULL,
  `Start` varchar(50) NOT NULL,
  `Destination` varchar(50) NOT NULL,
  `DepartureTime` varchar(50) NOT NULL,
  `ArriveTime` varchar(50) NOT NULL,
  `Date` varchar(50) NOT NULL,
  `BookedSeats` int NOT NULL,
  `Price` double NOT NULL,
  `Driver` int NOT NULL,
  `Train` int NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `trains`;
CREATE TABLE `trains` (
  `ID` int NOT NULL,
  `Capacity` int NOT NULL,
  `Description` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `passengers`;
CREATE TABLE `passengers` (
  `ID` int NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Tel` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees` (
  `ID` int NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Tel` varchar(20) NOT NULL,
  `Salary` double NOT NULL,
  `Position` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
