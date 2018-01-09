DROP DATABASE IF EXISTS `exhibcalendar`;
CREATE SCHEMA `exhibcalendar` ;
DROP TABLE IF EXISTS `exhibcalendar`.`users`;
CREATE TABLE `exhibcalendar`.`users` (
  `id_us` INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(110) NOT NULL,
  `login` VARCHAR(100) NOT NULL UNIQUE,
  `password` VARCHAR(50) NOT NULL,
  `phone` INTEGER(15) NOT NULL DEFAULT 0000000000,
  `balance` INTEGER(10) DEFAULT 0,
  `role` INT(10) DEFAULT 0,
  `email` VARCHAR(255) NOT NULL);

DROP TABLE IF EXISTS `exhibcalendar`.`halls`;

CREATE TABLE `exhibcalendar`.`halls` (
  `id_ha` INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE,
  `name` VARCHAR(255) NOT NULL UNIQUE,
  `city` VARCHAR(50) NOT NULL);

DROP TABLE IF EXISTS `exhibcalendar`.`expositions`;

CREATE TABLE `exhibcalendar`.`expositions` (
  `id_ex` INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE,
  `name` VARCHAR(255) NOT NULL,
  `date_start` DATETIME,
  `date_end` DATETIME,
  `description` VARCHAR(255) NOT NULL,
  `theme_name` VARCHAR(255) NOT NULL,
  `fk_id_ha` INTEGER NOT NULL,
  `price` INTEGER NOT NULL );

ALTER TABLE `exhibcalendar`.`expositions` ADD FOREIGN KEY `expositions`(`fk_id_ha`) REFERENCES `halls`(`id_ha`);

DROP TABLE IF EXISTS `exhibcalendar`.`payments`;

CREATE TABLE `exhibcalendar`.`payments` (
  `id_pa` INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE,
  `fk_id_ti` INTEGER NOT NULL ,
  `fk_id_us` INTEGER NOT NULL );

ALTER TABLE `exhibcalendar`.`payments` ADD FOREIGN KEY `payments`(`fk_id_us`) REFERENCES `users`(`id_us`);

DROP TABLE IF EXISTS `exhibcalendar`.`tickets`;

CREATE TABLE `exhibcalendar`.`tickets` (
  `id_ti` INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE,
  `number` INTEGER UNIQUE NOT NULL DEFAULT 0,
  `fk_id_ex` INTEGER NOT NULL );

ALTER TABLE `exhibcalendar`.`payments` ADD FOREIGN KEY (`fk_id_ti`) REFERENCES `tickets`(`id_ti`);
ALTER TABLE `exhibcalendar`.`tickets` ADD FOREIGN KEY `tickets`(`fk_id_ex`) REFERENCES `expositions`(`id_ex`);

INSERT INTO `exhibcalendar`.`users`
(`first_name`, `last_name`, `login`, `password`, `role`, `email`,`balance`,`phone`) VALUES ('vasya', 'pupkin', 'pupVas', 'qwerty', '0', 'pupkin@gmail.com','100','0524654152');
