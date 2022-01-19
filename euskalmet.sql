-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-01-2022 a las 17:01:20
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
-- Estructura de tabla para la tabla `datosdiarios`
--

CREATE TABLE `datosdiarios` (
  `CodEstacion` varchar(50) NOT NULL,
  `Date` varchar(50) NOT NULL,
  `COmgm3` decimal(4,2) DEFAULT NULL,
  `Co8hmgm3` decimal(4,2) DEFAULT NULL,
  `NOgm3` decimal(4,2) DEFAULT NULL,
  `NO2gm3` decimal(4,2) DEFAULT NULL,
  `NOXgm3` decimal(4,2) DEFAULT NULL,
  `PM10gm3` decimal(4,2) DEFAULT NULL,
  `PM25gm3` decimal(4,2) DEFAULT NULL,
  `S2gm3` decimal(4,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `datoshorarios`
--

CREATE TABLE `datoshorarios` (
  `CodEstacion` varchar(11) COLLATE utf8_spanish_ci NOT NULL,
  `Fecha` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `Hora` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `COmgm3` decimal(4,2) DEFAULT NULL,
  `CO8hmgm3` decimal(4,2) DEFAULT NULL,
  `NOgm3` decimal(4,2) DEFAULT NULL,
  `NO2gm3` decimal(4,2) DEFAULT NULL,
  `NOXgm3` decimal(4,2) DEFAULT NULL,
  `PM10gm3` decimal(4,2) DEFAULT NULL,
  `PM25gm3` decimal(4,2) DEFAULT NULL,
  `SO2gm3` decimal(4,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `datosindice`
--

CREATE TABLE `datosindice` (
  `CodEstacion` varchar(11) NOT NULL,
  `Date` varchar(50) NOT NULL,
  `HourGMT` varchar(50) NOT NULL,
  `COmgm3` decimal(4,2) NOT NULL,
  `CO8hmgm3` decimal(4,2) NOT NULL,
  `NOgm3` decimal(4,2) NOT NULL,
  `NO2` decimal(4,2) NOT NULL,
  `NO2ICA` varchar(50) NOT NULL,
  `NOXgm3` decimal(4,2) NOT NULL,
  `PM10` decimal(4,2) NOT NULL,
  `PM10ICA` varchar(50) NOT NULL,
  `PM25` decimal(4,2) NOT NULL,
  `PM25ICA` varchar(50) NOT NULL,
  `SO2` decimal(4,2) NOT NULL,
  `SO2ICA` varchar(50) NOT NULL,
  `ICAEstacion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `espacios_naturales`
--

CREATE TABLE `espacios_naturales` (
  `CodEspacio` varchar(11) COLLATE utf8_spanish_ci NOT NULL,
  `Nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `Descripcion` varchar(500) COLLATE utf8_spanish_ci DEFAULT NULL,
  `codMunicipio` varchar(11) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `espacios_naturales`
--

INSERT INTO `espacios_naturales` (`CodEspacio`, `Nombre`, `Descripcion`, `codMunicipio`) VALUES
('1', '\"Piscinas fluviales de Espejo\"', '\"<p>Las piscinas fluviales de Espejo se hallan en el territorio hist&oacute rico de &Aacute lava, en el municipio de Valdegov&iacute a, dentro de la Cuadrilla de A&ntilde ana.    Este bello entorno, emplazado en el r&iacute o Omecillo, se convierte en lugar de descanso para muchos lugare&ntilde os y visitantes durante los meses de verano.    Cuenta con amplios aparcamientos a ambos lados del r&iacute o, una zona de ba&ntilde o y un &aacute rea de', '\"055'),
('10', '\"Playa de Laidatxu\"', '\"<p>La playa de Laidatxu se halla en el municipio vizca&iacute no de <a href=\\\"https://turismo.euskadi.eus/es/localidades/mundaka/aa30-12375/es/\\\" target=\\\"_blank\\\">Mundaka</a>, muy cerca del <strong>casco urbano</strong>.    De dimensiones peque&ntilde as y <strong>arena fina y dorada</strong>, la calidad de sus aguas es muy buena, por lo que es muy recomendable para familias con ni&ntilde os.    </p><p>&nbsp </p><p>Considerada como u<strong>na ', '\"068'),
('11', '\"Playa de Las Arenas\"', '\"<p>La playa de Las Arenas se encuentra en el municipio vizca&iacute no de <a href=\\\"https://turismo.euskadi.eus/es/localidades/getxo/aa30-12375/es/\\\" target=\\\"_blank\\\">Getxo</a>, en el Abra de Bilbao, junto al muelle de Churruca y muy pr&oacute xima al <a href=\\\"https://turismo.euskadi.eus/es/top10/patrimonio-cultural/puente-colgante-de-bizkaia/aa30-12376/es/\\\" target=\\\"_blank\\\">Puente de Bizkaia</a>, monumento declarado Patrimonio de la Humanid', '\"044'),
('12', '\"Playa de Ondarreta\"', '\"<p>Situada en el extremo oeste de la capital donostiarra, al abrigo del monte Igeldo y frente a la isla de Santa Clara, esta playa de fina arena dorada es una de las m&aacute s elegantes de Gipuzkoa.    Ondarreta, con sus 600 metros de longitud, dispone de una variada oferta deportiva. Durante los meses de verano, se organizan cursos de nataci&oacute n y voleibol. Asimismo, es posible practicar numerosas actividades acu&aacute ticas y posee, ade', '\"069'),
('13', '\"Playa de Zarautz\"', '\"<p>La playa de <a href=\\\"https://turismo.euskadi.eus/es/localidades/zarautz/aa30-12375/es/\\\">Zarautz</a> llama especialmente la atenci&oacute n por su extensa longitud, con 2.500 metros de fina arena dorada.</p><p>&nbsp </p><p>Este hermoso arenal, con sus llamativos toldos, cuenta con un <strong>animado paseo mar&iacute timo</strong> repleto de bares, cafeter&iacute as y restaurantes. La playa est&aacute  abierta al mar y es muy conocida por las', '\"079'),
('14', '\"Playa de la Zurriola\"', '\"<p>La playa de la Zurriola se halla en el barrio donostiarra de Gros, bajo la atenta mirada del Palacio de Congresos y Auditorio Kursaal. Antiguamente, estuvo formada por grandes arenales ubicados entre la desembocadura del r&iacute o Urumea y el monte Ul&iacute a. En la d&eacute cada de los 90, comenzaron los trabajos de rehabilitaci&oacute n y se procedi&oacute  a la construcci&oacute n de la actual playa, de <strong>fina arena dorada y fuerte', '\"069'),
('15', '\"Zierbena-Puerto\"', '\"<p>Situada en la costa occidental de Bizkaia, esta playa artificial de tan s&oacute lo 100 metros de longitud se encuentra en el propio puerto pesquero de Zierbena.    Esta peque&ntilde a playa se asoma en bajamar. Sus aguas tranquilas son seguras para los ba&ntilde istas y sobre todo, para los m&aacute s peque&ntilde os. En verano, dispone de varios servicios, como duchas, hondartzainas, socorristas...    El lugar mantiene su encanto y cierto s', '\"913'),
('2', '\"Playa de Arrigunaga\"', '\"<p>La playa de Arrigunaga se encuentra en el municipio de Getxo, en Bizkaia. Ubicada bajo los acantilados de La Galea, ofrece hermosas vistas al Abra y al Molino de Aixerrota.    Adem&aacute s de poder tomar el sol y un agradable ba&ntilde o, la playa de Arrigunaga ofrece al visitante la posibilidad de realizar numerosas actividades como el <strong>surf </strong>o el skate. Aunque es de f&aacute cil acceso, resulta algo peligrosa, ya que su fond', '\"044'),
('3', '\"Playa de Barinatxe\"', '\"<p>Enclavada entre los municipios vizca&iacute nos de Sopela y Getxo, la playa de Barinatxe est&aacute  compuesta por tres elementos vitales de la naturaleza: agua de una inmensa bravura, fina arena y un aire vol&aacute til que emana una pureza solamente propia de enclaves tan caracter&iacute sticos como este. Precisamente gracias a su innato e impredecible ecosistema, Barinatxe se ha ganado el nombre de &ldquo La Salvaje&rdquor .    Se trata de', '\"044'),
('4', '\"Playa de Ereaga\"', '\"<p>La playa de Ereaga se encuentra en el municipio de Getxo, entre el puerto viejo de Algorta y el espig&oacute n de Arriluze. Con sus m&aacute s de <strong>800 metros de fina arena</strong>, este hermoso arenal est&aacute  muy bien equipado de cara a la &eacute poca estival.    Por otro lado, son muchos los deportes que se practican en esta playa: surf, pirag&uuml ismo, voleibol y vela, entre otros. Pero, sin duda, Ereaga es muy conocida por lo', '\"044'),
('5', '\"Playa de Gorrondatxe (Azkorri)\"', '\"<p><strong>Gorrondatxe</strong> es la cuarta playa de <a href=\\\"https://turismo.euskadi.eus/es/localidades/getxo/aa30-12375/es/\\\">Getxo</a>&nbsp y&nbsp quiz&aacute  la menos accesible, pero la m&aacute s limpia. Conocido tambi&eacute n por el nombre de <strong>Azkorri</strong>, este arenal se encuentra alejado del casco urbano del municipio al que pertenece, pero pr&oacute ximo a la vecina localidad de Berango.</p><p>&nbsp </p><p>La playa est&aa', '\"044'),
('6', '\"Playa de Hondarribia\"', '\"<p>La playa de <a href=\\\"https://turismo.euskadi.eus/es/localidades/hondarribia/aa30-12375/es/\\\">Hondarribia</a> est&aacute  ubicada en el extremo oriental del litoral guipuzcoano, concretamente en la hermosa <a href=\\\"https://turismo.euskadi.eus/es/espacios-naturales/bahia-de-txingudi-y-hondarribia/aa30-12375/es/\\\">bah&iacute a de Txingudi</a>, junto a la desembocadura del r&iacute o Bidasoa.</p><p>&nbsp </p><p>Este hermoso arenal de <strong>fi', '\"036'),
('7', '\"Playa de Hondartzape\"', '\"<p>La playa de Hondartzape est&aacute  ubicada en el municipio vizca&iacute no de Mundaka, a 36 km de Bilbao. La principal caracter&iacute stica de esta playa son sus rocas y su arena negra, formando as&iacute , en bajamar, una <strong>peque&ntilde a y bonita cala</strong>.    La playa cuenta en sus inmediaciones con unas <strong>espectaculares vistas</strong>, dado que se encuentra dentro de la Reserva de la Biosfera de Urdaibai, espacio natura', '\"068'),
('8', '\"Playa de La Arena\"', '\"<p>Situada entre Muskiz y Zierbena, la hermosa playa de La Arena tiene 966 metros de longitud y es<strong> uno de los rincones preferidos de los surfistas</strong> de la Margen Izquierda del Gran Bilbao.    Es la &uacute ltima playa de Euskadi en su l&iacute mite oeste. Tiene fundamentalmente tres olas principales: la izquierda de Pobe&ntilde a, en la zona oeste de la playa, el Centro y la derecha del Vivero, en la zona este de la playa. Es en e', '\"913'),
('9', '\"Playa de La Concha\"', '\"<p>La incomparable <strong>playa de La Concha</strong> es una de las se&ntilde as de identidad de <a href=\\\"https://turismo.euskadi.eus/es/top10/localidades/donostia-san-sebastian/aa30-12375/es/\\\">San Sebasti&aacute n</a>. Situada en pleno centro de la ciudad, ofrece al visitante la oportunidad de disfrutar de magn&iacute ficas vistas, como la que nos ofrece su hermosa bah&iacute a en forma de concha, con la isla de Santa Clara en el centro, fla', '\"069');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estaciones`
--

CREATE TABLE `estaciones` (
  `CodEstacion` varchar(11) COLLATE utf8_spanish_ci NOT NULL,
  `NombreEstacion` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `CoordenadaX` decimal(13,10) NOT NULL,
  `CoordenadaY` decimal(13,10) NOT NULL,
  `NomMunicipio` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `estaciones`
--

INSERT INTO `estaciones` (`CodEstacion`, `NombreEstacion`, `CoordenadaX`, `CoordenadaY`, `NomMunicipio`) VALUES
('1', '3 DE MARZO', '-2.6677849293', '42.8560485840', 'Vitoria-Gasteiz'),
('10', 'AVDA. TOLOSA', '-2.0109384060', '43.3094139099', 'Donostia / San SebastiÃ¡n'),
('11', 'AZPEITIA', '-2.2682104111', '43.1803703308', 'Azpeitia'),
('12', 'AÃ‘ORGA', '-1.9957411289', '43.2905311584', 'Donostia / San SebastiÃ¡n'),
('13', 'BANDERAS (meteo)', '-2.9532783031', '43.2809906006', 'Bilbao'),
('14', 'BARAKALDO', '-2.9871330261', '43.2983779907', 'Barakaldo'),
('15', 'BASAURI', '-2.8837609291', '43.2411308289', 'Basauri'),
('16', 'BEASAIN', '-2.1913802624', '43.0480957031', 'Beasain'),
('17', 'BOROA METEO', '-2.7506873608', '43.2359046936', 'Amorebieta-Etxano'),
('18', 'CASTREJANA', '-2.9734566212', '43.2580795288', 'Barakaldo'),
('19', 'DURANGO', '-2.6379580498', '43.1682853699', 'Durango'),
('2', 'ABANTO', '-3.0741560459', '43.3204727173', 'Abanto y CiÃ©rvana-Abanto Zierbena'),
('20', 'EASO', '-1.9809008837', '43.3121528625', 'Donostia / San SebastiÃ¡n'),
('21', 'ELCIEGO', '-2.6194751263', '42.5182495117', 'Elciego'),
('22', 'ERANDIO', '-2.9772400856', '43.3026542664', 'Erandio'),
('23', 'EUROPA', '-2.9023761749', '43.2549095154', 'Bilbao'),
('24', 'FARMACIA', '-2.6725630760', '42.8400993347', 'Vitoria-Gasteiz'),
('25', 'FERIA (meteo)', '-2.9475440979', '43.2652206421', 'Bilbao'),
('26', 'HERNANI', '-1.9777156115', '43.2674331665', 'Hernani'),
('27', 'JAIZKIBEL', '-1.8594129086', '43.3427734375', 'Hondarribia'),
('28', 'LARRABETZU', '-2.7993991375', '43.2612266541', 'Larrabetzu'),
('29', 'LAS CARRERAS', '-3.0973975658', '43.3196029663', 'Abanto y CiÃ©rvana-Abanto Zierbena'),
('3', 'AGURAIN', '-2.3937034607', '42.8490142822', 'Agurain/Salvatierra'),
('30', 'LASARTE-ORIA', '-2.0191292763', '43.2686996460', 'Lasarte-Oria'),
('31', 'LEZO', '-1.9001311064', '43.3215217590', 'Lezo'),
('32', 'LLODIO', '-2.9633853436', '43.1440277100', 'Laudio/Llodio'),
('33', 'LOS HERRAN', '-2.6612305641', '42.8437042236', 'Vitoria-Gasteiz'),
('34', 'MAZARREDO', '-2.9351880550', '43.2675056458', 'Bilbao'),
('35', 'MONDRAGON', '-2.4903967381', '43.0641212463', 'Arrasate/MondragÃ³n'),
('36', 'MONTORRA', '-2.7165884972', '43.2090148926', 'Amorebieta-Etxano'),
('37', 'MUNDAKA', '-2.7031593323', '43.4058876038', 'Mundaka'),
('38', 'MUNOA', '-2.9786961079', '43.2855873108', 'Barakaldo'),
('39', 'MUSKIZ', '-3.1127161980', '43.3207130432', 'Muskiz'),
('4', 'ALGORTA (BBIZI2)', '-3.0227823257', '43.3620567322', 'Getxo'),
('40', 'MÂª DIAZ HARO', '-2.9456567764', '43.2588043213', 'Bilbao'),
('41', 'NAUTICA', '-3.0233333111', '43.3269462585', 'Portugalete'),
('42', 'PAGOETA', '-2.1548874378', '43.2506065369', 'Aia'),
('43', 'PUYO', '-1.9840112925', '43.3027763367', 'Donostia / San SebastiÃ¡n'),
('44', 'SAN JULIAN', '-3.1129946709', '43.3326492310', 'Muskiz'),
('45', 'SAN MIGUEL', '-2.8865089417', '43.2212219238', 'Basauri'),
('46', 'SANGRONIZ', '-2.9303858280', '43.2984199524', 'Sondika'),
('47', 'SANTURCE', '-3.0425601006', '43.3330116272', 'Santurtzi'),
('48', 'SERANTES', '-3.0629906654', '43.3344383240', 'Santurtzi'),
('49', 'SESTAO', '-2.9959495068', '43.3077278137', 'Sestao'),
('5', 'ALONSOTEGI', '-2.9880239964', '43.2475662231', 'Alonsotegi'),
('50', 'TOLOSA', '-2.0780022144', '43.1307792664', 'Tolosa'),
('51', 'URKIOLA', '-2.6508700848', '43.1004409790', 'AbadiÃ±o'),
('52', 'USURBIL', '-2.0505111217', '43.2737808228', 'Usurbil'),
('53', 'VALDEREJO', '-3.2317326069', '42.8751678467', 'ValdegovÃ­a/Gaubea'),
('54', 'ZALLA', '-3.1344611645', '43.2128562927', 'Zalla'),
('55', 'ZELAIETA PARQUE', '-2.7344970703', '43.2189064026', 'Amorebieta-Etxano'),
('56', 'ZIERBENA (Puerto)', '-3.0809881687', '43.3530540466', 'Zierbena'),
('57', 'ZUBIETA', '-2.0313508511', '43.2720603943', 'Donostia / San SebastiÃ¡n'),
('58', 'ZUBIETA METEO', '-2.0317165852', '43.2559738159', 'Donostia / San SebastiÃ¡n'),
('59', 'ZUMARRAGA', '-2.3162288666', '43.0850753784', 'Zumarraga'),
('6', 'ANDOAIN', '-2.0233252048', '43.2213783264', 'Andoain'),
('7', 'ARRAIZ (Monte)', '-2.9604759216', '43.2455520630', 'Bilbao'),
('8', 'ATEGORRIETA', '-1.9606505632', '43.3219909668', 'Donostia / San SebastiÃ¡n'),
('9', 'AV. GASTEIZ', '-2.6807098389', '42.8547821045', 'Vitoria-Gasteiz');

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
-- Estructura de tabla para la tabla `hashes`
--

CREATE TABLE `hashes` (
  `CodHash` int(11) NOT NULL,
  `Url` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `imagenes_espaciosnaturales`
--

CREATE TABLE `imagenes_espaciosnaturales` (
  `codEspacio` varchar(11) NOT NULL,
  `imagen` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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

--
-- Volcado de datos para la tabla `municipios`
--

INSERT INTO `municipios` (`CodMunicipio`, `Nombre`, `Descripcion`, `CodProvincia`) VALUES
('001', 'AbadiÃ±o', '<p>Montes y &aacute rboles ti&ntilde en de verde los parajes de esta localidad perteneciente a la comarca del Duranguesado y por los que circulan los r&iacute os Ibaizabal y Urkiola. Una buena opci&oacute n para admirar estos parajes es a trav&eacute s de diversas rutas que ofrece Abadi&ntilde o y que se pueden realizar a pie, en bicicleta incluso a caballo.    Al llegar a esta bella localidad, quedaremos impresionados por sus edificios, con una', '48'),
('002', 'Abanto y CiÃ©rvana-Abanto Zierbena', '<p>Esta peque&ntilde a localidad de Bizkaia disfruta de un enclave natural &uacute nico, a tan solo 20 minutos de Bilbao. Sin necesidad de elegir mar o monta&ntilde a, Abanto y Zierbena lo tiene todo, siendo un lugar id&oacute neo para disfrutar y descansar.    <strong>Pasado minero</strong> Los viajeros que elijan Abanto y Ci&eacute rvana como destino se dar&aacute n cuenta del especial paisaje que rodea el municipio. El visitante podr&aacute  ', '48'),
('009', 'Andoain', '<p>Rodeado de bellos y verdes montes nos sumergimos en el municipio de Andoain, localidad perteneciente al territorio hist&oacute rico de Gipuzkoa y por la que circulan las aguas del rio Oria.    Andoain destaca tanto por su extenso entorno natural como por la oferta cultural que ofrece. Los montes Belkoain, Buruntza y Adarra rodean el pueblo, dot&aacute ndolo de un paraje realmente admirable. Adem&aacute s, se emplaza en el valle de Leizaran, d', '20'),
('013', 'Barakaldo', '<p>Barakaldo se encuentra dentro de la comarca del Gran Bilbao, en la margen izquierda de la r&iacute a del Ibaizabal. Cuenta con monta&ntilde as de peque&ntilde a altura, como Argalario y Arroletza, y limita al norte con los municipios de Sestao y Trapagaran  al sur con G&uuml e&ntilde es y Alonsotegi  al este con Bilbao y Erandio, y al oeste con Galdames y G&uuml e&ntilde es.    Es el segundo municipio m&aacute s poblado de Bizkaia, por detr&a', '48'),
('015', 'Basauri', '<p>Basauri se halla en el territorio hist&oacute rico de Bizkaia, m&aacute s concretamente, en la comarca de Uribe Nervi&oacute n. El municipio se alza a ambos lados del r&iacute o Nervi&oacute n, en el punto donde &eacute ste se une con el Ibaizabal.    Pueblo eminentemente industrial, Basauri se desanexion&oacute  de Arrigorriaga en 1510 como Anteiglesia de San Miguel de Basauri. En este barrio estuvo el Ayuntamiento de la localidad, hasta ini', '48'),
('016', 'Aia', '<p>El entorno natural de esta peque&ntilde a localidad rural es espectacular y el <strong>Parque Natural de Pagoeta </strong>es la mejor prueba de ello. Est&aacute  ubicado en el monte Pagoeta (714 m) y es realmente apropiado para disfrutar de la naturaleza  no en vano, tenemos la oportunidad de realizar preciosas rutas por los verdes bosques y prados de la zona. Adem&aacute s, existen diversas &aacute reas recreativas habilitadas para el disfru', '20'),
('018', 'Azpeitia', '<p>Bajo el abrigo del macizo de Izarraitz en la comarca de Urola Medio, se encuentra el municipio guipuzcoano de <strong>Azpeitia</strong>.</p><p>&nbsp </p><p>La localidad es muy conocida por el&nbsp <a href=\\https://turismo.euskadi.eus/es/top10/patrimonio-cultural/santuario-de-loiola/aa30-12376/es/\\>santuario de Loiola</a>. Se encuentra a un kil&oacute metro del casco urbano y es, sin duda, el conjunto m&aacute s emblem&aacute tico del munici', '20'),
('019', 'Beasain', '<p>Beasain, situada en el <strong>coraz&oacute n del Goierri</strong>, se encuentra en un paraje id&iacute lico, en medio de montes como Murumendi, Usurbe y Pagokabar y acompa&ntilde ado del r&iacute o Oria.</p><p> Los primeros moradores de Beasain poblaron estas tierras muchos siglos atr&aacute s, tal y como lo atestiguan los t&uacute mulos de Basagain, Trikuazti l y Trikuazti ll e Illaun, descubiertos en 1927, 1978 y 1989, respectivamente.</p>', '20'),
('020', 'Bilbao', '<p><strong>Bilbao</strong> es entre las ciudades vascas la m&aacute s cosmopolita, poblada e inquieta. Se trata de una ciudad camale&oacute nica, que durante las &uacute ltimas dos d&eacute cadas ha sabido reinventarse a s&iacute  misma, transformando por completo tanto su car&aacute cter como su fisonom&iacute a  ello no le ha impedido conservar el encanto y carisma de sus viejos rincones. Su nombre est&aacute  y estar&aacute  indiscutiblemente', '48'),
('022', 'Elciego', '<p><strong>El ciego</strong>&nbsp se sit&uacute a al sur de la&nbsp <a href=\\https://turismo.euskadi.eus/rioja-alavesa/\\>Rioja Alavesa</a>, en el territorio hist&oacute rico de &Aacute lava. Hacia el Norte, divisaremos la Sierra de Tolo&ntilde o, y al Sur, veremos pasar el r&iacute o Ebro. Al Este, limita con Lapuebla de Labarca y al Oeste, con Ba&ntilde os de Ebro.</p><p>&nbsp </p><p>Al igual que en todos los municipios de la zona,&nbsp <stro', '01'),
('027', 'Durango', '<p><strong>Durango</strong> es un bello municipio del Duranguesado, atravesado por el r&iacute o Ibaizabal. Se sit&uacute a en las proximidades del <a href=\\https://turismo.euskadi.eus/es/espacios-naturales/parque-natural-de-urkiola/aa30-12375/es/\\>Parque Natural de Urkiola</a>, espacio natural de excepci&oacute n donde es posible realizar agradables recorridos o ascender cimas como Anboto (1.331 m), Alluitz (1.040 m) y Mugarra (964 m).</p><p>', '48'),
('036', 'Hondarribia', '<p>Ba&ntilde ado por las aguas del mar Cant&aacute brico, <strong>Hondarribia</strong> se halla al nordeste del territorio hist&oacute rico de Gipuzkoa, en la comarca de Bidasoa-Txingudi, a los pies del monte Jaizkibel (545 m).</p><p>&nbsp </p><p>Podemos iniciar la visita a Hondarribia contemplando las vistas que ofrece la maravillosa bah&iacute a de Txingudi, con la vecina Hendaia al fondo. Despu&eacute s, nos acercaremos hasta la inmensa playa', '20'),
('040', 'Hernani', '<p>A tan solo 10 km de la capital donostiarra y a los pies del monte Santa B&aacute rbara, Hernani, permite divisar una amplia zona de la Vega del Urumea. El r&iacute o Urumea recorre la villa de norte a sur y recoge las aguas de todos los arroyos de los alrededores.    El t&eacute rmino municipal de Hernani, compuesto por 10 barrios, est&aacute  rodeado de diversos montes (Akora, Urdaburu, Oindi, Azketa), donde encontraremos numerosos caser&iac', '20'),
('044', 'Getxo', '<p>Ba&ntilde ado por las aguas del Cant&aacute brico, Getxo se halla en el territorio hist&oacute rico de Bizkaia, dentro de la comarca de Uribe Costa, y est&aacute  formado por cinco n&uacute cleos: Andra Mari, Algorta, Las Arenas, Neguri y Romo.    Adem&aacute s, rodeado por la bah&iacute a del Abra, Getxo se caracteriza por sus <strong>playas y acantilados</strong>, ya que ofrece un bello paisaje con 10 kil&oacute metros de acantilados, costa', '48'),
('051', 'Salvatierra/Agurain', '<p>Esta localidad est&aacute  situada entre las sierras de Entzia e Iturrieta, en las que se esconden impresionantes hayedos. Sus primeros siglos de vida estuvieron marcados por su car&aacute cter fronterizo y las disputas de las Coronas de Navarra y Castilla sobre su control. De esta &eacute poca hered&oacute  un bello casco medieval amurallado, que fue declarado conjunto hist&oacute rico en 1975.    La parte vieja est&aacute  dividida en tres ', '01'),
('052', 'Larrabetzu', '<p>Este municipio del valle del Txorierri, cuenta con un hermoso casco hist&oacute rico, declarado Conjunto Monumental, que fue construido bas&aacute ndose en patrones de estilo neocl&aacute sico. Entre las edificaciones de la zona destacamos la Iglesia de Santa Mar&iacute a de la Asunci&oacute n, formando parte del mismo n&uacute cleo hist&oacute rico de Larrabetzu.    Adentr&aacute ndonos en el barrio Goikolexea, uno de los edificios que merec', '48'),
('053', 'Lezo', '<p><strong>Lezo</strong> es un municipio que cuenta con el <strong>monte Jaizkibel</strong> como su cota m&aacute s alta. Asimismo el <strong>arte</strong> que nos ofrece su <strong>Casco Hist&oacute rico</strong> hace hincapi&eacute  en el alto valor arquitect&oacute nico-art&iacute stico que nos podemos encontrar si observamos los elementos colindantes de la plaza cuadrada.</p><p>&nbsp </p><p>Una de las visitas imprescindibles en el municipio ', '20'),
('055', 'ValdegovÃ­a', '<p>Valdegov&iacute a es un lugar situado en &Aacute lava pr&oacute ximo a Burgos, donde la naturaleza se impone y sorprende en cada momento del a&ntilde o, visti&eacute ndose de colores intensos. En este valle te esperan sus pueblos, sus bosques, sus r&iacute os, arroyos y fuentes, su silencio, etc. donde podr&aacute s descubrir la naturaleza en su estado m&aacute s puro. Valdegov&iacute a ofrece la posibilidad de disfrutar de este entorno privi', '01'),
('059', 'Vitoria-Gasteiz', '<p>Adem&aacute s de ser el centro pol&iacute tico y administrativo de Euskadi, <strong>Vitoria-Gasteiz</strong> es un innegable ejemplo de buen vivir. Y es que, la capital alavesa es una de esas ciudades que cuentan con un rico patrimonio hist&oacute rico-art&iacute stico, un sinf&iacute n de zonas ajardinadas, espacios peatonales ideales para el ocio y equipamientos c&iacute vicos. Una ciudad accesible, especial, con personalidad.    Uno de sus', '01'),
('068', 'Mundaka', '<p>Famosa por su ola izquierda que atrae cada a&ntilde o a numerosos surfistas, este pintoresco pueblo marinero est&aacute  situado en la margen izquierda de la desembocadura de la r&iacute a de Mundaka, en plena <a href=\\https://turismo.euskadi.eus/es/top10/espacios-naturales/reserva-de-la-biosfera-de-urdaibai/aa30-12376/es/\\>Reserva de la Biosfera de Urdaibai</a>, espacio natural formado por amplias marismas y 12 km de extensos arenales que ', '48'),
('069', 'San SebastiÃ¡n', '<p>Hermosa, elegante, exquisita, coqueta, se&ntilde orial. As&iacute  es <strong>Donostia / San Sebasti&aacute n</strong>, una ciudad en plena costa vasca, abierta al mar y considerada por muchos la capital tur&iacute stica de Euskadi. Es una de las ciudades que posee&nbsp una de las bah&iacute as m&aacute s bellas del mundo, la de <strong>La Concha</strong>, y tres playas urbanas (<strong>La Concha</strong>, <strong>Zurriola</strong> y <strong>', '20'),
('071', 'Muskiz', '<p>Ba&ntilde ado por las aguas del mar Cant&aacute brico, Muskiz se ubica en el extremo m&aacute s occidental del territorio hist&oacute rico de Bizkaia, asentado sobre el <strong>valle del r&iacute o Barbad&uacute n</strong>.    Este enclave costero permite disfrutar de un paisaje encantador.</p><p>&nbsp </p><p>Con sus <strong>extensos kil&oacute metros de costa</strong>, donde destacan la <a href=\\https://turismo.euskadi.eus/es/playas-embalse', '48'),
('073', 'Usurbil', '<p>Muy conocido por su tradici&oacute n sidrera y pesca de angulas, este peque&ntilde o municipio guipuzcoano se halla a poca distancia de la costa y protegido por los montes Andatza (562 m) y Mendizorrotz (415 m).    Usurbil alberga numerosos edificios hist&oacute ricos que atraen la mirada de los visitantes que llegan al municipio. El n&uacute cleo hist&oacute rico se organiza en torno a la iglesia de San Salvador, de una &uacute nica nave y c', '20'),
('078', 'Portugalete', '<p>Portugalete, elegante villa marinera, es uno de los principales n&uacute cleos urbanos de la margen izquierda de la R&iacute a de Bilbao.    Situado en su desembocadura, el Puente Colgante o <a href=\\https://turismo.euskadi.eus/es/top10/patrimonio-cultural/puente-colgante-de-bizkaia/aa30-12376/es/\\>Puente&nbsp Bizkaia</a>, declarado <strong>Patrimonio de la Humanidad por la UNESCO</strong>, representa la cara m&aacute s conocida del municip', '48'),
('080', 'Zumarraga', '<p>Zumarraga nos embelesa con los muchos lugares de inter&eacute s que ofrece, tanto <strong>culturales, como gastron&oacute micos</strong>.</p><p>&nbsp </p><p>Se halla a los pies del <a href=\\https://turismo.euskadi.eus/es/espacios-naturales/parque-natural-de-aizkorri-aratz/aa30-12375/es/\\>Parque Natural de Aizkorri</a>, en la comarca del Alto Urola, valle estrechamente ligado a la cultura del hierro, donde encontraremos restos de antiguas fe', '20'),
('082', 'Santurtzi', '<p><strong>Santurtzi </strong>es una localidad de gran <strong>tradici&oacute n marinera</strong> situada en el territorio hist&oacute rico de Bizkaia. Se halla, m&aacute s concretamente, en la margen izquierda de la r&iacute a del Ibaizabal, a los pies del monte <strong>Serantes </strong>(451 m).</p><p>&nbsp </p><p>Es conocida por su <a href=\\https://turismo.euskadi.eus/es/puertos-pesqueros/puerto-de-santurtzi/aa30-12375/es/\\><strong>puerto p', '48'),
('084', 'Sestao', '<p>Encaramado sobre una peque&ntilde a meseta paralela a los Montes de Triano y arropado por la cima del Serantes (451 m), la localidad de <strong>Sestao</strong>, situada en la margen izquierda de la R&iacute a de Bilbao, representa un fiel retrato del pasado, presente y futuro de la evoluci&oacute n de la vida en la comarca del Gran Bilbao.</p><p>&nbsp </p><p>Nacida originariamente como asentamiento de pescadores, Sestao comienza a aparecer en', '48'),
('096', 'Zalla', '<p>Rodeada de monta&ntilde as y ocupando una llanura atravesada por el r&iacute o Cadagua, Zalla recibe al visitante desde el mismo coraz&oacute n de la comarca de las Encartaciones. Villa de rica historia de litigios fronterizos y antiguas ferrer&iacute as, Zalla es paso obligado para aquellos peregrinos que sigan el Camino de Santiago en su vertiente costera, el cual discurre por el mismo centro urbano de la localidad.    A pesar de que la may', '48'),
('902', 'Erandio', '<p>Con una ubicaci&oacute n estrat&eacute gica, a las orillas de la R&iacute a y entre los montes San Pablo y San Bernab&eacute  se situa el municipio vizca&iacute no Erandio.    Este cuenta con numerosos elementos de inter&eacute s que componen su patrimonio hist&oacute rico y art&iacute stico. <strong>Hermosas vistas de la r&iacute a de Bilbao</strong> Una vez de haber visitado los elementos de car&aacute cter hist&oacute rico, tendremos la op', '48'),
('904', 'Sondika', '<p>Situado en Txorierri, un valle de grandes infraestructuras urban&iacute sticas, Sondika vio transformado su car&aacute cter rural por el proceso de industrializaci&oacute n que experimentaron la mayor&iacute a de los municipios que rodean la capital vizca&iacute na. De hecho, esta localidad es conocida por albergar durante d&eacute cadas las instalaciones del aeropuerto de Bilbao, que en la actualidad se ubican en la vecina Loiu.    Formada p', '48'),
('912', 'Alonsotegi', '<p>En el pasillo que forma el r&iacute o <strong>Cadagua </strong>entre los montes <strong>Sasiburu, Ganekogorta y Pagasarri </strong>asoma el municipio de Alonsotegi.</p><p>&nbsp </p><p>Esta es una de las localidades que no se puede perder ning&uacute n amante de la naturaleza y el senderismo, ya que el pueblo se encuentra rodeado de montes. Entre &eacute stos destacan el&nbsp <strong>Ganekogorta&nbsp </strong>y el&nbsp <strong>Pagasarri</stron', '48'),
('913', 'Zierbena', '<p>Situada en el extremo occidental del litoral vizca&iacute no, Zierbena es conocida por el <strong>sabor marinero </strong>de su puerto pesquero.</p><p>&nbsp </p><p>A pesar de sufrir una importante transformaci&oacute n por la construcci&oacute n del Puerto de Bilbao, mantiene el <strong>encanto de anta&ntilde o con sus barcos de pesca y de recreo</strong>. Junto con las <strong>dos playas</strong> de la localidad, constituye el principal atra', '48');

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
-- Indices de la tabla `datosdiarios`
--
ALTER TABLE `datosdiarios`
  ADD PRIMARY KEY (`CodEstacion`);

--
-- Indices de la tabla `datoshorarios`
--
ALTER TABLE `datoshorarios`
  ADD PRIMARY KEY (`CodEstacion`);

--
-- Indices de la tabla `datosindice`
--
ALTER TABLE `datosindice`
  ADD PRIMARY KEY (`CodEstacion`);

--
-- Indices de la tabla `espacios_naturales`
--
ALTER TABLE `espacios_naturales`
  ADD PRIMARY KEY (`CodEspacio`);

--
-- Indices de la tabla `estaciones`
--
ALTER TABLE `estaciones`
  ADD PRIMARY KEY (`CodEstacion`);

--
-- Indices de la tabla `esta_en`
--
ALTER TABLE `esta_en`
  ADD PRIMARY KEY (`CodMunicipio`,`CodEspacio`);

--
-- Indices de la tabla `favoritos_espacios`
--
ALTER TABLE `favoritos_espacios`
  ADD PRIMARY KEY (`CodUsuario`,`CodEspacio`);

--
-- Indices de la tabla `hashes`
--
ALTER TABLE `hashes`
  ADD PRIMARY KEY (`CodHash`);

--
-- Indices de la tabla `municipios`
--
ALTER TABLE `municipios`
  ADD PRIMARY KEY (`CodMunicipio`);

--
-- Indices de la tabla `provincia`
--
ALTER TABLE `provincia`
  ADD PRIMARY KEY (`CodProvincia`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`CodUsuario`),
  ADD UNIQUE KEY `Nombre` (`Nombre`) USING BTREE;

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `CodUsuario` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
