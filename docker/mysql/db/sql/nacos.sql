/*
 Navicat Premium Data Transfer

 Source Server         : ParkingLot
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : 192.168.6.6:3306
 Source Schema         : nacos

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 18/02/2022 23:49:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 89 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (10, 'sentinel-gateway', 'DEFAULT_GROUP', '[\n    {\n    \"resource\": \"parking-admin\",\n    \"count\": 500,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n    },\n    {\n    \"resource\": \"parking-auth\",\n    \"count\": 500,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n    },\n    {\n    \"resource\": \"parking-file\",\n    \"count\": 500,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n    }\n]', '4f85dc9f7eb6539e7394fe2b5081a07b', '2021-12-05 14:29:06', '2022-02-18 15:34:55', NULL, '192.168.6.1', '', '', 'sentinel网关限流配置', '', '', 'json', '');
INSERT INTO `config_info` VALUES (16, 'gateway-admin-dev.yml', 'DEFAULT_GROUP', 'spring:\n  cloud:\n    gateway:\n      routes:\n        # 认证服务\n        - id: parking-auth\n          uri: lb://parking-auth\n          predicates:\n            - Path=/parking-auth/**\n          filters:\n            # 验证码处理\n            - CacheRequestFilter\n            - CaptchaFilter\n            - StripPrefix=1\n        # 系统服务\n        - id: parking-admin\n          uri: lb://parking-admin\n          predicates:\n            - Path=/parking-admin/**\n          filters:\n            - StripPrefix=1\n        # 文件服务\n        - id: parking-file\n          uri: lb://parking-file\n          predicates:\n            - Path=/parking-file/**\n          filters:\n            - StripPrefix=1\n# 验证码\ncaptcha:\n  enabled: false\n  type: math\n# 白名单\nignore:\n  whites: /parking-auth/auth/logout,/parking-auth/auth/login,/*/v2/api-docs', '21a03a26d4fc46da6e71b3799941b3e0', '2021-12-09 13:45:24', '2022-02-12 13:16:02', NULL, '192.168.6.1', '', '', '网关配置', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (18, 'common-dev.yml', 'DEFAULT_GROUP', 'spring:\n  redis:\n    host: redis\n    port: 6379\n    # 连接池最大阻塞等待时间\n    max-wait: 30000\n    # 连接池最大连接数\n    max-active: 100\n    # 连接池最大空闲连接\n    max-idle: 20\n    # 连接池最小空闲连接\n    min-idle: 0\n    # 连接超时\n    timeout: 3000\n  main:\n    allow-bean-definition-overriding: true\n  autoconfigure:\n    exclude: com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure\n# feign 配置\nfeign:\n  sentinel:\n    enabled: true\n  okhttp:\n    enabled: true\n  httpclient:\n    enabled: false\n  client:\n    config:\n      default:\n        connectTimeout: 10000\n        readTimeout: 10000\n  compression:\n    request:\n      enabled: true\n    response:\n      enabled: true\n# 暴露监控端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'', 'f9dfa46a0f148c6acd2c602f2811c030', '2021-12-09 14:04:36', '2022-02-18 15:36:08', NULL, '192.168.6.1', '', '', '通用配置', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (23, 'application-dev.yml', 'DEFAULT_GROUP', 'spring:\r\n  main:\r\n    allow-bean-definition-overriding: true\r\n  autoconfigure:\r\n    exclude: com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure\r\n\r\n# feign 配置\r\nfeign:\r\n  sentinel:\r\n    enabled: true\r\n  okhttp:\r\n    enabled: true\r\n  httpclient:\r\n    enabled: false\r\n  client:\r\n    config:\r\n      default:\r\n        connectTimeout: 10000\r\n        readTimeout: 10000\r\n  compression:\r\n    request:\r\n      enabled: true\r\n    response:\r\n      enabled: true\r\n\r\n# 暴露监控端点\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'\r\n', '0ed0ef49ad44fbc60471a8cde2c63fe7', '2021-12-12 03:28:34', '2021-12-12 03:28:34', NULL, '192.168.6.1', '', 'f69a4454-26af-43e2-88fb-627343f63079', '通用配置', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (24, 'ruoyi-gateway-dev.yml', 'DEFAULT_GROUP', 'spring:\n  redis:\n    host: 192.168.6.6\n    port: 6379\n    password: \n  cloud:\n    gateway:\n      discovery:\n        locator:\n          lowerCaseServiceId: true\n          enabled: true\n      routes:\n        # 认证中心\n        - id: ruoyi-auth\n          uri: lb://ruoyi-auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            # 验证码处理\n            - CacheRequestFilter\n            - ValidateCodeFilter\n            - StripPrefix=1\n        # 代码生成\n        - id: ruoyi-gen\n          uri: lb://ruoyi-gen\n          predicates:\n            - Path=/code/**\n          filters:\n            - StripPrefix=1\n        # 定时任务\n        - id: ruoyi-job\n          uri: lb://ruoyi-job\n          predicates:\n            - Path=/schedule/**\n          filters:\n            - StripPrefix=1\n        # 系统模块\n        - id: ruoyi-system\n          uri: lb://ruoyi-system\n          predicates:\n            - Path=/system/**\n          filters:\n            - StripPrefix=1\n        # 文件服务\n        - id: ruoyi-file\n          uri: lb://ruoyi-file\n          predicates:\n            - Path=/file/**\n          filters:\n            - StripPrefix=1\n\n# 安全配置\nsecurity:\n  # 验证码\n  captcha:\n    enabled: true\n    type: math\n  # 防止XSS攻击\n  xss:\n    enabled: true\n    excludeUrls:\n      - /system/notice\n  # 不校验白名单\n  ignore:\n    whites:\n      - /auth/logout\n      - /auth/login\n      - /auth/register\n      - /*/v2/api-docs\n      - /csrf\n', 'bccbe779a2cd42fd6f4ef4f879b39500', '2021-12-12 03:29:11', '2021-12-12 04:08:07', NULL, '192.168.6.1', '', 'f69a4454-26af-43e2-88fb-627343f63079', '网关模块', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (25, 'ruoyi-auth-dev.yml', 'DEFAULT_GROUP', 'spring: \n  redis:\n    host: 192.168.6.6\n    port: 6379\n    password: \n', 'e3666cf0c937e47688c29696832d6e4e', '2021-12-12 03:29:44', '2021-12-12 04:08:32', NULL, '192.168.6.1', '', 'f69a4454-26af-43e2-88fb-627343f63079', '认证中心', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (26, 'ruoyi-monitor-dev.yml', 'DEFAULT_GROUP', '# spring\r\nspring: \r\n  security:\r\n    user:\r\n      name: ruoyi\r\n      password: 123456\r\n  boot:\r\n    admin:\r\n      ui:\r\n        title: 若依服务状态监控\r\n', 'd8997d0707a2fd5d9fc4e8409da38129', '2021-12-12 03:30:21', '2021-12-12 03:30:21', NULL, '192.168.6.1', '', 'f69a4454-26af-43e2-88fb-627343f63079', '监控中心', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (27, 'ruoyi-system-dev.yml', 'DEFAULT_GROUP', '# spring配置\nspring: \n  redis:\n    host: 192.168.6.6\n    port: 6379\n    password: \n  datasource:\n    druid:\n      stat-view-servlet:\n        enabled: true\n        loginUsername: admin\n        loginPassword: 123456\n    dynamic:\n      druid:\n        initial-size: 5\n        min-idle: 5\n        maxActive: 20\n        maxWait: 60000\n        timeBetweenEvictionRunsMillis: 60000\n        minEvictableIdleTimeMillis: 300000\n        validationQuery: SELECT 1 FROM DUAL\n        testWhileIdle: true\n        testOnBorrow: false\n        testOnReturn: false\n        poolPreparedStatements: true\n        maxPoolPreparedStatementPerConnectionSize: 20\n        filters: stat,slf4j\n        connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\n      datasource:\n          # 主库数据源\n          master:\n            driver-class-name: com.mysql.cj.jdbc.Driver\n            url: jdbc:mysql://192.168.6.6:3306/ruoyi?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n            username: root\n            password: root\n          # 从库数据源\n          # slave:\n            # username: \n            # password: \n            # url: \n            # driver-class-name: \n      # seata: true    # 开启seata代理，开启后默认每个数据源都代理，如果某个不需要代理可单独关闭\n\n# seata配置\nseata:\n  # 默认关闭，如需启用spring.datasource.dynami.seata需要同时开启\n  enabled: false\n  # Seata 应用编号，默认为 ${spring.application.name}\n  application-id: ${spring.application.name}\n  # Seata 事务组编号，用于 TC 集群名\n  tx-service-group: ${spring.application.name}-group\n  # 关闭自动代理\n  enable-auto-data-source-proxy: false\n  # 服务配置项\n  service:\n    # 虚拟组和分组的映射\n    vgroup-mapping:\n      ruoyi-system-group: default\n  config:\n    type: nacos\n    nacos:\n      serverAddr: 127.0.0.1:8848\n      group: SEATA_GROUP\n      namespace:\n  registry:\n    type: nacos\n    nacos:\n      application: seata-server\n      server-addr: 127.0.0.1:8848\n      namespace:\n\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.ruoyi.system\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml\n\n# swagger配置\nswagger:\n  title: 系统模块接口文档\n  license: Powered By ruoyi\n  licenseUrl: https://ruoyi.vip', '4ab73667b0b537b87bb751365e7b3e40', '2021-12-12 03:30:52', '2021-12-12 04:10:17', NULL, '192.168.6.1', '', 'f69a4454-26af-43e2-88fb-627343f63079', '系统模块', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (28, 'ruoyi-gen-dev.yml', 'DEFAULT_GROUP', '# spring配置\nspring: \n  redis:\n    host: 192.168.6.6\n    port: 6379\n    password: \n  datasource: \n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://192.168.6.6:3306/ruoyi?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n    username: root\n    password: root\n\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.ruoyi.gen.domain\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml\n\n# swagger配置\nswagger:\n  title: 代码生成接口文档\n  license: Powered By ruoyi\n  licenseUrl: https://ruoyi.vip\n\n# 代码生成\ngen: \n  # 作者\n  author: ruoyi\n  # 默认生成包路径 system 需改成自己的模块名称 如 system monitor tool\n  packageName: com.ruoyi.system\n  # 自动去除表前缀，默认是false\n  autoRemovePre: false\n  # 表前缀（生成类名不会包含表前缀，多个用逗号分隔）\n  tablePrefix: sys_\n', '7e349f5cbd1927594da0fb38f87ede9a', '2021-12-12 03:31:14', '2021-12-12 04:11:05', NULL, '192.168.6.1', '', 'f69a4454-26af-43e2-88fb-627343f63079', '代码生成', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (29, 'ruoyi-job-dev.yml', 'DEFAULT_GROUP', '# spring配置\nspring: \n  redis:\n    host: 192.168.6.6\n    port: 6379\n    password: \n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://192.168.6.6:3306/ruoyi?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n    username: root\n    password: root\n\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.ruoyi.job.domain\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml\n\n# swagger配置\nswagger:\n  title: 定时任务接口文档\n  license: Powered By ruoyi\n  licenseUrl: https://ruoyi.vip\n', '2f4cfa50913f4c6dc17bc4c744f369d7', '2021-12-12 03:31:37', '2021-12-12 04:11:51', NULL, '192.168.6.1', '', 'f69a4454-26af-43e2-88fb-627343f63079', '定时任务', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (30, 'ruoyi-file-dev.yml', 'DEFAULT_GROUP', '# 本地文件上传    \r\nfile:\r\n    domain: http://127.0.0.1:9300\r\n    path: D:/ruoyi/uploadPath\r\n    prefix: /statics\r\n\r\n# FastDFS配置\r\nfdfs:\r\n  domain: http://8.129.231.12\r\n  soTimeout: 3000\r\n  connectTimeout: 2000\r\n  trackerList: 8.129.231.12:22122\r\n\r\n# Minio配置\r\nminio:\r\n  url: http://8.129.231.12:9000\r\n  accessKey: minioadmin\r\n  secretKey: minioadmin\r\n  bucketName: test', '5382b93f3d8059d6068c0501fdd41195', '2021-12-12 03:31:59', '2021-12-12 03:31:59', NULL, '192.168.6.1', '', 'f69a4454-26af-43e2-88fb-627343f63079', '文件服务', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (31, 'sentinel-ruoyi-gateway', 'DEFAULT_GROUP', '[\r\n    {\r\n        \"resource\": \"ruoyi-auth\",\r\n        \"count\": 500,\r\n        \"grade\": 1,\r\n        \"limitApp\": \"default\",\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0\r\n    },\r\n	{\r\n        \"resource\": \"ruoyi-system\",\r\n        \"count\": 1000,\r\n        \"grade\": 1,\r\n        \"limitApp\": \"default\",\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0\r\n    },\r\n	{\r\n        \"resource\": \"ruoyi-gen\",\r\n        \"count\": 200,\r\n        \"grade\": 1,\r\n        \"limitApp\": \"default\",\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0\r\n    },\r\n	{\r\n        \"resource\": \"ruoyi-job\",\r\n        \"count\": 300,\r\n        \"grade\": 1,\r\n        \"limitApp\": \"default\",\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0\r\n    }\r\n]', '9f3a3069261598f74220bc47958ec252', '2021-12-12 03:32:21', '2021-12-12 03:32:21', NULL, '192.168.6.1', '', 'f69a4454-26af-43e2-88fb-627343f63079', '限流策略', NULL, NULL, 'json', NULL);
INSERT INTO `config_info` VALUES (70, 'parking-admin-dev.yml', 'DEFAULT_GROUP', 'spring:\n  web:\n    resources:\n      # 不要为我们工程中的资源文件建立映射\n      add-mappings: false\n  mvc:\n    # 出现错误时, 直接抛出异常\n    throw-exception-if-no-handler-found: true\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://mysql:3306/smart-parking-lot?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai\n    username: root\n    password: root\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      # 初始化大小\n      initial-size: 5\n      # 最少数量\n      min-idle: 5\n      # 最大数量\n      max-active: 20\n      # 超时时长\n      max-wait: 60000\n      # 间隔多久才进行一次检测\n      timeBetweenEvictionRunsMillis: 60000\n      # 一个连接在池中最小生存的时间\n      minEvictableIdleTimeMillis: 300000\n      # 验证查询\n      validationQuery: select 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      # 是否缓存preparedStatement\n      poolPreparedStatements: true\n      maxPoolPreparedStatementPerConnectionSize: 20\n      # 配置监控统计拦截的filters\n      filters: stat,slf4j\n      # 通过connectProperties属性来打开mergeSql功能\n      connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    #typeAliasesPackage: com.cetuer.parking.admin\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml\n    configuration:\n      log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n      map-underscore-to-camel-case: true', 'c0ff7da6d9dbcaedcf294241e96633ea', '2022-01-21 13:04:42', '2022-02-18 15:36:39', NULL, '192.168.6.1', '', '', '系统模块相关配置', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (84, 'parking-file-dev.yml', 'DEFAULT_GROUP', '# Minio配置\nminio:\n  url: http://file.cetuer.com\n  username: root\n  password: Zqb..990826\n  bucketName: parking', '7f5cb55fa98024f288055443c0ab043f', '2022-02-12 07:27:32', '2022-02-18 15:37:00', NULL, '192.168.6.1', '', '', '文件服务配置', '', '', 'yaml', '');

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id`, `group_id`, `tenant_id`, `datum_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id`, `group_id`, `tenant_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint(64) UNSIGNED NOT NULL,
  `nid` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
  INDEX `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 125 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (16, 95, 'gateway-admin-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        # 认证服务\n        - id: auth\n          uri: lb://auth\n          predicates:\n            - Path=/parking-auth/**\n          filters:\n            # 验证码处理\n            - CacheRequestFilter\n            - CaptchaFilter\n            - StripPrefix=1\n        # 菜单服务\n        - id: service-menu\n          uri: lb://service-menu\n          predicates:\n            - Path=/parking-menu/**\n          filters:\n            - StripPrefix=1\n        # 用户服务\n        - id: service-user\n          uri: lb://service-user\n          predicates:\n            - Path=/parking-user/**\n          filters:\n            - StripPrefix=1\n# 验证码\ncaptcha:\n  enabled: true\n  type: math', 'fc3394ed001a315ed43a36f03ee02991', '2022-01-20 07:46:00', '2022-01-20 07:46:00', NULL, '192.168.6.1', 'U', '');
INSERT INTO `his_config_info` VALUES (10, 96, 'sentinel-gateway', 'DEFAULT_GROUP', '', '[\n    {\n    \"resource\": \"service-menu\",\n    \"count\": 500,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n    },\n    {\n    \"resource\": \"service-user\",\n    \"count\": 500,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n    },\n    {\n    \"resource\": \"auth\",\n    \"count\": 500,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n    }\n]', '36f741afc56a2101c0bd92f447d270c8', '2022-01-21 12:59:47', '2022-01-21 12:59:47', NULL, '192.168.6.1', 'U', '');
INSERT INTO `his_config_info` VALUES (16, 97, 'gateway-admin-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        # 认证服务\n        - id: auth\n          uri: lb://auth\n          predicates:\n            - Path=/parking-auth/**\n          filters:\n            # 验证码处理\n            - CacheRequestFilter\n            - CaptchaFilter\n            - StripPrefix=1\n        # 菜单服务\n        - id: service-menu\n          uri: lb://service-menu\n          predicates:\n            - Path=/parking-menu/**\n          filters:\n            - StripPrefix=1\n        # 用户服务\n        - id: service-user\n          uri: lb://service-user\n          predicates:\n            - Path=/parking-user/**\n          filters:\n            - StripPrefix=1\n# 验证码\ncaptcha:\n  enabled: true\n  type: math\n# 白名单\nignore:\n  whites:\n    - /parking-auth/auth/logout\n    - /parking-auth/auth/login\n    - /*/v2/api-docs', '741d5cc42797c890df4af8bb86dd9b22', '2022-01-21 13:01:22', '2022-01-21 13:01:23', NULL, '192.168.6.1', 'U', '');
INSERT INTO `his_config_info` VALUES (10, 98, 'sentinel-gateway', 'DEFAULT_GROUP', '', '[\n    {\n    \"resource\": \"parking-admin\",\n    \"count\": 500,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n    },\n    {\n    \"resource\": \"auth\",\n    \"count\": 500,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n    }\n]', '1c037e9ccda187c0574b10e53b73402d', '2022-01-21 13:01:34', '2022-01-21 13:01:34', NULL, '192.168.6.1', 'U', '');
INSERT INTO `his_config_info` VALUES (51, 99, 'auth-dev.yml', 'DEFAULT_GROUP', '', '111', '698d51a19d8a121ce581499d7b701668', '2022-01-21 13:02:37', '2022-01-21 13:02:38', NULL, '192.168.6.1', 'D', '');
INSERT INTO `his_config_info` VALUES (0, 100, 'parking-admin-dev.yml', 'DEFAULT_GROUP', '', 'admin', '21232f297a57a5a743894a0e4a801fc3', '2022-01-21 13:04:41', '2022-01-21 13:04:42', NULL, '192.168.6.1', 'I', '');
INSERT INTO `his_config_info` VALUES (70, 101, 'parking-admin-dev.yml', 'DEFAULT_GROUP', '', 'admin', '21232f297a57a5a743894a0e4a801fc3', '2022-01-21 13:05:02', '2022-01-21 13:05:02', NULL, '192.168.6.1', 'U', '');
INSERT INTO `his_config_info` VALUES (57, 102, 'service-common-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  web:\n    resources:\n      # 不要为我们工程中的资源文件建立映射\n      add-mappings: false\n  mvc:\n    # 出现错误时, 直接抛出异常\n    throw-exception-if-no-handler-found: true\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://192.168.6.6:3306/smart-parking-lot?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai\n    username: root\n    password: root\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      # 初始化大小\n      initial-size: 5\n      # 最少数量\n      min-idle: 5\n      # 最大数量\n      max-active: 20\n      # 超时时长\n      max-wait: 60000\n      # 间隔多久才进行一次检测\n      timeBetweenEvictionRunsMillis: 60000\n      # 一个连接在池中最小生存的时间\n      minEvictableIdleTimeMillis: 300000\n      # 验证查询\n      validationQuery: select 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      # 是否缓存preparedStatement\n      poolPreparedStatements: true\n      maxPoolPreparedStatementPerConnectionSize: 20\n      # 配置监控统计拦截的filters\n      filters: stat,slf4j\n      # 通过connectProperties属性来打开mergeSql功能\n      connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000', '8d7711c2d987adcf6903bf3ea74182d7', '2022-01-21 13:05:16', '2022-01-21 13:05:17', NULL, '192.168.6.1', 'D', '');
INSERT INTO `his_config_info` VALUES (70, 103, 'parking-admin-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  web:\n    resources:\n      # 不要为我们工程中的资源文件建立映射\n      add-mappings: false\n  mvc:\n    # 出现错误时, 直接抛出异常\n    throw-exception-if-no-handler-found: true\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://192.168.6.6:3306/smart-parking-lot?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai\n    username: root\n    password: root\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      # 初始化大小\n      initial-size: 5\n      # 最少数量\n      min-idle: 5\n      # 最大数量\n      max-active: 20\n      # 超时时长\n      max-wait: 60000\n      # 间隔多久才进行一次检测\n      timeBetweenEvictionRunsMillis: 60000\n      # 一个连接在池中最小生存的时间\n      minEvictableIdleTimeMillis: 300000\n      # 验证查询\n      validationQuery: select 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      # 是否缓存preparedStatement\n      poolPreparedStatements: true\n      maxPoolPreparedStatementPerConnectionSize: 20\n      # 配置监控统计拦截的filters\n      filters: stat,slf4j\n      # 通过connectProperties属性来打开mergeSql功能\n      connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000', '8d7711c2d987adcf6903bf3ea74182d7', '2022-01-21 13:07:20', '2022-01-21 13:07:20', NULL, '192.168.6.1', 'U', '');
INSERT INTO `his_config_info` VALUES (17, 104, 'service-menu-dev.yml', 'DEFAULT_GROUP', '', '# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.cetuer.parking.menu\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml', '55ea2c7b8b1324f1b8517e101b3e654f', '2022-01-21 13:07:30', '2022-01-21 13:07:31', NULL, '192.168.6.1', 'D', '');
INSERT INTO `his_config_info` VALUES (65, 105, 'service-user-dev.yml', 'DEFAULT_GROUP', '', '# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.cetuer.parking.user\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml', 'f1cb188ddabccc0eda6edb2af90b128e', '2022-01-21 13:07:34', '2022-01-21 13:07:35', NULL, '192.168.6.1', 'D', '');
INSERT INTO `his_config_info` VALUES (16, 106, 'gateway-admin-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        # 认证服务\n        - id: parking-auth\n          uri: lb://parking-auth\n          predicates:\n            - Path=/parking-auth/**\n          filters:\n            # 验证码处理\n            - CacheRequestFilter\n            - CaptchaFilter\n            - StripPrefix=1\n        # 系统服务\n        - id: parking-admin\n          uri: lb://parking-admin\n          predicates:\n            - Path=/parking-admin/**\n          filters:\n            - StripPrefix=1\n# 验证码\ncaptcha:\n  enabled: true\n  type: math\n# 白名单\nignore:\n  whites:\n    - /parking-auth/auth/logout\n    - /parking-auth/auth/login\n    - /*/v2/api-docs', 'da5a3b914633385d1b029232f8ff23b2', '2022-01-23 09:09:52', '2022-01-23 09:09:52', NULL, '192.168.6.1', 'U', '');
INSERT INTO `his_config_info` VALUES (70, 107, 'parking-admin-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  web:\n    resources:\n      # 不要为我们工程中的资源文件建立映射\n      add-mappings: false\n  mvc:\n    # 出现错误时, 直接抛出异常\n    throw-exception-if-no-handler-found: true\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://192.168.6.6:3306/smart-parking-lot?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai\n    username: root\n    password: root\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      # 初始化大小\n      initial-size: 5\n      # 最少数量\n      min-idle: 5\n      # 最大数量\n      max-active: 20\n      # 超时时长\n      max-wait: 60000\n      # 间隔多久才进行一次检测\n      timeBetweenEvictionRunsMillis: 60000\n      # 一个连接在池中最小生存的时间\n      minEvictableIdleTimeMillis: 300000\n      # 验证查询\n      validationQuery: select 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      # 是否缓存preparedStatement\n      poolPreparedStatements: true\n      maxPoolPreparedStatementPerConnectionSize: 20\n      # 配置监控统计拦截的filters\n      filters: stat,slf4j\n      # 通过connectProperties属性来打开mergeSql功能\n      connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.cetuer.parking.*.domain\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml', 'eaf27f1083272268351d5cdba3f70106', '2022-01-23 09:16:56', '2022-01-23 09:16:57', NULL, '192.168.6.1', 'U', '');
INSERT INTO `his_config_info` VALUES (70, 108, 'parking-admin-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  web:\n    resources:\n      # 不要为我们工程中的资源文件建立映射\n      add-mappings: false\n  mvc:\n    # 出现错误时, 直接抛出异常\n    throw-exception-if-no-handler-found: true\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://192.168.6.6:3306/smart-parking-lot?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai\n    username: root\n    password: root\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      # 初始化大小\n      initial-size: 5\n      # 最少数量\n      min-idle: 5\n      # 最大数量\n      max-active: 20\n      # 超时时长\n      max-wait: 60000\n      # 间隔多久才进行一次检测\n      timeBetweenEvictionRunsMillis: 60000\n      # 一个连接在池中最小生存的时间\n      minEvictableIdleTimeMillis: 300000\n      # 验证查询\n      validationQuery: select 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      # 是否缓存preparedStatement\n      poolPreparedStatements: true\n      maxPoolPreparedStatementPerConnectionSize: 20\n      # 配置监控统计拦截的filters\n      filters: stat,slf4j\n      # 通过connectProperties属性来打开mergeSql功能\n      connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.cetuer.parking.admin.domain\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml', '8864a8410f64cd34f86edd5d364a40ff', '2022-01-23 09:21:08', '2022-01-23 09:21:09', NULL, '192.168.6.1', 'U', '');
INSERT INTO `his_config_info` VALUES (16, 109, 'gateway-admin-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        # 认证服务\n        - id: parking-auth\n          uri: lb://parking-auth\n          predicates:\n            - Path=/parking-auth/**\n          filters:\n            # 验证码处理\n            - CacheRequestFilter\n            - CaptchaFilter\n            - StripPrefix=1\n        # 系统服务\n        - id: parking-admin\n          uri: lb://parking-admin\n          predicates:\n            - Path=/parking-admin/**\n          filters:\n            - StripPrefix=1\n# 验证码\ncaptcha:\n  enabled: true\n  type: math\n# 白名单\nignore:\n  whites: /parking-auth/auth/logout, /parking-auth/auth/login, /*/v2/api-docs', '72d94e8d86eaebff21cad5e96d377558', '2022-01-23 14:31:55', '2022-01-23 14:31:55', NULL, '192.168.6.1', 'U', '');
INSERT INTO `his_config_info` VALUES (70, 110, 'parking-admin-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  web:\n    resources:\n      # 不要为我们工程中的资源文件建立映射\n      add-mappings: false\n  mvc:\n    # 出现错误时, 直接抛出异常\n    throw-exception-if-no-handler-found: true\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://192.168.6.6:3306/smart-parking-lot?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai\n    username: root\n    password: root\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      # 初始化大小\n      initial-size: 5\n      # 最少数量\n      min-idle: 5\n      # 最大数量\n      max-active: 20\n      # 超时时长\n      max-wait: 60000\n      # 间隔多久才进行一次检测\n      timeBetweenEvictionRunsMillis: 60000\n      # 一个连接在池中最小生存的时间\n      minEvictableIdleTimeMillis: 300000\n      # 验证查询\n      validationQuery: select 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      # 是否缓存preparedStatement\n      poolPreparedStatements: true\n      maxPoolPreparedStatementPerConnectionSize: 20\n      # 配置监控统计拦截的filters\n      filters: stat,slf4j\n      # 通过connectProperties属性来打开mergeSql功能\n      connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.cetuer.parking.admin\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml', '46f704008853a7824ee68d3905d06afa', '2022-01-27 14:29:37', '2022-01-27 14:29:36', NULL, '192.168.6.1', 'U', '');
INSERT INTO `his_config_info` VALUES (70, 111, 'parking-admin-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  web:\n    resources:\n      # 不要为我们工程中的资源文件建立映射\n      add-mappings: false\n  mvc:\n    # 出现错误时, 直接抛出异常\n    throw-exception-if-no-handler-found: true\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://192.168.6.6:3306/smart-parking-lot?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai\n    username: root\n    password: root\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      # 初始化大小\n      initial-size: 5\n      # 最少数量\n      min-idle: 5\n      # 最大数量\n      max-active: 20\n      # 超时时长\n      max-wait: 60000\n      # 间隔多久才进行一次检测\n      timeBetweenEvictionRunsMillis: 60000\n      # 一个连接在池中最小生存的时间\n      minEvictableIdleTimeMillis: 300000\n      # 验证查询\n      validationQuery: select 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      # 是否缓存preparedStatement\n      poolPreparedStatements: true\n      maxPoolPreparedStatementPerConnectionSize: 20\n      # 配置监控统计拦截的filters\n      filters: stat,slf4j\n      # 通过connectProperties属性来打开mergeSql功能\n      connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    #typeAliasesPackage: com.cetuer.parking.admin\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml', 'f0dae9fb1f8939eaec217020dfd77ca3', '2022-01-27 14:33:22', '2022-01-27 14:33:22', NULL, '192.168.6.1', 'U', '');
INSERT INTO `his_config_info` VALUES (16, 112, 'gateway-admin-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        # 认证服务\n        - id: parking-auth\n          uri: lb://parking-auth\n          predicates:\n            - Path=/parking-auth/**\n          filters:\n            # 验证码处理\n            - CacheRequestFilter\n            - CaptchaFilter\n            - StripPrefix=1\n        # 系统服务\n        - id: parking-admin\n          uri: lb://parking-admin\n          predicates:\n            - Path=/parking-admin/**\n          filters:\n            - StripPrefix=1\n# 验证码\ncaptcha:\n  enabled: true\n  type: math\n# 白名单\nignore:\n  whites: /parking-auth/auth/logout,/parking-auth/auth/login,/*/v2/api-docs', 'cf78ec6c65489920684ac84e9eb71286', '2022-01-28 12:37:51', '2022-01-28 12:37:51', NULL, '192.168.6.1', 'U', '');
INSERT INTO `his_config_info` VALUES (16, 113, 'gateway-admin-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        # 认证服务\n        - id: parking-auth\n          uri: lb://parking-auth\n          predicates:\n            - Path=/parking-auth/**\n          filters:\n            # 验证码处理\n            - CacheRequestFilter\n            - CaptchaFilter\n            - StripPrefix=1\n        # 系统服务\n        - id: parking-admin\n          uri: lb://parking-admin\n          predicates:\n            - Path=/parking-admin/**\n          filters:\n            - StripPrefix=1\n# 验证码\ncaptcha:\n  enabled: false\n  type: math\n# 白名单\nignore:\n  whites: /parking-auth/auth/logout,/parking-auth/auth/login,/*/v2/api-docs', '5e31c8832ea455d012a577b80e228053', '2022-02-06 15:16:33', '2022-02-06 15:16:34', NULL, '192.168.6.1', 'U', '');
INSERT INTO `his_config_info` VALUES (16, 114, 'gateway-admin-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        # 认证服务\n        - id: parking-auth\n          uri: lb://parking-auth\n          predicates:\n            - Path=/parking-auth/**\n          filters:\n            # 验证码处理\n            - CacheRequestFilter\n            - CaptchaFilter\n            - StripPrefix=1\n        # 系统服务\n        - id: parking-admin\n          uri: lb://parking-admin\n          predicates:\n            - Path=/parking-admin/**\n          filters:\n            - StripPrefix=1\n# 验证码\ncaptcha:\n  enabled: true\n  type: math\n# 白名单\nignore:\n  whites: /parking-auth/auth/logout,/parking-auth/auth/login,/*/v2/api-docs', 'cf78ec6c65489920684ac84e9eb71286', '2022-02-06 15:17:10', '2022-02-06 15:17:11', NULL, '192.168.6.1', 'U', '');
INSERT INTO `his_config_info` VALUES (16, 115, 'gateway-admin-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        # 认证服务\n        - id: parking-auth\n          uri: lb://parking-auth\n          predicates:\n            - Path=/parking-auth/**\n          filters:\n            # 验证码处理\n            - CacheRequestFilter\n            - CaptchaFilter\n            - StripPrefix=1\n        # 系统服务\n        - id: parking-admin\n          uri: lb://parking-admin\n          predicates:\n            - Path=/parking-admin/**\n          filters:\n            - StripPrefix=1\n# 验证码\ncaptcha:\n  enabled: true\n  type: char\n# 白名单\nignore:\n  whites: /parking-auth/auth/logout,/parking-auth/auth/login,/*/v2/api-docs', '048e841d9f62da6c8c00c0e37be8a67c', '2022-02-06 15:17:24', '2022-02-06 15:17:25', NULL, '192.168.6.1', 'U', '');
INSERT INTO `his_config_info` VALUES (16, 116, 'gateway-admin-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        # 认证服务\n        - id: parking-auth\n          uri: lb://parking-auth\n          predicates:\n            - Path=/parking-auth/**\n          filters:\n            # 验证码处理\n            - CacheRequestFilter\n            - CaptchaFilter\n            - StripPrefix=1\n        # 系统服务\n        - id: parking-admin\n          uri: lb://parking-admin\n          predicates:\n            - Path=/parking-admin/**\n          filters:\n            - StripPrefix=1\n# 验证码\ncaptcha:\n  enabled: true\n  type: math\n# 白名单\nignore:\n  whites: /parking-auth/auth/logout,/parking-auth/auth/login,/*/v2/api-docs', 'cf78ec6c65489920684ac84e9eb71286', '2022-02-07 03:36:38', '2022-02-07 03:36:38', NULL, '192.168.6.1', 'U', '');
INSERT INTO `his_config_info` VALUES (0, 117, 'parking-file-dev.yml', 'DEFAULT_GROUP', '', '# Minio配置\r\nminio:\r\n  url: http://192.168.6.6:8888\r\n  username: root\r\n  password: Zqb..990826\r\n  bucketName: parking', 'f55f8acaa8ec12c725a6d4dee4d48fca', '2022-02-12 07:27:32', '2022-02-12 07:27:32', NULL, '192.168.6.1', 'I', '');
INSERT INTO `his_config_info` VALUES (84, 118, 'parking-file-dev.yml', 'DEFAULT_GROUP', '', '# Minio配置\r\nminio:\r\n  url: http://192.168.6.6:8888\r\n  username: root\r\n  password: Zqb..990826\r\n  bucketName: parking', 'f55f8acaa8ec12c725a6d4dee4d48fca', '2022-02-12 09:52:02', '2022-02-12 09:52:02', NULL, '192.168.6.1', 'U', '');
INSERT INTO `his_config_info` VALUES (84, 119, 'parking-file-dev.yml', 'DEFAULT_GROUP', '', '# Minio配置\nminio:\n  url: http://192.168.6.6:8888\n  username: root\n  password: Zqb..990826\n  bucketName: parking\nspring:\n  servlet:\n    multipart:\n      # 单个文件最大\n      max-file-size: 50MB\n      # 请求文件最大\n      max-request-size: 50MB', 'eb18e010eb211b856cd864569b723f13', '2022-02-12 09:52:32', '2022-02-12 09:52:33', NULL, '192.168.6.1', 'U', '');
INSERT INTO `his_config_info` VALUES (16, 120, 'gateway-admin-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  cloud:\n    gateway:\n      routes:\n        # 认证服务\n        - id: parking-auth\n          uri: lb://parking-auth\n          predicates:\n            - Path=/parking-auth/**\n          filters:\n            # 验证码处理\n            - CacheRequestFilter\n            - CaptchaFilter\n            - StripPrefix=1\n        # 系统服务\n        - id: parking-admin\n          uri: lb://parking-admin\n          predicates:\n            - Path=/parking-admin/**\n          filters:\n            - StripPrefix=1\n# 验证码\ncaptcha:\n  enabled: false\n  type: math\n# 白名单\nignore:\n  whites: /parking-auth/auth/logout,/parking-auth/auth/login,/*/v2/api-docs', '5e31c8832ea455d012a577b80e228053', '2022-02-12 13:16:02', '2022-02-12 13:16:02', NULL, '192.168.6.1', 'U', '');
INSERT INTO `his_config_info` VALUES (10, 121, 'sentinel-gateway', 'DEFAULT_GROUP', '', '[\n    {\n    \"resource\": \"parking-admin\",\n    \"count\": 500,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n    },\n    {\n    \"resource\": \"parking-auth\",\n    \"count\": 500,\n    \"grade\": 1,\n    \"limitApp\": \"default\",\n    \"strategy\": 0,\n    \"controlBehavior\": 0\n    }\n]', 'b7dde3e1cef81e5385054ef59a070d6a', '2022-02-18 15:34:55', '2022-02-18 15:34:55', NULL, '192.168.6.1', 'U', '');
INSERT INTO `his_config_info` VALUES (18, 122, 'common-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  redis:\n    host: 192.168.6.6\n    port: 6379\n    # 连接池最大阻塞等待时间\n    max-wait: 30000\n    # 连接池最大连接数\n    max-active: 100\n    # 连接池最大空闲连接\n    max-idle: 20\n    # 连接池最小空闲连接\n    min-idle: 0\n    # 连接超时\n    timeout: 3000\n  main:\n    allow-bean-definition-overriding: true\n  autoconfigure:\n    exclude: com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure\n# feign 配置\nfeign:\n  sentinel:\n    enabled: true\n  okhttp:\n    enabled: true\n  httpclient:\n    enabled: false\n  client:\n    config:\n      default:\n        connectTimeout: 10000\n        readTimeout: 10000\n  compression:\n    request:\n      enabled: true\n    response:\n      enabled: true\n# 暴露监控端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'', '39288ec1f4e28684d9837d52592f41aa', '2022-02-18 15:36:08', '2022-02-18 15:36:08', NULL, '192.168.6.1', 'U', '');
INSERT INTO `his_config_info` VALUES (70, 123, 'parking-admin-dev.yml', 'DEFAULT_GROUP', '', 'spring:\n  web:\n    resources:\n      # 不要为我们工程中的资源文件建立映射\n      add-mappings: false\n  mvc:\n    # 出现错误时, 直接抛出异常\n    throw-exception-if-no-handler-found: true\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://192.168.6.6:3306/smart-parking-lot?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai\n    username: root\n    password: root\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      # 初始化大小\n      initial-size: 5\n      # 最少数量\n      min-idle: 5\n      # 最大数量\n      max-active: 20\n      # 超时时长\n      max-wait: 60000\n      # 间隔多久才进行一次检测\n      timeBetweenEvictionRunsMillis: 60000\n      # 一个连接在池中最小生存的时间\n      minEvictableIdleTimeMillis: 300000\n      # 验证查询\n      validationQuery: select 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      # 是否缓存preparedStatement\n      poolPreparedStatements: true\n      maxPoolPreparedStatementPerConnectionSize: 20\n      # 配置监控统计拦截的filters\n      filters: stat,slf4j\n      # 通过connectProperties属性来打开mergeSql功能\n      connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    #typeAliasesPackage: com.cetuer.parking.admin\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml\n    configuration:\n      log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n      map-underscore-to-camel-case: true', '0e339d8488c46b0f7cba934325392c23', '2022-02-18 15:36:38', '2022-02-18 15:36:39', NULL, '192.168.6.1', 'U', '');
INSERT INTO `his_config_info` VALUES (84, 124, 'parking-file-dev.yml', 'DEFAULT_GROUP', '', '# Minio配置\nminio:\n  url: http://192.168.6.6:8888\n  username: root\n  password: Zqb..990826\n  bucketName: parking', 'd0d093f0ffa97dde387c6f66c76d2699', '2022-02-18 15:36:59', '2022-02-18 15:37:00', NULL, '192.168.6.1', 'U', '');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `role` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `resource` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `action` varchar(8) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  UNIQUE INDEX `uk_role_permission`(`role`, `resource`, `action`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permissions
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `role` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  UNIQUE INDEX `idx_user_role`(`username`, `role`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp`, `tenant_id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_info
-- ----------------------------
INSERT INTO `tenant_info` VALUES (1, '1', 'f69a4454-26af-43e2-88fb-627343f63079', 'ruoyi', 'ruoyi', 'nacos', 1639279664331, 1639279664331);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `password` varchar(500) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

SET FOREIGN_KEY_CHECKS = 1;
