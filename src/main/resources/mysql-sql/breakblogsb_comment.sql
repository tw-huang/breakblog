-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: breakblogsb
-- ------------------------------------------------------
-- Server version	5.7.28-log

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
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(20) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `site` varchar(255) DEFAULT NULL,
  `body` text,
  `from_admin` tinyint(1) DEFAULT '0',
  `reviewed` tinyint(1) DEFAULT '0',
  `timestamp` datetime DEFAULT NULL,
  `replied_id` int(11) DEFAULT NULL,
  `post_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `replied_id` (`replied_id`),
  KEY `post_id` (`post_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`replied_id`) REFERENCES `comment` (`id`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'tw.huang','tw.huang@foxmail.com','http://www.breakblog.me/','测试评论系统~',0,1,'2019-11-20 14:20:19',NULL,1),(2,'vv','vv@vv.com','','vv',0,1,'2020-03-05 19:58:00',NULL,15),(4,'test11','tw.huang@foxmail.com','http://www.breakblog.me/','测试评论系统~',0,1,'2020-03-05 19:58:00',NULL,15),(5,'test11','tw.huang@foxmail.com','http://www.breakblog.me/','测试评论系统~',1,1,'2020-03-05 19:58:00',4,15),(6,'test11','tw.huang@foxmail.com','http://www.breakblog.me/','测试评论系统~',0,1,'2020-03-05 19:58:00',4,15),(13,'twhuang','tw.huang@foxmail.com',NULL,'test',1,1,'2020-03-24 22:14:56',NULL,15),(14,'twhuang','tw.huang@foxmail.com',NULL,'test',1,1,'2020-03-24 22:14:58',NULL,15),(15,'twhuang','tw.huang@foxmail.com',NULL,'test',1,1,'2020-03-24 22:15:01',NULL,15),(16,'twhuang','tw.huang@foxmail.com',NULL,'test',1,1,'2020-03-24 22:15:04',NULL,15),(17,'twhuang','tw.huang@foxmail.com',NULL,'test',1,1,'2020-03-24 22:15:08',NULL,15),(18,'twhuang','tw.huang@foxmail.com',NULL,'1',1,1,'2020-03-24 22:15:10',NULL,15),(19,'twhuang','tw.huang@foxmail.com',NULL,'2',1,1,'2020-03-24 22:15:12',NULL,15),(20,'twhuang','tw.huang@foxmail.com',NULL,'3',1,1,'2020-03-24 22:15:14',NULL,15),(21,'twhuang','tw.huang@foxmail.com',NULL,'4',1,1,'2020-03-24 22:15:16',NULL,15),(22,'twhuang','tw.huang@foxmail.com',NULL,'5',1,1,'2020-03-24 22:15:19',NULL,15),(23,'twhuang','tw.huang@foxmail.com',NULL,'6',1,1,'2020-03-24 22:15:22',NULL,15),(24,'twhuang','tw.huang@foxmail.com',NULL,'7',1,1,'2020-03-24 22:15:25',NULL,15),(25,'twhuang','tw.huang@foxmail.com',NULL,'8',1,1,'2020-03-24 22:15:27',NULL,15),(26,'twhuang','tw.huang@foxmail.com',NULL,'9',1,1,'2020-03-24 22:15:30',NULL,15),(27,'twhuang','tw.huang@foxmail.com',NULL,'10',1,1,'2020-03-24 22:15:34',NULL,15),(28,'twhuang','tw.huang@foxmail.com',NULL,'11',1,1,'2020-03-24 22:15:38',NULL,15),(29,'twhuang','tw.huang@foxmail.com',NULL,'12',1,1,'2020-03-24 22:15:40',NULL,15),(30,'twhuang','tw.huang@foxmail.com',NULL,'13',1,1,'2020-03-24 22:15:43',NULL,15),(31,'twhuang','tw.huang@foxmail.com',NULL,'14',1,1,'2020-03-24 22:15:46',NULL,15),(32,'twhuang','tw.huang@foxmail.com',NULL,'15',1,1,'2020-03-24 22:15:49',NULL,15),(33,'黄天文','tw.huang@foxmail.com','http://www.baidu.com','真逗啊。嘻嘻嘻嘻嘻',0,0,'2020-03-26 07:39:35',NULL,15),(34,'黄天文','tw.huang@foxmail.com','http://www.baidu.com','是是是',0,0,'2020-03-26 07:53:14',NULL,12),(35,'twhuang','tw.huang@foxmail.com',NULL,'试试上',1,1,'2020-03-26 07:53:34',NULL,12),(36,'twhuang','tw.huang@foxmail.com',NULL,'反反复复方法',1,1,'2020-03-26 07:53:53',34,12),(37,'twhuang','tw.huang@foxmail.com',NULL,'哈哈哈',1,1,'2020-03-26 07:54:11',35,12);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-27 20:49:38
