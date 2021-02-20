CREATE TABLE IF NOT EXISTS `projects` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255),
  `student_amount_per_group` INT,
  `group_amount` INT,
  PRIMARY KEY (`id`)
);