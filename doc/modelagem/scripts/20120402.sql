# --------------------------------------------------------
# Host:                         localhost
# Database:                     prisila
# Server version:               5.5.16
# Server OS:                    Win32
# HeidiSQL version:             5.0.0.3272
# Date/time:                    2012-04-02 22:47:22
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

# Dumping structure for table prisila.cliente
CREATE TABLE IF NOT EXISTS `cliente` (
  `cd_cliente` int(10) NOT NULL AUTO_INCREMENT,
  `nm_cliente` varchar(255) NOT NULL,
  `dt_nasc_cliente` date NOT NULL,
  PRIMARY KEY (`cd_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

# Dumping data for table prisila.cliente: 3 rows
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` (`cd_cliente`, `nm_cliente`, `dt_nasc_cliente`) VALUES (1, 'José', '1990-04-02'), (2, 'João', '1986-12-03'), (3, 'Fábio', '1982-05-21');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;


# Dumping structure for table prisila.cliente_responsavel
CREATE TABLE IF NOT EXISTS `cliente_responsavel` (
  `fk_cliente` int(10) NOT NULL,
  `fk_responsavel` int(10) NOT NULL,
  KEY `Index 1` (`fk_responsavel`,`fk_cliente`),
  KEY `fk_cliente` (`fk_cliente`),
  CONSTRAINT `fk_reponsavel` FOREIGN KEY (`fk_responsavel`) REFERENCES `responsavel` (`cd_responsavel`),
  CONSTRAINT `fk_cliente` FOREIGN KEY (`fk_cliente`) REFERENCES `cliente` (`cd_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table prisila.cliente_responsavel: 1 rows
/*!40000 ALTER TABLE `cliente_responsavel` DISABLE KEYS */;
INSERT INTO `cliente_responsavel` (`fk_cliente`, `fk_responsavel`) VALUES (1, 1);
/*!40000 ALTER TABLE `cliente_responsavel` ENABLE KEYS */;


# Dumping structure for table prisila.curso
CREATE TABLE IF NOT EXISTS `curso` (
  `cd_curso` int(10) NOT NULL AUTO_INCREMENT,
  `nm_curso` varchar(255) NOT NULL,
  `fk_mod_aula` int(10) NOT NULL,
  PRIMARY KEY (`cd_curso`),
  KEY `mod_aula` (`fk_mod_aula`),
  CONSTRAINT `mod_aula` FOREIGN KEY (`fk_mod_aula`) REFERENCES `mod_aula` (`cd_mod_aula`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

# Dumping data for table prisila.curso: 1 rows
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` (`cd_curso`, `nm_curso`, `fk_mod_aula`) VALUES (1, 'Iniciação musical', 1);
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;


# Dumping structure for table prisila.etapa
CREATE TABLE IF NOT EXISTS `etapa` (
  `cd_etapa` tinyint(3) NOT NULL,
  `ds_ciclo` varchar(30) NOT NULL,
  `fk_curso` int(10) NOT NULL,
  PRIMARY KEY (`cd_etapa`),
  KEY `fk_curso` (`fk_curso`),
  CONSTRAINT `fk_curso` FOREIGN KEY (`fk_curso`) REFERENCES `curso` (`cd_curso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table prisila.etapa: 0 rows
/*!40000 ALTER TABLE `etapa` DISABLE KEYS */;
/*!40000 ALTER TABLE `etapa` ENABLE KEYS */;


# Dumping structure for table prisila.matricula
CREATE TABLE IF NOT EXISTS `matricula` (
  `cd_matricula` int(10) NOT NULL AUTO_INCREMENT,
  `dt_matricula` date NOT NULL,
  `ic_teoria` tinyint(1) NOT NULL,
  `ic_pratica` tinyint(1) NOT NULL,
  `fk_cliente` int(10) NOT NULL,
  `fk_responsavel` int(10) NOT NULL,
  `fk_tipo_curso` tinyint(3) NOT NULL,
  `fk_curso` int(10) NOT NULL,
  PRIMARY KEY (`cd_matricula`),
  KEY `cliente` (`fk_cliente`),
  KEY `responsavel` (`fk_responsavel`),
  KEY `tipo_curso` (`fk_tipo_curso`),
  KEY `curso` (`fk_curso`),
  CONSTRAINT `curso` FOREIGN KEY (`fk_curso`) REFERENCES `curso` (`cd_curso`),
  CONSTRAINT `cliente` FOREIGN KEY (`fk_cliente`) REFERENCES `cliente` (`cd_cliente`),
  CONSTRAINT `responsavel` FOREIGN KEY (`fk_responsavel`) REFERENCES `responsavel` (`cd_responsavel`),
  CONSTRAINT `tipo_curso` FOREIGN KEY (`fk_tipo_curso`) REFERENCES `tipo_curso` (`cd_tipo_curso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table prisila.matricula: 0 rows
/*!40000 ALTER TABLE `matricula` DISABLE KEYS */;
/*!40000 ALTER TABLE `matricula` ENABLE KEYS */;


# Dumping structure for table prisila.mod_aula
CREATE TABLE IF NOT EXISTS `mod_aula` (
  `cd_mod_aula` int(10) NOT NULL AUTO_INCREMENT,
  `ds_mod_aula` varchar(255) NOT NULL,
  PRIMARY KEY (`cd_mod_aula`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

# Dumping data for table prisila.mod_aula: 3 rows
/*!40000 ALTER TABLE `mod_aula` DISABLE KEYS */;
INSERT INTO `mod_aula` (`cd_mod_aula`, `ds_mod_aula`) VALUES (1, 'Prática individual'), (2, 'Teoria individual'), (3, 'Prática em grupo');
/*!40000 ALTER TABLE `mod_aula` ENABLE KEYS */;


# Dumping structure for table prisila.responsavel
CREATE TABLE IF NOT EXISTS `responsavel` (
  `cd_responsavel` int(10) NOT NULL AUTO_INCREMENT,
  `ds_cpf_responsavel` char(11) NOT NULL,
  `nm_responsavel` varchar(255) NOT NULL,
  `ds_end_responsavel` varchar(255) NOT NULL,
  `ds_cep_responsavel` char(8) NOT NULL,
  `ds_tel_responsavel` char(11) NOT NULL,
  `ds_cel_ds_end_responsavel` char(11) NOT NULL,
  `ds_email_ds_end_responsavel` varchar(255) NOT NULL,
  PRIMARY KEY (`cd_responsavel`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

# Dumping data for table prisila.responsavel: 2 rows
/*!40000 ALTER TABLE `responsavel` DISABLE KEYS */;
INSERT INTO `responsavel` (`cd_responsavel`, `ds_cpf_responsavel`, `nm_responsavel`, `ds_end_responsavel`, `ds_cep_responsavel`, `ds_tel_responsavel`, `ds_cel_ds_end_responsavel`, `ds_email_ds_end_responsavel`) VALUES (1, '12345678900', 'André', 'Rua tal', '49100000', '1332323232', '1391212121', 'jose@asd.com'), (2, '12345678900', 'Silva', 'Rua tal', '49100000', '1332323232', '1391212121', 'silva@asd.com');
/*!40000 ALTER TABLE `responsavel` ENABLE KEYS */;


# Dumping structure for table prisila.tipo_curso
CREATE TABLE IF NOT EXISTS `tipo_curso` (
  `cd_tipo_curso` tinyint(3) NOT NULL AUTO_INCREMENT,
  `nm_tipo_curso` varchar(255) NOT NULL,
  PRIMARY KEY (`cd_tipo_curso`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

# Dumping data for table prisila.tipo_curso: 2 rows
/*!40000 ALTER TABLE `tipo_curso` DISABLE KEYS */;
INSERT INTO `tipo_curso` (`cd_tipo_curso`, `nm_tipo_curso`) VALUES (1, 'Personal'), (2, 'Curricular');
/*!40000 ALTER TABLE `tipo_curso` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
