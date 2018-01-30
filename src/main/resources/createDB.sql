CREATE TABLE IF NOT EXISTS `roles` (
  `id_ro` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(20) NOT NULL UNIQUE);
INSERT INTO `roles` (`name`) VALUES ('USER');
INSERT INTO `roles` (`name`) VALUES ('ADMIN');

CREATE TABLE IF NOT EXISTS `users` (
  `id_us` INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(110) NOT NULL,
  `login` VARCHAR(100) NOT NULL UNIQUE,
  `password` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(12) NOT NULL ,
  `balance` INTEGER(10) DEFAULT 0,
  `fk_role` INT(10) DEFAULT 1,
  `email` VARCHAR(255) NOT NULL);

ALTER TABLE `users` ADD FOREIGN KEY `users`(`fk_role`) REFERENCES `roles` (`id_ro`);

CREATE TABLE IF NOT EXISTS `cities` (
  `id_ci` INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE,
  `name` VARCHAR(100) NOT NULL UNIQUE);
DROP TABLE IF EXISTS `exhibcalendar`.`halls`;
CREATE TABLE `exhibcalendar`.`halls` (
  `id_ha` INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE,
  `name` VARCHAR(255) NOT NULL UNIQUE,
  `fk_id_ci` INTEGER NOT NULL,
  `address` VARCHAR(50) NOT NULL);

ALTER TABLE `halls` ADD FOREIGN KEY `halls`(`fk_id_ci`) REFERENCES `cities`(`id_ci`);

CREATE TABLE IF NOT EXISTS `expositions` (
  `id_ex` INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE,
  `theme` VARCHAR(255) NOT NULL,
  `date_start` DATE,
  `date_end` DATE,
  `description` VARCHAR(255) NOT NULL,
  `fk_id_ha` INTEGER NOT NULL,
  `price` INTEGER NOT NULL ,
  `tickets` INT DEFAULT 0);

ALTER TABLE `expositions` ADD FOREIGN KEY `expositions`(`fk_id_ha`) REFERENCES `halls`(`id_ha`);

CREATE TABLE IF NOT EXISTS `payments` (
  `id_pa` INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE,
  `fk_id_ti` INTEGER NOT NULL UNIQUE ,
  `fk_id_us` INTEGER NOT NULL,
  `amount` INTEGER NOT NULL DEFAULT 0,
  `date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP );

ALTER TABLE `payments` ADD FOREIGN KEY `payments`(`fk_id_us`) REFERENCES `users`(`id_us`);


CREATE TABLE IF NOT EXISTS `tickets` (
  `id_ti` INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE,
  `number` VARCHAR(244) UNIQUE NOT NULL ,
  `fk_id_ex` INTEGER NOT NULL );

ALTER TABLE `payments` ADD FOREIGN KEY (`fk_id_ti`) REFERENCES `tickets`(`id_ti`);
ALTER TABLE `tickets` ADD FOREIGN KEY `tickets`(`fk_id_ex`) REFERENCES `expositions`(`id_ex`);
