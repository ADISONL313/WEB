-- MySQL dump 10.13  Distrib 8.0.37, for Win64 (x86_64)
--
-- Host: localhost    Database: sav
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `affairs`
--

DROP TABLE IF EXISTS `affairs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `affairs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` bigint NOT NULL,
  `teacher_id` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `affairsType` int NOT NULL,
  `audits` int DEFAULT NULL,
  `reason` text,
  `sourceRoomOrMajor` varchar(255) DEFAULT NULL,
  `refuseReason` varchar(255) DEFAULT NULL,
  `majorOrRoomId` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `affairs`
--

LOCK TABLES `affairs` WRITE;
/*!40000 ALTER TABLE `affairs` DISABLE KEYS */;
INSERT INTO `affairs` VALUES (16,2024000002,'2024104221536','人工智能与大数据学院-计算机科学与技术',1,3,'11111','人工智能与大数据学院-软件工程','23123',27),(17,2024000002,'2024104221536','男生公寓2栋-101',2,1,'123123','男生公寓1栋-102',NULL,11),(18,2024000004,'2024104221618','人工智能与大数据学院-计算机科学与技术',1,2,'2111','电气工程与智能制造学院-智能制造',NULL,27),(19,2024000004,'2024104221618','男生公寓2栋-101',2,2,'11111','男生公寓1栋-102','不准换',11),(22,2024000006,'2024104221536','人工智能与大数据学院-计算机科学与技术',1,3,NULL,'人工智能与大数据学院-软件工程','11',27);
/*!40000 ALTER TABLE `affairs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `className` varchar(255) DEFAULT NULL,
  `major_id` int NOT NULL,
  `grade` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` VALUES (27,'软件工程1班',49,'2023'),(28,'人工智能1班',32,'2023'),(29,'人工智能1班',32,'2021'),(30,'软件工程1班',49,'2021'),(31,'电气自动化1班',34,'2022'),(32,'电气自动化2班',34,'2022'),(33,'电气自动化3班',34,'2022'),(34,'软件工程3班',49,'2022'),(35,'软件工程1班',49,'2022'),(36,'软件工程2班',49,'2022'),(38,'软件工程2班',49,'2021'),(39,'软件工程3班',49,'2021'),(40,'智能制造1班',30,'2021'),(41,'计算机科学与技术1班',27,'2021'),(42,'计算机科学与技术1班',27,'2022'),(43,'计算机科学与技术1班',27,'2023'),(44,'影视传媒1班',33,'2021'),(45,'土木工程1班',28,'2021'),(46,'哲学1班',38,'2023'),(47,'社会科学1班',50,'2023'),(49,'软件工程2班',49,'2023');
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dormitories`
--

DROP TABLE IF EXISTS `dormitories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dormitories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `buildingName` varchar(255) NOT NULL,
  `room_id` varchar(50) NOT NULL,
  `gender` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dormitories`
--

