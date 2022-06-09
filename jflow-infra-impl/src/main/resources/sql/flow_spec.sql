-- jflow.flow_spec definition

CREATE TABLE `flow_spec` (
     `id` bigint NOT NULL AUTO_INCREMENT,
     `spec_id` varchar(64) NOT NULL,
     `spec_code` varchar(128) NOT NULL,
     `spec_version` tinyint NOT NULL,
     `spec_desc` varchar(512) DEFAULT NULL,
     `spec_status` varchar(64) NOT NULL,
     `enable_multi_instance` tinyint(1) NOT NULL,
     `init_context` text,
     `output_script` text,
     `scheduled` tinyint(1) NOT NULL,
     `cron` varchar(16) DEFAULT NULL,
     `start_action` text,
     `end_action` text,
     `nodes` text,
     `edges` text,
     `create_at` timestamp NULL DEFAULT NULL,
     `release_at` timestamp NULL DEFAULT NULL,
     PRIMARY KEY (`id`),
     UNIQUE KEY `flow_spec_spec_id_IDX` (`spec_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;