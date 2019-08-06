-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 12, 2018 at 01:07 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bazaxml`
--

-- --------------------------------------------------------

--
-- Table structure for table `aerodrom`
--

CREATE TABLE `aerodrom` (
  `id_aerodrom` int(11) NOT NULL,
  `naziv` varchar(20) NOT NULL,
  `grad` varchar(20) NOT NULL,
  `ulica` varchar(20) NOT NULL,
  `telefon` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `aerodrom`
--

INSERT INTO `aerodrom` (`id_aerodrom`, `naziv`, `grad`, `ulica`, `telefon`) VALUES
(1, 'Aerodrom Podgorica', 'Podgorica', 'Aerodromska bb', '6777777'),
(2, 'Aerodrom Tivat', 'Tivat', 'Josipa Broza', '6888888');

-- --------------------------------------------------------

--
-- Table structure for table `let`
--

CREATE TABLE `let` (
  `id_let` int(11) NOT NULL,
  `naziv` varchar(20) NOT NULL,
  `kompanija` varchar(20) NOT NULL,
  `id_aerodrom` int(11) NOT NULL,
  `brojputnika` int(11) NOT NULL,
  `terminal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `let`
--

INSERT INTO `let` (`id_let`, `naziv`, `kompanija`, `id_aerodrom`, `brojputnika`, `terminal`) VALUES
(1, 'JP-312', 'Montenegro Airlines', 1, 100, 1),
(2, 'JYJ-321', 'Air Serbia', 1, 123, 4),
(3, 'AA-33', 'Wizz Air', 2, 33, 3),
(4, 'BB-312', 'Rayan Air', 1, 141, 2),
(5, 'CC-503', 'Swiss Air', 2, 63, 1),
(6, 'FAS-13', 'Air Serbia', 2, 32, 4),
(7, 'DSA-31', 'Montenegro Airlines', 1, 200, 2),
(8, 'GAG-22', 'Wizz Air', 1, 33, 3),
(9, 'JAT-312', 'Pobeda', 1, 131, 4),
(10, 'JJ-363', 'Croatia Air', 1, 52, 5),
(11, 'HH-321', 'Lufhanza', 2, 311, 1),
(12, 'PP-643', 'Wizz Air', 1, 11, 2);

-- --------------------------------------------------------

--
-- Table structure for table `putnik`
--

CREATE TABLE `putnik` (
  `id_putnik` int(11) NOT NULL,
  `ime` varchar(20) NOT NULL,
  `prezime` varchar(20) NOT NULL,
  `pol` varchar(20) NOT NULL,
  `godina` int(11) NOT NULL,
  `grad` varchar(20) NOT NULL,
  `ulica` varchar(20) NOT NULL,
  `telefon` varchar(20) NOT NULL,
  `klasa` varchar(20) NOT NULL,
  `id_let` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `putnik`
--

INSERT INTO `putnik` (`id_putnik`, `ime`, `prezime`, `pol`, `godina`, `grad`, `ulica`, `telefon`, `klasa`, `id_let`) VALUES
(1, 'Marko', 'Markovic', 'Muski', 22, 'Niksic', 'Nikole Tesle', '123124', 'Ekonomska', 1),
(2, 'Petar', 'Petrovic', 'Muski', 44, 'Niksic', 'Josipa Broza', '2411', 'Biznis', 2),
(3, 'Ivan', 'Ivanovic', 'Muski', 32, 'Podgorica', 'Zmaj Jovina', '312', 'Ekonomska', 3),
(4, 'Milica', 'Petrovic', 'Zenski', 11, 'Pljevlja', 'Bulevar Oslobodjenja', '5131', 'Biznis', 4),
(5, 'Mira', 'Cicmil', 'Zenski', 29, 'Budva', 'Njegoseva', '555', 'Ekonomska', 5),
(6, 'Ivan', 'Todorovic', 'Muski', 4, 'Cetinje', 'Alekse Backovic', '14203', 'Biznis', 8),
(7, 'Jelena', 'Lalic', 'Zenski', 20, 'Beograd', 'Cadjalicka bb', '4142', 'Ekonomska', 7),
(8, 'Brajan', 'Cvorovic', 'Muski', 70, 'Niksic', 'VI Crnogorska', '5125124', 'Biznis', 10),
(9, 'Veselin', 'Tomanovic', 'Muski', 50, 'Cetinje', 'Cetinjski put bb', '5123213', 'Ekonomska', 6),
(10, 'Danilo', 'Canovic', 'Zenski', 30, 'Herceg Novi', 'Stjepe Sarenca bb', '215412', 'Biznis', 9);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `aerodrom`
--
ALTER TABLE `aerodrom`
  ADD PRIMARY KEY (`id_aerodrom`);

--
-- Indexes for table `let`
--
ALTER TABLE `let`
  ADD PRIMARY KEY (`id_let`),
  ADD KEY `id_aerodrom` (`id_aerodrom`),
  ADD KEY `id_let` (`id_let`);

--
-- Indexes for table `putnik`
--
ALTER TABLE `putnik`
  ADD PRIMARY KEY (`id_putnik`),
  ADD KEY `id_let` (`id_let`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `aerodrom`
--
ALTER TABLE `aerodrom`
  MODIFY `id_aerodrom` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `let`
--
ALTER TABLE `let`
  MODIFY `id_let` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `putnik`
--
ALTER TABLE `putnik`
  MODIFY `id_putnik` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `let`
--
ALTER TABLE `let`
  ADD CONSTRAINT `let_ibfk_1` FOREIGN KEY (`id_aerodrom`) REFERENCES `aerodrom` (`id_aerodrom`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `putnik`
--
ALTER TABLE `putnik`
  ADD CONSTRAINT `putnik_ibfk_1` FOREIGN KEY (`id_let`) REFERENCES `let` (`id_let`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
