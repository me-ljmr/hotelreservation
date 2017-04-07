-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 08, 2017 at 12:15 AM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_info`
--

CREATE TABLE `admin_info` (
  `id` int(11) NOT NULL,
  `login_name` varchar(20) NOT NULL,
  `login_password` varchar(100) NOT NULL,
  `display_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin_info`
--

INSERT INTO `admin_info` (`id`, `login_name`, `login_password`, `display_name`) VALUES
(1, 'admin', '$31$16$mK_xxdrh4qXhzKXiAeW3xSYnpCURUEZM6lE2YKXBJBE', 'Administrator');

-- --------------------------------------------------------

--
-- Table structure for table `hotel_info`
--

CREATE TABLE `hotel_info` (
  `id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL DEFAULT '',
  `logo` mediumblob NOT NULL,
  `contact_number` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `web_url` varchar(100) NOT NULL,
  `address` varchar(100) DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hotel_info`
--
 

--
-- Table structure for table `photo_gallery`
--

CREATE TABLE `photo_gallery` (
  `id` int(11) NOT NULL,
  `hotel_id` int(11) DEFAULT NULL,
  `photo` blob,
  `photo_title` varchar(20) DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `id` int(11) NOT NULL,
  `booked_date` datetime NOT NULL,
  `date_from` date DEFAULT NULL,
  `date_till` date DEFAULT NULL,
  `room_id` int(6) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `special_service` varchar(100) NOT NULL,
  `status` char(2) NOT NULL DEFAULT 'BK' COMMENT 'holds BK booked, CC cancelled, CI checked in, CO checked out'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`id`, `booked_date`, `date_from`, `date_till`, `room_id`, `user_id`, `special_service`, `status`) VALUES
(1, '2017-04-11 00:00:00', '2017-04-17', '2017-04-28', 34, 1, ' ', 'BK');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `id` int(11) NOT NULL,
  `floor` int(2) DEFAULT NULL,
  `room_number` varchar(10) DEFAULT '0',
  `room_type_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`id`, `floor`, `room_number`, `room_type_id`) VALUES
(34, 8, '805', 3),
(35, 2, '204', 1),
(36, 1233, '34', 5);

-- --------------------------------------------------------

--
-- Table structure for table `room_calendar`
--

