CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) NOT NULL,
  `role` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8

insert into user_role values(1, 1000, 1);

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8

insert into values (1, "ADMIN", "Administractive role");
insert into values (2, "USER", "User role");

CREATE TABLE `member` (
  `m_id` int(11) NOT NULL AUTO_INCREMENT,
  `m_lastname` varchar(45) NOT NULL,
  `m_firstname` varchar(45) NOT NULL,
  `m_country` varchar(45) NOT NULL,
  `m_email` varchar(45) NOT NULL,
  `m_pwd` varchar(256) NOT NULL,
  `m_regdate` date NOT NULL,
  `m_enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`m_id`),
  UNIQUE KEY `m_id_UNIQUE` (`m_id`),
  UNIQUE KEY `m_email_UNIQUE` (`m_email`)
) ENGINE=InnoDB AUTO_INCREMENT=1007 DEFAULT CHARSET=utf8

insert into member values(100, "Lee", "SeungYoon", "South Korea", "sylee@mtos.co.kr", "$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu", now(), 1)