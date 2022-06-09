-- jflow.flow_ins definition

CREATE TABLE `flow_ins` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `flow_instance_id` varchar(64) NOT NULL,
    `flow_spec_id` varchar(64) NOT NULL,
    `task_instance_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `status` varchar(32) NOT NULL,
    `context` text,
    `input` text,
    `output` text,
    `nodes` text,
    `edges` text,
    `create_at` timestamp NULL DEFAULT NULL,
    `cancel_at` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;