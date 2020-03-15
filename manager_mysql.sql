/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.40 : Database - manager
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`manager` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `manager`;

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `phoneNum` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `member` */

/*Table structure for table `order_traveller` */

DROP TABLE IF EXISTS `order_traveller`;

CREATE TABLE `order_traveller` (
  `orderId` int(11) NOT NULL DEFAULT '0',
  `travellerId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`orderId`,`travellerId`),
  KEY `travellerId` (`travellerId`),
  CONSTRAINT `order_traveller_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `orders` (`id`),
  CONSTRAINT `order_traveller_ibfk_2` FOREIGN KEY (`travellerId`) REFERENCES `traveller` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `order_traveller` */

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderNum` int(11) NOT NULL,
  `orderTime` date DEFAULT NULL,
  `peopleCount` int(11) DEFAULT NULL,
  `orderDesc` varchar(500) DEFAULT NULL,
  `payType` int(11) DEFAULT NULL,
  `orderStatus` int(11) DEFAULT NULL,
  `productId` int(11) DEFAULT NULL,
  `memberId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `orderNum` (`orderNum`),
  KEY `productId` (`productId`),
  KEY `memberId` (`memberId`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `product` (`id`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`memberId`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permissionName` varchar(50) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `permission` */

insert  into `permission`(`id`,`permissionName`,`url`) values (1,'user findAll','/user/findAll.do'),(2,'user findById','/user/findById.do');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productNum` int(11) NOT NULL,
  `productName` varchar(50) DEFAULT NULL,
  `cityName` varchar(50) DEFAULT NULL,
  `DepartureTime` date DEFAULT NULL,
  `productPrice` double DEFAULT NULL,
  `productDesc` varchar(500) DEFAULT NULL,
  `productStatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `product` (`id`,`productNum`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`id`,`productNum`,`productName`,`cityName`,`DepartureTime`,`productPrice`,`productDesc`,`productStatus`) values (1,1,'广州一日游','岳阳','2019-12-01',1999,'fda 他说的',1),(2,1,'广州一日游','长沙','2019-11-26',2000,'',1),(3,1,'广州一日游','岳阳','2019-12-02',2000,'',1);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(50) DEFAULT NULL,
  `roleDesc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`roleName`,`roleDesc`) values (1,'admin','vip'),(2,'user','visitor');

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `permissionId` int(11) NOT NULL DEFAULT '0',
  `roleId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`permissionId`,`roleId`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`permissionId`) REFERENCES `permission` (`id`),
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_permission` */

insert  into `role_permission`(`permissionId`,`roleId`) values (1,1),(1,2),(2,2);

/*Table structure for table `syslog` */

DROP TABLE IF EXISTS `syslog`;

CREATE TABLE `syslog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `visitTime` datetime DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `ip` varchar(30) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `executionTime` int(11) DEFAULT NULL,
  `method` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

/*Data for the table `syslog` */

insert  into `syslog`(`id`,`visitTime`,`username`,`ip`,`url`,`executionTime`,`method`) values (1,'2020-01-06 00:00:00','tom','0:0:0:0:0:0:0:1','/user/findAll.do',22,'[类名 ]com.itheima.ssm.controller.UserController[方法名 ]findAll'),(2,'2020-01-06 00:00:00','tom','0:0:0:0:0:0:0:1','/role/findAll.do',12,'[类名 ]com.itheima.ssm.controller.RoleController[方法名 ]findAll'),(3,'2020-01-06 00:00:00','tom','0:0:0:0:0:0:0:1','/permission/findAll.do',10,'[类名 ]com.itheima.ssm.controller.PermissionController[方法名 ]findAll'),(4,'2020-01-06 00:00:00','tom','0:0:0:0:0:0:0:1','/sysLog/findAll.do',41,'[类名 ]com.itheima.ssm.controller.SysLogController[方法名 ]findAll'),(5,'2020-01-06 00:00:00','tom','0:0:0:0:0:0:0:1','/product/findAll.do',9,'[类名 ]com.itheima.ssm.controller.ProductController[方法名 ]findAll'),(6,'2020-01-06 00:00:00','tom','0:0:0:0:0:0:0:1','/orders/findAll.do',10,'[类名 ]com.itheima.ssm.controller.OrdersController[方法名 ]findAll'),(7,'2020-01-06 00:00:00','tom','0:0:0:0:0:0:0:1','/product/findAll.do',5,'[类名 ]com.itheima.ssm.controller.ProductController[方法名 ]findAll'),(8,'2020-01-06 00:00:00','tom','0:0:0:0:0:0:0:1','/product/findAll.do',10,'[类名 ]com.itheima.ssm.controller.ProductController[方法名 ]findAll'),(9,'2020-01-06 00:00:00','tom','0:0:0:0:0:0:0:1','/product/findAll.do',5,'[类名 ]com.itheima.ssm.controller.ProductController[方法名 ]findAll'),(10,'2020-01-06 00:00:00','tom','0:0:0:0:0:0:0:1','/product/findAll.do',4,'[类名 ]com.itheima.ssm.controller.ProductController[方法名 ]findAll'),(11,'2020-02-06 00:00:00','tom','0:0:0:0:0:0:0:1','/user/findAll.do',30,'[类名 ]com.itheima.ssm.controller.UserController[方法名 ]findAll'),(12,'2020-02-06 00:00:00','tom','0:0:0:0:0:0:0:1','/user/findAll.do',27,'[类名 ]com.itheima.ssm.controller.UserController[方法名 ]findAll'),(13,'2020-02-06 00:00:00','tom','0:0:0:0:0:0:0:1','/role/findAll.do',57,'[类名 ]com.itheima.ssm.controller.RoleController[方法名 ]findAll'),(14,'2020-02-06 19:21:17','tom','0:0:0:0:0:0:0:1','/permission/findAll.do',77,'[类名 ]com.itheima.ssm.controller.PermissionController[方法名 ]findAll'),(15,'2020-02-06 19:21:21','tom','0:0:0:0:0:0:0:1','/sysLog/findAll.do',138,'[类名 ]com.itheima.ssm.controller.SysLogController[方法名 ]findAll'),(16,'2020-02-06 19:21:26','tom','0:0:0:0:0:0:0:1','/sysLog/findAll.do',18,'[类名 ]com.itheima.ssm.controller.SysLogController[方法名 ]findAll'),(17,'2020-03-09 17:27:38','tom','0:0:0:0:0:0:0:1','/user/findAll.do',19,'[类名 ]com.itheima.ssm.controller.UserController[方法名 ]findAll'),(18,'2020-03-09 17:27:42','tom','0:0:0:0:0:0:0:1','/orders/findAll.do',62,'[类名 ]com.itheima.ssm.controller.OrdersController[方法名 ]findAll'),(19,'2020-03-09 17:27:45','tom','0:0:0:0:0:0:0:1','/product/findAll.do',12,'[类名 ]com.itheima.ssm.controller.ProductController[方法名 ]findAll'),(20,'2020-03-09 17:27:50','tom','0:0:0:0:0:0:0:1','/sysLog/findAll.do',35,'[类名 ]com.itheima.ssm.controller.SysLogController[方法名 ]findAll'),(21,'2020-03-09 17:27:58','tom','0:0:0:0:0:0:0:1','/role/findAll.do',19,'[类名 ]com.itheima.ssm.controller.RoleController[方法名 ]findAll'),(22,'2020-03-09 17:28:02','tom','0:0:0:0:0:0:0:1','/permission/findAll.do',25,'[类名 ]com.itheima.ssm.controller.PermissionController[方法名 ]findAll'),(23,'2020-03-09 17:28:07','tom','0:0:0:0:0:0:0:1','/user/findAll.do',16,'[类名 ]com.itheima.ssm.controller.UserController[方法名 ]findAll'),(24,'2020-03-09 17:28:29','tom','0:0:0:0:0:0:0:1','/product/findAll.do',4,'[类名 ]com.itheima.ssm.controller.ProductController[方法名 ]findAll'),(25,'2020-03-09 17:29:17','tom','0:0:0:0:0:0:0:1','/product/findAll.do',6,'[类名 ]com.itheima.ssm.controller.ProductController[方法名 ]findAll'),(26,'2020-03-09 17:29:17','tom','0:0:0:0:0:0:0:1','/product/findAll.do',3,'[类名 ]com.itheima.ssm.controller.ProductController[方法名 ]findAll'),(27,'2020-03-15 08:48:35','tom','0:0:0:0:0:0:0:1','/user/findAll.do',43,'[类名 ]com.itheima.ssm.controller.UserController[方法名 ]findAll'),(28,'2020-03-15 08:48:40','tom','0:0:0:0:0:0:0:1','/role/findAll.do',43,'[类名 ]com.itheima.ssm.controller.RoleController[方法名 ]findAll'),(29,'2020-03-15 08:48:44','tom','0:0:0:0:0:0:0:1','/permission/findAll.do',45,'[类名 ]com.itheima.ssm.controller.PermissionController[方法名 ]findAll'),(30,'2020-03-15 08:48:47','tom','0:0:0:0:0:0:0:1','/sysLog/findAll.do',156,'[类名 ]com.itheima.ssm.controller.SysLogController[方法名 ]findAll'),(31,'2020-03-15 08:48:55','tom','0:0:0:0:0:0:0:1','/product/findAll.do',43,'[类名 ]com.itheima.ssm.controller.ProductController[方法名 ]findAll'),(32,'2020-03-15 08:48:59','tom','0:0:0:0:0:0:0:1','/orders/findAll.do',28,'[类名 ]com.itheima.ssm.controller.OrdersController[方法名 ]findAll'),(33,'2020-03-15 08:49:22','tom','0:0:0:0:0:0:0:1','/product/findAll.do',24,'[类名 ]com.itheima.ssm.controller.ProductController[方法名 ]findAll'),(34,'2020-03-15 08:49:22','tom','0:0:0:0:0:0:0:1','/product/findAll.do',9,'[类名 ]com.itheima.ssm.controller.ProductController[方法名 ]findAll'),(35,'2020-03-15 08:49:27','tom','0:0:0:0:0:0:0:1','/orders/findAll.do',9,'[类名 ]com.itheima.ssm.controller.OrdersController[方法名 ]findAll');

/*Table structure for table `traveller` */

DROP TABLE IF EXISTS `traveller`;

CREATE TABLE `traveller` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `phoneNum` varchar(20) DEFAULT NULL,
  `credentialsType` int(11) DEFAULT NULL,
  `credentialsNum` varchar(50) DEFAULT NULL,
  `travellerType` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `traveller` */

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `PASSWORD` varchar(100) DEFAULT NULL,
  `phoneNum` varchar(20) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`id`,`email`,`username`,`PASSWORD`,`phoneNum`,`STATUS`) values (1,'111@163.com','tom','$2a$10$BxR.CrVabf.wdwrM7UDwN.LRwiiu21hErc91VLEhXbGFejSHUJecy','19999999999',1),(2,'222@163.com','kiss','$2a$10$wWLNc2ZagqEJP9w5F5Wwpeeur2gO8Un37PgXcJFOfLqI/7Zz45NbC','18888888888',1);

/*Table structure for table `users_role` */

DROP TABLE IF EXISTS `users_role`;

CREATE TABLE `users_role` (
  `userId` int(11) NOT NULL DEFAULT '0',
  `roleId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userId`,`roleId`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `users_role_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`id`),
  CONSTRAINT `users_role_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `users_role` */

insert  into `users_role`(`userId`,`roleId`) values (1,1),(2,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
