-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 13, 2023 at 07:02 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `angamiza`
--

-- --------------------------------------------------------

--
-- Table structure for table `feed`
--

CREATE TABLE `feed` (
  `id` int(10) NOT NULL,
  `photo` varchar(225) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `police_precinct` varchar(225) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `feed`
--

INSERT INTO `feed` (`id`, `photo`, `description`, `police_precinct`) VALUES
(1, 'http://192.168.137.1//img/p.jpeg', 'This is Phyllis Thuo, last seen in Kiambu county.', 'Kiambu'),
(2, 'http://192.168.137.1//img/b.jpeg', 'This is Brian Yegon, Last seen in Kenol.', 'Kenol'),
(3, 'http://192.168.137.1//img/avatar.jpg', 'This is Ghali Muga, Last seen in Meru.', 'Meru'),
(4, 'http://192.168.137.1//img/j.jpeg', 'This is Joan Kamongo, Last seen in Kisumu.', 'Kisumu'),
(5, 'http://192.168.137.1//img/avatar.jpg', 'This is Ghali Muga, Last seen in HomaBay.', 'HomaBay');

-- --------------------------------------------------------

--
-- Table structure for table `motor_vehicle`
--

CREATE TABLE `motor_vehicle` (
  `id` int(225) NOT NULL,
  `date_logged` date DEFAULT NULL,
  `vehicle_plate` varchar(225) NOT NULL,
  `vin` varchar(225) DEFAULT NULL,
  `model` varchar(500) DEFAULT NULL,
  `vehicle_make` varchar(225) DEFAULT NULL,
  `vehicle_desc` varchar(10000) DEFAULT NULL,
  `theft_date` varchar(20) DEFAULT NULL,
  `theft_details` varchar(10000) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `image` varchar(10000) DEFAULT NULL,
  `image_desc` varchar(225) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `motor_vehicle`
--

INSERT INTO `motor_vehicle` (`id`, `date_logged`, `vehicle_plate`, `vin`, `model`, `vehicle_make`, `vehicle_desc`, `theft_date`, `theft_details`, `status`, `image`, `image_desc`) VALUES
(14, '2023-03-26', 'KCV 567Y', '16717272727', '', 'sedan', 'white', '12/10/23', 'broke windshield', 'missing', 'http://192.168.137.1/upload/burna.jpg', 'burna'),
(15, '2023-03-26', 'plate', '', '', 'make', '', '', '', '', 'http://192.168.137.1/upload/ghali.jpg', 'ghali'),
(16, '2023-03-26', 'KCE', '16266262', '', 'vanguard', 'black', '12/02/87', 'gone', 'found', 'http://192.168.137.1/upload/vanguard.jpg', 'vanguard'),
(18, '2023-04-24', 'KCJ 456Y', '00001627272', '', 'pickup', 'Khat', '12/3/23', 'took the whole car', 'missing', 'http://192.168.137.1/upload/jaba.jpg', 'jaba');

-- --------------------------------------------------------

--
-- Table structure for table `suspect`
--

CREATE TABLE `suspect` (
  `id` int(225) NOT NULL,
  `name` varchar(225) DEFAULT NULL,
  `sex` varchar(225) DEFAULT NULL,
  `race` varchar(225) DEFAULT NULL,
  `height` varchar(225) DEFAULT NULL,
  `build` varchar(225) DEFAULT NULL,
  `age` varchar(225) DEFAULT NULL,
  `facial` varchar(225) DEFAULT NULL,
  `clothing` varchar(225) DEFAULT NULL,
  `voice` varchar(225) DEFAULT NULL,
  `location` varchar(225) DEFAULT NULL,
  `other_desc` varchar(10000) DEFAULT NULL,
  `picture` varchar(225) DEFAULT NULL,
  `image_desc` varchar(225) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `suspect`
--

INSERT INTO `suspect` (`id`, `name`, `sex`, `race`, `height`, `build`, `age`, `facial`, `clothing`, `voice`, `location`, `other_desc`, `picture`, `image_desc`) VALUES
(3, 'bame', 'denger', 'rave', 'height', NULL, 'age', 'face', 'clothes', 'voice', 'loca', 'signi', 'http://192.168.137.1/upload/burna.jpg', 'burna'),
(5, 'Krishan', 'Male', 'white', '2 meters', NULL, '25-40', 'none', 'black jacket', 'deep', 'nairobi', 'tatto of dragon in left arm', 'http://192.168.137.1/upload/Krishan.jpg', 'Krishan');

-- --------------------------------------------------------

--
-- Table structure for table `tasks`
--

CREATE TABLE `tasks` (
  `id` int(10) NOT NULL,
  `signinname` varchar(225) DEFAULT NULL,
  `empid` varchar(225) DEFAULT NULL,
  `task` varchar(1000) DEFAULT NULL,
  `priority` varchar(225) NOT NULL,
  `date_created` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tasks`
--

INSERT INTO `tasks` (`id`, `signinname`, `empid`, `task`, `priority`, `date_created`) VALUES
(1, 'Ghali Muga', '000', 'Fill in your paper work for today', 'HIGH', '2022-08-22'),
(2, 'Ghali Muga', '000', 'Catch the thief who stole the cabbage', 'MEDIUM', '2022-08-23'),
(3, 'Ghali Muga', '000', 'Report to Nakuru for previous missions debrief', 'LOW', '2022-08-23');

-- --------------------------------------------------------

--
-- Table structure for table `task_leader`
--

CREATE TABLE `task_leader` (
  `id` int(11) NOT NULL,
  `name` varchar(225) DEFAULT NULL,
  `precinct` varchar(225) NOT NULL,
  `photo` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `task_leader`
--

INSERT INTO `task_leader` (`id`, `name`, `precinct`, `photo`) VALUES
(1, 'Sarah McKenna', 'Brooklyn 99', 'http://192.168.137.1/img/t.jpg'),
(2, 'David Too', 'WestSide 44', 'http://192.168.137.1/img/leader2.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `tblusers`
--

CREATE TABLE `tblusers` (
  `id` int(10) NOT NULL,
  `firstname` varchar(225) DEFAULT NULL,
  `lastname` varchar(225) DEFAULT NULL,
  `signinname` varchar(225) DEFAULT NULL,
  `empid` varchar(225) DEFAULT NULL,
  `phone_no` varchar(20) DEFAULT NULL,
  `role` varchar(225) DEFAULT NULL,
  `status` varchar(225) DEFAULT NULL,
  `email` varchar(225) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblusers`
--

INSERT INTO `tblusers` (`id`, `firstname`, `lastname`, `signinname`, `empid`, `phone_no`, `role`, `status`, `email`) VALUES
(1, 'Ghali', 'Muga', 'mugaghali@gmail.com', '000', '0704872471', 'Management', 'In Office', 'mugaghali@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `feed`
--
ALTER TABLE `feed`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `motor_vehicle`
--
ALTER TABLE `motor_vehicle`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `suspect`
--
ALTER TABLE `suspect`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `task_leader`
--
ALTER TABLE `task_leader`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tblusers`
--
ALTER TABLE `tblusers`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `feed`
--
ALTER TABLE `feed`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `motor_vehicle`
--
ALTER TABLE `motor_vehicle`
  MODIFY `id` int(225) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `suspect`
--
ALTER TABLE `suspect`
  MODIFY `id` int(225) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tasks`
--
ALTER TABLE `tasks`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `task_leader`
--
ALTER TABLE `task_leader`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tblusers`
--
ALTER TABLE `tblusers`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
