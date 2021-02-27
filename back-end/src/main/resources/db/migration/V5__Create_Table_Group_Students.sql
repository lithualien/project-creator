CREATE TABLE IF NOT EXISTS `project_group_students` (
  `project_group_id` BIGINT NOT NULL,
  `student_id` BIGINT NOT NULL,
  CONSTRAINT `fk_student_project_groups` FOREIGN KEY (`project_group_id`) REFERENCES `project_groups` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_group_students` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (`student_id`, `project_group_id`)
);