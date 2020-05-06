CREATE TABLE `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `user_role` VARCHAR(55),
  `status` BIT(1) NOT NULL,
  `created_dttm` TIMESTAMP NULL DEFAULT NULL,
  `last_updated_dttm` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `audit_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action` varchar(255) DEFAULT NULL,  
  `entity_name` varchar(255) DEFAULT NULL,
  `ref_table_id` int(11) DEFAULT NULL,
  `content` longtext DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;