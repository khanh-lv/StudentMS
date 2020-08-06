-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th8 06, 2020 lúc 01:04 PM
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
  `username` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `roleId` int(11) NOT NULL,
  `status` int(11) DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `roleId` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`id`, `username`, `password`, `roleId`, `status`) VALUES
(1, 'khanh@admin', '123456', 1, 1),
(2, 'quang@gv', '123456', 1, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `attendance`
--

CREATE TABLE IF NOT EXISTS `attendance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentId` int(11) NOT NULL,
  `scheduleId` int(11) NOT NULL,
  `date` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` int(11) DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `studentId` (`studentId`),
  KEY `scheduleId` (`scheduleId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `attendance`
--

INSERT INTO `attendance` (`id`, `studentId`, `scheduleId`, `date`, `status`) VALUES
(16, 1, 1, '06-08-2020', 1),
(17, 3, 1, '06-08-2020', 0),
(18, 2, 2, '06-08-2020', 0);

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `class`
--

INSERT INTO `class` (`id`, `classNo`, `status`) VALUES
(1, 'C1808G', 1),
(2, 'C1010H', 0),
(3, 'C1808G2', 1),
(4, 'C1901A', 0);

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
  `email` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phoneNo` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` int(11) DEFAULT 1,
  `accId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `empNum` (`empNum`),
  KEY `accId` (`accId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `employee`
--

INSERT INTO `employee` (`id`, `empNum`, `fullname`, `birthdate`, `gender`, `address`, `email`, `phoneNo`, `status`, `accId`) VALUES
(1, 'E002', 'Nguyễn Minh Quang', '26-08-2020', 'Nam', 'Nam Định', 'quang.nm.113@aptechlearning.edu.vn', '0123658497', 1, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `mark`
--

CREATE TABLE IF NOT EXISTS `mark` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentId` int(11) NOT NULL,
  `subjectId` int(11) NOT NULL,
  `mark` double DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `studentId` (`studentId`,`subjectId`),
  KEY `subjectId` (`subjectId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `mark`
--

INSERT INTO `mark` (`id`, `studentId`, `subjectId`, `mark`) VALUES
(1, 1, 1, 8),
(2, 3, 1, 6),
(3, 2, 1, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`id`, `role`) VALUES
(1, 'administrator'),
(2, 'giáo vụ'),
(3, 'ctsv');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `schedule`
--

CREATE TABLE IF NOT EXISTS `schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classId` int(11) NOT NULL,
  `subjectId` int(11) NOT NULL,
  `teacher` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `teachingFrameTime` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `teachingTime` int(11) DEFAULT 0,
  `startDate` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `endDate` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` int(11) DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `classId` (`classId`,`subjectId`),
  KEY `subjectId` (`subjectId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `schedule`
--

INSERT INTO `schedule` (`id`, `classId`, `subjectId`, `teacher`, `teachingFrameTime`, `teachingTime`, `startDate`, `endDate`, `status`) VALUES
(1, 1, 1, 'Trần Văn Điệp', 'Sáng t3,t5,t7', 4, '14-09-2018', '14-10-2018', 1),
(2, 3, 1, 'Trần Văn Điệp', 'Chiều t2,t4,t6', 4, '01-05-2019', '31-05-2019', 1),
(3, 3, 2, 'Trần Văn Điệp', 'Sáng t2,t4,t6', 4, '01-06-2019', '05-07-2019', 1);

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
  `email` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phoneNo` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `classId` int(11) NOT NULL,
  `status` int(11) DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `rollNo` (`rollNo`),
  KEY `classId` (`classId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `student`
--

INSERT INTO `student` (`id`, `rollNo`, `fullname`, `birthdate`, `gender`, `address`, `email`, `phoneNo`, `classId`, `status`) VALUES
(1, 'C1808G4191', 'Lê Văn Khánh', '14-09-1996', 'Nam', 'Thanh Hóa', 'gunvolf@gmail.com', '0985136842', 1, 1),
(2, 'C1808G5152', 'Lê Thị B', '21-08-2020', 'Nữ', 'Hà nội', 'lethib@gmail.com', '0123456789', 3, 1),
(3, 'C1010G1234', 'Lê Thị C', '20-08-2020', 'Nữ', 'hà Nội', 'lethib@gmail.com', '1234567890', 1, 1);

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
  UNIQUE KEY `subjectNo` (`subjectNo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `subject`
--

INSERT INTO `subject` (`id`, `subjectNo`, `subjectName`, `status`) VALUES
(1, 'S001', 'C Programming', 1),
(2, 'S002', 'Web Design', 1);

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
  ADD CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`studentId`) REFERENCES `student` (`id`),
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
  ADD CONSTRAINT `mark_ibfk_1` FOREIGN KEY (`studentId`) REFERENCES `student` (`id`),
  ADD CONSTRAINT `mark_ibfk_2` FOREIGN KEY (`subjectId`) REFERENCES `subject` (`id`);

--
-- Các ràng buộc cho bảng `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`classId`) REFERENCES `class` (`id`),
  ADD CONSTRAINT `schedule_ibfk_2` FOREIGN KEY (`subjectId`) REFERENCES `subject` (`id`);

--
-- Các ràng buộc cho bảng `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`classId`) REFERENCES `class` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
