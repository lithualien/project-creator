CREATE TABLE IF NOT EXISTS `groups` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `group_name_id` BIGINT,
  `student_amount_per_group` INT,
  CONSTRAINT `fk_groups_group_names` FOREIGN KEY (`group_name_id`) REFERENCES `group_names` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  PRIMARY KEY (`id`)
);