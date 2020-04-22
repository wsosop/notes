# myemployees数据库

```mysql
/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50714
Source Host           : 127.0.0.1:3306
Source Database       : myemployees

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2020-04-05 17:39:04
*/

CREATE DATABASE /*!32312 IF NOT EXISTS*/`myemployees` /*!40100 DEFAULT CHARACTER SET gb2312 */;

USE `myemployees`;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for departments
-- ----------------------------
DROP TABLE IF EXISTS `departments`;
CREATE TABLE `departments` (
  `department_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `department_name` varchar(3) CHARACTER SET gb2312 DEFAULT NULL COMMENT '部门名称',
  `manager_id` int(6) DEFAULT NULL COMMENT '部门领导的员工编号',
  `location_id` int(4) DEFAULT NULL COMMENT '位置编号',
  PRIMARY KEY (`department_id`),
  KEY `loc_id_fk` (`location_id`),
  CONSTRAINT `loc_id_fk` FOREIGN KEY (`location_id`) REFERENCES `locations` (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=271 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of departments
-- ----------------------------
INSERT INTO `departments` VALUES ('10', 'Adm', '200', '1700');
INSERT INTO `departments` VALUES ('20', 'Mar', '201', '1800');
INSERT INTO `departments` VALUES ('30', 'Pur', '114', '1700');
INSERT INTO `departments` VALUES ('40', 'Hum', '203', '2400');
INSERT INTO `departments` VALUES ('50', 'Shi', '121', '1500');
INSERT INTO `departments` VALUES ('60', 'IT', '103', '1400');
INSERT INTO `departments` VALUES ('70', 'Pub', '204', '2700');
INSERT INTO `departments` VALUES ('80', 'Sal', '145', '2500');
INSERT INTO `departments` VALUES ('90', 'Exe', '100', '1700');
INSERT INTO `departments` VALUES ('100', 'Fin', '108', '1700');
INSERT INTO `departments` VALUES ('110', 'Acc', '205', '1700');
INSERT INTO `departments` VALUES ('120', 'Tre', null, '1700');
INSERT INTO `departments` VALUES ('130', 'Cor', null, '1700');
INSERT INTO `departments` VALUES ('140', 'Con', null, '1700');
INSERT INTO `departments` VALUES ('150', 'Sha', null, '1700');
INSERT INTO `departments` VALUES ('160', 'Ben', null, '1700');
INSERT INTO `departments` VALUES ('170', 'Man', null, '1700');
INSERT INTO `departments` VALUES ('180', 'Con', null, '1700');
INSERT INTO `departments` VALUES ('190', 'Con', null, '1700');
INSERT INTO `departments` VALUES ('200', 'Ope', null, '1700');
INSERT INTO `departments` VALUES ('210', 'IT ', null, '1700');
INSERT INTO `departments` VALUES ('220', 'NOC', null, '1700');
INSERT INTO `departments` VALUES ('230', 'IT ', null, '1700');
INSERT INTO `departments` VALUES ('240', 'Gov', null, '1700');
INSERT INTO `departments` VALUES ('250', 'Ret', null, '1700');
INSERT INTO `departments` VALUES ('260', 'Rec', null, '1700');
INSERT INTO `departments` VALUES ('270', 'Pay', null, '1700');

-- ----------------------------
-- Table structure for employees
-- ----------------------------
DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees` (
  `employee_id` int(6) NOT NULL AUTO_INCREMENT COMMENT '员工编号',
  `first_name` varchar(20) CHARACTER SET gb2312 DEFAULT NULL COMMENT '名',
  `last_name` varchar(25) CHARACTER SET gb2312 DEFAULT NULL COMMENT '姓',
  `email` varchar(25) CHARACTER SET gb2312 DEFAULT NULL COMMENT '邮箱',
  `phone_number` varchar(20) CHARACTER SET gb2312 DEFAULT NULL COMMENT '电话号码',
  `job_id` varchar(10) CHARACTER SET gb2312 DEFAULT NULL COMMENT '工种编号',
  `salary` double(10,2) DEFAULT NULL COMMENT '月薪',
  `commission_pct` double(4,2) DEFAULT NULL COMMENT '奖金率',
  `manager_id` int(6) DEFAULT NULL COMMENT '上级领导的员工编号',
  `department_id` int(4) DEFAULT NULL COMMENT '部门id',
  `hiredate` datetime DEFAULT NULL COMMENT '入职日期',
  PRIMARY KEY (`employee_id`),
  KEY `dept_id_fk` (`department_id`),
  KEY `job_id_fk` (`job_id`),
  CONSTRAINT `dept_id_fk` FOREIGN KEY (`department_id`) REFERENCES `departments` (`department_id`),
  CONSTRAINT `job_id_fk` FOREIGN KEY (`job_id`) REFERENCES `jobs` (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=207 DEFAULT CHARSET=utf8 COMMENT='员工表';

-- ----------------------------
-- Records of employees
-- ----------------------------
INSERT INTO `employees` VALUES ('100', 'Steven', 'K_ing', 'SKING', '515.123.4567', 'AD_PRES', '24000.00', null, null, '90', '1992-04-03 00:00:00');
INSERT INTO `employees` VALUES ('101', 'Neena', 'Kochhar', 'NKOCHHAR', '515.123.4568', 'AD_VP', '17000.00', null, '100', '90', '1992-04-03 00:00:00');
INSERT INTO `employees` VALUES ('102', 'Lex', 'De Haan', 'LDEHAAN', '515.123.4569', 'AD_VP', '17000.00', null, '100', '90', '1992-04-03 00:00:00');
INSERT INTO `employees` VALUES ('103', 'Alexander', 'Hunold', 'AHUNOLD', '590.423.4567', 'IT_PROG', '9000.00', null, '102', '60', '1992-04-03 00:00:00');
INSERT INTO `employees` VALUES ('104', 'Bruce', 'Ernst', 'BERNST', '590.423.4568', 'IT_PROG', '6000.00', null, '103', '60', '1992-04-03 00:00:00');
INSERT INTO `employees` VALUES ('105', 'David', 'Austin', 'DAUSTIN', '590.423.4569', 'IT_PROG', '4800.00', null, '103', '60', '1998-03-03 00:00:00');
INSERT INTO `employees` VALUES ('106', 'Valli', 'Pataballa', 'VPATABAL', '590.423.4560', 'IT_PROG', '4800.00', null, '103', '60', '1998-03-03 00:00:00');
INSERT INTO `employees` VALUES ('107', 'Diana', 'Lorentz', 'DLORENTZ', '590.423.5567', 'IT_PROG', '4200.00', null, '103', '60', '1998-03-03 00:00:00');
INSERT INTO `employees` VALUES ('108', 'Nancy', 'Greenberg', 'NGREENBE', '515.124.4569', 'FI_MGR', '12000.00', null, '101', '100', '1998-03-03 00:00:00');
INSERT INTO `employees` VALUES ('109', 'Daniel', 'Faviet', 'DFAVIET', '515.124.4169', 'FI_ACCOUNT', '9000.00', null, '108', '100', '1998-03-03 00:00:00');
INSERT INTO `employees` VALUES ('110', 'John', 'Chen', 'JCHEN', '515.124.4269', 'FI_ACCOUNT', '8200.00', null, '108', '100', '2000-09-09 00:00:00');
INSERT INTO `employees` VALUES ('111', 'Ismael', 'Sciarra', 'ISCIARRA', '515.124.4369', 'FI_ACCOUNT', '7700.00', null, '108', '100', '2000-09-09 00:00:00');
INSERT INTO `employees` VALUES ('112', 'Jose Manuel', 'Urman', 'JMURMAN', '515.124.4469', 'FI_ACCOUNT', '7800.00', null, '108', '100', '2000-09-09 00:00:00');
INSERT INTO `employees` VALUES ('113', 'Luis', 'Popp', 'LPOPP', '515.124.4567', 'FI_ACCOUNT', '6900.00', null, '108', '100', '2000-09-09 00:00:00');
INSERT INTO `employees` VALUES ('114', 'Den', 'Raphaely', 'DRAPHEAL', '515.127.4561', 'PU_MAN', '11000.00', null, '100', '30', '2000-09-09 00:00:00');
INSERT INTO `employees` VALUES ('115', 'Alexander', 'Khoo', 'AKHOO', '515.127.4562', 'PU_CLERK', '3100.00', null, '114', '30', '2000-09-09 00:00:00');
INSERT INTO `employees` VALUES ('116', 'Shelli', 'Baida', 'SBAIDA', '515.127.4563', 'PU_CLERK', '2900.00', null, '114', '30', '2000-09-09 00:00:00');
INSERT INTO `employees` VALUES ('117', 'Sigal', 'Tobias', 'STOBIAS', '515.127.4564', 'PU_CLERK', '2800.00', null, '114', '30', '2000-09-09 00:00:00');
INSERT INTO `employees` VALUES ('118', 'Guy', 'Himuro', 'GHIMURO', '515.127.4565', 'PU_CLERK', '2600.00', null, '114', '30', '2000-09-09 00:00:00');
INSERT INTO `employees` VALUES ('119', 'Karen', 'Colmenares', 'KCOLMENA', '515.127.4566', 'PU_CLERK', '2500.00', null, '114', '30', '2000-09-09 00:00:00');
INSERT INTO `employees` VALUES ('120', 'Matthew', 'Weiss', 'MWEISS', '650.123.1234', 'ST_MAN', '8000.00', null, '100', '50', '2004-02-06 00:00:00');
INSERT INTO `employees` VALUES ('121', 'Adam', 'Fripp', 'AFRIPP', '650.123.2234', 'ST_MAN', '8200.00', null, '100', '50', '2004-02-06 00:00:00');
INSERT INTO `employees` VALUES ('122', 'Payam', 'Kaufling', 'PKAUFLIN', '650.123.3234', 'ST_MAN', '7900.00', null, '100', '50', '2004-02-06 00:00:00');
INSERT INTO `employees` VALUES ('123', 'Shanta', 'Vollman', 'SVOLLMAN', '650.123.4234', 'ST_MAN', '6500.00', null, '100', '50', '2004-02-06 00:00:00');
INSERT INTO `employees` VALUES ('124', 'Kevin', 'Mourgos', 'KMOURGOS', '650.123.5234', 'ST_MAN', '5800.00', null, '100', '50', '2004-02-06 00:00:00');
INSERT INTO `employees` VALUES ('125', 'Julia', 'Nayer', 'JNAYER', '650.124.1214', 'ST_CLERK', '3200.00', null, '120', '50', '2004-02-06 00:00:00');
INSERT INTO `employees` VALUES ('126', 'Irene', 'Mikkilineni', 'IMIKKILI', '650.124.1224', 'ST_CLERK', '2700.00', null, '120', '50', '2004-02-06 00:00:00');
INSERT INTO `employees` VALUES ('127', 'James', 'Landry', 'JLANDRY', '650.124.1334', 'ST_CLERK', '2400.00', null, '120', '50', '2004-02-06 00:00:00');
INSERT INTO `employees` VALUES ('128', 'Steven', 'Markle', 'SMARKLE', '650.124.1434', 'ST_CLERK', '2200.00', null, '120', '50', '2004-02-06 00:00:00');
INSERT INTO `employees` VALUES ('129', 'Laura', 'Bissot', 'LBISSOT', '650.124.5234', 'ST_CLERK', '3300.00', null, '121', '50', '2004-02-06 00:00:00');
INSERT INTO `employees` VALUES ('130', 'Mozhe', 'Atkinson', 'MATKINSO', '650.124.6234', 'ST_CLERK', '2800.00', null, '121', '50', '2004-02-06 00:00:00');
INSERT INTO `employees` VALUES ('131', 'James', 'Marlow', 'JAMRLOW', '650.124.7234', 'ST_CLERK', '2500.00', null, '121', '50', '2004-02-06 00:00:00');
INSERT INTO `employees` VALUES ('132', 'TJ', 'Olson', 'TJOLSON', '650.124.8234', 'ST_CLERK', '2100.00', null, '121', '50', '2004-02-06 00:00:00');
INSERT INTO `employees` VALUES ('133', 'Jason', 'Mallin', 'JMALLIN', '650.127.1934', 'ST_CLERK', '3300.00', null, '122', '50', '2004-02-06 00:00:00');
INSERT INTO `employees` VALUES ('134', 'Michael', 'Rogers', 'MROGERS', '650.127.1834', 'ST_CLERK', '2900.00', null, '122', '50', '2002-12-23 00:00:00');
INSERT INTO `employees` VALUES ('135', 'Ki', 'Gee', 'KGEE', '650.127.1734', 'ST_CLERK', '2400.00', null, '122', '50', '2002-12-23 00:00:00');
INSERT INTO `employees` VALUES ('136', 'Hazel', 'Philtanker', 'HPHILTAN', '650.127.1634', 'ST_CLERK', '2200.00', null, '122', '50', '2002-12-23 00:00:00');
INSERT INTO `employees` VALUES ('137', 'Renske', 'Ladwig', 'RLADWIG', '650.121.1234', 'ST_CLERK', '3600.00', null, '123', '50', '2002-12-23 00:00:00');
INSERT INTO `employees` VALUES ('138', 'Stephen', 'Stiles', 'SSTILES', '650.121.2034', 'ST_CLERK', '3200.00', null, '123', '50', '2002-12-23 00:00:00');
INSERT INTO `employees` VALUES ('139', 'John', 'Seo', 'JSEO', '650.121.2019', 'ST_CLERK', '2700.00', null, '123', '50', '2002-12-23 00:00:00');
INSERT INTO `employees` VALUES ('140', 'Joshua', 'Patel', 'JPATEL', '650.121.1834', 'ST_CLERK', '2500.00', null, '123', '50', '2002-12-23 00:00:00');
INSERT INTO `employees` VALUES ('141', 'Trenna', 'Rajs', 'TRAJS', '650.121.8009', 'ST_CLERK', '3500.00', null, '124', '50', '2002-12-23 00:00:00');
INSERT INTO `employees` VALUES ('142', 'Curtis', 'Davies', 'CDAVIES', '650.121.2994', 'ST_CLERK', '3100.00', null, '124', '50', '2002-12-23 00:00:00');
INSERT INTO `employees` VALUES ('143', 'Randall', 'Matos', 'RMATOS', '650.121.2874', 'ST_CLERK', '2600.00', null, '124', '50', '2002-12-23 00:00:00');
INSERT INTO `employees` VALUES ('144', 'Peter', 'Vargas', 'PVARGAS', '650.121.2004', 'ST_CLERK', '2500.00', null, '124', '50', '2002-12-23 00:00:00');
INSERT INTO `employees` VALUES ('145', 'John', 'Russell', 'JRUSSEL', '011.44.1344.429268', 'SA_MAN', '14000.00', '0.40', '100', '80', '2002-12-23 00:00:00');
INSERT INTO `employees` VALUES ('146', 'Karen', 'Partners', 'KPARTNER', '011.44.1344.467268', 'SA_MAN', '13500.00', '0.30', '100', '80', '2002-12-23 00:00:00');
INSERT INTO `employees` VALUES ('147', 'Alberto', 'Errazuriz', 'AERRAZUR', '011.44.1344.429278', 'SA_MAN', '12000.00', '0.30', '100', '80', '2002-12-23 00:00:00');
INSERT INTO `employees` VALUES ('148', 'Gerald', 'Cambrault', 'GCAMBRAU', '011.44.1344.619268', 'SA_MAN', '11000.00', '0.30', '100', '80', '2002-12-23 00:00:00');
INSERT INTO `employees` VALUES ('149', 'Eleni', 'Zlotkey', 'EZLOTKEY', '011.44.1344.429018', 'SA_MAN', '10500.00', '0.20', '100', '80', '2002-12-23 00:00:00');
INSERT INTO `employees` VALUES ('150', 'Peter', 'Tucker', 'PTUCKER', '011.44.1344.129268', 'SA_REP', '10000.00', '0.30', '145', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('151', 'David', 'Bernstein', 'DBERNSTE', '011.44.1344.345268', 'SA_REP', '9500.00', '0.25', '145', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('152', 'Peter', 'Hall', 'PHALL', '011.44.1344.478968', 'SA_REP', '9000.00', '0.25', '145', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('153', 'Christopher', 'Olsen', 'COLSEN', '011.44.1344.498718', 'SA_REP', '8000.00', '0.20', '145', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('154', 'Nanette', 'Cambrault', 'NCAMBRAU', '011.44.1344.987668', 'SA_REP', '7500.00', '0.20', '145', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('155', 'Oliver', 'Tuvault', 'OTUVAULT', '011.44.1344.486508', 'SA_REP', '7000.00', '0.15', '145', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('156', 'Janette', 'K_ing', 'JKING', '011.44.1345.429268', 'SA_REP', '10000.00', '0.35', '146', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('157', 'Patrick', 'Sully', 'PSULLY', '011.44.1345.929268', 'SA_REP', '9500.00', '0.35', '146', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('158', 'Allan', 'McEwen', 'AMCEWEN', '011.44.1345.829268', 'SA_REP', '9000.00', '0.35', '146', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('159', 'Lindsey', 'Smith', 'LSMITH', '011.44.1345.729268', 'SA_REP', '8000.00', '0.30', '146', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('160', 'Louise', 'Doran', 'LDORAN', '011.44.1345.629268', 'SA_REP', '7500.00', '0.30', '146', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('161', 'Sarath', 'Sewall', 'SSEWALL', '011.44.1345.529268', 'SA_REP', '7000.00', '0.25', '146', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('162', 'Clara', 'Vishney', 'CVISHNEY', '011.44.1346.129268', 'SA_REP', '10500.00', '0.25', '147', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('163', 'Danielle', 'Greene', 'DGREENE', '011.44.1346.229268', 'SA_REP', '9500.00', '0.15', '147', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('164', 'Mattea', 'Marvins', 'MMARVINS', '011.44.1346.329268', 'SA_REP', '7200.00', '0.10', '147', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('165', 'David', 'Lee', 'DLEE', '011.44.1346.529268', 'SA_REP', '6800.00', '0.10', '147', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('166', 'Sundar', 'Ande', 'SANDE', '011.44.1346.629268', 'SA_REP', '6400.00', '0.10', '147', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('167', 'Amit', 'Banda', 'ABANDA', '011.44.1346.729268', 'SA_REP', '6200.00', '0.10', '147', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('168', 'Lisa', 'Ozer', 'LOZER', '011.44.1343.929268', 'SA_REP', '11500.00', '0.25', '148', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('169', 'Harrison', 'Bloom', 'HBLOOM', '011.44.1343.829268', 'SA_REP', '10000.00', '0.20', '148', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('170', 'Tayler', 'Fox', 'TFOX', '011.44.1343.729268', 'SA_REP', '9600.00', '0.20', '148', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('171', 'William', 'Smith', 'WSMITH', '011.44.1343.629268', 'SA_REP', '7400.00', '0.15', '148', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('172', 'Elizabeth', 'Bates', 'EBATES', '011.44.1343.529268', 'SA_REP', '7300.00', '0.15', '148', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('173', 'Sundita', 'Kumar', 'SKUMAR', '011.44.1343.329268', 'SA_REP', '6100.00', '0.10', '148', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('174', 'Ellen', 'Abel', 'EABEL', '011.44.1644.429267', 'SA_REP', '11000.00', '0.30', '149', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('175', 'Alyssa', 'Hutton', 'AHUTTON', '011.44.1644.429266', 'SA_REP', '8800.00', '0.25', '149', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('176', 'Jonathon', 'Taylor', 'JTAYLOR', '011.44.1644.429265', 'SA_REP', '8600.00', '0.20', '149', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('177', 'Jack', 'Livingston', 'JLIVINGS', '011.44.1644.429264', 'SA_REP', '8400.00', '0.20', '149', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('178', 'Kimberely', 'Grant', 'KGRANT', '011.44.1644.429263', 'SA_REP', '7000.00', '0.15', '149', null, '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('179', 'Charles', 'Johnson', 'CJOHNSON', '011.44.1644.429262', 'SA_REP', '6200.00', '0.10', '149', '80', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('180', 'Winston', 'Taylor', 'WTAYLOR', '650.507.9876', 'SH_CLERK', '3200.00', null, '120', '50', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('181', 'Jean', 'Fleaur', 'JFLEAUR', '650.507.9877', 'SH_CLERK', '3100.00', null, '120', '50', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('182', 'Martha', 'Sullivan', 'MSULLIVA', '650.507.9878', 'SH_CLERK', '2500.00', null, '120', '50', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('183', 'Girard', 'Geoni', 'GGEONI', '650.507.9879', 'SH_CLERK', '2800.00', null, '120', '50', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('184', 'Nandita', 'Sarchand', 'NSARCHAN', '650.509.1876', 'SH_CLERK', '4200.00', null, '121', '50', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('185', 'Alexis', 'Bull', 'ABULL', '650.509.2876', 'SH_CLERK', '4100.00', null, '121', '50', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('186', 'Julia', 'Dellinger', 'JDELLING', '650.509.3876', 'SH_CLERK', '3400.00', null, '121', '50', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('187', 'Anthony', 'Cabrio', 'ACABRIO', '650.509.4876', 'SH_CLERK', '3000.00', null, '121', '50', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('188', 'Kelly', 'Chung', 'KCHUNG', '650.505.1876', 'SH_CLERK', '3800.00', null, '122', '50', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('189', 'Jennifer', 'Dilly', 'JDILLY', '650.505.2876', 'SH_CLERK', '3600.00', null, '122', '50', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('190', 'Timothy', 'Gates', 'TGATES', '650.505.3876', 'SH_CLERK', '2900.00', null, '122', '50', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('191', 'Randall', 'Perkins', 'RPERKINS', '650.505.4876', 'SH_CLERK', '2500.00', null, '122', '50', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('192', 'Sarah', 'Bell', 'SBELL', '650.501.1876', 'SH_CLERK', '4000.00', null, '123', '50', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('193', 'Britney', 'Everett', 'BEVERETT', '650.501.2876', 'SH_CLERK', '3900.00', null, '123', '50', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('194', 'Samuel', 'McCain', 'SMCCAIN', '650.501.3876', 'SH_CLERK', '3200.00', null, '123', '50', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('195', 'Vance', 'Jones', 'VJONES', '650.501.4876', 'SH_CLERK', '2800.00', null, '123', '50', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('196', 'Alana', 'Walsh', 'AWALSH', '650.507.9811', 'SH_CLERK', '3100.00', null, '124', '50', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('197', 'Kevin', 'Feeney', 'KFEENEY', '650.507.9822', 'SH_CLERK', '3000.00', null, '124', '50', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('198', 'Donald', 'OConnell', 'DOCONNEL', '650.507.9833', 'SH_CLERK', '2600.00', null, '124', '50', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('199', 'Douglas', 'Grant', 'DGRANT', '650.507.9844', 'SH_CLERK', '2600.00', null, '124', '50', '2014-03-05 00:00:00');
INSERT INTO `employees` VALUES ('200', 'Jennifer', 'Whalen', 'JWHALEN', '515.123.4444', 'AD_ASST', '4400.00', null, '101', '10', '2016-03-03 00:00:00');
INSERT INTO `employees` VALUES ('201', 'Michael', 'Hartstein', 'MHARTSTE', '515.123.5555', 'MK_MAN', '13000.00', null, '100', '20', '2016-03-03 00:00:00');
INSERT INTO `employees` VALUES ('202', 'Pat', 'Fay', 'PFAY', '603.123.6666', 'MK_REP', '6000.00', null, '201', '20', '2016-03-03 00:00:00');
INSERT INTO `employees` VALUES ('203', 'Susan', 'Mavris', 'SMAVRIS', '515.123.7777', 'HR_REP', '6500.00', null, '101', '40', '2016-03-03 00:00:00');
INSERT INTO `employees` VALUES ('204', 'Hermann', 'Baer', 'HBAER', '515.123.8888', 'PR_REP', '10000.00', null, '101', '70', '2016-03-03 00:00:00');
INSERT INTO `employees` VALUES ('205', 'Shelley', 'Higgins', 'SHIGGINS', '515.123.8080', 'AC_MGR', '12000.00', null, '101', '110', '2016-03-03 00:00:00');
INSERT INTO `employees` VALUES ('206', 'William', 'Gietz', 'WGIETZ', '515.123.8181', 'AC_ACCOUNT', '8300.00', null, '205', '110', '2016-03-03 00:00:00');

-- ----------------------------
-- Table structure for jobs
-- ----------------------------
DROP TABLE IF EXISTS `jobs`;
CREATE TABLE `jobs` (
  `job_id` varchar(10) CHARACTER SET gb2312 NOT NULL COMMENT '工种id',
  `job_title` varchar(35) CHARACTER SET gb2312 DEFAULT NULL COMMENT '工种名称',
  `min_salary` int(6) DEFAULT NULL COMMENT '最低工资',
  `max_salary` int(6) DEFAULT NULL COMMENT '最高工资',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工种表';

-- ----------------------------
-- Records of jobs
-- ----------------------------
INSERT INTO `jobs` VALUES ('AC_ACCOUNT', 'Public Accountant', '4200', '9000');
INSERT INTO `jobs` VALUES ('AC_MGR', 'Accounting Manager', '8200', '16000');
INSERT INTO `jobs` VALUES ('AD_ASST', 'Administration Assistant', '3000', '6000');
INSERT INTO `jobs` VALUES ('AD_PRES', 'President', '20000', '40000');
INSERT INTO `jobs` VALUES ('AD_VP', 'Administration Vice President', '15000', '30000');
INSERT INTO `jobs` VALUES ('FI_ACCOUNT', 'Accountant', '4200', '9000');
INSERT INTO `jobs` VALUES ('FI_MGR', 'Finance Manager', '8200', '16000');
INSERT INTO `jobs` VALUES ('HR_REP', 'Human Resources Representative', '4000', '9000');
INSERT INTO `jobs` VALUES ('IT_PROG', 'Programmer', '4000', '10000');
INSERT INTO `jobs` VALUES ('MK_MAN', 'Marketing Manager', '9000', '15000');
INSERT INTO `jobs` VALUES ('MK_REP', 'Marketing Representative', '4000', '9000');
INSERT INTO `jobs` VALUES ('PR_REP', 'Public Relations Representative', '4500', '10500');
INSERT INTO `jobs` VALUES ('PU_CLERK', 'Purchasing Clerk', '2500', '5500');
INSERT INTO `jobs` VALUES ('PU_MAN', 'Purchasing Manager', '8000', '15000');
INSERT INTO `jobs` VALUES ('SA_MAN', 'Sales Manager', '10000', '20000');
INSERT INTO `jobs` VALUES ('SA_REP', 'Sales Representative', '6000', '12000');
INSERT INTO `jobs` VALUES ('SH_CLERK', 'Shipping Clerk', '2500', '5500');
INSERT INTO `jobs` VALUES ('ST_CLERK', 'Stock Clerk', '2000', '5000');
INSERT INTO `jobs` VALUES ('ST_MAN', 'Stock Manager', '5500', '8500');

-- ----------------------------
-- Table structure for locations
-- ----------------------------
DROP TABLE IF EXISTS `locations`;
CREATE TABLE `locations` (
  `location_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '位置id',
  `street_address` varchar(40) CHARACTER SET gb2312 DEFAULT NULL COMMENT '街道地址',
  `postal_code` varchar(12) CHARACTER SET gb2312 DEFAULT NULL COMMENT '邮政编号',
  `city` varchar(30) CHARACTER SET gb2312 DEFAULT NULL COMMENT '城市',
  `state_province` varchar(25) CHARACTER SET gb2312 DEFAULT NULL COMMENT '州/省',
  `country_id` varchar(2) CHARACTER SET gb2312 DEFAULT NULL COMMENT '国家id',
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3201 DEFAULT CHARSET=utf8 COMMENT='位置表';

-- ----------------------------
-- Records of locations
-- ----------------------------
INSERT INTO `locations` VALUES ('1000', '1297 Via Cola di Rie', '00989', 'Roma', null, 'IT');
INSERT INTO `locations` VALUES ('1100', '93091 Calle della Testa', '10934', 'Venice', null, 'IT');
INSERT INTO `locations` VALUES ('1200', '2017 Shinjuku-ku', '1689', 'Tokyo', 'Tokyo Prefecture', 'JP');
INSERT INTO `locations` VALUES ('1300', '9450 Kamiya-cho', '6823', 'Hiroshima', null, 'JP');
INSERT INTO `locations` VALUES ('1400', '2014 Jabberwocky Rd', '26192', 'Southlake', 'Texas', 'US');
INSERT INTO `locations` VALUES ('1500', '2011 Interiors Blvd', '99236', 'South San Francisco', 'California', 'US');
INSERT INTO `locations` VALUES ('1600', '2007 Zagora St', '50090', 'South Brunswick', 'New Jersey', 'US');
INSERT INTO `locations` VALUES ('1700', '2004 Charade Rd', '98199', 'Seattle', 'Washington', 'US');
INSERT INTO `locations` VALUES ('1800', '147 Spadina Ave', 'M5V 2L7', 'Toronto', 'Ontario', 'CA');
INSERT INTO `locations` VALUES ('1900', '6092 Boxwood St', 'YSW 9T2', 'Whitehorse', 'Yukon', 'CA');
INSERT INTO `locations` VALUES ('2000', '40-5-12 Laogianggen', '190518', 'Beijing', null, 'CN');
INSERT INTO `locations` VALUES ('2100', '1298 Vileparle (E)', '490231', 'Bombay', 'Maharashtra', 'IN');
INSERT INTO `locations` VALUES ('2200', '12-98 Victoria Street', '2901', 'Sydney', 'New South Wales', 'AU');
INSERT INTO `locations` VALUES ('2300', '198 Clementi North', '540198', 'Singapore', null, 'SG');
INSERT INTO `locations` VALUES ('2400', '8204 Arthur St', null, 'London', null, 'UK');
INSERT INTO `locations` VALUES ('2500', 'Magdalen Centre, The Oxford Science Park', 'OX9 9ZB', 'Oxford', 'Oxford', 'UK');
INSERT INTO `locations` VALUES ('2600', '9702 Chester Road', '09629850293', 'Stretford', 'Manchester', 'UK');
INSERT INTO `locations` VALUES ('2700', 'Schwanthalerstr. 7031', '80925', 'Munich', 'Bavaria', 'DE');
INSERT INTO `locations` VALUES ('2800', 'Rua Frei Caneca 1360 ', '01307-002', 'Sao Paulo', 'Sao Paulo', 'BR');
INSERT INTO `locations` VALUES ('2900', '20 Rue des Corps-Saints', '1730', 'Geneva', 'Geneve', 'CH');
INSERT INTO `locations` VALUES ('3000', 'Murtenstrasse 921', '3095', 'Bern', 'BE', 'CH');
INSERT INTO `locations` VALUES ('3100', 'Pieter Breughelstraat 837', '3029SK', 'Utrecht', 'Utrecht', 'NL');
INSERT INTO `locations` VALUES ('3200', 'Mariano Escobedo 9991', '11932', 'Mexico City', 'Distrito Federal,', 'MX');


##工资等级表
/*CREATE TABLE job_grades
(grade_level VARCHAR(3),
 lowest_sal  int,
 highest_sal int);

INSERT INTO job_grades
VALUES ('A', 1000, 2999);

INSERT INTO job_grades
VALUES ('B', 3000, 5999);

INSERT INTO job_grades
VALUES('C', 6000, 9999);

INSERT INTO job_grades
VALUES('D', 10000, 14999);

INSERT INTO job_grades
VALUES('E', 15000, 24999);

INSERT INTO job_grades
VALUES('F', 25000, 40000);*/

```



# girls数据库

```mysql
/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.7.18-log : Database - girls
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`girls` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `girls`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`id`,`username`,`password`) values (1,'john','8888'),(2,'lyt','6666');

