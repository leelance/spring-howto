-- sql
CREATE TABLE t_order_item
(
    order_item_id BIGINT AUTO_INCREMENT,
    order_id      BIGINT,
    user_id       INT NOT NULL,
    `status`      tinyint NULL DEFAULT NULL,
    `creator`     varchar(32) NULL DEFAULT NULL,
    `create_time` datetime NULL DEFAULT NULL,
    `updater`     varchar(32) NULL DEFAULT NULL,
    `update_time` datetime NULL DEFAULT NULL,
    PRIMARY KEY (order_item_id)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `t_order`
(
    `order_id`    bigint NOT NULL AUTO_INCREMENT,
    `user_id`     int    NOT NULL,
    `address_id`  bigint NOT NULL,
    `status`      tinyint NULL DEFAULT NULL,
    `creator`     varchar(32) NULL DEFAULT NULL,
    `create_time` datetime NULL DEFAULT NULL,
    `updater`     varchar(32) NULL DEFAULT NULL,
    `update_time` datetime NULL DEFAULT NULL,
    PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `t_order_beijing_0`
(
    `order_id`    bigint NOT NULL AUTO_INCREMENT,
    `user_id`     int    NOT NULL,
    `address_id`  bigint NOT NULL,
    `city`        varchar(32) NULL DEFAULT NULL,
    `status`      tinyint NULL DEFAULT NULL,
    `creator`     varchar(32) NULL DEFAULT NULL,
    `create_time` datetime NULL DEFAULT NULL,
    `updater`     varchar(32) NULL DEFAULT NULL,
    `update_time` datetime NULL DEFAULT NULL,
    PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic

CREATE TABLE `t_order_202201`
(
    `order_id`    bigint NOT NULL AUTO_INCREMENT,
    `user_id`     int    NOT NULL,
    `address_id`  bigint NOT NULL,
    `city`        varchar(32) NULL DEFAULT NULL,
    `status`      tinyint NULL DEFAULT NULL,
    `interval_time` datetime NULL DEFAULT NULL,
    `creator`     varchar(32) NULL DEFAULT NULL,
    `create_time` datetime NULL DEFAULT NULL,
    `updater`     varchar(32) NULL DEFAULT NULL,
    `update_time` datetime NULL DEFAULT NULL,
    PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic

CREATE TABLE `t_order_0`
(
    `order_id`    bigint NOT NULL AUTO_INCREMENT,
    `user_id`     int    NOT NULL,
    `address_id`  bigint NOT NULL,
    `city`        varchar(32) NULL DEFAULT NULL,
    `status`      tinyint NULL DEFAULT NULL,
    `interval_time` datetime NULL DEFAULT NULL,
    `creator`     varchar(32) NULL DEFAULT NULL,
    `create_time` datetime NULL DEFAULT NULL,
    `updater`     varchar(32) NULL DEFAULT NULL,
    `update_time` datetime NULL DEFAULT NULL,
    PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic


CREATE TABLE t_address
(
    address_id    BIGINT       NOT NULL,
    address_name  VARCHAR(128) NOT NULL,
    `status`      tinyint NULL DEFAULT NULL,
    `creator`     varchar(32) NULL DEFAULT NULL,
    `create_time` datetime NULL DEFAULT NULL,
    `updater`     varchar(32) NULL DEFAULT NULL,
    `update_time` datetime NULL DEFAULT NULL,
    PRIMARY KEY (address_id)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE t_user
(
    user_id       INT NOT NULL AUTO_INCREMENT,
    username      VARCHAR(32),
    pwd           VARCHAR(32),
    `status`      tinyint NULL DEFAULT NULL,
    `creator`     varchar(32) NULL DEFAULT NULL,
    `create_time` datetime NULL DEFAULT NULL,
    `updater`     varchar(32) NULL DEFAULT NULL,
    `update_time` datetime NULL DEFAULT NULL,
    PRIMARY KEY (user_id)
);
