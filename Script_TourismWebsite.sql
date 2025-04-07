-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema tourism_website
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tourism_website
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tourism_website` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `tourism_website` ;

-- -----------------------------------------------------
-- Table `tourism_website`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tourism_website`.`admin` (
  `ad_useradmin` VARCHAR(255) NOT NULL,
  `ad_password` VARCHAR(255) NULL DEFAULT NULL,
  `ad_state` INT NULL DEFAULT NULL,
  PRIMARY KEY (`ad_useradmin`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `tourism_website`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tourism_website`.`user` (
  `US_ID` VARCHAR(8) NOT NULL,
  `US_Name` VARCHAR(45) NOT NULL,
  `US_Password` VARCHAR(45) NOT NULL,
  `US_FullName` VARCHAR(45) NOT NULL,
  `US_Gender` INT NOT NULL,
  `US_Email` VARCHAR(100) NOT NULL,
  `US_PhoneNumber` VARCHAR(10) NOT NULL,
  `US_Address` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`US_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `tourism_website`.`destination`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tourism_website`.`destination` (
  `DES_ID` INT NOT NULL,
  `DES_Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`DES_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `tourism_website`.`tour_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tourism_website`.`tour_type` (
  `TT_ID` INT NOT NULL,
  `TT_TypeName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`TT_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `tourism_website`.`tour`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tourism_website`.`tour` (
  `TOUR_ID` VARCHAR(8) NOT NULL,
  `TOUR_Title` VARCHAR(200) NOT NULL,
  `TOUR_Description` TEXT NOT NULL,
  `TOUR_Day` VARCHAR(45) NOT NULL,
  `TOUR_Slot` INT NOT NULL,
  `TOUR_DepartureDay` DATE NOT NULL,
  `TOUR_BackDay` DATE NOT NULL,
  `TT_ID` INT NOT NULL,
  `DES_ID` INT NOT NULL,
  `TOUR_UnitPrice` DOUBLE NOT NULL,
  `TOUR_Image` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`TOUR_ID`),
  INDEX `FKgxr8olu2tsj6vhb42s6rvts6q` (`DES_ID` ASC) VISIBLE,
  INDEX `FKi4xypxbk231cgvfqgc9w5ohhu` (`TT_ID` ASC) VISIBLE,
  CONSTRAINT `FK_DES`
    FOREIGN KEY (`DES_ID`)
    REFERENCES `tourism_website`.`destination` (`DES_ID`),
  CONSTRAINT `FK_TT`
    FOREIGN KEY (`TT_ID`)
    REFERENCES `tourism_website`.`tour_type` (`TT_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `tourism_website`.`booking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tourism_website`.`booking` (
  `BOOKING_ID` VARCHAR(8) NOT NULL,
  `BOOKING_Time` DATETIME NOT NULL,
  `BOOKING_Quantity` INT NOT NULL,
  `BOOKING_TotalMoney` DOUBLE NOT NULL,
  `US_ID` VARCHAR(8) NOT NULL,
  `tour_TOUR_ID` VARCHAR(8) NOT NULL,
  PRIMARY KEY (`BOOKING_ID`),
  INDEX `user_booking_fk_idx` (`US_ID` ASC) VISIBLE,
  INDEX `fk_booking_tour1_idx` (`tour_TOUR_ID` ASC) VISIBLE,
  CONSTRAINT `user_booking_fk`
    FOREIGN KEY (`US_ID`)
    REFERENCES `tourism_website`.`user` (`US_ID`),
  CONSTRAINT `fk_booking_tour1`
    FOREIGN KEY (`tour_TOUR_ID`)
    REFERENCES `tourism_website`.`tour` (`TOUR_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