/*Table structure for table `beauty` */

DROP TABLE IF EXISTS `beauty`;

CREATE TABLE `beauty` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `sex` char(1) DEFAULT '女',
  `borndate` datetime DEFAULT '1987-01-01 00:00:00',
  `phone` varchar(11) NOT NULL,
  `photo` blob,
  `boyfriend_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `beauty` */

insert  into `beauty`(`id`,`name`,`sex`,`borndate`,`phone`,`photo`,`boyfriend_id`) values (1,'柳岩','女','1988-02-03 00:00:00','18209876577',NULL,8),(2,'苍老师','女','1987-12-30 00:00:00','18219876577',NULL,9),(3,'Angelababy','女','1989-02-03 00:00:00','18209876567',NULL,3),(4,'热巴','女','1993-02-03 00:00:00','18209876579',NULL,2),(5,'周冬雨','女','1992-02-03 00:00:00','18209179577',NULL,9),(6,'周芷若','女','1988-02-03 00:00:00','18209876577',NULL,1),(7,'岳灵珊','女','1987-12-30 00:00:00','18219876577',NULL,9),(8,'小昭','女','1989-02-03 00:00:00','18209876567',NULL,1),(9,'双儿','女','1993-02-03 00:00:00','18209876579',NULL,9),(10,'王语嫣','女','1992-02-03 00:00:00','18209179577',NULL,4),(11,'夏雪','女','1993-02-03 00:00:00','18209876579',NULL,9),(12,'赵敏','女','1992-02-03 00:00:00','18209179577',NULL,1);

/*Table structure for table `boys` */

DROP TABLE IF EXISTS `boys`;

CREATE TABLE `boys` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `boyName` varchar(20) DEFAULT NULL,
  `userCP` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `boys` */

insert  into `boys`(`id`,`boyName`,`userCP`) values (1,'张无忌',100),(2,'鹿晗',800),(3,'黄晓明',50),(4,'段誉',300);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

```

