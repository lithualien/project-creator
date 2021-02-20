CREATE TABLE IF NOT EXISTS `project_group_projects` (
  `project_group_id` BIGINT NOT NULL,
  `project_id` BIGINT NOT NULL,
  CONSTRAINT `fk_project_project_groups` FOREIGN KEY (`project_group_id`) REFERENCES `project_groups` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_group_projects` FOREIGN KEY (`project_id`) REFERENCES `students` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  PRIMARY KEY (`project_id`, `project_group_id`)
);