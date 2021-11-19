CREATE TABLE `cloth` (
  `id` int NOT NULL AUTO_INCREMENT,
  `brand` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `color` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `img_url` text COLLATE utf8mb4_general_ci NOT NULL,
  `is_owned` bit(1) DEFAULT NULL,
  `measure` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `price` int DEFAULT NULL,
  `season` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `tag` text COLLATE utf8mb4_general_ci,
  `type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `updated_at` datetime NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmmtadp7ckq9gm97ydrxc8c8do` (`user_id`),
  CONSTRAINT `FKmmtadp7ckq9gm97ydrxc8c8do` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `codi` (
  `id` int NOT NULL AUTO_INCREMENT,
  `coordinate` text COLLATE utf8mb4_general_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `description` text COLLATE utf8mb4_general_ci,
  `name` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `tag` text COLLATE utf8mb4_general_ci,
  `thumbnail` text COLLATE utf8mb4_general_ci NOT NULL,
  `updated_at` datetime NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq2ur5yb8uhvi9idrvugr8n0ng` (`user_id`),
  CONSTRAINT `FKq2ur5yb8uhvi9idrvugr8n0ng` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `codi_diary` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text COLLATE utf8mb4_general_ci,
  `created_at` datetime NOT NULL,
  `date` date NOT NULL,
  `thumbnail` text COLLATE utf8mb4_general_ci NOT NULL,
  `updated_at` datetime NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmnmmp68hhpsqon89dkhyo4050` (`user_id`),
  CONSTRAINT `FKmnmmp68hhpsqon89dkhyo4050` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `codi_liked` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codi_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2rtpkms6ahknme3wpram176ys` (`codi_id`),
  KEY `FK3sw7b7pua5nfxjfqgxqt9gqob` (`user_id`),
  CONSTRAINT `FK2rtpkms6ahknme3wpram176ys` FOREIGN KEY (`codi_id`) REFERENCES `codi` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK3sw7b7pua5nfxjfqgxqt9gqob` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `codi_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsj5ereaoau0mmplrd8jlyytvt` (`codi_id`),
  KEY `FK8kcum44fvpupyw6f5baccx25c` (`user_id`),
  CONSTRAINT `FK8kcum44fvpupyw6f5baccx25c` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKsj5ereaoau0mmplrd8jlyytvt` FOREIGN KEY (`codi_id`) REFERENCES `codi` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `follow` (
  `id` int NOT NULL AUTO_INCREMENT,
  `from_user_id` int NOT NULL,
  `to_user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKepp5qc1696afiyipw8jhk6et7` (`from_user_id`),
  KEY `FKbo8snnjqnckmjhm2d71j3bc84` (`to_user_id`),
  CONSTRAINT `FKbo8snnjqnckmjhm2d71j3bc84` FOREIGN KEY (`to_user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKepp5qc1696afiyipw8jhk6et7` FOREIGN KEY (`from_user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `grade` (
  `id` int NOT NULL AUTO_INCREMENT,
  `point` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKasgj3xfoi6sd5ghc8psbueb64` (`user_id`),
  CONSTRAINT `FKasgj3xfoi6sd5ghc8psbueb64` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `notice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `is_checked` bit(1) NOT NULL,
  `message` text COLLATE utf8mb4_general_ci NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `codi_id` int DEFAULT NULL,
  `receive_user_id` int NOT NULL,
  `send_user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8nu45y4wuadakvh52yvxv5bs6` (`codi_id`),
  KEY `FKg2hj62otk5j3nxp86a6x61a2s` (`receive_user_id`),
  KEY `FKpku46qqaj9krb719fu9yrmnwl` (`send_user_id`),
  CONSTRAINT `FK8nu45y4wuadakvh52yvxv5bs6` FOREIGN KEY (`codi_id`) REFERENCES `codi` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKg2hj62otk5j3nxp86a6x61a2s` FOREIGN KEY (`receive_user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKpku46qqaj9krb719fu9yrmnwl` FOREIGN KEY (`send_user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `birth` date NOT NULL,
  `created_at` datetime NOT NULL,
  `email` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `gender` varchar(5) COLLATE utf8mb4_general_ci NOT NULL,
  `introduction` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `nickname` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `profile` text COLLATE utf8mb4_general_ci,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_n4swgcf30j6bmtb4l4cjryuym` (`nickname`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `wood` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `created_at` date NOT NULL,
  `terminated_at` date NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK83yyv3b7gbub3hwv0gtrt8uph` (`user_id`),
  CONSTRAINT `FK83yyv3b7gbub3hwv0gtrt8uph` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `wood_codi` (
  `id` int NOT NULL AUTO_INCREMENT,
  `coordinate` text COLLATE utf8mb4_general_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `description` text COLLATE utf8mb4_general_ci,
  `name` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `tag` text COLLATE utf8mb4_general_ci,
  `thumbnail` text COLLATE utf8mb4_general_ci NOT NULL,
  `updated_at` datetime NOT NULL,
  `user_id` int NOT NULL,
  `wood_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm2jilfjdtdk35vcvqaxljpuj3` (`user_id`),
  KEY `FKnlxflf0pxfeyra2x45ydd23i2` (`wood_id`),
  CONSTRAINT `FKm2jilfjdtdk35vcvqaxljpuj3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKnlxflf0pxfeyra2x45ydd23i2` FOREIGN KEY (`wood_id`) REFERENCES `wood` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `wood_codi_liked` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `wood_codi_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKowkwov9dcx3fe1qf41fkdsgv1` (`user_id`),
  KEY `FKs7s7np2xs2bxs2plhgtivpcry` (`wood_codi_id`),
  CONSTRAINT `FKowkwov9dcx3fe1qf41fkdsgv1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKs7s7np2xs2bxs2plhgtivpcry` FOREIGN KEY (`wood_codi_id`) REFERENCES `wood_codi` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
