CREATE DATABASE ssm_sms;
USE ssm_sms;

管理员表
CREATE TABLE tb_admin (
	id INT(11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(15) NOT NULL,
	gender CHAR(1) DEFAULT NULL,
	`password` VARCHAR(20) NOT NULL,
	email VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	telephone VARCHAR(12) NOT NULL,
	address VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	portrait_path VARCHAR(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
	PRIMARY KEY(id)
	) ENGINE = INNODB AUTO_INCREMENT = 160  DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;
	`tb_clazz`
INSERT INTO tb_admin VALUES('666','多瑞c','男','1234','1468241627@qq.com','17674314297',
			 '湖南','D:\downLoad\科比.jpg');
INSERT INTO tb_admin VALUES('777','厂长','男','1234','changzhang@qq.com','1234567890',
			 '湖北','');
SELECT * FROM tb_admin;
DROP TABLE admin;			 

班级信息
CREATE TABLE tb_clazz (
	id INT(11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	number INT(3) NOT NULL,
	introducation VARCHAR(200) NOT NULL,
	coordinator VARCHAR(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	email VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	telephone VARCHAR(12) NOT NULL,
	grade_name VARCHAR(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	PRIMARY KEY (id),
	KEY tb_clazz_tb_grade__fk_idx (grade_name)
	) ENGINE=INNODB AUTO_INCREMENT = 71 DEFAULT CHARSET=utf8 COLLATE = utf8_general_ci;
	
INSERT INTO tb_clazz VALUES ('1', '软件一班', '40', '主要都是高薪就业', '树哥', '328472116@qq.cm', '15939407503', '信息工程大四');
select * from tb_clazz;

年级及年级主任信息
CREATE TABLE tb_grade (
	id int(11) NOT NULL AUTO_INCREMENT,
	`name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	manager varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	email varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	telephone varchar(12) NOT NULL,
	introducation varchar(200) NOT NULL,
	PRIMARY KEY (id,`name`)
	) ENGINE=InnoDB AUTO_INCREMENT = 95 DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;
	
INSERT INTO tb_grade VALUES ('93', '信息工程大四', '李主任', '328472116@qq.com', '18037151521', '主管信息工程大四学生事务');
select * from tb_grade;

学生信息
CREATE TABLE tb_student (
	id int(11) NOT NULL AUTO_INCREMENT,
	sno varchar(20) NOT NULL,
	`name` varchar(15) NOT NULL,
	gender char(1) DEFAULT NULL,
	`password` varchar(20) NOT NULL,
	email varchar(50) NOT NULL,
	telephone varchar(12) NOT NULL,
	address varchar(100) NOT NULL,
	introducation varchar(200) DEFAULT NULL,
	portrait_path varchar(200) DEFAULT NULL,
	clazz_name varchar(15) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE KEY tb_student_sno_uindex (sno)
	) ENGINE=InnoDB AUTO_INCREMENT = 9 DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;
	
INSERT INTO tb_student VALUES ('8', '201809123', '李胜利', '男', '123456', '328472116@qq.com', '18037151521', '北京', '优秀', '', '软件一班');
select * from tb_student;

老师表
CREATE TABLE tb_teacher (
	id int(11) NOT NULL AUTO_INCREMENT,
	tno varchar(20) NOT NULL,
	`name` varchar(15) NOT NULL,
	gender char(1) DEFAULT NULL,
	`password` varchar(20) NOT NULL,
	email varchar(50) NOT NULL,
	telephone varchar(12) NOT NULL,
	address varchar(100) NOT NULL,
	portrait_path varchar(200) DEFAULT NULL,
	clazz_name varchar(15) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE KEY tb_teacher_tno_uindex (tno)
	) ENGINE=InnoDB AUTO_INCREMENT = 14 DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;
	
INSERT INTO `tb_teacher` VALUES ('12', '201809208', '王嘉盛', '男', '122600', '328472116@qq.com', '13037151511', '河南', '', '软件一班');
INSERT INTO `tb_teacher` VALUES ('13', '201809111', '赵宏宇', '女', '123456', '328472116@qq.com', '13725031269', '北京', '', '软件二班');
select * from tb_teacher;