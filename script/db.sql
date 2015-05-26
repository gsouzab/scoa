SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `scoa` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `scoa` ;

-- -----------------------------------------------------
-- Table `scoa`.`person`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `scoa`.`person` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `cpf` VARCHAR(25) NOT NULL ,
  `email` VARCHAR(255) NOT NULL ,
  `birthdate` DATE NOT NULL ,
  `password` VARCHAR(255) NOT NULL ,
  `entry` INT NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `scoa`.`course`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `scoa`.`course` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `code` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `scoa`.`student`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `scoa`.`student` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `person_id` INT NOT NULL ,
  `cr` DECIMAL(10,2) NULL ,
  `course_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_student_person_idx` (`person_id` ASC) ,
  INDEX `fk_student_courses1_idx` (`course_id` ASC) ,
  CONSTRAINT `fk_student_person`
    FOREIGN KEY (`person_id` )
    REFERENCES `scoa`.`person` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_student_courses1`
    FOREIGN KEY (`course_id` )
    REFERENCES `scoa`.`course` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `scoa`.`professor`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `scoa`.`professor` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `person_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_professor_person1_idx` (`person_id` ASC) ,
  CONSTRAINT `fk_professor_person1`
    FOREIGN KEY (`person_id` )
    REFERENCES `scoa`.`person` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `scoa`.`employee`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `scoa`.`employee` (
  `id` INT NOT NULL ,
  `person_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_employee_person1_idx` (`person_id` ASC) ,
  CONSTRAINT `fk_employee_person1`
    FOREIGN KEY (`person_id` )
    REFERENCES `scoa`.`person` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `scoa`.`professor_has_course`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `scoa`.`professor_has_course` (
  `professor_id` INT NOT NULL ,
  `course_id` INT NOT NULL ,
  PRIMARY KEY (`professor_id`, `course_id`) ,
  INDEX `fk_professor_has_course_course1_idx` (`course_id` ASC) ,
  INDEX `fk_professor_has_course_professor1_idx` (`professor_id` ASC) ,
  CONSTRAINT `fk_professor_has_course_professor1`
    FOREIGN KEY (`professor_id` )
    REFERENCES `scoa`.`professor` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_professor_has_course_course1`
    FOREIGN KEY (`course_id` )
    REFERENCES `scoa`.`course` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `scoa` ;

INSERT INTO person VALUES(DEFAULT,"Admin", "111.111.111-11","admin@admin.com","1900-10-10",md5("12345"),111111111);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
