-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 07. April 2013 um 00:02
-- Server Version: 5.1.37
-- PHP-Version: 5.3.0
--
-- Version 1.2
--

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Datenbank: `messenger`
--
DROP DATABASE `messenger`;
CREATE DATABASE `messenger` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `messenger`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `message`
--

CREATE TABLE IF NOT EXISTS `message` (
  `M_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Sender_ID` int(11) NOT NULL,
  `Empfaenger_ID` int(11) NOT NULL,
  `Text` text NOT NULL,
  `Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Status` int(11) NOT NULL,
  PRIMARY KEY (`M_ID`),
  KEY `Sender_ID` (`Sender_ID`),
  KEY `Empfaenger_ID` (`Empfaenger_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Daten für Tabelle `message`
--

INSERT INTO `message` (`M_ID`, `Sender_ID`, `Empfaenger_ID`, `Text`, `Time`, `Status`) VALUES
(1, 1, 2, 'Hallo', '2013-03-08 14:43:20', 0);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `U_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Telefon` varchar(15) NOT NULL,
  `Password` varchar(64) NOT NULL,
  `LastOnline` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`U_ID`),
  UNIQUE KEY `Telefon` (`Telefon`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`U_ID`, `Telefon`, `Password`, `LastOnline`) VALUES
(1, '00491234', 'Password', '0000-00-00 00:00:00'),
(2, '004954321', 'Password', '0000-00-00 00:00:00');

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `message_ibfk_2` FOREIGN KEY (`Empfaenger_ID`) REFERENCES `user` (`U_ID`),
  ADD CONSTRAINT `message_ibfk_1` FOREIGN KEY (`Sender_ID`) REFERENCES `user` (`U_ID`);
