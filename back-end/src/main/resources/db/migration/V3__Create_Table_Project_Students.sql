CREATE TABLE IF NOT EXISTS `project_students` (
  `project_id` BIGINT NOT NULL,
  `student_id` BIGINT NOT NULL,
  CONSTRAINT `fk_students_project` FOREIGN KEY (`project_id`) REFERENCES `projects` (id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_projects_student` FOREIGN KEY (`student_id`) REFERENCES `students` (id) ON DELETE RESTRICT ON UPDATE CASCADE,
  PRIMARY KEY (`student_id`, `project_id`)
  );