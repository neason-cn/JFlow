-- jflow.task_ins definition

CREATE TABLE `task_ins` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `task_instance_id` varchar(64) NOT NULL,
    `flow_instance_id` varchar(64) NOT NULL,
    `node_id` varchar(64) NOT NULL,
    `status` varchar(32) NOT NULL,
    `type` varchar(32) NOT NULL,
    `task_context` text,
    `error` varchar(512) DEFAULT NULL,
    `records` text,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;