CREATE TABLE IF NOT EXISTS `group_projects` (
  `group_id` BIGINT NOT NULL,
  `project_id` BIGINT NOT NULL,
  CONSTRAINT `fk_project_groups` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_group_projects` FOREIGN KEY (`project_id`) REFERENCES `students` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  PRIMARY KEY (`project_id`, `group_id`)
);