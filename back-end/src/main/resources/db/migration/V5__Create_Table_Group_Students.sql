CREATE TABLE IF NOT EXISTS `group_students` (
  `group_id` BIGINT NOT NULL,
  `student_id` BIGINT NOT NULL,
  CONSTRAINT `fk_student_groups` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_group_students` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  PRIMARY KEY (`student_id`, `group_id`)
);