-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: tlias
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classes` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(30) NOT NULL COMMENT '班级名称',
  `classes_number` varchar(20) DEFAULT NULL COMMENT '班级教室',
  `start_time` date NOT NULL COMMENT '开课时间',
  `finish_time` date NOT NULL COMMENT '结课时间',
  `emp_id` int unsigned DEFAULT NULL COMMENT '班主任，员工表ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `classes_name_uindex` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='班级表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` VALUES (1,'西安黑马JavaEE就业100期','308','2023-05-01','2023-05-10',1,'2023-05-01 11:44:57','2023-05-01 11:44:59'),(2,'合肥黑马JavaEE37期','508','2023-05-01','2023-05-24',2,'2023-05-01 11:45:38','2023-05-01 11:45:40'),(9,'合肥黑马就业班38期','506','2023-05-01','2023-05-10',1,'2023-07-20 20:50:42','2023-07-20 20:50:42'),(10,'合肥黑马就业班60期','403','2023-06-05','2023-10-24',10,'2023-07-20 21:39:28','2023-07-21 20:02:21');
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dept`
--

DROP TABLE IF EXISTS `dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dept` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(10) NOT NULL COMMENT '部门名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dept`
--

LOCK TABLES `dept` WRITE;
/*!40000 ALTER TABLE `dept` DISABLE KEYS */;
INSERT INTO `dept` VALUES (1,'学工部','2023-07-20 14:43:22','2023-07-20 14:43:22'),(2,'教研部','2023-07-20 14:43:22','2023-07-20 14:43:22'),(3,'咨询部','2023-07-20 14:43:22','2023-07-20 14:43:22'),(4,'就业部','2023-07-20 14:43:22','2023-07-20 14:43:22'),(5,'人事部','2023-07-20 14:43:22','2023-07-20 14:43:22');
/*!40000 ALTER TABLE `dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp`
--

DROP TABLE IF EXISTS `emp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emp` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT '123456' COMMENT '密码',
  `name` varchar(10) NOT NULL COMMENT '姓名',
  `gender` tinyint unsigned NOT NULL COMMENT '性别, 说明: 1 男, 2 女',
  `image` varchar(300) DEFAULT NULL COMMENT '图像',
  `job` tinyint unsigned DEFAULT NULL COMMENT '职位, 说明: 1 班主任,2 讲师, 3 学工主管, 4 教研主管, 5 咨询师',
  `entrydate` date DEFAULT NULL COMMENT '入职时间',
  `dept_id` int unsigned DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='员工表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp`
--

LOCK TABLES `emp` WRITE;
/*!40000 ALTER TABLE `emp` DISABLE KEYS */;
INSERT INTO `emp` VALUES (1,'jinyong','123456','金庸',1,'https://fengyouquan.oss-cn-shanghai.aliyuncs.com/TliasDir/f249a1c6-ebf6-4767-a87f-7705374ab18d.jpg',4,'2000-01-01',2,'2023-07-20 14:43:29','2023-07-20 14:52:16'),(2,'zhangwuji','123456','张无忌',1,'https://fengyouquan.oss-cn-shanghai.aliyuncs.com/TliasDir/460c6783-5a83-4fa2-a9e4-a83427eb0542.jpg',2,'2015-01-01',2,'2023-07-20 14:43:29','2023-07-20 14:53:36'),(3,'yangxiao','123456','杨逍',1,'3.jpg',2,'2008-05-01',2,'2023-07-20 14:43:29','2023-07-20 14:43:29'),(4,'weiyixiao','123456','韦一笑',1,'4.jpg',2,'2007-01-01',2,'2023-07-20 14:43:29','2023-07-20 14:43:29'),(5,'changyuchun','123456','常遇春',1,'5.jpg',2,'2012-12-05',2,'2023-07-20 14:43:29','2023-07-20 14:43:29'),(6,'xiaozhao','123456','小昭',2,'6.jpg',3,'2013-09-05',1,'2023-07-20 14:43:29','2023-07-20 14:43:29'),(7,'jixiaofu','123456','纪晓芙',2,'7.jpg',1,'2005-08-01',1,'2023-07-20 14:43:29','2023-07-20 14:43:29'),(8,'zhouzhiruo','123456','周芷若',2,'8.jpg',1,'2014-11-09',1,'2023-07-20 14:43:29','2023-07-20 14:43:29'),(9,'dingminjun','123456','丁敏君',2,'9.jpg',1,'2011-03-11',1,'2023-07-20 14:43:29','2023-07-20 14:43:29'),(10,'zhaomin','123456','赵敏',2,'10.jpg',1,'2013-09-05',1,'2023-07-20 14:43:29','2023-07-20 14:43:29'),(11,'luzhangke','123456','鹿杖客',1,'11.jpg',5,'2007-02-01',3,'2023-07-20 14:43:29','2023-07-20 14:43:29'),(12,'hebiweng','123456','鹤笔翁',1,'12.jpg',5,'2008-08-18',3,'2023-07-20 14:43:29','2023-07-20 14:43:29'),(13,'fangdongbai','123456','方东白',1,'13.jpg',5,'2012-11-01',3,'2023-07-20 14:43:29','2023-07-20 14:43:29'),(14,'zhangsanfeng','123456','张三丰',1,'14.jpg',2,'2002-08-01',2,'2023-07-20 14:43:29','2023-07-20 14:43:29'),(15,'yulianzhou','123456','俞莲舟',1,'15.jpg',2,'2011-05-01',2,'2023-07-20 14:43:29','2023-07-20 14:43:29'),(16,'songyuanqiao','123456','宋远桥',1,'16.jpg',2,'2007-01-01',2,'2023-07-20 14:43:29','2023-07-20 14:43:29'),(17,'chenyouliang','123456','陈友谅',1,'17.jpg',NULL,'2015-03-21',NULL,'2023-07-20 14:43:29','2023-07-20 14:43:29');
/*!40000 ALTER TABLE `emp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operate_log`
--

DROP TABLE IF EXISTS `operate_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operate_log` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `operate_user` int unsigned DEFAULT NULL COMMENT '操作人',
  `operate_time` datetime DEFAULT NULL COMMENT '操作时间',
  `class_name` varchar(100) DEFAULT NULL COMMENT '操作的类名',
  `method_name` varchar(100) DEFAULT NULL COMMENT '操作的方法名',
  `method_params` varchar(1000) DEFAULT NULL COMMENT '方法参数',
  `return_value` varchar(2000) DEFAULT NULL COMMENT '返回值',
  `cost_time` bigint DEFAULT NULL COMMENT '方法执行耗时, 单位:ms',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operate_log`
--

LOCK TABLES `operate_log` WRITE;
/*!40000 ALTER TABLE `operate_log` DISABLE KEYS */;
INSERT INTO `operate_log` VALUES (1,1,'2023-07-20 14:53:36','com.youquan.controller.EmpController','update','[{\"createTime\":\"2023-07-20 14:43:29\",\"deptId\":2,\"entrydate\":\"2015-01-01\",\"gender\":1,\"id\":2,\"image\":\"https://fengyouquan.oss-cn-shanghai.aliyuncs.com/TliasDir/460c6783-5a83-4fa2-a9e4-a83427eb0542.jpg\",\"job\":2,\"name\":\"张无忌\",\"password\":\"123456\",\"updateTime\":\"2023-07-20 14:43:29\",\"username\":\"zhangwuji\"}]','{\"code\":1,\"msg\":\"success\"}',44),(2,1,'2023-07-21 19:58:36','com.youquan.controller.DeptController','save','[{\"name\":\"哈哈哈哈哈哈\"}]','{\"code\":1,\"msg\":\"success\"}',151),(3,1,'2023-07-21 19:58:53','com.youquan.controller.DeptController','removeById','[6]','{\"code\":1,\"msg\":\"success\"}',155),(4,1,'2023-07-21 20:02:21','com.youquan.service.impl.ClassServiceImpl','update','[{\"classesNumber\":\"403\",\"empId\":10,\"finishTime\":\"2023-10-24\",\"id\":10,\"name\":\"合肥黑马就业班60期\",\"startTime\":\"2023-06-05\",\"updateTime\":\"2023-07-21 19:57:09\"}]','null',112),(5,1,'2023-07-21 20:09:43','com.youquan.service.impl.ClassServiceImpl','save','[{\"classesNumber\":\"随风倒士大夫\",\"empId\":9,\"finishTime\":\"2023-07-27\",\"name\":\"而通过是\",\"startTime\":\"2023-07-12\"}]','null',143),(6,1,'2023-07-21 20:10:25','com.youquan.service.impl.ClassServiceImpl','remove','[11]','null',105),(7,1,'2023-07-21 23:50:35','com.youquan.service.impl.StudentServiceImpl','save','[{\"classesId\":10,\"gender\":1,\"highestDegree\":4,\"name\":\"冯友泉\",\"phone\":\"15614318695\",\"studentNumber\":\"A230401360\"}]','null',144147),(8,1,'2023-07-22 10:49:37','com.youquan.service.impl.StudentServiceImpl','save','[{\"classesId\":10,\"gender\":2,\"highestDegree\":5,\"name\":\"哈哈\",\"phone\":\"19883239366\",\"studentNumber\":\"A234234234\"}]','null',142),(9,1,'2023-07-22 11:11:54','com.youquan.service.impl.StudentServiceImpl','update','[{\"classesId\":10,\"gender\":2,\"highestDegree\":5,\"id\":4,\"name\":\"哈哈哈哈\",\"phone\":\"19883239366\",\"studentNumber\":\"A234234234\",\"updateTime\":\"2023-07-22 10:49:37\"}]','null',326),(10,1,'2023-07-22 11:12:10','com.youquan.service.impl.StudentServiceImpl','update','[{\"classesId\":10,\"gender\":2,\"highestDegree\":5,\"id\":4,\"name\":\"哈哈哈哈哈哈\",\"phone\":\"19883239366\",\"studentNumber\":\"A234234236\",\"updateTime\":\"2023-07-22 11:11:54\"}]','null',146),(11,1,'2023-07-22 11:12:20','com.youquan.service.impl.StudentServiceImpl','update','[{\"classesId\":10,\"gender\":2,\"highestDegree\":6,\"id\":4,\"name\":\"哈哈哈哈哈哈\",\"phone\":\"19883239366\",\"studentNumber\":\"A234234236\",\"updateTime\":\"2023-07-22 11:12:10\"}]','null',41),(12,1,'2023-07-22 11:12:28','com.youquan.service.impl.StudentServiceImpl','update','[{\"classesId\":9,\"gender\":2,\"highestDegree\":6,\"id\":4,\"name\":\"哈哈哈哈哈哈\",\"phone\":\"19883239366\",\"studentNumber\":\"A234234236\",\"updateTime\":\"2023-07-22 11:12:20\"}]','null',66),(13,1,'2023-07-22 11:12:34','com.youquan.service.impl.StudentServiceImpl','update','[{\"classesId\":10,\"gender\":2,\"highestDegree\":6,\"id\":4,\"name\":\"哈哈哈哈哈哈\",\"phone\":\"19883239366\",\"studentNumber\":\"A234234236\",\"updateTime\":\"2023-07-22 11:12:28\"}]','null',75),(14,1,'2023-07-22 11:12:57','com.youquan.service.impl.StudentServiceImpl','update','[{\"classesId\":9,\"gender\":1,\"highestDegree\":5,\"id\":4,\"name\":\"哈哈哈哈\",\"phone\":\"19883239388\",\"studentNumber\":\"A234234234\",\"updateTime\":\"2023-07-22 11:12:34\"}]','null',63),(15,1,'2023-07-22 11:13:10','com.youquan.service.impl.StudentServiceImpl','remove','[4]','null',73),(16,1,'2023-07-22 14:20:53','com.youquan.service.impl.StudentServiceImpl','updateDeductionPoint','[1,1]','null',36),(17,1,'2023-07-22 14:20:59','com.youquan.service.impl.StudentServiceImpl','updateDeductionPoint','[1,2]','null',30),(18,2,'2023-07-22 22:21:42','com.youquan.service.impl.StudentServiceImpl','save','[{\"classesId\":10,\"gender\":1,\"highestDegree\":5,\"name\":\"王二\",\"phone\":\"19883239366\",\"studentNumber\":\"A230401222\"}]','null',1476);
/*!40000 ALTER TABLE `operate_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(10) NOT NULL COMMENT '学员姓名',
  `student_number` char(10) NOT NULL COMMENT '学号',
  `gender` tinyint unsigned NOT NULL COMMENT '性别',
  `phone` char(11) NOT NULL COMMENT '手机号',
  `highest_degree` tinyint unsigned DEFAULT NULL COMMENT '最高学历：1.初中 2.高中 3.大专 4.本科 5.硕士 6.博士',
  `classes_id` int unsigned NOT NULL COMMENT '所属班级，班级ID',
  `discipline_times` int unsigned DEFAULT '0' COMMENT '违纪次数',
  `discipline_score` int unsigned DEFAULT '0' COMMENT '违纪累计分数',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `student_phone_uindex` (`phone`) USING BTREE,
  UNIQUE KEY `student_student_number_uindex` (`student_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'张三','A220505001',1,'18888888888',1,1,5,6,'2023-05-01 12:17:45','2023-05-01 13:01:12'),(2,'李四','A220505002',2,'19999999999',2,1,3,3,'2023-05-01 12:18:21','2023-05-01 12:18:23'),(3,'冯友泉','A230401360',1,'15614318695',4,10,3,3,'2023-07-21 23:52:23','2023-07-21 23:52:25'),(5,'王二','A230401222',1,'19883239366',5,10,0,0,'2023-07-22 22:21:43','2023-07-22 22:21:43');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-22 23:53:03
