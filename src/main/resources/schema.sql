-- Auto-generated schema from MyBatis-Plus entities (best-effort)
-- Replace with real mysqldump if any page errors.

CREATE TABLE IF NOT EXISTS `access_token` (
  `token_id` INT NOT NULL AUTO_INCREMENT,
  `token` VARCHAR(255),
  `maxage` INT,
  `create_time` DATETIME,
  `update_time` DATETIME,
  `user_id` INT,
  PRIMARY KEY (`token_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS ``activity_checkin`` (
  `activity_checkin_id` INT NOT NULL AUTO_INCREMENT,
  ``teacher_user`` INT,
  ``activity_number`` VARCHAR(255),
  ``event_name`` VARCHAR(255),
  ``activity_type`` VARCHAR(255),
  ``activity_time`` VARCHAR(255),
  ``activity_location`` VARCHAR(255),
  ``student_users`` INT,
  ``student_name`` VARCHAR(255),
  ``enrollment_information_id`` INT,
  ``checkin_type`` VARCHAR(255),
  ``checkin_time`` DATETIME,
  ``checkin_status`` VARCHAR(255),
  ``certificate_no`` VARCHAR(255),
  `source_table` VARCHAR(255),
  `source_id` INT,
  `source_user_id` INT,
  `update_time` DATETIME,
  `create_time` DATETIME,
  PRIMARY KEY (`activity_checkin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS ``activity_classification`` (
  `activity_classification_id` INT NOT NULL AUTO_INCREMENT,
  ``activity_type`` VARCHAR(255),
  `update_time` DATETIME,
  `create_time` DATETIME,
  PRIMARY KEY (`activity_classification_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS ``activity_costs`` (
  `activity_costs_id` INT NOT NULL AUTO_INCREMENT,
  ``teacher_user`` INT,
  ``activity_number`` VARCHAR(255),
  ``event_name`` VARCHAR(255),
  ``activity_type`` VARCHAR(255),
  ``activity_time`` VARCHAR(255),
  ``activity_location`` VARCHAR(255),
  ``student_users`` INT,
  ``student_name`` VARCHAR(255),
  ``number_of_enrolment`` VARCHAR(255),
  ``expense_item`` VARCHAR(255),
  ``amount_of_expenses`` DOUBLE,
  ``expense_details`` VARCHAR(255),
  `pay_state` VARCHAR(255),
  `pay_type` VARCHAR(255),
  `source_table` VARCHAR(255),
  `source_id` INT,
  `source_user_id` INT,
  `update_time` DATETIME,
  `create_time` DATETIME,
  PRIMARY KEY (`activity_costs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS ``activity_information`` (
  `activity_information_id` INT NOT NULL AUTO_INCREMENT,
  ``teacher_user`` INT,
  ``activity_number`` VARCHAR(255),
  ``event_name`` VARCHAR(255),
  ``activity_type`` VARCHAR(255),
  ``activity_time`` VARCHAR(255),
  ``activity_location`` VARCHAR(255),
  ``activity_cover`` VARCHAR(255),
  ``activity_objectives`` VARCHAR(255),
  ``limit_the_number_of_people`` DOUBLE,
  ``whether_to_charge`` VARCHAR(255),
  ``deadline`` DATETIME,
  ``conditions_for_participation`` VARCHAR(255),
  ``activity_content`` VARCHAR(255),
  ``event_details`` VARCHAR(255),
  `hits` INT,
  `praise_len` INT,
  `collect_len` INT,
  `comment_len` INT,
  `examine_state` VARCHAR(255),
  `enrollment_information_limit_times` VARCHAR(255),
  `update_time` DATETIME,
  `create_time` DATETIME,
  PRIMARY KEY (`activity_information_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS ``activity_notification`` (
  `activity_notification_id` INT NOT NULL AUTO_INCREMENT,
  ``teacher_user`` INT,
  ``activity_number`` VARCHAR(255),
  ``event_name`` VARCHAR(255),
  ``activity_type`` VARCHAR(255),
  ``activity_time`` VARCHAR(255),
  ``activity_location`` VARCHAR(255),
  ``student_users`` INT,
  ``student_name`` VARCHAR(255),
  ``notification_title`` VARCHAR(255),
  ``notification_type`` VARCHAR(255),
  ``notification_time`` DATETIME,
  ``notification_content`` VARCHAR(255),
  `source_table` VARCHAR(255),
  `source_id` INT,
  `source_user_id` INT,
  `update_time` DATETIME,
  `create_time` DATETIME,
  PRIMARY KEY (`activity_notification_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `article` (
  `article_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `type` VARCHAR(255),
  `hits` INT,
  `praise_len` INT,
  `create_time` DATETIME,
  `update_time` DATETIME,
  `source` VARCHAR(255),
  `url` VARCHAR(255),
  `tag` VARCHAR(255),
  `content` VARCHAR(255),
  `img` VARCHAR(255),
  `description` VARCHAR(255),
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `article_type` (
  `type_id` INT NOT NULL AUTO_INCREMENT,
  `display` INT,
  `name` VARCHAR(255),
  `father_id` INT,
  `description` VARCHAR(255),
  `icon` VARCHAR(255),
  `url` VARCHAR(255),
  `create_time` DATETIME,
  `update_time` DATETIME,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `auth` (
  `auth_id` INT NOT NULL AUTO_INCREMENT,
  `user_group` VARCHAR(255),
  `mod_name` VARCHAR(255),
  `table_name` VARCHAR(255),
  `page_title` VARCHAR(255),
  `path` VARCHAR(255),
  `position` VARCHAR(255),
  `mode` VARCHAR(255),
  ``add`` INT,
  `del` INT,
  ``set`` INT,
  ``get`` INT,
  `field_add` VARCHAR(255),
  `field_set` VARCHAR(255),
  `field_get` VARCHAR(255),
  `table_nav_name` VARCHAR(255),
  `table_nav` TINYINT(1),
  ``option`` VARCHAR(255),
  `create_time` DATETIME,
  `update_time` DATETIME,
  PRIMARY KEY (`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `code_token` (
  `code_token_id` INT NOT NULL AUTO_INCREMENT,
  `token` VARCHAR(255),
  `code` VARCHAR(255),
  `expire_time` DATETIME,
  `create_time` DATETIME,
  `update_time` DATETIME,
  PRIMARY KEY (`code_token_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `collect` (
  `collect_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT,
  `source_table` VARCHAR(255),
  `source_field` VARCHAR(255),
  `source_id` INT,
  `title` VARCHAR(255),
  `img` VARCHAR(255),
  `create_time` DATETIME,
  `update_time` DATETIME,
  PRIMARY KEY (`collect_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `comment` (
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT,
  `reply_to_id` INT,
  `content` VARCHAR(255),
  `nickname` VARCHAR(255),
  `avatar` VARCHAR(255),
  `create_time` DATETIME,
  `update_time` DATETIME,
  `source_table` VARCHAR(255),
  `source_field` VARCHAR(255),
  `source_id` INT,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS ``enrollment_information`` (
  `enrollment_information_id` INT NOT NULL AUTO_INCREMENT,
  ``teacher_user`` INT,
  ``activity_number`` VARCHAR(255),
  ``event_name`` VARCHAR(255),
  ``activity_type`` VARCHAR(255),
  ``activity_time`` VARCHAR(255),
  ``activity_location`` VARCHAR(255),
  ``student_users`` INT,
  ``student_name`` VARCHAR(255),
  ``registration_date`` VARCHAR(255),
  ``number_of_enrolment`` VARCHAR(255),
  ``application_for_registration`` VARCHAR(255),
  ``activity_progress`` VARCHAR(255),
  `examine_state` VARCHAR(255),
  `examine_reply` VARCHAR(255),
  `activity_notification_limit_times` VARCHAR(255),
  `activity_costs_limit_times` VARCHAR(255),
  `feedback_evaluation_limit_times` VARCHAR(255),
  `source_table` VARCHAR(255),
  `source_id` INT,
  `source_user_id` INT,
  `update_time` DATETIME,
  `create_time` DATETIME,
  PRIMARY KEY (`enrollment_information_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS ``feedback_evaluation`` (
  `feedback_evaluation_id` INT NOT NULL AUTO_INCREMENT,
  ``teacher_user`` INT,
  ``activity_number`` VARCHAR(255),
  ``event_name`` VARCHAR(255),
  ``activity_type`` VARCHAR(255),
  ``activity_time`` VARCHAR(255),
  ``activity_location`` VARCHAR(255),
  ``student_users`` INT,
  ``student_name`` VARCHAR(255),
  ``number_of_enrolment`` VARCHAR(255),
  ``number_of_participants`` VARCHAR(255),
  ``activity_rating`` DOUBLE,
  ``evaluation_content`` VARCHAR(255),
  ``feedback_content`` VARCHAR(255),
  `source_table` VARCHAR(255),
  `source_id` INT,
  `source_user_id` INT,
  `update_time` DATETIME,
  `create_time` DATETIME,
  PRIMARY KEY (`feedback_evaluation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `hits` (
  `hits_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT,
  `create_time` DATETIME,
  `update_time` DATETIME,
  `source_table` VARCHAR(255),
  `source_field` VARCHAR(255),
  `source_id` INT,
  PRIMARY KEY (`hits_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `message` (
  `message_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT,
  `title` VARCHAR(255),
  `content` VARCHAR(255),
  `nickname` VARCHAR(255),
  `avatar` VARCHAR(255),
  `email` VARCHAR(255),
  `phone` VARCHAR(255),
  `create_time` DATETIME,
  `update_time` DATETIME,
  `reply` VARCHAR(255),
  `reply_state` INT,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `notice` (
  `notice_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255),
  `content` VARCHAR(255),
  `create_time` DATETIME,
  `update_time` DATETIME,
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `praise` (
  `praise_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT,
  `create_time` DATETIME,
  `update_time` DATETIME,
  `source_table` VARCHAR(255),
  `source_field` VARCHAR(255),
  `source_id` INT,
  `status` INT,
  PRIMARY KEY (`praise_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `slides` (
  `slides_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255),
  `content` VARCHAR(255),
  `url` VARCHAR(255),
  `img` VARCHAR(255),
  `hits` INT,
  `create_time` DATETIME,
  `update_time` DATETIME,
  PRIMARY KEY (`slides_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS ``student_users`` (
  `student_users_id` INT NOT NULL AUTO_INCREMENT,
  ``student_no`` VARCHAR(255),
  ``student_name`` VARCHAR(255),
  `examine_state` VARCHAR(255),
  `user_id` INT,
  `update_time` DATETIME,
  `create_time` DATETIME,
  PRIMARY KEY (`student_users_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS ``teacher_user`` (
  `teacher_user_id` INT NOT NULL AUTO_INCREMENT,
  ``teacher_no`` VARCHAR(255),
  ``teachers_name`` VARCHAR(255),
  `user_id` INT,
  `update_time` DATETIME,
  `create_time` DATETIME,
  PRIMARY KEY (`teacher_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `upload` (
  `upload_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255),
  `path` VARCHAR(255),
  `file` VARCHAR(255),
  `display` VARCHAR(255),
  `father_id` INT,
  `dir` VARCHAR(255),
  `type` VARCHAR(255),
  PRIMARY KEY (`upload_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `state` INT,
  `user_group` VARCHAR(255),
  `login_time` DATETIME,
  `phone` VARCHAR(255),
  `phone_state` INT,
  `username` VARCHAR(255),
  `nickname` VARCHAR(255),
  `password` VARCHAR(255),
  `email` VARCHAR(255),
  `email_state` INT,
  `avatar` VARCHAR(255),
  `open_id` VARCHAR(255),
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `user_group` (
  `group_id` INT NOT NULL AUTO_INCREMENT,
  `display` INT,
  `name` VARCHAR(255),
  `description` VARCHAR(255),
  `source_table` VARCHAR(255),
  `source_field` VARCHAR(255),
  `register` VARCHAR(255),
  `create_time` DATETIME,
  `update_time` DATETIME,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
