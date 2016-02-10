-- phpMyAdmin SQL Dump
-- version 4.4.13.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost:3306
-- Tiempo de generación: 10-02-2016 a las 14:34:31
-- Versión del servidor: 5.6.26
-- Versión de PHP: 5.5.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `cuotaafiliado`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `afiliado`
--

CREATE TABLE IF NOT EXISTS `afiliado` (
  `id` int(9) NOT NULL,
  `nombre` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `apellido1` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `apellido2` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fnac` date DEFAULT NULL,
  `falta` date DEFAULT NULL,
  `movil` int(9) DEFAULT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_empresa` int(10) DEFAULT NULL,
  `id_centro` int(10) DEFAULT NULL,
  `id_cuota` int(2) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `afiliado`
--

INSERT INTO `afiliado` (`id`, `nombre`, `apellido1`, `apellido2`, `fnac`, `falta`, `movil`, `email`, `id_empresa`, `id_centro`, `id_cuota`) VALUES
(1, 'Fernando ', 'Putadas', 'Cantadas', '2016-02-03', '2015-06-15', 654854555, 'f@gmail.com', 1, 1, 1),
(2, 'Lucas', 'Pegasus', 'Siopara', '1991-02-02', '2015-03-22', 755777415, 'pegas@hotmail.com', 2, 2, 1),
(3, 'Carlos', 'García', 'Gómez', '2016-02-03', '2016-02-03', 454444755, 'sinton_81|@hotmail.com', 1, 2, 2),
(4, 'Raúl', 'Erre', 'zeta', '2016-02-13', '2016-02-12', 45656656, 'f@gmail.com', 2, 1, 3),
(5, 'Iván', 'Gómez', 'Gómez', '2016-02-04', '2016-02-04', 4548444, 'gsfg@hotmail.com', 3, 2, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `centro`
--

CREATE TABLE IF NOT EXISTS `centro` (
  `id` int(8) NOT NULL,
  `nombre` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `direccion` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cp` int(5) DEFAULT NULL,
  `id_municipio` int(3) DEFAULT NULL,
  `id_sector` int(2) DEFAULT NULL,
  `id_empresa` int(11) DEFAULT NULL,
  `telefono` int(9) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `centro`
--

INSERT INTO `centro` (`id`, `nombre`, `direccion`, `cp`, `id_municipio`, `id_sector`, `id_empresa`, `telefono`) VALUES
(1, 'Malilla', 'calle Malilla 28', 46036, 1, 3, 3, 987544544),
(2, 'Manises', 'Manises 33', 46111, 1, 3, 2, 777888444),
(3, 'Quart', 'Quart 333', 78454, 1, 3, 3, 111444888),
(4, 'Serrano', 'serrano 22', 12444, 3, 1, 1, 12),
(5, 'fonteta', 'fonteta 145', 33333, 2, 1, 3, 3535432),
(6, 'Ana Marx', 'Ana Marx 45', 3333, 3, 3, 1, 33334566);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comarca`
--

CREATE TABLE IF NOT EXISTS `comarca` (
  `id` int(3) NOT NULL,
  `nombre` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `comarca`
--

INSERT INTO `comarca` (`id`, `nombre`) VALUES
(1, 'Rincón Ademuz'),
(2, 'Manises'),
(3, 'La Safor');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuota`
--

CREATE TABLE IF NOT EXISTS `cuota` (
  `id` int(2) NOT NULL,
  `tipocuota` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `importe` double(6,2) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `cuota`
--

INSERT INTO `cuota` (`id`, `tipocuota`, `importe`) VALUES
(1, 'Cuota ordinaria', 11.70),
(2, 'Cuota reducida', 7.75),
(3, 'Cuotas especiales jubilados', 5.50),
(4, 'Cuota desempleado', 3.30);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE IF NOT EXISTS `empresa` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cif` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `alta` date DEFAULT NULL,
  `cambio` date DEFAULT NULL,
  `contacto` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `telefono` int(9) DEFAULT NULL,
  `id_usuario` int(2) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `empresa`
--

INSERT INTO `empresa` (`id`, `nombre`, `cif`, `alta`, `cambio`, `contacto`, `telefono`, `id_usuario`) VALUES
(1, 'Mercadona', '464647777', '2016-01-08', '2016-02-18', 'Paquillo', 444888444, 1),
(2, 'Ford', '45343', '2016-02-02', '2016-02-17', 'Gregorio', 3333, 1),
(3, 'Panasonic', '2222', '2016-02-02', '2016-02-11', '333333', 333333, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `liquidacion`
--

CREATE TABLE IF NOT EXISTS `liquidacion` (
  `id` int(10) NOT NULL,
  `id_periodo` int(6) DEFAULT NULL,
  `fliquidacion` date DEFAULT NULL,
  `importe` double(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `municipio`
--

CREATE TABLE IF NOT EXISTS `municipio` (
  `id` int(3) NOT NULL,
  `nombre` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_provincia` int(2) DEFAULT NULL,
  `id_comarca` int(2) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `municipio`
--

INSERT INTO `municipio` (`id`, `nombre`, `id_provincia`, `id_comarca`) VALUES
(1, 'Ademuz', 1, 1),
(2, 'Manises', 1, 2),
(3, 'Xeraco', 1, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pago`
--

CREATE TABLE IF NOT EXISTS `pago` (
  `id` int(10) NOT NULL,
  `fpago` date DEFAULT NULL,
  `id_afiliado` int(2) DEFAULT NULL,
  `id_cuota` int(2) DEFAULT NULL,
  `id_recibo` int(2) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `pago`
--

INSERT INTO `pago` (`id`, `fpago`, `id_afiliado`, `id_cuota`, `id_recibo`) VALUES
(1, '2016-02-16', 1, 1, 1),
(2, '2016-02-08', 2, 2, 2),
(3, '2016-02-12', 1, 3, 1),
(4, '2016-02-17', 2, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagoafiliado`
--

CREATE TABLE IF NOT EXISTS `pagoafiliado` (
  `id` int(2) NOT NULL,
  `id_afiliado` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_cuota` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_recibo` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `provincia`
--

CREATE TABLE IF NOT EXISTS `provincia` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `provincia`
--

INSERT INTO `provincia` (`id`, `nombre`) VALUES
(1, 'valencia'),
(2, 'Alicante');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recibo`
--

CREATE TABLE IF NOT EXISTS `recibo` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `emision` int(12) DEFAULT NULL,
  `periodo` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `recibo`
--

INSERT INTO `recibo` (`id`, `descripcion`, `emision`, `periodo`) VALUES
(1, 'DN Enero 2016', 100034, 'M012016'),
(2, 'DN Febrero 2016', 100036, 'M022016');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sector`
--

CREATE TABLE IF NOT EXISTS `sector` (
  `id` int(2) NOT NULL,
  `nombre` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `sector`
--

INSERT INTO `sector` (`id`, `nombre`) VALUES
(1, 'agricultura'),
(2, 'coches'),
(3, 'electrónica');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(6) NOT NULL,
  `login` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `descripcion` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `login`, `password`, `descripcion`) VALUES
(1, 'ugt', 'ugt', 'usuario 1');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `afiliado`
--
ALTER TABLE `afiliado`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`);

--
-- Indices de la tabla `centro`
--
ALTER TABLE `centro`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indices de la tabla `comarca`
--
ALTER TABLE `comarca`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indices de la tabla `cuota`
--
ALTER TABLE `cuota`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indices de la tabla `liquidacion`
--
ALTER TABLE `liquidacion`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `municipio`
--
ALTER TABLE `municipio`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pago`
--
ALTER TABLE `pago`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_pago` (`id`);

--
-- Indices de la tabla `pagoafiliado`
--
ALTER TABLE `pagoafiliado`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `provincia`
--
ALTER TABLE `provincia`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `recibo`
--
ALTER TABLE `recibo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `sector`
--
ALTER TABLE `sector`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `afiliado`
--
ALTER TABLE `afiliado`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `centro`
--
ALTER TABLE `centro`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `comarca`
--
ALTER TABLE `comarca`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT de la tabla `cuota`
--
ALTER TABLE `cuota`
  MODIFY `id` int(2) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `empresa`
--
ALTER TABLE `empresa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `liquidacion`
--
ALTER TABLE `liquidacion`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `municipio`
--
ALTER TABLE `municipio`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `pago`
--
ALTER TABLE `pago`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `pagoafiliado`
--
ALTER TABLE `pagoafiliado`
  MODIFY `id` int(2) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `provincia`
--
ALTER TABLE `provincia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `recibo`
--
ALTER TABLE `recibo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `sector`
--
ALTER TABLE `sector`
  MODIFY `id` int(2) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
