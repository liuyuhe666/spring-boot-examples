DROP TABLE IF EXISTS `orm_user`;
CREATE TABLE `orm_user`
(
  `id`               INT(11)     NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  `name`             VARCHAR(32) NOT NULL UNIQUE COMMENT '用户名',
  `password`         VARCHAR(32) NOT NULL COMMENT '加密后的密码',
  `salt`             VARCHAR(32) NOT NULL COMMENT '加密使用的盐',
  `email`            VARCHAR(32) NOT NULL UNIQUE COMMENT '邮箱',
  `phone_number`     VARCHAR(15) NOT NULL UNIQUE COMMENT '手机号码',
  `status`           INT(2)      NOT NULL DEFAULT 1 COMMENT '状态，-1：逻辑删除，0：禁用，1：启用',
  `create_time`      DATETIME    NOT NULL DEFAULT NOW() COMMENT '创建时间',
  `last_login_time`  DATETIME             DEFAULT NULL COMMENT '上次登录时间',
  `last_update_time` DATETIME    NOT NULL DEFAULT NOW() COMMENT '上次更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='Spring Boot Examples Orm 示例表';

DROP TABLE IF EXISTS `orm_role`;
CREATE TABLE `orm_role`
(
  `id`   INT(11)     NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  `name` VARCHAR(32) NOT NULL UNIQUE COMMENT '角色名'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='Spring Boot Examples Orm 示例表';