LOCK TABLES `dormitories` WRITE;
/*!40000 ALTER TABLE `dormitories` DISABLE KEYS */;
INSERT INTO `dormitories` VALUES (3,'男生公寓1栋','102',0),(4,'男生公寓1栋','104',0),(6,'男生公寓1栋','106',0),(7,'男生公寓1栋','105',0),(8,'女生公寓1栋','101',1),(9,'女生公寓1栋','102',1),(10,'女生公寓2栋','201',1),(11,'男生公寓2栋','101',0),(12,'男生公寓2栋','102',0),(13,'男生公寓4栋','402',0),(16,'男生公寓1栋','518',0);
/*!40000 ALTER TABLE `dormitories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `major` (
  `id` int NOT NULL AUTO_INCREMENT,
  `majorName` varchar(255) NOT NULL,
  `college` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major`
--

LOCK TABLES `major` WRITE;
/*!40000 ALTER TABLE `major` DISABLE KEYS */;
INSERT INTO `major` VALUES (27,'计算机科学与技术','人工智能与大数据学院'),(28,'土木工程','建筑与土木工程学院'),(29,'工商管理','经济管理学院'),(30,'智能制造','电气工程与智能制造学院'),(31,'材料工程','建筑与土木工程学院'),(32,'人工智能技术1','人工智能与大数据学院'),(33,'影视传媒','艺术与传媒学院'),(34,'电气自动化','电气工程与智能制造学院'),(35,'智能建造','建筑与土木工程学院'),(36,'数字媒体技术','艺术与传媒学院'),(37,'会计','经济管理学院'),(38,'哲学','人文学院'),(39,'历史','人文学院'),(40,'工程造价','建筑管理学院'),(41,'建设工程管理','建筑管理学院'),(42,'社会体育指导与管理','体育与大健康学院'),(43,'运动康复','体育与大健康学院'),(45,'新闻传播','艺术与传媒学院'),(47,'工业机器人','电气工程与智能制造学院'),(49,'软件工程','人工智能与大数据学院'),(50,'社会科学','人文学院'),(54,'艺术','人文学院');
/*!40000 ALTER TABLE `major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `password` varchar(255) NOT NULL,
  `id_card` varchar(18) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `counselor` varchar(255) DEFAULT NULL,
  `college` varchar(255) DEFAULT NULL,
  `major` int DEFAULT NULL,
  `student_id` bigint NOT NULL AUTO_INCREMENT,
  `studentName` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `id_card` (`id_card`)
) ENGINE=InnoDB AUTO_INCREMENT=2024000029 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES ('96e79218965eb72c92a549dd5a330112','50010220020921269X','11111111111','男','2024104221536','人工智能与大数据学院',27,2024000002,'adison','吉林省'),('96e79218965eb72c92a549dd5a330112','111111111111111111','11111111111','男','2024104221618','电气工程与智能制造学院',27,2024000004,'ht','海南省'),('e3ceb5881a0a1fdaad01296d7554868d','222222222222222222','11111111111','女','2024104221536','人工智能与大数据学院',27,2024000005,'rhw','青海省'),('1a100d2c0dab19c4430e7d73762b3423','233133333333333333','11111111111','男','2024104221536','人工智能与大数据学院',32,2024000006,'bzh','黑龙江省'),('96e79218965eb72c92a549dd5a330112','132141411111111111','11111111111','男','2024104221536','人工智能与大数据学院',49,2024000007,'xxx','河北省'),('642589746de146b3021c0d158e45b78e','123333333333331111','12333333333','男','2024104221643','艺术与传媒学院',33,2024000008,'wqy','青海省'),('96e79218965eb72c92a549dd5a330112','123213231231111111','11111111111','女','2024104221552','人工智能与大数据学院',49,2024000009,'znf','青海省'),('b9567373bdc49651c6254acb3c828e25','232144444444444441','12333333333','女','2024104221819','建筑与土木工程学院',28,2024000010,'xx','河北省'),('1a100d2c0dab19c4430e7d73762b3423','233333333333333333','11111111111','女','20241051020','人文学院',38,2024000011,'dd','黑龙江省'),('cd87cd5ef753a06ee79fc75dc7cfe66c','weeeeeeeeeeeeeeeee','qqqqqqqqqqq','男','2024104221643','艺术与传媒学院',33,2024000012,'ed','青海省'),('1a100d2c0dab19c4430e7d73762b3423','112333333333333333','31111111111','男','2024104221643','艺术与传媒学院',33,2024000013,'3123',NULL),('96e79218965eb72c92a549dd5a330112','412312311111111111','11111111111','女','20241051020','人文学院',38,2024000014,'dd',NULL),('661d01f6bc520caa3af6f326c08d4925','50010223406788888X','11111111111','男','20241051020','人文学院',50,2024000015,'王志强','吉林省'),('123456','500102312322333312','23111111111','女','2024000002','人工智能与大数据学院',27,2024000016,'小王','河南省'),('be85c819ed7425f4c9be3f46d464f43a','500102362322343312','11111111111','男','2024000002','人工智能与大数据学院',27,2024000020,'老王3',NULL),('123456','500102312322341312','11111111111','男','2024000002','人工智能与大数据学院',27,2024000023,'老黑',NULL),('79220b47b65534be555668a01a75f293','500102312322343612','11111111111','男','2024000002','人工智能与大数据学院',27,2024000024,'老王2',NULL),('9e34975fae1d0f87812afd13a11bf4eb','500102362322343712','11111111111','男','2024000002','人工智能与大数据学院',27,2024000025,'老王3',NULL),('6886badb36b23129002bbbae0d9432d0','123444','123','男','202411123123','人工智能与大数据学院',27,2024000027,'123',NULL),('698d51a19d8a121ce581499d7b701668','111','111','男','2024104221536','艺术与传媒学院',33,2024000028,'a',NULL);
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentsdetail`
--

DROP TABLE IF EXISTS `studentsdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studentsdetail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `level` varchar(40) DEFAULT NULL,
  `moral` int DEFAULT NULL,
  `course` int DEFAULT NULL,
  `attendance` int DEFAULT NULL,
  `practice` int DEFAULT NULL,
  `ideology` int DEFAULT NULL,
  `evaluate` text,
  `class_id` int DEFAULT NULL,
  `homeAddress` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `roomId` int DEFAULT NULL,
  `student_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentsdetail`
--

LOCK TABLES `studentsdetail` WRITE;
/*!40000 ALTER TABLE `studentsdetail` DISABLE KEYS */;
INSERT INTO `studentsdetail` VALUES (49,'2022',5,5,4,5,5,'你好',42,'巴南区龙洲湾2','2020-06-10',11,2024000002),(50,'2023',3,0,0,1,4,'坏学生',47,'黑龙江漠河县','1981-10-14',13,2024000015),(51,'2021',4,1,2,3,2,'坏学生',41,'重庆市','2024-10-08',8,2024000005),(52,'2021',2,0,4,3,2,'1111',29,'巴南','2024-10-01',3,2024000006),(53,'2021',4,0,3,3,3,'1111',41,'黑河','2024-10-15',3,2024000004),(54,'2021',0,0,0,0,0,'好学生',27,'巴南','2023-09-01',3,NULL),(55,'2021',0,0,0,0,0,'好学生',27,'巴南','2023-09-01',3,NULL),(56,'2021',0,0,0,0,0,'好学生',27,'巴南','2023-09-01',3,NULL);
/*!40000 ALTER TABLE `studentsdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teachers`
--

DROP TABLE IF EXISTS `teachers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teachers` (
  `password` varchar(255) DEFAULT NULL,
  `teacherName` varchar(100) DEFAULT NULL,
  `college` varchar(255) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `idCard` varchar(18) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `teacher_id` varchar(255) NOT NULL,
  `remarks` text,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teachers`
--

LOCK TABLES `teachers` WRITE;
/*!40000 ALTER TABLE `teachers` DISABLE KEYS */;
INSERT INTO `teachers` VALUES ('4941d2acef18aac15eee7881360929f0','李小明','建筑管理学院','18888888889','438432493284784812','女','2024102193133','好老师'),('c09cc5e8c7cea9307ff8a7330ecebc50','123','艺术与传媒学院','123','213','女','2024102224548','123123'),('e10adc3949ba59abbe56e057f20f883e','刘老师','人工智能与大数据学院','11111111111','111111111111111111','女','2024104221536',NULL),('b5d246f5901ab74d593f045b3456e57e','李老师','人工智能与大数据学院','11111111111','222222222222222222','男','2024104221552',NULL),('27ff0ec5aae6fe086726a679187d0e71','王老师','电气工程与智能制造学院','11111111111','222222223333333322','女','2024104221618',NULL),('e6f4500886fc3ce9bb2f10e9b9fd305a','钱老师','艺术与传媒学院','11111111111','222244444444444444','男','2024104221643',NULL),('80db3c65c22770a680212e0c897a1132','赵老师','电气工程与智能制造学院','11111111111','222222223333333333','男','202410422165',NULL),('8f655d9b0b609b4b2e62309a51e4346b','汪老师','经济管理学院','11111111111','222244444413111111','女','2024104221730',NULL),('0edb8a9b1e4384024a08e4d263eaec84','包老师','经济管理学院','11111111111','222244444413111133','男','2024104221754',NULL),('a13c4f258f074e3a87f0162893bee5ad','饶老师','建筑与土木工程学院','11111111111','222244444413666666','男','2024104221819',NULL),('603bc118049ef8e71e983480cbba8c95','谭老师','建筑与土木工程学院','11111111111','222244444413666667','女','2024104221831',NULL),('53abf8f94cc964ee277b6c92949cbd1c','xxxx','体育与大健康学院','11111111111','333413111111111111','男','202410505945',NULL),('099e52885074440fd8d09d067cddac85','xxx2','体育与大健康学院','11111111111','333413111111434344','女','202410505955',NULL),('a33b058668bb5200d61cdff3e3bc647c','xx4233','建筑管理学院','11111111111','333413111444444444','女','20241051010',NULL),('1b89e2987311dd2c6291ba66a7befa21','xx42332','人文学院','11111111111','333413111445435544','女','20241051020',NULL),('f11ab5ed14331ce856631776accc28c4','xxx233','建筑管理学院','11111111111','333413111124444444','女','2024105103',NULL),('3b0880c1de1328d06106bc4b097c509d','xx4','人文学院','11111111111','333413116666665544','女','20241051030',NULL),('1234567','李小黑1','人工智能与大数据学院',NULL,'1113132134441123','男','202411123123',NULL),('123456','李小黑','人工智能与大数据学院',NULL,'1113132134641123','男','202411123553',NULL),('21232f297a57a5a743894a0e4a801fc3','超级管理员',NULL,'11111111111','111111111111111111','女','admin',NULL);
/*!40000 ALTER TABLE `teachers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-18  9:32:33
