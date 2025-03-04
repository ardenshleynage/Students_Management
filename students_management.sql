-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Feb 25, 2025 at 02:03 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `students_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `classes`
--

CREATE TABLE `classes` (
  `ID` bigint(20) NOT NULL,
  `access` int(11) NOT NULL,
  `reg_date` date NOT NULL,
  `score` float DEFAULT NULL,
  `course_id` bigint(20) NOT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  `teacher_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `classes`
--

INSERT INTO `classes` (`ID`, `access`, `reg_date`, `score`, `course_id`, `student_id`, `teacher_id`) VALUES
(1, 1, '2025-02-24', NULL, 21, NULL, 12),
(2, 1, '2025-02-24', NULL, 13, NULL, 12),
(3, 1, '2025-02-24', NULL, 23, NULL, 13),
(4, 1, '2025-02-24', 98, 21, 16, 12),
(5, 1, '2025-02-24', NULL, 13, 16, 12),
(6, 1, '2025-02-24', NULL, 13, 15, 12),
(7, 1, '2025-02-24', 89, 21, 15, 12),
(8, 1, '2025-02-24', 90, 23, 15, 13),
(9, 1, '2025-02-24', NULL, 22, NULL, 15),
(10, 1, '2025-02-24', 84, 22, 15, 15);

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `ID` bigint(20) NOT NULL,
  `access` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `reg_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`ID`, `access`, `description`, `name`, `reg_date`) VALUES
(13, 1, 'ok tier', 'physique', '2025-02-23'),
(21, 1, 'ok', 'chimie', '2025-02-23'),
(22, 1, 'utile', 'anglais', '2025-02-23'),
(23, 1, 'pratique', 'eps', '2025-02-23');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `ID` bigint(20) NOT NULL,
  `access` int(11) NOT NULL,
  `address` varchar(255) NOT NULL,
  `dob` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` int(11) NOT NULL,
  `reg_date` date NOT NULL,
  `reg_number` varchar(255) NOT NULL,
  `sex` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`ID`, `access`, `address`, `dob`, `email`, `first_name`, `last_name`, `password`, `phone`, `reg_date`, `reg_number`, `sex`) VALUES
(15, 1, 'haiti', '2002-02-05', 'ng@gmail.com', 'genshley', 'Nage', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 12312312, '2025-02-23', '733706', 1),
(16, 1, 'haiti', '2007-02-23', 'nx@gmail.com', 'Xenshley', 'Nage', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 12345123, '2025-02-23', '064422', 1),
(17, 1, 'haiti', '2025-02-02', 'xd@gmail.com', 'Dhjkdcw', 'Xage', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 12341234, '2025-02-23', '887568', 1);

-- --------------------------------------------------------

--
-- Table structure for table `super_admin`
--

CREATE TABLE `super_admin` (
  `ID` bigint(20) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `super_admin`
--

INSERT INTO `super_admin` (`ID`, `first_name`, `last_name`, `password`, `username`) VALUES
(3, 'Ardenshley', 'Nage', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'an');

-- --------------------------------------------------------

--
-- Table structure for table `teachers`
--

CREATE TABLE `teachers` (
  `ID` bigint(20) NOT NULL,
  `access` int(11) NOT NULL,
  `dob` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` int(11) NOT NULL,
  `reg_date` date NOT NULL,
  `reg_number` varchar(255) NOT NULL,
  `sex` int(11) NOT NULL,
  `speciality` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `teachers`
--

INSERT INTO `teachers` (`ID`, `access`, `dob`, `email`, `first_name`, `last_name`, `password`, `phone`, `reg_date`, `reg_number`, `sex`, `speciality`) VALUES
(12, 1, '2000-02-02', 'an@gmail.com', 'Ardenshley', 'Nage', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 12341234, '2025-02-23', '468666', 1, 'Sciences'),
(13, 1, '1998-02-11', 'nf@gmail.com', 'Fenshley', 'Nage', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 12312312, '2025-02-23', '678480', 1, 'Sciences'),
(15, 1, '1997-02-02', 'en@gmail.com', 'Emma', 'Nage', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 12341239, '2025-02-23', '233630', 2, 'Math');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `classes`
--
ALTER TABLE `classes`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_classes_student_id` (`student_id`),
  ADD KEY `FK_classes_teacher_id` (`teacher_id`),
  ADD KEY `FK_classes_course_id` (`course_id`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `description` (`description`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `phone` (`phone`),
  ADD UNIQUE KEY `reg_number` (`reg_number`);

--
-- Indexes for table `super_admin`
--
ALTER TABLE `super_admin`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `phone` (`phone`),
  ADD UNIQUE KEY `reg_number` (`reg_number`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `classes`
--
ALTER TABLE `classes`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `course`
--
ALTER TABLE `course`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `super_admin`
--
ALTER TABLE `super_admin`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `teachers`
--
ALTER TABLE `teachers`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `classes`
--
ALTER TABLE `classes`
  ADD CONSTRAINT `FK_classes_course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`ID`),
  ADD CONSTRAINT `FK_classes_student_id` FOREIGN KEY (`student_id`) REFERENCES `students` (`ID`),
  ADD CONSTRAINT `FK_classes_teacher_id` FOREIGN KEY (`teacher_id`) REFERENCES `teachers` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
