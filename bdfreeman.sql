DROP DATABASE IF EXISTS bdfreeman;
CREATE DATABASE bdfreeman;
USE bdfreeman;

DROP TABLE IF EXISTS mundos;
CREATE TABLE mundos (
	nombre VARCHAR(30) PRIMARY KEY
);

DROP TABLE IF EXISTS jugadores;
CREATE TABLE jugadores (
	id INT PRIMARY KEY,
    vida INT NOT NULL,
    posicionX DOUBLE NOT NULL,
    posicionY DOUBLE NOT NULL,
    mundo VARCHAR(30),
    CONSTRAINT mundoJugador FOREIGN KEY (mundo) REFERENCES mundos(nombre)
);

DROP TABLE IF EXISTS enemigos;
CREATE TABLE enemigos (
	posicionX DOUBLE NOT NULL,
    posicionY DOUBLE NOT NULL,
    mundo VARCHAR(30),
    CONSTRAINT mundoEnemigo FOREIGN KEY (mundo) REFERENCES mundos(nombre)
);