CREATE TABLE IF NOT EXISTS `project_groups` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `project_group_name_id` BIGINT NOT NULL,
  `project_id` BIGINT NOT NULL,
  CONSTRAINT `fk_project_group_project_group_names` FOREIGN KEY (`project_group_name_id`) REFERENCES `project_group_names` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_group_projects` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  PRIMARY KEY (`id`)
);