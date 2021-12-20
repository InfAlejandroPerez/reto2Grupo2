-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-12-2021 a las 19:21:55
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `euskalmet`
--
CREATE DATABASE IF NOT EXISTS `euskalmet` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `euskalmet`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `datos`
--

CREATE TABLE `datos` (
  `CodEstacion` varchar(11) COLLATE utf8_spanish_ci NOT NULL,
  `Fecha` date NOT NULL,
  `Hora` time NOT NULL,
  `COmgm3` decimal(4,2) NOT NULL,
  `CO8hmgm3` decimal(4,2) NOT NULL,
  `NOgm3` decimal(4,2) NOT NULL,
  `NO2gm3` decimal(4,2) NOT NULL,
  `NOXgm3` decimal(4,2) NOT NULL,
  `PM10gm3` decimal(4,2) NOT NULL,
  `PM25gm3` decimal(4,2) NOT NULL,
  `SO2gm3` decimal(4,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `espacios_naturales`
--

CREATE TABLE `espacios_naturales` (
  `CodEspacio` varchar(11) COLLATE utf8_spanish_ci NOT NULL,
  `Nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `Descripcion` varchar(500) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estaciones`
--

CREATE TABLE `estaciones` (
  `CodEstacion` varchar(11) COLLATE utf8_spanish_ci NOT NULL,
  `CoordenadaX` decimal(13,10) NOT NULL,
  `CoordenadaY` decimal(13,10) NOT NULL,
  `CodMunicipio` varchar(11) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `esta_en`
--

CREATE TABLE `esta_en` (
  `CodMunicipio` varchar(11) COLLATE utf8_spanish_ci NOT NULL,
  `CodEspacio` varchar(11) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `favoritos_espacios`
--

CREATE TABLE `favoritos_espacios` (
  `CodUsuario` int(11) NOT NULL,
  `CodEspacio` varchar(11) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `favoritos_municipios`
--

CREATE TABLE `favoritos_municipios` (
  `CodUsuario` int(11) NOT NULL,
  `CodMunicipio` varchar(11) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hashes`
--

CREATE TABLE `hashes` (
  `CodHash` int(11) NOT NULL,
  `Url` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `municipios`
--

CREATE TABLE `municipios` (
  `CodMunicipio` varchar(11) COLLATE utf8_spanish_ci NOT NULL,
  `Nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `Descripcion` varchar(500) COLLATE utf8_spanish_ci DEFAULT NULL,
  `CodProvincia` varchar(11) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `provincia`
--

CREATE TABLE `provincia` (
  `CodProvincia` varchar(11) COLLATE utf8_spanish_ci NOT NULL,
  `Nombre` varchar(50) CHARACTER SET utf8mb4 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `provincia`
--

INSERT INTO `provincia` (`CodProvincia`, `Nombre`) VALUES
('01', 'Araba'),
('20', 'Guipuzcoa'),
('48', 'Bizkaia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `CodUsuario` int(11) NOT NULL,
  `Nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `Password` varchar(100) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `datos`
--
ALTER TABLE `datos`
  ADD PRIMARY KEY (`CodEstacion`,`Fecha`,`Hora`);

--
-- Indices de la tabla `espacios_naturales`
--
ALTER TABLE `espacios_naturales`
  ADD PRIMARY KEY (`CodEspacio`);

--
-- Indices de la tabla `estaciones`
--
ALTER TABLE `estaciones`
  ADD PRIMARY KEY (`CodEstacion`),
  ADD KEY `FK_municipios_estaciones` (`CodMunicipio`);

--
-- Indices de la tabla `esta_en`
--
ALTER TABLE `esta_en`
  ADD PRIMARY KEY (`CodMunicipio`,`CodEspacio`),
  ADD KEY `FK_estaciones_esta` (`CodEspacio`);

--
-- Indices de la tabla `favoritos_espacios`
--
ALTER TABLE `favoritos_espacios`
  ADD PRIMARY KEY (`CodUsuario`,`CodEspacio`),
  ADD KEY `Fk_espacios_favesp` (`CodEspacio`);

--
-- Indices de la tabla `favoritos_municipios`
--
ALTER TABLE `favoritos_municipios`
  ADD PRIMARY KEY (`CodUsuario`,`CodMunicipio`),
  ADD KEY `Fk_municipio_favmun` (`CodMunicipio`);

--
-- Indices de la tabla `hashes`
--
ALTER TABLE `hashes`
  ADD PRIMARY KEY (`CodHash`);

--
-- Indices de la tabla `municipios`
--
ALTER TABLE `municipios`
  ADD PRIMARY KEY (`CodMunicipio`),
  ADD KEY `Fk_provincia_municipios` (`CodProvincia`);

--
-- Indices de la tabla `provincia`
--
ALTER TABLE `provincia`
  ADD PRIMARY KEY (`CodProvincia`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`CodUsuario`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `datos`
--
ALTER TABLE `datos`
  ADD CONSTRAINT `FK_estaciones_datos` FOREIGN KEY (`CodEstacion`) REFERENCES `estaciones` (`CodEstacion`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `estaciones`
--
ALTER TABLE `estaciones`
  ADD CONSTRAINT `FK_municipios_estaciones` FOREIGN KEY (`CodMunicipio`) REFERENCES `municipios` (`CodMunicipio`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `esta_en`
--
ALTER TABLE `esta_en`
  ADD CONSTRAINT `FK_estaciones_esta` FOREIGN KEY (`CodEspacio`) REFERENCES `espacios_naturales` (`CodEspacio`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_municipios_esta` FOREIGN KEY (`CodMunicipio`) REFERENCES `municipios` (`CodMunicipio`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `favoritos_espacios`
--
ALTER TABLE `favoritos_espacios`
  ADD CONSTRAINT `Fk_espacios_favesp` FOREIGN KEY (`CodEspacio`) REFERENCES `espacios_naturales` (`CodEspacio`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Fk_usuario_favesp` FOREIGN KEY (`CodUsuario`) REFERENCES `usuarios` (`CodUsuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `favoritos_municipios`
--
ALTER TABLE `favoritos_municipios`
  ADD CONSTRAINT `Fk_municipio_favmun` FOREIGN KEY (`CodMunicipio`) REFERENCES `municipios` (`CodMunicipio`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Fk_usuario_favmun` FOREIGN KEY (`CodUsuario`) REFERENCES `usuarios` (`CodUsuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `municipios`
--
ALTER TABLE `municipios`
  ADD CONSTRAINT `Fk_provincia_municipios` FOREIGN KEY (`CodProvincia`) REFERENCES `provincia` (`CodProvincia`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
