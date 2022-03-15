DROP TABLE IF EXISTS `oauth2_authorization`;
CREATE TABLE `oauth2_authorization`
(
    `id`                            varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `registered_client_id`          varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `principal_name`                varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `authorization_grant_type`      varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `attributes`                    blob NULL,
    `state`                         varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `authorization_code_value`      blob NULL,
    `authorization_code_issued_at`  timestamp NULL DEFAULT NULL,
    `authorization_code_expires_at` timestamp NULL DEFAULT NULL,
    `authorization_code_metadata`   blob NULL,
    `access_token_value`            blob NULL,
    `access_token_issued_at`        timestamp NULL DEFAULT NULL,
    `access_token_expires_at`       timestamp NULL DEFAULT NULL,
    `access_token_metadata`         blob NULL,
    `access_token_type`             varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `access_token_scopes`           varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `oidc_id_token_value`           blob NULL,
    `oidc_id_token_issued_at`       timestamp NULL DEFAULT NULL,
    `oidc_id_token_expires_at`      timestamp NULL DEFAULT NULL,
    `oidc_id_token_metadata`        blob NULL,
    `refresh_token_value`           blob NULL,
    `refresh_token_issued_at`       timestamp NULL DEFAULT NULL,
    `refresh_token_expires_at`      timestamp NULL DEFAULT NULL,
    `refresh_token_metadata`        blob NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth2_authorization_consent
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_authorization_consent`;
CREATE TABLE `oauth2_authorization_consent`
(
    `registered_client_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL,
    `principal_name`       varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL,
    `authorities`          varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    PRIMARY KEY (`registered_client_id`, `principal_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth2_authorization_consent
-- ----------------------------

-- ----------------------------
-- Table structure for oauth2_registered_client
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_registered_client`;
CREATE TABLE `oauth2_registered_client`
(
    `id`                            varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL,
    `client_id`                     varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL,
    `client_id_issued_at`           timestamp                                                NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `client_secret`                 varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `client_secret_expires_at`      timestamp NULL DEFAULT NULL,
    `client_name`                   varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL,
    `client_authentication_methods` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `authorization_grant_types`     varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `redirect_uris`                 varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `scopes`                        varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `client_settings`               varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `token_settings`                varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth2_registered_client
-- ----------------------------
INSERT INTO `oauth2_registered_client`
VALUES ('833cec50-fc11-4488-b29c-d3bb7fe7da98', '8000000010', '2022-03-14 15:41:43', '{noop}secret', NULL,
        '833cec50-fc11-4488-b29c-d3bb7fe7da98', 'client_secret_basic',
        'refresh_token,client_credentials,authorization_code',
        'http://127.0.0.1:8080/authorized,http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc',
        'openid,message.read,message.write',
        '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":true}',
        '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",300.000000000],\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",3600.000000000]}');
INSERT INTO `oauth2_registered_client`
VALUES ('ab87af70920044649e6ceb941bcd67ac', '8000000012', '2022-03-15 14:08:34', '{noop}secret', '2022-04-04 14:08:34',
        'Client credentials client_secret_post有限公司', 'client_secret_post', 'refresh_token,client_credentials', '',
        'server',
        '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}',
        '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":false,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",28800.000000000],\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",604800.000000000]}');
INSERT INTO `oauth2_registered_client`
VALUES ('e1328b0728924903a96ede8f2101485a', '8000000013', '2022-03-15 14:44:59', '{noop}secret', '2022-04-04 14:44:59',
        'Client credentials client_secret_basic有限公司', 'client_secret_basic', 'refresh_token,client_credentials', '',
        'server',
        '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}',
        '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":false,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",28800.000000000],\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",604800.000000000]}');
