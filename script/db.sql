SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `scoa` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
CREATE SCHEMA IF NOT EXISTS `scoa` DEFAULT CHARACTER SET utf8 ;
USE `scoa` ;

-- -----------------------------------------------------
-- Table `scoa`.`discipline`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `scoa`.`discipline` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `description` TEXT NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `scoa`.`course`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `scoa`.`course` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `code` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `scoa`.`person`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `scoa`.`person` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `cpf` VARCHAR(25) NOT NULL ,
  `email` VARCHAR(255) NOT NULL ,
  `birthdate` DATE NOT NULL ,
  `password` VARCHAR(255) NOT NULL ,
  `entry` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `scoa`.`class`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `scoa`.`class` (
  `id` INT(11) NOT NULL ,
  `code` VARCHAR(45) NULL ,
  `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `scoa`.`student`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `scoa`.`student` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `person_id` INT(11) NOT NULL ,
  `cr` DECIMAL(10,2) NULL DEFAULT NULL ,
  `course_id` INT(11) NOT NULL ,
  `class_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_student_person_idx` (`person_id` ASC) ,
  INDEX `fk_student_courses1_idx` (`course_id` ASC) ,
  INDEX `fk_student_class1_idx` (`class_id` ASC) ,
  CONSTRAINT `fk_student_courses1`
    FOREIGN KEY (`course_id` )
    REFERENCES `scoa`.`course` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_student_person`
    FOREIGN KEY (`person_id` )
    REFERENCES `scoa`.`person` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_student_class1`
    FOREIGN KEY (`class_id` )
    REFERENCES `scoa`.`class` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `scoa`.`room`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `scoa`.`room` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `number` VARCHAR(45) NULL ,
  `building` VARCHAR(45) NULL ,
  `floor` INT(11) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `scoa`.`course_discipline`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `scoa`.`course_discipline` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `course_id` INT(11) NOT NULL ,
  `discipline_id` INT(11) NOT NULL ,
  `credits` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_course_discipline_course1_idx` (`course_id` ASC) ,
  INDEX `fk_course_discipline_discipline1_idx` (`discipline_id` ASC) ,
  CONSTRAINT `fk_course_discipline_course1`
    FOREIGN KEY (`course_id` )
    REFERENCES `scoa`.`course` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_course_discipline_discipline1`
    FOREIGN KEY (`discipline_id` )
    REFERENCES `scoa`.`discipline` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `scoa`.`student_discipline`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `scoa`.`student_discipline` (
  `student_id` INT(11) NOT NULL ,
  `rate` INT(11) NULL ,
  `class_time` VARCHAR(45) NULL ,
  `frequency` INT(11) NULL ,
  `room_id` INT(11) NOT NULL ,
  `course_discipline_id` INT NOT NULL ,
  PRIMARY KEY (`student_id`) ,
  INDEX `fk_Disciplines_has_student_student1_idx` (`student_id` ASC) ,
  INDEX `fk_student_discipline_room1_idx` (`room_id` ASC) ,
  INDEX `fk_student_discipline_course_discipline1_idx` (`course_discipline_id` ASC) ,
  CONSTRAINT `fk_Disciplines_has_student_student1`
    FOREIGN KEY (`student_id` )
    REFERENCES `scoa`.`student` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_student_discipline_room1`
    FOREIGN KEY (`room_id` )
    REFERENCES `scoa`.`room` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_student_discipline_course_discipline1`
    FOREIGN KEY (`course_discipline_id` )
    REFERENCES `scoa`.`course_discipline` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `scoa` ;

-- -----------------------------------------------------
-- Table `scoa`.`employee`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `scoa`.`employee` (
  `id` INT(11) NOT NULL ,
  `person_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_employee_person1_idx` (`person_id` ASC) ,
  CONSTRAINT `fk_employee_person1`
    FOREIGN KEY (`person_id` )
    REFERENCES `scoa`.`person` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `scoa`.`professor`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `scoa`.`professor` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `person_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_professor_person1_idx` (`person_id` ASC) ,
  CONSTRAINT `fk_professor_person1`
    FOREIGN KEY (`person_id` )
    REFERENCES `scoa`.`person` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `scoa`.`professor_course`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `scoa`.`professor_course` (
  `professor_id` INT(11) NOT NULL ,
  `course_id` INT(11) NOT NULL ,
  PRIMARY KEY (`professor_id`, `course_id`) ,
  INDEX `fk_professor_has_course_course1_idx` (`course_id` ASC) ,
  INDEX `fk_professor_has_course_professor1_idx` (`professor_id` ASC) ,
  CONSTRAINT `fk_professor_has_course_course1`
    FOREIGN KEY (`course_id` )
    REFERENCES `scoa`.`course` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_professor_has_course_professor1`
    FOREIGN KEY (`professor_id` )
    REFERENCES `scoa`.`professor` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `scoa` ;

INSERT INTO person VALUES(DEFAULT,"Admin", "111.111.111-11","admin@admin.com","1900-10-10",md5("12345"),111111111);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
