-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th8 01, 2020 lúc 04:11 AM
-- Phiên bản máy phục vụ: 10.4.11-MariaDB
-- Phiên bản PHP: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `project`
--
CREATE DATABASE IF NOT EXISTS `project` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `project`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE IF NOT EXISTS `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `roleId` int(11) NOT NULL,
  `status` int(11) DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `roleId` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`id`, `username`, `password`, `roleId`, `status`) VALUES
(1, 'khanh@admin', '123456', 4, 1),
(2, 'sonvan@gv', '123456', 2, 1),
(3, 'halv@admin', '123456', 3, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `attendance`
--

CREATE TABLE IF NOT EXISTS `attendance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rollNo` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `scheduleId` int(11) NOT NULL,
  `date` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` int(11) DEFAULT 1,
  PRIMARY KEY (`id`),
  KEY `rollNo` (`rollNo`),
  KEY `scheduleId` (`scheduleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `class`
--

CREATE TABLE IF NOT EXISTS `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classNo` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` int(11) DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `classNo` (`classNo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `class`
--

INSERT INTO `class` (`id`, `classNo`, `status`) VALUES
(1, 'C1808G', 1),
(2, 'C1708i', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `empNum` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fullname` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `birthdate` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `gender` varchar(6) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phoneNo` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `accId` int(11) NOT NULL,
  `status` int(11) DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `empNum` (`empNum`),
  KEY `accId` (`accId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `employee`
--

INSERT INTO `employee` (`id`, `empNum`, `fullname`, `birthdate`, `gender`, `address`, `email`, `phoneNo`, `accId`, `status`) VALUES
(1, 'E001', 'Lê Văn Khánh', '14-09-1996', 'nam', 'Yên Định, Thanh Hóa', 'khanh.lv.138@aptechlearning.edu.vn', '0985136842', 1, 1),
(4, 'E002', 'Lê Văn Sơn', '13-08-2020', 'nam', 'Thanh Hóa', 'son.lv.112@aptechlearning.edu.vn', '0123854967', 2, 1),
(5, 'E003', 'Lê Thị Hà', '26-08-2020', 'nữ', 'Thanh Hóa', 'ha.lt.112@aptechlearning.edu.vn', '0125879466', 3, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `mark`
--

CREATE TABLE IF NOT EXISTS `mark` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rollNo` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `subjectNo` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mark` double NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `rollNo` (`rollNo`,`subjectNo`),
  KEY `subjectNo` (`subjectNo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `mark`
--

INSERT INTO `mark` (`id`, `rollNo`, `subjectNo`, `mark`) VALUES
(1, 'C1808G4191', 'S001', 8.5),
(3, 'C1808G4191', 'S002', 8.5);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`id`, `role`) VALUES
(2, 'Giáo vụ'),
(3, 'CTSV'),
(4, 'Administration');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `schedule`
--

CREATE TABLE IF NOT EXISTS `schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classNo` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `subjectNo` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `teacher` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `teachingFrameTime` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `teachingTime` int(11) NOT NULL DEFAULT 0,
  `startDate` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `endDate` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` int(11) DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `classNo` (`classNo`,`subjectNo`),
  KEY `subjectNo` (`subjectNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rollNo` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fullname` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `birthdate` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `gender` varchar(6) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phoneNo` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `classNo` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` int(11) DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `rollNo` (`rollNo`),
  KEY `classNo` (`classNo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `student`
--

INSERT INTO `student` (`id`, `rollNo`, `fullname`, `birthdate`, `gender`, `address`, `email`, `phoneNo`, `classNo`, `status`) VALUES
(1, 'C1808G4191', 'Lê Văn Khánh', '14-09-1996', 'nam', 'Thanh Hoa', 'khanh.lv.138@aptectlearning.edu.vn', '0985136842', 'C1808G', 1),
(2, 'C1708i4045', 'Lê Văn Khánh', '14-09-1996', 'nam', 'Thanh Hóa', 'khanh1.lv.138@aptechlearning.edu.vn', '0985136842', 'C1708i', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `subject`
--

CREATE TABLE IF NOT EXISTS `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subjectNo` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `subjectName` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` int(11) DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `subjectNo` (`subjectNo`),
  UNIQUE KEY `subjectName` (`subjectName`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `subject`
--

INSERT INTO `subject` (`id`, `subjectNo`, `subjectName`, `status`) VALUES
(1, 'S001', 'C Programming', 1),
(2, 'S002', 'Java', 1),
(3, 'S003', 'SQL Server', 1),
(5, 'S004', 'Web Design', 1);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `account_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`);

--
-- Các ràng buộc cho bảng `attendance`
--
ALTER TABLE `attendance`
  ADD CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`rollNo`) REFERENCES `student` (`rollNo`),
  ADD CONSTRAINT `attendance_ibfk_2` FOREIGN KEY (`scheduleId`) REFERENCES `schedule` (`id`);

--
-- Các ràng buộc cho bảng `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`accId`) REFERENCES `account` (`id`);

--
-- Các ràng buộc cho bảng `mark`
--
ALTER TABLE `mark`
  ADD CONSTRAINT `mark_ibfk_1` FOREIGN KEY (`rollNo`) REFERENCES `student` (`rollNo`),
  ADD CONSTRAINT `mark_ibfk_2` FOREIGN KEY (`subjectNo`) REFERENCES `subject` (`subjectNo`);

--
-- Các ràng buộc cho bảng `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`classNo`) REFERENCES `class` (`classNo`),
  ADD CONSTRAINT `schedule_ibfk_2` FOREIGN KEY (`subjectNo`) REFERENCES `subject` (`subjectNo`);

--
-- Các ràng buộc cho bảng `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`classNo`) REFERENCES `class` (`classNo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
