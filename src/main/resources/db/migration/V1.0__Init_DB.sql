CREATE SCHEMA IF NOT EXISTS `demo_base` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `demo_base` ;

CREATE TABLE `role` (
                        `Id` varchar(255) NOT NULL,
                        `CreationTime` datetime NOT NULL,
                        `CreatorUserId` varchar(255) DEFAULT NULL,
                        `DeleterUserId` varchar(255) DEFAULT NULL,
                        `DeletionTime` datetime DEFAULT NULL,
                        `IsDeleted` bit(1) NOT NULL DEFAULT b'0',
                        `LastModificationTime` datetime DEFAULT NULL,
                        `LastModifierUserId` varchar(255) DEFAULT NULL,
                        `name` varchar(255) NOT NULL,
                        PRIMARY KEY (`Id`),
                        UNIQUE KEY `UK_7d8a768x6aiuvmsa24hqiharg` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
                         `Id` varchar(255) NOT NULL,
                         `CreationTime` datetime NOT NULL,
                         `CreatorUserId` varchar(255) DEFAULT NULL,
                         `DeleterUserId` varchar(255) DEFAULT NULL,
                         `DeletionTime` datetime DEFAULT NULL,
                         `IsDeleted` bit(1) NOT NULL DEFAULT b'0',
                         `LastModificationTime` datetime DEFAULT NULL,
                         `LastModifierUserId` varchar(255) DEFAULT NULL,
                         `about` text,
                         `address` varchar(255) DEFAULT NULL,
                         `birthday` datetime DEFAULT NULL,
                         `email` varchar(255) DEFAULT NULL,
                         `fullName` varchar(50) DEFAULT NULL,
                         `genderUser` int DEFAULT NULL,
                         `mobile` varchar(20) DEFAULT NULL,
                         `password` varchar(255) NOT NULL,
                         `userName` varchar(255) NOT NULL,
                         PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- useridentity.userinrole definition

CREATE TABLE `userinrole` (
                              `UserId` varchar(255) NOT NULL,
                              `RoleId` varchar(255) NOT NULL,
                              PRIMARY KEY (`UserId`,`RoleId`),
                              KEY `FK695l4h2yf4ydc2fs02ujhslcj` (`RoleId`),
                              CONSTRAINT `FK695l4h2yf4ydc2fs02ujhslcj` FOREIGN KEY (`RoleId`) REFERENCES `role` (`Id`),
                              CONSTRAINT `FKqwkesckfaba3krrr8qkpl2gtj` FOREIGN KEY (`UserId`) REFERENCES `users` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Applicant` (
                             `Id` varchar(36) NOT NULL,
                             `FirstName` varchar(255) NOT NULL,
                             `LastName` varchar(255) NOT NULL,
                             `IdentityCode` varchar(36) NOT NULL,
                             PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `LoanType` (
                            `Id` varchar(36) NOT NULL,
                            `Name` varchar(255) NOT NULL,
                            `InterestRate` decimal NOT NULL,
                            `Currency` varchar(36) NOT NULL,
                            `LoanLimit` datetime DEFAULT NULL,
                            PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Facility` (
                            `Id` varchar(36) NOT NULL,
                            `NumberCard` varchar(255) NOT NULL,
                            `TotalLimit` decimal NOT NULL,
                            `Currency` varchar(36) NOT NULL,
                            `StartDate` datetime DEFAULT NULL,
                            `EndDate` datetime DEFAULT NULL,
                            PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `HistoryTransaction` (
                                                        `Id` varchar(36) NOT NULL,
                                                        `Action` varchar(255) NOT NULL,
                                                        `FacilityId` varchar(255) NOT NULL,
                                                        `LoanTypeId` varchar(255) NOT NULL,
                                                        `Amount` decimal NOT NULL,
                                                        PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `LoanManagement` (
                                                    `Id` varchar(36) NOT NULL,
                                                    `PaymentMode` varchar(255) NOT NULL,
                                                    `FacilityId` varchar(255) NOT NULL,
                                                    `LoanTypeId` varchar(255) NOT NULL,
                                                    `TotalLimit` decimal NOT NULL,
                                                    `CurrentAmount` decimal NOT NULL,
                                                    `Status` varchar(255) NOT NULL,
                                                    `StartDate` datetime DEFAULT NULL,
                                                    `EndDate` datetime DEFAULT NULL,
                                                    PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
