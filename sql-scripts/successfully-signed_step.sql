-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: successfully-signed
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `step`
--

DROP TABLE IF EXISTS `step`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `step` (
  `id` int NOT NULL AUTO_INCREMENT,
  `service_id` int DEFAULT NULL,
  `action` varchar(45) DEFAULT NULL,
  `document_name` varchar(45) DEFAULT NULL,
  `no` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `services_steps_idx` (`service_id`),
  CONSTRAINT `step_service` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `step`
--

LOCK TABLES `step` WRITE;
/*!40000 ALTER TABLE `step` DISABLE KEYS */;
INSERT INTO `step` VALUES (36,46,'upload','doc1',1),(37,46,'upload','doc2',2),(38,46,'sign','doc1',3),(39,47,'upload','document1',1),(40,47,'upload','document2',2),(41,47,'sign','socument1',3),(42,48,'upload','doc1',1),(43,48,'sign','doc1',2),(44,49,'upload','doc1',1),(45,49,'sign','doc2',2),(46,50,'upload','doc1',1),(47,50,'upload','doc2',2),(48,50,'sign','doc1',3),(49,51,'upload','document1',1),(50,51,'sign','document2',2),(51,52,'upload','doc1',1),(52,52,'upload','doc2',2),(53,52,'upload','doc3',3),(54,53,'upload','doc1',1),(55,54,'upload','doc1',1),(56,54,'upload','doc2',2),(57,54,'upload','doc3',3),(58,55,'upload','doc1',1),(59,55,'upload','doc2',2),(60,56,'upload','doc1',1),(61,56,'sign','doc1',2),(62,58,'upload','doc1',1),(63,58,'sign','doc2',2),(64,58,'upload','',3),(65,58,'upload','docTestBlank',4),(66,58,'sign','docTestBlank',5),(67,59,'upload','buletin',1),(68,59,'sign','document',2),(69,60,'upload','doc1',1),(70,60,'sign','doc1',2),(71,61,'sign','doc1',1),(72,61,'upload','doc2',2);
/*!40000 ALTER TABLE `step` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-17 21:31:04
