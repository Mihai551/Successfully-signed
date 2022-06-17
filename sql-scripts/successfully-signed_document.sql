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
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `document` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `folder_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `doc_folder_idx` (`folder_id`),
  CONSTRAINT `doc_folder` FOREIGN KEY (`folder_id`) REFERENCES `folder` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document`
--

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
INSERT INTO `document` VALUES (3,'doc1',5),(4,'doc1',6),(5,'document2',6),(6,'document1',10),(7,'document2',10),(8,'doc1',11),(9,'doc2',11),(10,'doc3',11),(11,'aviz',11),(12,'doc1',12),(13,'doc1',13),(14,'doc1',26),(15,'doc1',20),(16,'doc1',27),(17,'doc1',28),(18,'doc1',40),(19,'fdgfdhfd',40),(20,'ceva',40),(21,'doc2',40),(22,'x',40),(23,'doc3',40),(24,'doc1',43),(25,'nume',43),(26,'doc1',24),(27,'doc1',21),(28,'doc1',14),(29,'doc1',15),(30,'',27),(31,'jhgj',40),(32,'zd',41),(33,'doc1',50),(34,'doc1',51),(35,'doc1',52),(36,'doc1',53),(37,'doc1',54),(38,'doc1',55),(39,'doc1',56),(40,'doc1',57),(41,'doc1',58),(42,'doc1',59),(43,'doc2',59),(44,'doc1',60),(45,'',50),(46,'docTestmail',50),(47,'docTestmail1',50),(48,'',64),(49,'docX1',64),(50,'docX2',64),(51,'doc1',66),(52,'docX2',66),(53,'doc2',66),(54,'doc1',72),(55,'buletin',73),(56,'document',73),(57,'doc1',74),(58,'doc1',75),(59,'doc2',74);
/*!40000 ALTER TABLE `document` ENABLE KEYS */;
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