CREATE TABLE `room_calendar` (
  `id` int(11) NOT NULL,
  `discount_date` date DEFAULT NULL,
  `promotion_percentage` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `room_photo_gallery`
--

CREATE TABLE `room_photo_gallery` (
  `id` int(11) NOT NULL,
  `room_id` int(11) DEFAULT NULL,
  `photo` blob,
  `photo_title` varchar(20) DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `room_service`
--

CREATE TABLE `room_service` (
  `id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `room_service`
--

INSERT INTO `room_service` (`id`, `room_id`, `service_id`) VALUES
(35, 34, 51),
(36, 34, 71),
(37, 34, 85),
(38, 34, 70),
(39, 34, 75),
(40, 34, 68),
(41, 34, 92),
(42, 34, 94),
(43, 34, 63),
(44, 34, 83),
(45, 35, 50),
(46, 35, 51),
(47, 36, 56),
(48, 36, 83),
(49, 36, 81),
(50, 36, 66),
(51, 36, 54),
(52, 36, 80),
(53, 36, 65),
(54, 36, 57),
(55, 36, 64);

-- --------------------------------------------------------

--
-- Table structure for table `room_type`
--

CREATE TABLE `room_type` (
  `id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `description` text COMMENT 'Basic, Deluxe, Family of Four',
  `rate` decimal(6,2) DEFAULT '0.00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `room_type`
--

INSERT INTO `room_type` (`id`, `title`, `description`, `rate`) VALUES
(1, 'Deluxe 1 Bed A', 'Deluxe 1 Bed A', '149.99'),
(3, 'Single Bed', 'One Bed', '129.99'),
(5, 'Luxury Suite', 'Luxury Suite 3 Bedroom with Jacuzzi ', '800.00'),
(6, 'Double Bed Delux', 'Double Bed Delux type BA', '199.00'),
(7, 'Deluxe 1 Bed', 'Deluxe 1 Bed As dfadsfadsfas', '149.99');

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

CREATE TABLE `service` (
  `id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `description` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `service`
--

INSERT INTO `service` (`id`, `title`, `description`) VALUES
(50, '250 Thread Count Sheets', ' '),
(51, 'Air Conditioning', ' '),
(52, 'Arm Chair with Ottoman', ' '),
(54, 'Clock Radio w/ MP3 Connection', ' '),
(55, 'Curved Shower Rod', ' '),
(56, 'Duvet Covers', ' '),
(57, 'Ergonomic Mirra Desk Chair', ' '),
(58, 'Feather Pillows Non Allergenic', ' '),
(59, 'Granite Counter Tops/Vanities', ' '),
(60, 'Lever Door Handles', ' '),
(62, 'On-Demand Video Games', ' '),
(63, 'TV-Cable', ' '),
(64, 'TV-Premium HBO,CNN,ESPN', ' '),
(65, 'TV-Standard Network', ' '),
(66, 'Thermostat (adjustable)', ' '),
(67, 'Work Desk with Adjustable Lamp', ' '),
(68, 'Alarms - Audible', ' '),
(69, 'Automatic Door Closer', ' '),
(70, 'Double Locking Doors', ' '),
(71, 'Electronic Smoke Detector', ' '),
(73, 'Scald Proof Shower/Tub', ' '),
(74, 'Wide Angle Door Viewer', ' '),
(75, 'Bathroom Amenities', ' '),
(76, 'Coffee Maker', ' '),
(77, 'Complimentary Remote Printing', ' '),
(78, 'Convenient Electrical Outlets', ' '),
(79, 'Desk Level Electric Plugs', ' '),
(80, 'Desk Level HSIA Connection', ' '),
(81, 'Desk Level Phone Jacks', ' '),
(82, 'Evening Room Service Available', ' '),
(83, 'Hairdryer', ' '),
(84, 'High Speed Internet-No Charge', ' '),
(85, 'Iron', ' '),
(86, 'Iron/Ironing Board', ' '),
(87, 'Microwave', ' '),
(88, 'Mini Refrigerator', ' '),
(89, 'Neutrogena(R) Bath Amenities', ' '),
(90, 'Newspaper M-F (USA Today)', ' '),
(91, 'Self Laundry Available', ' '),
(92, 'Telephone - Auto Wakeup', ' '),
(93, 'Two Phones with Dataport', ' '),
(94, 'Two Phones with Speaker', ' '),
(95, 'Two Phones with Voicemail', ' '),
(96, 'Baby Crib', 'A small crib for a baby');

-- --------------------------------------------------------

--
-- Table structure for table `user_info`
--

CREATE TABLE `user_info` (
  `id` int(11) NOT NULL,
  `first_name` varchar(20) DEFAULT '',
  `last_name` varchar(20) DEFAULT '',
  `user_address` varchar(50) DEFAULT '',
  `contact_number` varchar(20) DEFAULT '000-000-0000',
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(120) DEFAULT '',
  `password` varchar(50) DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_info`
--

INSERT INTO `user_info` (`id`, `first_name`, `last_name`, `user_address`, `contact_number`, `date_of_birth`, `email`, `password`) VALUES
(1, 'Nuga', 'Manandhar', 'kipling and finch', '6477097705', '2016-12-19', 'nuga@gmail.com', '$31$16$mK_xxdrh4qXhzKXiAeW3xSYnpCURUEZM6lE2YKXBJBE');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_info`
--
ALTER TABLE `admin_info`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `hotel_info`
--
ALTER TABLE `hotel_info`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `photo_gallery`
--
ALTER TABLE `photo_gallery`
  ADD PRIMARY KEY (`id`),
  ADD KEY `hotel_id` (`hotel_id`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `room_id` (`room_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id`),
  ADD KEY `room_type_id` (`room_type_id`);

--
-- Indexes for table `room_calendar`
--
ALTER TABLE `room_calendar`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `room_photo_gallery`
--
ALTER TABLE `room_photo_gallery`
  ADD PRIMARY KEY (`id`),
  ADD KEY `room_id` (`room_id`);

--
-- Indexes for table `room_service`
--
ALTER TABLE `room_service`
  ADD PRIMARY KEY (`id`),
  ADD KEY `room_id` (`room_id`) USING BTREE,
  ADD KEY `service_id` (`service_id`);

--
-- Indexes for table `room_type`
--
ALTER TABLE `room_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_info`
--
ALTER TABLE `user_info`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin_info`
--
ALTER TABLE `admin_info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `hotel_info`
--
ALTER TABLE `hotel_info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `photo_gallery`
--
ALTER TABLE `photo_gallery`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `room`
--
ALTER TABLE `room`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;
--
-- AUTO_INCREMENT for table `room_calendar`
--
ALTER TABLE `room_calendar`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `room_photo_gallery`
--
ALTER TABLE `room_photo_gallery`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `room_service`
--
ALTER TABLE `room_service`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;
--
-- AUTO_INCREMENT for table `room_type`
--
ALTER TABLE `room_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `service`
--
ALTER TABLE `service`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=97;
--
-- AUTO_INCREMENT for table `user_info`
--
ALTER TABLE `user_info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `photo_gallery`
--
ALTER TABLE `photo_gallery`
  ADD CONSTRAINT `photo_gallery_ibfk_1` FOREIGN KEY (`hotel_id`) REFERENCES `hotel_info` (`id`);

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`),
  ADD CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`);

--
-- Constraints for table `room`
--
ALTER TABLE `room`
  ADD CONSTRAINT `room_ibfk_1` FOREIGN KEY (`room_type_id`) REFERENCES `room_type` (`id`);

--
-- Constraints for table `room_photo_gallery`
--
ALTER TABLE `room_photo_gallery`
  ADD CONSTRAINT `room_photo_gallery_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`);

--
-- Constraints for table `room_service`
--
ALTER TABLE `room_service`
  ADD CONSTRAINT `room_service_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`),
  ADD CONSTRAINT `room_service_ibfk_2` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
