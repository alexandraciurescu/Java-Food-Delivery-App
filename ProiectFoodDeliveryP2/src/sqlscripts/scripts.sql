DROP TABLE IF EXISTS burger, fries, milkshake, client, deliveryDriver;

CREATE TABLE `fooddelivery`.`burger` (
                                        `name` VARCHAR(45) NOT NULL,
                                        `price` DOUBLE NULL,
                                        `description` VARCHAR(45) NULL,
                                        `typeOfMeat` VARCHAR(25) NULL,
                                        `sauce` VARCHAR(30) NULL,
                                        `cheese` TINYINT(1) NULL,
                                        PRIMARY KEY (`name`));


CREATE TABLE `fooddelivery`.`fries` (
                                        `name` VARCHAR(45) NOT NULL,
                                        `price` DOUBLE NULL,
                                        `description` VARCHAR(45) NULL,
                                        `type` VARCHAR(45) NULL,
                                        `size` VARCHAR(25) NULL,
                                        `topping` VARCHAR(30) NULL,
                                        PRIMARY KEY (`name`));

CREATE TABLE `fooddelivery`.`milkshake` (
                                        `name` VARCHAR(45) NOT NULL,
                                        `price` DOUBLE NULL,
                                        `description` VARCHAR(45) NULL,
                                        `flavour` VARCHAR(45) NULL,
                                        `topping` VARCHAR(25) NULL,
                                        `whippingCream` TINYINT(1) NULL,
                                         PRIMARY KEY (`name`));

CREATE TABLE `fooddelivery`.`client` (
                                            `name` VARCHAR(45) NOT NULL,
                                            `phone` VARCHAR(15) NULL,
                                            `email` VARCHAR(45) NULL,
                                            `address` VARCHAR(45) NULL,
                                            `card` VARCHAR(25) NULL,
                                             PRIMARY KEY (`name`));

CREATE TABLE `fooddelivery`.`deliveryDriver` (
                                         `name` VARCHAR(45) NOT NULL,
                                         `phone` VARCHAR(15) NULL,
                                         `email` VARCHAR(45) NULL,
                                         `rating` DOUBLE NULL,
                                         `vehicle` VARCHAR(25) NULL,
                                         PRIMARY KEY (`name`));

