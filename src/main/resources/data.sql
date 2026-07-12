-- 预置演示数据（演示登录用 admin / 123456）
INSERT INTO user_group (group_id, display, name, description, source_table, source_field, register, create_time, update_time)
VALUES (1, 1, '管理员', '系统管理员', '', '', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO user (state, user_group, username, nickname, password, email_state, phone_state)
VALUES (1, '管理员', 'admin', '管理员', '123456', 0, 0);

INSERT INTO activity_information (event_name, activity_type, activity_location, activity_cover, examine_state, hits, praise_len, collect_len, comment_len, create_time, update_time)
VALUES
 ('校园迎新音乐会', '文艺活动', '大学生活动中心', '', '已通过', 128, 32, 12, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
 ('Python 编程训练营', '学术讲座', '工科楼 A301', '', '已通过', 256, 64, 20, 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
