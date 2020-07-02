-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: medical_store
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `medicine`
--

DROP TABLE IF EXISTS `medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicine` (
  `mbno` varchar(10) NOT NULL,
  `mname` varchar(50) DEFAULT NULL,
  `mcompany` varchar(50) DEFAULT NULL,
  `mqty` int DEFAULT NULL,
  `mexpdate` varchar(50) DEFAULT NULL,
  `mpurdate` varchar(50) DEFAULT NULL,
  `mtype` varchar(30) DEFAULT NULL,
  `mpurprice` float DEFAULT NULL,
  `msaleprice` float DEFAULT NULL,
  `mrackno` varchar(20) DEFAULT NULL,
  `sid` int DEFAULT NULL,
  `sname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`mbno`),
  KEY `sid` (`sid`),
  CONSTRAINT `medicine_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `supplier` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine`
--

LOCK TABLES `medicine` WRITE;
/*!40000 ALTER TABLE `medicine` DISABLE KEYS */;
INSERT INTO `medicine` VALUES ('E20302','Mecobion-OD','Medley pharmacceuticals ltd',100,'8-2013','6-2-2013','Tablet',50,78,'m',2,'rupesh kamble'),('l127','cystone','himalaya',60,'2-2015','6-2-2013','Tablet',60,80,'c',7,'baban marne'),('m145','metxl-25','meta parma',10,'5-2014','6-2-2013','Tablet',80,100,'m',3,'Ganesh marne'),('p20776','GlimiSave-4','Gilmepiride',100,'7-2014','6-2-2013','Tablet',70,87,'g',1,'akshay mahadik'),('q111','vix500','vix  pharma',10,'2-2014','7-2-2013','Tablet',25,50,'v1',2,'Ganesh marne'),('r2025','tiger balm','makson pvt ltd',50,'2-2016','6-2-2013','Balm',15,27,'x',6,'nitin pawar'),('s1234','combiflan-5mg','combifan pharma',10,'4-2015','7-2-2013','Tablet',50,100,'c1',2,'rupesh kamble'),('s167','supradin','supra phrma',50,'8-2014','6-2-2013','Tablet',17,30,'s',4,'Mayur joshi'),('w12','crocine','crocine pharma',10,'2-2015','7-2-2013','Tablet',50,100,'c`',2,'rupesh kamble');
/*!40000 ALTER TABLE `medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `type` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (123,'HIT',NULL,'03-1232234','Golomb St 52, Holon, 5810201',1,1),(200441467,'Amit Shani','1993-03-10','053-3855523','very near HIT, Holon',1,1),(308335918,'David Zukerman','1997-01-20','054-92472304','Vaizman 125, Bat Yam',1,1),(313108342,'Dima Ruven','1986-01-28','0547-2423320','ha-Shomron 19, Holon',2,1),(323501890,'Alexandra Fedorov','1995-05-03','054-3742947','Rabin street, 22',2,1);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_supply`
--

DROP TABLE IF EXISTS `patient_supply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient_supply` (
  `id_patient` int NOT NULL,
  `id_supply` int NOT NULL,
  `needed` int NOT NULL DEFAULT '0',
  `exist` int NOT NULL DEFAULT '0',
  `need_from` datetime DEFAULT NULL,
  PRIMARY KEY (`id_patient`,`id_supply`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_supply`
--

LOCK TABLES `patient_supply` WRITE;
/*!40000 ALTER TABLE `patient_supply` DISABLE KEYS */;
INSERT INTO `patient_supply` VALUES (123,7,2,0,NULL),(200441467,3,3,2,NULL),(200441467,4,3,2,NULL),(308335918,4,0,0,'2020-07-01 00:00:00'),(313108342,3,1,0,'2020-07-01 00:00:00'),(313108342,5,3,2,'2020-06-29 00:00:00'),(313108342,6,2,2,'2020-06-29 00:00:00'),(313108342,8,1,0,NULL),(323501890,7,2,1,'2020-07-01 00:00:00');
/*!40000 ALTER TABLE `patient_supply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `patient_supply_view`
--

DROP TABLE IF EXISTS `patient_supply_view`;
/*!50001 DROP VIEW IF EXISTS `patient_supply_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `patient_supply_view` AS SELECT 
 1 AS `id`,
 1 AS `name`,
 1 AS `device_name`,
 1 AS `location_patient`,
 1 AS `location_supply`,
 1 AS `needed`,
 1 AS `exist`,
 1 AS `s_id`,
 1 AS `s_name`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `sid` int NOT NULL AUTO_INCREMENT,
  `sname` varchar(50) DEFAULT NULL,
  `saddress` varchar(100) DEFAULT NULL,
  `sphoneno` varchar(20) DEFAULT NULL,
  `semailid` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'akshay mahadik','kothrud,pune-411038','9604856881','akshay@gmail.com'),(2,'rupesh kamble','varaje,Pune-411052','9869663450','rpesh@gmail.com'),(3,'Ganesh marne','sadashiv peth,pune-411001','9922205610','ganesh@gmail.com'),(4,'Mayur joshi','sadashiv peth,pune-4110111','98989863620','mayur@gmail.com'),(5,'dinesh divekar','kondhva','89898565601','dinesh@gmail.com'),(6,'nitin pawar','pimpri,pune','0202546468567','nitin@gmail.com'),(7,'baban marne','vadgaon,pune','7788556611','baban@gmail.com'),(9,'arnav kulkarni','aundh,pune','7852146585','arnav@gmail.com'),(10,'ganesh pawale','kothrud','020-25487978','ganesh@gmail.com'),(11,'sagar sirke!','ganesh nagar,pune','8956237412','sagar@gmail.com'),(12,'sandeep shinde1','shashtrinagar,pune','8789789899','sandeep@gmail.com'),(13,'akash rahane','kothrud,pune','7858963258','aksha@gmail.com'),(14,'Ganesh vele','kothrud,pune','7845123288','ganesh1@gmail.com'),(16,'harish rawat','nalstop ,pune','78451203695','harish@gmail.com'),(17,'name','address','123','email@id.com');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supply`
--

DROP TABLE IF EXISTS `supply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supply` (
  `id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `device_name` varchar(45) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `amount` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supply`
--

LOCK TABLES `supply` WRITE;
/*!40000 ALTER TABLE `supply` DISABLE KEYS */;
INSERT INTO `supply` VALUES (1,'Delta Inc.','Surgical masks','Lod, rail station',4998),(2,'Chemical Defence','Respirators','Haifa, Vulkan junction',3001),(3,'Chemical Defence','Gloves','Haifa, Vulkan Junction',8000),(4,'Chemical Defence','Hand sanitizer dispensers','Haifa, Vulkan Junction',2000),(5,'Kogene Biotech','PCR-Kits','North Korea, Seul',4998),(6,'RAUMEDIC','Ventilators','Petach Tiqwa, Orlov 12',9998),(7,'Super Tech','Oxygen therapy machines','California, USA',9999),(8,'Super Tech','Nebulizers','California, USA',1000);
/*!40000 ALTER TABLE `supply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'medical_store'
--

--
-- Dumping routines for database 'medical_store'
--

--
-- Final view structure for view `patient_supply_view`
--

/*!50001 DROP VIEW IF EXISTS `patient_supply_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `patient_supply_view` AS select `p`.`id` AS `id`,`p`.`name` AS `name`,`s`.`device_name` AS `device_name`,`p`.`location` AS `location_patient`,`s`.`location` AS `location_supply`,`ps`.`needed` AS `needed`,`ps`.`exist` AS `exist`,`s`.`id` AS `s_id`,`s`.`name` AS `s_name` from ((`patient` `p` join `patient_supply` `ps` on((`p`.`id` = `ps`.`id_patient`))) join `supply` `s` on((`ps`.`id_supply` = `s`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-01 23:03:33
