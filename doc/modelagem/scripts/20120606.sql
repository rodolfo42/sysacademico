SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `prisila` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;

-- -----------------------------------------------------
-- Table `prisila`.`cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `prisila`.`cliente` ;

CREATE  TABLE IF NOT EXISTS `prisila`.`cliente` (
  `cd_cliente` INT(10) NOT NULL AUTO_INCREMENT ,
  `nm_cliente` VARCHAR(255) NOT NULL ,
  `dt_nasc_cliente` DATE NOT NULL ,
  PRIMARY KEY (`cd_cliente`) )
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `prisila`.`responsavel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `prisila`.`responsavel` ;

CREATE  TABLE IF NOT EXISTS `prisila`.`responsavel` (
  `cd_responsavel` INT(10) NOT NULL AUTO_INCREMENT ,
  `ds_cpf_responsavel` CHAR(11) NOT NULL ,
  `nm_responsavel` VARCHAR(255) NOT NULL ,
  `ds_end_responsavel` VARCHAR(255) NOT NULL ,
  `ds_cep_responsavel` CHAR(8) NOT NULL ,
  `ds_tel_responsavel` CHAR(11) NOT NULL ,
  `ds_cel_ds_end_responsavel` CHAR(11) NOT NULL ,
  `ds_email_ds_end_responsavel` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`cd_responsavel`) )
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `prisila`.`cliente_responsavel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `prisila`.`cliente_responsavel` ;

CREATE  TABLE IF NOT EXISTS `prisila`.`cliente_responsavel` (
  `fk_cliente` INT(10) NOT NULL ,
  `fk_responsavel` INT(10) NOT NULL ,
  CONSTRAINT `fk_reponsavel`
    FOREIGN KEY (`fk_responsavel` )
    REFERENCES `prisila`.`responsavel` (`cd_responsavel` ),
  CONSTRAINT `fk_cliente`
    FOREIGN KEY (`fk_cliente` )
    REFERENCES `prisila`.`cliente` (`cd_cliente` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `Index 1` ON `prisila`.`cliente_responsavel` (`fk_responsavel` ASC, `fk_cliente` ASC) ;

CREATE INDEX `fk_cliente` ON `prisila`.`cliente_responsavel` (`fk_cliente` ASC) ;


-- -----------------------------------------------------
-- Table `prisila`.`mod_aula`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `prisila`.`mod_aula` ;

CREATE  TABLE IF NOT EXISTS `prisila`.`mod_aula` (
  `cd_mod_aula` INT(10) NOT NULL AUTO_INCREMENT ,
  `ds_mod_aula` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`cd_mod_aula`) )
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `prisila`.`curso`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `prisila`.`curso` ;

CREATE  TABLE IF NOT EXISTS `prisila`.`curso` (
  `cd_curso` INT(10) NOT NULL AUTO_INCREMENT ,
  `nm_curso` VARCHAR(255) NOT NULL ,
  `fk_mod_aula` INT(10) NOT NULL ,
  PRIMARY KEY (`cd_curso`) ,
  CONSTRAINT `mod_aula`
    FOREIGN KEY (`fk_mod_aula` )
    REFERENCES `prisila`.`mod_aula` (`cd_mod_aula` ))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `mod_aula` ON `prisila`.`curso` (`fk_mod_aula` ASC) ;


-- -----------------------------------------------------
-- Table `prisila`.`tipo_curso`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `prisila`.`tipo_curso` ;

CREATE  TABLE IF NOT EXISTS `prisila`.`tipo_curso` (
  `cd_tipo_curso` TINYINT(3) NOT NULL AUTO_INCREMENT ,
  `nm_tipo_curso` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`cd_tipo_curso`) )
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `prisila`.`etapa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `prisila`.`etapa` ;

CREATE  TABLE IF NOT EXISTS `prisila`.`etapa` (
  `cd_etapa` TINYINT(3) NOT NULL ,
  `ds_ciclo` VARCHAR(30) NOT NULL ,
  `fk_curso` INT(10) NOT NULL ,
  `fk_tipo_curso` INT(10) NOT NULL ,
  PRIMARY KEY (`cd_etapa`) ,
  CONSTRAINT `fk_curso`
    FOREIGN KEY (`fk_curso` )
    REFERENCES `prisila`.`curso` (`cd_curso` ),
  CONSTRAINT `fk_tipo_curso`
    FOREIGN KEY ()
    REFERENCES `prisila`.`tipo_curso` ()
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_curso` ON `prisila`.`etapa` (`fk_curso` ASC) ;

CREATE INDEX `fk_tipo_curso` ON `prisila`.`etapa` () ;


-- -----------------------------------------------------
-- Table `prisila`.`matricula`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `prisila`.`matricula` ;

CREATE  TABLE IF NOT EXISTS `prisila`.`matricula` (
  `cd_matricula` INT(10) NOT NULL AUTO_INCREMENT ,
  `dt_matricula` DATE NOT NULL ,
  `ic_teoria` TINYINT(1) NOT NULL ,
  `ic_pratica` TINYINT(1) NOT NULL ,
  `fk_cliente` INT(10) NOT NULL ,
  `fk_responsavel` INT(10) NOT NULL ,
  `fk_tipo_curso` TINYINT(3) NOT NULL ,
  `fk_curso` INT(10) NOT NULL ,
  PRIMARY KEY (`cd_matricula`) ,
  CONSTRAINT `curso`
    FOREIGN KEY (`fk_curso` )
    REFERENCES `prisila`.`curso` (`cd_curso` ),
  CONSTRAINT `cliente`
    FOREIGN KEY (`fk_cliente` )
    REFERENCES `prisila`.`cliente` (`cd_cliente` ),
  CONSTRAINT `responsavel`
    FOREIGN KEY (`fk_responsavel` )
    REFERENCES `prisila`.`responsavel` (`cd_responsavel` ),
  CONSTRAINT `tipo_curso`
    FOREIGN KEY (`fk_tipo_curso` )
    REFERENCES `prisila`.`tipo_curso` (`cd_tipo_curso` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `cliente` ON `prisila`.`matricula` (`fk_cliente` ASC) ;

CREATE INDEX `responsavel` ON `prisila`.`matricula` (`fk_responsavel` ASC) ;

CREATE INDEX `tipo_curso` ON `prisila`.`matricula` (`fk_tipo_curso` ASC) ;

CREATE INDEX `curso` ON `prisila`.`matricula` (`fk_curso` ASC) ;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
