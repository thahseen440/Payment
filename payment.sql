-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 10, 2022 at 07:32 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `eb_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `ID` int(11) NOT NULL,
  `Account_No` varchar(200) NOT NULL,
  `Cus_Name` varchar(400) NOT NULL,
  `BillAmount` float NOT NULL,
  `Payment_Type` varchar(100) NOT NULL,
  `PayDate` varchar(250) NOT NULL,
  `Status` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`ID`, `Account_No`, `Cus_Name`, `BillAmount`, `Payment_Type`, `PayDate`, `Status`) VALUES
(1, 'EB101', 'Shajith', 2450, 'Card ', '20/4/22', 'Active'),
(2, 'EB1015', 'Thahseen ', 1500, 'Card', '10/04/2202', 'Active'),
(9, 'EB1555', 'ihsn', 250, 'cash', '02/05/2022', 'active'),
(10, 'EB4323', 'Thahseen', 4300, 'Not availabe', '--', 'Deactive'),
(12, 'EB2589', 'faisan', 4800, 'cash', '05/04/2022', 'active'),
(13, 'EB364', 'prajeeth', 8640, 'card', '05/05/2022', 'active'),
(15, 'EB364', 'prajeeth', 8640, 'card', '05/05/2022', 'deactive');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
