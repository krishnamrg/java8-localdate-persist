CREATE TABLE `person` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`first_name` VARCHAR(50) NULL DEFAULT NULL,
	`last_name` VARCHAR(50) NULL DEFAULT NULL,
	`dob` DATE NULL DEFAULT NULL,
	`create_datetime` DATETIME NULL DEFAULT NULL,
	`modified_datetime` DATETIME NULL DEFAULT NULL,
	`version` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;