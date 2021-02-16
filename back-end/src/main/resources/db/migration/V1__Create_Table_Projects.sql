CREATE TABLE IF NOT EXISTS `projects` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255),
  `group_amount` INT,
  `student_amount_per_group` INT,
  PRIMARY KEY (`id`)
  );