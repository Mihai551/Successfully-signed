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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `company` varchar(80) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (20,'user1@email.com','$2a$10$SnR2l26uKnSfti65ohYc2ulMi9RLWVME675LVS6qOqnYEujC82E5C','user1_fn','user1_ln','company1','user1'),(21,'user2@email.com','$2a$10$uMghKrrB6nrAIqzbTuipPe7KJOh7cOMMI6ldxl2Cwx0bGqkyY/0x6','user2_fn','user2_ln',NULL,'user2'),(22,'user3@email.com','$2a$10$hlIowfYPVGv9D.wAT198DenCaFFZJCWhtGgoeW8jKj40GRfhSqqv2','user3','user3','company2','user3'),(23,'user4@email.com','$2a$10$tz0Dww9MbP5ICbz8FIuVP.minRAjYZFHh7OyQBO/UtviWd.FKruga','user4','user4','company1','user4'),(24,'cristi@CRISTI.br','$2a$10$8KH0DP1BpR8oFFs1Tbzes.dglZaCDXcFveixk5zbd7b6VV0IUfiWO','cristi','cristi','upb','cristi'),(25,'mihairadu999@gmail.com','$2a$10$I.gR8XdO8AuglJ2XkNfboOKAEfkHK3GMDDjnAmo563f2xCOb9FK5O','mihai','radu','ETTI','mihai'),(26,'mihai.radu.the.god@gmail.com','$2a$10$XRDzrZF2JLix00KZSOpVluzwGYx4QbzE3UYVoSKmtBfjMKIjv9lZe','mihai','radu',NULL,'mihai_persoanaFizica'),(27,'X@email.com','$2a$10$uSB7aDpysBFdZT/hnTUrB./PxepprarFVbK5q5MDQ.0wxwNvrBB3W','first name','last name',NULL,'userX'),(29,'userX2@email.com','$2a$10$t5CneYODLP8aI9Z7UHSFOOJuW3nQ3mHePYoGut3/9GRLtn0CHrYZO','userx2 first name','userX2 last name','companyX2','userX2'),(30,'userDb@test.com','$2a$10$JaH6UJrtdj7xB/KNl4yY.eeHuE2aeG46B8nrzeD9rtUfBcZflQvYe','first name','last name','companyDB','userDb'),(31,'userDb2@email.com','$2a$10$8ynypkjrabBV4AKlIOfQBewwybioB3tAee2HDF2TNIFlIImIBVND2','first name','last name',NULL,'userDb2');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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
