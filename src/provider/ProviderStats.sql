-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Час створення: Гру 07 2015 р., 22:23
-- Версія сервера: 10.1.8-MariaDB
-- Версія PHP: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База даних: `providers_stats`
--
CREATE DATABASE IF NOT EXISTS `providers_stats` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `providers_stats`;

-- --------------------------------------------------------

--
-- Структура таблиці `accesses`
--

CREATE TABLE IF NOT EXISTS `accesses` (
  `access_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `access_state` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`access_id`),
  UNIQUE KEY `access_id` (`access_id`),
  KEY `access_id_2` (`access_id`),
  KEY `user_id` (`order_id`),
  KEY `access_state` (`access_state`),
  KEY `order_id` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=latin1;

--
-- Дамп даних таблиці `accesses`
--

INSERT INTO `accesses` (`access_id`, `order_id`, `access_state`) VALUES
(60, 4, 1),
(61, 5, 0),
(62, 6, 1),
(63, 8, 1),
(64, 10, 0);

-- --------------------------------------------------------

--
-- Структура таблиці `months`
--

CREATE TABLE IF NOT EXISTS `months` (
  `month_id` int(11) NOT NULL AUTO_INCREMENT,
  `month_name` varchar(10) NOT NULL,
  PRIMARY KEY (`month_id`),
  UNIQUE KEY `month_name` (`month_name`),
  UNIQUE KEY `month_id` (`month_id`),
  KEY `month_id_2` (`month_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Дамп даних таблиці `months`
--

INSERT INTO `months` (`month_id`, `month_name`) VALUES
(4, 'April'),
(8, 'August'),
(12, 'December'),
(2, 'February'),
(1, 'January'),
(7, 'July'),
(6, 'June_'),
(3, 'March'),
(5, 'May'),
(11, 'November'),
(10, 'October'),
(9, 'September');

-- --------------------------------------------------------

--
-- Структура таблиці `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `plan_id` int(11) NOT NULL,
  `month_id` int(11) NOT NULL,
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `order_id` (`order_id`),
  KEY `order_id_2` (`order_id`),
  KEY `user_id` (`user_id`),
  KEY `plan_id` (`plan_id`),
  KEY `month_id` (`month_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Дамп даних таблиці `orders`
--

INSERT INTO `orders` (`order_id`, `user_id`, `plan_id`, `month_id`) VALUES
(4, 3, 2, 9),
(5, 3, 1, 12),
(6, 3, 3, 4),
(8, 3, 1, 5),
(10, 3, 4, 6);

-- --------------------------------------------------------

--
-- Структура таблиці `payments`
--

CREATE TABLE IF NOT EXISTS `payments` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `month_id` int(11) NOT NULL,
  PRIMARY KEY (`payment_id`),
  UNIQUE KEY `payment_id` (`payment_id`),
  KEY `payment_id_2` (`payment_id`),
  KEY `user_id` (`user_id`),
  KEY `month_id` (`month_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Дамп даних таблиці `payments`
--

INSERT INTO `payments` (`payment_id`, `user_id`, `month_id`) VALUES
(6, 3, 4),
(7, 3, 10),
(8, 3, 5),
(15, 3, 9),
(16, 3, 1);

-- --------------------------------------------------------

--
-- Структура таблиці `plans`
--

CREATE TABLE IF NOT EXISTS `plans` (
  `plan_id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_name` varchar(50) NOT NULL,
  `plan_speed` int(4) NOT NULL,
  `plan_price` decimal(5,2) NOT NULL,
  PRIMARY KEY (`plan_id`),
  UNIQUE KEY `plan_name` (`plan_name`),
  UNIQUE KEY `plan_id` (`plan_id`),
  KEY `plan_id_2` (`plan_id`),
  KEY `plan_name_2` (`plan_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Дамп даних таблиці `plans`
--

INSERT INTO `plans` (`plan_id`, `plan_name`, `plan_speed`, `plan_price`) VALUES
(1, 'Light', 10, '9.99'),
(2, 'Medium', 20, '14.99'),
(3, 'Hard', 50, '19.99'),
(4, 'Extra', 100, '29.99');

-- --------------------------------------------------------

--
-- Структура таблиці `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `user_password` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`),
  UNIQUE KEY `user_id_2` (`user_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Дамп даних таблиці `users`
--

INSERT INTO `users` (`user_id`, `user_name`, `user_password`) VALUES
(1, 'Oleg Volkov', 2460511),
(3, 'Alex', 2043454);

--
-- Обмеження зовнішнього ключа збережених таблиць
--

--
-- Обмеження зовнішнього ключа таблиці `accesses`
--
ALTER TABLE `accesses`
  ADD CONSTRAINT `accesses_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Обмеження зовнішнього ключа таблиці `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`plan_id`) REFERENCES `plans` (`plan_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`month_id`) REFERENCES `months` (`month_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Обмеження зовнішнього ключа таблиці `payments`
--
ALTER TABLE `payments`
  ADD CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`month_id`) REFERENCES `months` (`month_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `payments_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
