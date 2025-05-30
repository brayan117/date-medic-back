-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS clinica;

-- Usar la base de datos
USE clinica;

-- Crear la tabla specialty si no existe
CREATE TABLE IF NOT EXISTS specialty (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_specialty_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
