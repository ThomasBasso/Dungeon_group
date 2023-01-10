-- Script de restauration de l'application "Donjeons and dragons"

-- Administration de la base de données
CREATE DATABASE IF NOT EXISTS donjeon_group ;
-- GRANT SHOW DATABASES ON *.* TO donjeon IDENTIFIED BY 'secret';
-- GRANT ALL PRIVILEGES ON `donjeon_group`.* TO donjeon;
SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
USE donjeon_group ;

-- Création de la structure de la base de données

CREATE TABLE IF NOT EXISTS entity (
  e_id INT(4) NOT NULL AUTO_INCREMENT,
  e_name CHAR(30) NOT NULL,
  e_life CHAR(30) NOT NULL, 
  e_strenght DATE DEFAULT NULL,
  e_gold CHAR(30) DEFAULT NULL,
  PRIMARY KEY (e_id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS player (

  PRIMARY KEY (p_id),
) ENGINE=InnoDB;

-- CREATE TABLE IF NOT EXISTS anamnese (
--   a_patient_id INT(5) NOT NULL,
--   a_demande TEXT DEFAULT NULL,
--   a_famille TEXT DEFAULT NULL,
--   a_amis TEXT DEFAULT NULL,
--   a_travail TEXT DEFAULT NULL,
--   a_symptome TEXT DEFAULT NULL,
--   a_difficulte TEXT DEFAULT NULL,
--   a_0a3ans TEXT DEFAULT NULL,
--   a_3a6ans TEXT DEFAULT NULL,
--   a_ecole TEXT DEFAULT NULL,
--   a_college TEXT DEFAULT NULL,
--   a_lycee TEXT DEFAULT NULL,
--   a_etude TEXT DEFAULT NULL,
--   a_post_etude TEXT DEFAULT NULL,
--   a_somatique TEXT DEFAULT NULL,
--   a_psychiatrique TEXT DEFAULT NULL,
--   a_autres TEXT DEFAULT NULL,
--   a_passe TEXT DEFAULT NULL,
--   a_actuel TEXT DEFAULT NULL,
--   PRIMARY KEY (a_patient_id),
--   FOREIGN KEY (a_patient_id) REFERENCES patient(p_id)
-- );

CREATE TABLE IF NOT EXISTS anamnese (
  a_patient_id INT(5) NOT NULL,
  a_informations TEXT DEFAULT NULL,
  PRIMARY KEY (a_patient_id),
  FOREIGN KEY (a_patient_id) REFERENCES patient(p_id)
);

CREATE TABLE IF NOT EXISTS tache (
  t_id INT(5) NOT NULL AUTO_INCREMENT,
  t_libelle CHAR(50) NOT NULL,
  t_echeance DATE DEFAULT NULL,
  t_date_validation DATE DEFAULT NULL,
  PRIMARY KEY (t_id)
) ENGINE=InnoDB;

-- Alimentation des données paramètres
INSERT INTO patient (p_name, p_surname) VALUES
('Fabrice', 'Pluviot')
