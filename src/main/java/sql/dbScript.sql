DROP DATABASE IF EXISTS `exhibcalendar`;
CREATE SCHEMA `exhibcalendar` ;


DROP TABLE IF EXISTS `exhibcalendar`.`roles`;
CREATE TABLE `exhibcalendar`.`roles` (
  `id_ro` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(20) NOT NULL UNIQUE);
INSERT INTO `exhibcalendar`.`roles` (`name`) VALUES ('USER');
INSERT INTO `exhibcalendar`.`roles` (`name`) VALUES ('ADMIN');

DROP TABLE IF EXISTS `exhibcalendar`.`users`;

CREATE TABLE `exhibcalendar`.`users` (
  `id_us` INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(110) NOT NULL,
  `login` VARCHAR(100) NOT NULL UNIQUE,
  `password` VARCHAR(50) NOT NULL,
  `phone` INTEGER(11) NOT NULL DEFAULT 1234567890,
  `balance` INTEGER(10) DEFAULT 0,
  `fk_role` INT(10) DEFAULT 1,
  `email` VARCHAR(255) NOT NULL);

ALTER TABLE `exhibcalendar`.`users` ADD FOREIGN KEY `users`(`fk_role`) REFERENCES `roles` (`id_ro`);

DROP TABLE IF EXISTS `exhibcalendar`.`cities`;
CREATE TABLE `exhibcalendar`.`cities` (
  `id_ci` INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE,
  `name` VARCHAR(100) NOT NULL UNIQUE);
DROP TABLE IF EXISTS `exhibcalendar`.`halls`;
CREATE TABLE `exhibcalendar`.`halls` (
  `id_ha` INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE,
  `name` VARCHAR(255) NOT NULL UNIQUE,
  `fk_id_ci` INTEGER NOT NULL,
  `address` VARCHAR(50) NOT NULL);

ALTER TABLE `exhibcalendar`.`halls` ADD FOREIGN KEY `halls`(`fk_id_ci`) REFERENCES `cities`(`id_ci`);
DROP TABLE IF EXISTS `exhibcalendar`.`expositions`;

CREATE TABLE `exhibcalendar`.`expositions` (
  `id_ex` INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE,
  `theme` VARCHAR(255) NOT NULL,
  `date_start` DATE,
  `date_end` DATE,
  `description` VARCHAR(255) NOT NULL,
  `fk_id_ha` INTEGER NOT NULL,
  `price` INTEGER NOT NULL );

ALTER TABLE `exhibcalendar`.`expositions` ADD FOREIGN KEY `expositions`(`fk_id_ha`) REFERENCES `halls`(`id_ha`);

DROP TABLE IF EXISTS `exhibcalendar`.`payments`;

CREATE TABLE `exhibcalendar`.`payments` (
  `id_pa` INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE,
  `fk_id_ti` INTEGER NOT NULL UNIQUE ,
  `fk_id_us` INTEGER NOT NULL,
  `date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP );

ALTER TABLE `exhibcalendar`.`payments` ADD FOREIGN KEY `payments`(`fk_id_us`) REFERENCES `users`(`id_us`);

DROP TABLE IF EXISTS `exhibcalendar`.`tickets`;

CREATE TABLE `exhibcalendar`.`tickets` (
  `id_ti` INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE,
  `number` INTEGER UNIQUE NOT NULL DEFAULT 0,
  `fk_id_ex` INTEGER NOT NULL );

ALTER TABLE `exhibcalendar`.`payments` ADD FOREIGN KEY (`fk_id_ti`) REFERENCES `tickets`(`id_ti`);
ALTER TABLE `exhibcalendar`.`tickets` ADD FOREIGN KEY `tickets`(`fk_id_ex`) REFERENCES `expositions`(`id_ex`);

INSERT INTO `exhibcalendar`.`users`
(`first_name`, `last_name`, `login`, `password`, `fk_role`, `email`,`balance`,`phone`)
VALUES ('vasya', 'pupkin', 'pupVas', 'qwerty', '1', 'pupkin@gmail.com','100','0524654152');
INSERT INTO `exhibcalendar`.`users` (`first_name`, `last_name`, `login`, `password`, `phone`, `balance`, `fk_role`, `email`)
VALUES ('adminovick', 'adminovskiy', 'admin', '12345', '254355466', '666', '2', 'admin@gmail.com');

INSERT INTO `exhibcalendar`.`cities`(`name`)VALUE ('kyiv');
INSERT INTO `exhibcalendar`.`halls`(`name`, `fk_id_ci`, `address`) VALUES ('Green', '1', 'kudriashova 7');
INSERT INTO `exhibcalendar`.`expositions`(`theme`, `price`, `date_start`, `date_end`, `description`, `fk_id_ha`)
VALUES ('Gals theme', '40', '2018-02-15', '2018-02-19', 'New gal exhibition', '1');
INSERT INTO `exhibcalendar`.`tickets`(`number`, `fk_id_ex`)
VALUES ('50067888', '1'), ('50067889', '1'), ('50067890', '1'), ('50067891', '1');
INSERT INTO `exhibcalendar`.`payments`(`fk_id_ti`, `fk_id_us`)
VALUES ('1', '1'), ('2', '1'), ('3', '1');
INSERT INTO `exhibcalendar`.`expositions` (`theme`, `date_start`, `date_end`, `description`, `fk_id_ha`, `price`)
VALUES ('Theme 3', '2018-02-15', '2018-02-19', 'Newl exhibition 2', '1', '55');
INSERT INTO `exhibcalendar`.`expositions` (`theme`, `date_start`, `date_end`, `description`, `fk_id_ha`, `price`)
VALUES ('Theme 4', '2018-02-15', '2018-02-19', 'Newl exhibition 2', '1', '44');
INSERT INTO `exhibcalendar`.`expositions` (`theme`, `date_start`, `date_end`, `description`, `fk_id_ha`, `price`)
VALUES ('Theme 5', '2018-02-15', '2018-02-19', 'Newl exhibition 2', '1', '55');
INSERT INTO `exhibcalendar`.`expositions` (`theme`, `date_start`, `date_end`, `description`, `fk_id_ha`, `price`)
VALUES ('Theme 6', '2018-02-15', '2018-02-19', 'Newl exhibition 2', '1', '22');
INSERT INTO `exhibcalendar`.`expositions` (`theme`, `date_start`, `date_end`, `description`, `fk_id_ha`, `price`)
VALUES ('Theme 7', '2018-02-15', '2018-02-19', 'Newl exhibition 2', '1', '44');
