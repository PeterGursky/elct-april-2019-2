-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hostiteľ: 127.0.0.1
-- Čas generovania: Po 08.Apr 2019, 13:51
-- Verzia serveru: 10.1.38-MariaDB
-- Verzia PHP: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáza: `notes`
--
CREATE DATABASE IF NOT EXISTS `notes` DEFAULT CHARACTER SET utf8 COLLATE utf8_slovak_ci;
USE `notes`;

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `note`
--

CREATE TABLE `note` (
  `id` int(11) NOT NULL,
  `title` varchar(100) COLLATE utf8_slovak_ci NOT NULL,
  `text` text COLLATE utf8_slovak_ci,
  `done` tinyint(1) NOT NULL DEFAULT '0',
  `deadline` date DEFAULT NULL,
  `id_type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_slovak_ci;

--
-- Sťahujem dáta pre tabuľku `note`
--

INSERT INTO `note` (`id`, `title`, `text`, `done`, `deadline`, `id_type`) VALUES
(1, 'Vyrobiť DaoFactory', 'jdbctemplate a NoteDao', 0, '2019-04-08', 2),
(2, 'kúpiť víno', 'veľa', 0, '2019-04-07', 3);

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `note_type`
--

CREATE TABLE `note_type` (
  `id` int(11) NOT NULL,
  `type` varchar(30) COLLATE utf8_slovak_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_slovak_ci;

--
-- Sťahujem dáta pre tabuľku `note_type`
--

INSERT INTO `note_type` (`id`, `type`) VALUES
(1, 'bežná'),
(2, 'java'),
(3, 'nákup');

--
-- Kľúče pre exportované tabuľky
--

--
-- Indexy pre tabuľku `note`
--
ALTER TABLE `note`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_note_type` (`id_type`);

--
-- Indexy pre tabuľku `note_type`
--
ALTER TABLE `note_type`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pre exportované tabuľky
--

--
-- AUTO_INCREMENT pre tabuľku `note`
--
ALTER TABLE `note`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pre tabuľku `note_type`
--
ALTER TABLE `note_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Obmedzenie pre exportované tabuľky
--

--
-- Obmedzenie pre tabuľku `note`
--
ALTER TABLE `note`
  ADD CONSTRAINT `fk_note_type` FOREIGN KEY (`id_type`) REFERENCES `note_type` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
