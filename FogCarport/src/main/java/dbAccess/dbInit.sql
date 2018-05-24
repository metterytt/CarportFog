INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Bræt 25x200 trykimp.', 'm', '5495');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Bræt 25x125 trykimp.', 'm', '3195');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Lægte 38x73 ubeh.', 'm', '1395');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Spærtræ 45x195 ubeh.', 'm', '4395');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Stolpe 97x97 trykimp.', 'stk.', '32700');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Bræt 19x100 trykimp.', 'm', '795');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Tagplader Plastmo blåtonet', 'stk.', '33000');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Plastmo bundskruer', 'stk.', '250');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Hulbånd, 1x20mm', 'm', '2100');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Universalbeslag 190mm højre', 'stk.', '4400');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Universalbeslag 190mm venstre', 'stk.', '4400');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Skruer, 4,5x60mm', 'stk.', '25');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Beslagskruer, 4,0x50mm', 'stk.', '110');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Bræddebolte 10x120mm', 'stk.', '500');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Firkantskiver 40x40x11mm', 'stk.', '400');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Lægte 38x73 ubeh.', 'm', '1495');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Reglar 45x95mm, ubeh.', 'm', '1895');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Bræt 25x150 trykimp.', 'm', '3395');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Byg-selv-spær', 'stk.', '39995');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Bræt 25x50mm, trykimp.', 'm', '995');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Tagsten B&C dobbelt-S sort', 'stk.', '795');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Rygsten B&C sort', 'stk.', '4995');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Toplægteholder B&C', 'stk.', '2495');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Rygstenbeslag B&C', 'stk.', '995');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Bindere B&C', 'stk.', '200');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Skruer, 5,0x100mm', 'stk.', '190');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Skruer, 4,5x70mm', 'stk.', '50');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Skruer, 4,5x50mm', 'stk.', '60');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Stalddørsgreb 50x75', 'sæt', '21900');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('T-hængsel 390mm', 'stk.', '10900');
INSERT INTO `carport`.`products` (`name`, `uom`, `price`) VALUES ('Vinkelbeslag 35', 'stk.', '595');

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema carport
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `carport` ;

-- -----------------------------------------------------
-- Schema carport
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `carport` DEFAULT CHARACTER SET latin1 ;
USE `carport` ;

-- -----------------------------------------------------
-- Table `carport`.`customercalculations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`customercalculations` ;

CREATE TABLE IF NOT EXISTS `carport`.`customercalculations` (
  `customercalculations_id` INT(11) NOT NULL AUTO_INCREMENT,
  `cp_length` INT(11) NOT NULL,
  `cp_width` INT(11) NOT NULL,
  `roof_angle` INT(11) NULL DEFAULT NULL,
  `shed_length` INT(11) NULL DEFAULT NULL,
  `shed_width` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`customercalculations_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `carport`.`customers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`customers` ;

CREATE TABLE IF NOT EXISTS `carport`.`customers` (
  `customerID` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `phonenumber` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`customerID`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `carport`.`employees`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`employees` ;

CREATE TABLE IF NOT EXISTS `carport`.`employees` (
  `empID` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`empID`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `carport`.`orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`orders` ;

CREATE TABLE IF NOT EXISTS `carport`.`orders` (
  `orderID` INT(11) NOT NULL AUTO_INCREMENT,
  `customerID` INT(11) NOT NULL,
  `length` INT(11) NOT NULL,
  `width` INT(11) NOT NULL,
  `roof_angle` INT(11) NULL DEFAULT NULL,
  `shed_length` INT(11) NULL DEFAULT NULL,
  `shed_width` INT(11) NULL DEFAULT NULL,
  `price` INT(11) NOT NULL,
  `empID` INT(11) NOT NULL,
  `order_placed` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`orderID`),
  INDEX `fk_orders_employees1_idx` (`empID` ASC),
  INDEX `customerID_idx` (`customerID` ASC),
  CONSTRAINT `customerID`
    FOREIGN KEY (`customerID`)
    REFERENCES `carport`.`customers` (`customerID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `empID`
    FOREIGN KEY (`empID`)
    REFERENCES `carport`.`employees` (`empID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `carport`.`products`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`products` ;

CREATE TABLE IF NOT EXISTS `carport`.`products` (
  `productID` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `uom` VARCHAR(45) NOT NULL,
  `price` INT(11) NOT NULL,
  PRIMARY KEY (`productID`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `carport`.`lineitems`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`lineitems` ;

CREATE TABLE IF NOT EXISTS `carport`.`lineitems` (
  `lineitemsID` INT(11) NOT NULL AUTO_INCREMENT,
  `orderID` INT(11) NOT NULL,
  `productID` INT(11) NOT NULL,
  `use_in` VARCHAR(200) NULL DEFAULT NULL,
  `quantity` DOUBLE NOT NULL,
  PRIMARY KEY (`lineitemsID`),
  INDEX `fk_lineitems_orders_idx` (`orderID` ASC),
  INDEX `fk_lineitems_products1_idx` (`productID` ASC),
  CONSTRAINT `fk_lineitems_orders`
    FOREIGN KEY (`orderID`)
    REFERENCES `carport`.`orders` (`orderID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_lineitems_products1`
    FOREIGN KEY (`productID`)
    REFERENCES `carport`.`products` (`productID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
