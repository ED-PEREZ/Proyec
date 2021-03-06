-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-11-2018 a las 13:30:17
-- Versión del servidor: 10.1.31-MariaDB
-- Versión de PHP: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sistemacontable`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuenta`
--

CREATE TABLE `cuenta` (
  `idcuenta` int(11) NOT NULL,
  `codigo` varchar(11) CHARACTER SET utf16 COLLATE utf16_spanish_ci DEFAULT NULL,
  `nombre` varchar(50) CHARACTER SET utf16 COLLATE utf16_spanish_ci DEFAULT NULL,
  `tiposaldo` varchar(11) CHARACTER SET utf16 COLLATE utf16_spanish_ci DEFAULT NULL,
  `saldo` double DEFAULT NULL,
  `idorden` int(11) DEFAULT NULL,
  `descripcion` varchar(60) DEFAULT NULL,
  `nivel` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cuenta`
--

INSERT INTO `cuenta` (`idcuenta`, `codigo`, `nombre`, `tiposaldo`, `saldo`, `idorden`, `descripcion`, `nivel`) VALUES
(1, '1', 'activo', 'Deudor', NULL, NULL, NULL, 1),
(2, '12', 'Corriente', 'Deudor', NULL, 1, NULL, 2),
(3, '12', 'No Corriente', 'Deudor', NULL, 1, NULL, 2),
(5, '2', 'Pasivo', 'Acreedor', NULL, NULL, NULL, 1),
(6, '3', 'Patrimonio', 'Acreedor', NULL, NULL, NULL, 1),
(7, '21', 'Corriente', 'Acreedor', NULL, 2, NULL, 2),
(9, '22', 'Pasivo no corriente', 'Acreedor', NULL, 5, NULL, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partida`
--

CREATE TABLE `partida` (
  `idpartida` int(11) NOT NULL,
  `numpartida` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `concepto` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transaccion`
--

CREATE TABLE `transaccion` (
  `idtransaccion` int(11) NOT NULL,
  `monto` double NOT NULL,
  `operacion` double NOT NULL,
  `idcuenta` int(11) NOT NULL,
  `idpartida` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD PRIMARY KEY (`idcuenta`),
  ADD KEY `fk_idorden` (`idorden`);

--
-- Indices de la tabla `partida`
--
ALTER TABLE `partida`
  ADD PRIMARY KEY (`idpartida`);

--
-- Indices de la tabla `transaccion`
--
ALTER TABLE `transaccion`
  ADD PRIMARY KEY (`idtransaccion`),
  ADD KEY `fk_idcuenta` (`idcuenta`),
  ADD KEY `fk_idpartida` (`idpartida`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  MODIFY `idcuenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `partida`
--
ALTER TABLE `partida`
  MODIFY `idpartida` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `transaccion`
--
ALTER TABLE `transaccion`
  MODIFY `idtransaccion` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD CONSTRAINT `fk_idorden` FOREIGN KEY (`idorden`) REFERENCES `cuenta` (`idcuenta`);

--
-- Filtros para la tabla `transaccion`
--
ALTER TABLE `transaccion`
  ADD CONSTRAINT `fk_idcuenta` FOREIGN KEY (`idcuenta`) REFERENCES `cuenta` (`idcuenta`),
  ADD CONSTRAINT `fk_idpartida` FOREIGN KEY (`idpartida`) REFERENCES `partida` (`idpartida`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
