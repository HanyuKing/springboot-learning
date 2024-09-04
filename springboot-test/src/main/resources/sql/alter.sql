CREATE TABLE `t_user` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `name` varchar(64) DEFAULT NULL COMMENT '姓名',
                          `age` int DEFAULT NULL COMMENT '年龄',
                          `submit_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '提交时间',
                          `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;