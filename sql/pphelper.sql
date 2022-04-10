SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- 角色表
-- ----------------------------
DROP TABLE IF EXISTS `store_role`;
CREATE TABLE `store_role` (
    `id` char(19) NOT NULL COMMENT '角色id',
    `author_id` char(19) NOT NULL DEFAULT '' COMMENT '授权人id',
    `role_name` varchar(20) NOT NULL DEFAULT '' COMMENT '角色名称',
    `role_code` varchar(20) DEFAULT '' COMMENT '角色编码',
    `remark` varchar(255) DEFAULT '' COMMENT '备注',
    `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
    `gmt_create` datetime NOT NULL COMMENT '创建时间',
    `gmt_modified` datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_author_id` (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';


-- ----------------------------
-- 用户表
-- ----------------------------
DROP TABLE IF EXISTS `store_user`;
CREATE TABLE `store_user` (
    `id` char(19) NOT NULL COMMENT '用户id',
    `username` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
    `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
    `sex` tinyint(2) unsigned DEFAULT '0' COMMENT '性别 1 女，0 男',
    `avatar` varchar(255) DEFAULT '' COMMENT '用户头像',
    `phone` varchar(255) DEFAULT '' COMMENT '电话',
    `email` varchar(255) DEFAULT '' COMMENT '邮箱',
    `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
    `gmt_create` datetime NOT NULL COMMENT '创建时间',
    `gmt_modified` datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- 用户-角色表
-- ----------------------------
DROP TABLE IF EXISTS `store_user_role`;
CREATE TABLE `store_user_role` (
    `id` char(19) NOT NULL COMMENT '主键id',
    `role_id` char(19) NOT NULL DEFAULT '' COMMENT '角色id',
    `user_id` char(19) NOT NULL DEFAULT '' COMMENT '用户id',
    `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
    `gmt_create` datetime NOT NULL COMMENT '创建时间',
    `gmt_modified` datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_role_id` (`role_id`),
    KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';


-- ----------------------------
-- 会员表
-- ----------------------------
DROP TABLE IF EXISTS `store_member`;
CREATE TABLE `store_member` (
  `id` char(19) NOT NULL COMMENT '会员id',
  `username` varchar(20) NOT NULL DEFAULT '' COMMENT '微信openid',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `nick_name` varchar(50) DEFAULT '' COMMENT '昵称',
  `sex` tinyint(2) unsigned DEFAULT '0' COMMENT '性别 1 女，0 男',
  `age` tinyint(3) unsigned DEFAULT '0' COMMENT '年龄',
  `avatar` varchar(255) DEFAULT '' COMMENT '用户头像',
  `phone` varchar(255) DEFAULT '' COMMENT '电话',
  `email` varchar(255) DEFAULT '' COMMENT '邮箱',
  `token` varchar(100) DEFAULT '' COMMENT '用户签名',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员表';

-- ----------------------------
-- 会员-商品表
-- ----------------------------
DROP TABLE IF EXISTS `store_member_product`;
CREATE TABLE `store_member_product` (
  `id` char(19) NOT NULL COMMENT 'id',
  `member_id` char(19) NOT NULL DEFAULT '' COMMENT '会员id',
  `product_id` char(19) NOT NULL DEFAULT '' COMMENT '商品id',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_member_id` (`member_id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员商品表';


-- ----------------------------
-- 商品表
-- ----------------------------
DROP TABLE IF EXISTS `store_product`;
CREATE TABLE `store_product` (
    `id` char(19) NOT NULL COMMENT '商品ID',
    `member_id` char(19) NOT NULL DEFAULT '' COMMENT '会员ID',
    `sort_id` char(19) NOT NULL DEFAULT '' COMMENT '商品分类ID',
    `product_name` varchar(50) NOT NULL COMMENT '商品名称',
    `product_price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '商品价格',
    `product_desc` varchar(255) DEFAULT '' COMMENT '商品描述',
    `product_detail` text COMMENT '商品细节',
    `version` bigint(20) unsigned NOT NULL DEFAULT '1' COMMENT '乐观锁',
    `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '商品状态 0下架  1在售 2待审核',
    `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
    `gmt_create` datetime NOT NULL COMMENT '创建时间',
    `gmt_modified` datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_product_name` (`product_name`),
    KEY `idx_member_id` (`member_id`),
    KEY `idx_sort_id` (`sort_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='商品表';

-- ----------------------------
-- 商品状态表
-- ----------------------------
DROP TABLE IF EXISTS `store_status`;
CREATE TABLE `store_status` (
    `id` char(19) NOT NULL COMMENT 'ID',
    `product_id` char(19) NOT NULL DEFAULT '' COMMENT '商品ID',
    `product_count` bigint(10) unsigned NOT NULL DEFAULT '0' COMMENT '商品数量',
    `buy_count` bigint(10) unsigned NOT NULL DEFAULT '0' COMMENT '销售数量',
    `view_count` bigint(10) unsigned DEFAULT '0' COMMENT '浏览数量',
    `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
    `gmt_create` datetime NOT NULL COMMENT '创建时间',
    `gmt_modified` datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品状态';

-- ----------------------------
-- 商品图片列表
-- ----------------------------
DROP TABLE IF EXISTS `store_product_image`;
CREATE TABLE `store_product_image` (
    `id` char(19) NOT NULL COMMENT 'ID',
    `product_id` char(19) NOT NULL DEFAULT '' COMMENT '商品ID',
    `image_name` varchar(50) DEFAULT '' COMMENT '图片名称',
    `image_url` varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '商品图片地址',
    `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
    `gmt_create` datetime NOT NULL COMMENT '创建时间',
    `gmt_modified` datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品图片表';

-- ----------------------------
-- 商品分类
-- ----------------------------
DROP TABLE IF EXISTS `store_category`;
CREATE TABLE `store_category` (
    `id` char(19) NOT NULL COMMENT '商品类别ID',
    `title` varchar(255) NOT NULL COMMENT '类别名称',
    `icon` varchar(255) DEFAULT '' COMMENT '图标名称',
    `parent_id` char(19) DEFAULT '0' COMMENT '父ID',
    `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
    `gmt_create` datetime NOT NULL COMMENT '创建时间',
    `gmt_modified` datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品分类';


-- ----------------------------
-- 订单表
-- ----------------------------
DROP TABLE IF EXISTS `store_order`;
CREATE TABLE `store_order` (
    `id` char(19) NOT NULL COMMENT '订单id',
    `order_no` varchar(20) NOT NULL DEFAULT '' COMMENT '订单号',
    `product_id` varchar(19) NOT NULL DEFAULT '' COMMENT '商品id',
    `product_title` varchar(100) NOT NULL DEFAULT '' COMMENT '商品名称',
    `product_cover` varchar(255) DEFAULT '' COMMENT '商品封面',
    `seller_id` char(19) NOT NULL DEFAULT '' COMMENT '卖家id',
    `seller_name` varchar(20) NOT NULL DEFAULT '' COMMENT '卖家名称',
    `member_id` varchar(19) NOT NULL DEFAULT '' COMMENT '会员id',
    `nick_name` varchar(50) NOT NULL DEFAULT '' COMMENT '会员昵称',
    `phone` varchar(11) DEFAULT '' COMMENT '号码',
    `total_fee` decimal(10,2) DEFAULT '0.01' COMMENT '订单金额（分）',
    `pay_type` tinyint(3) DEFAULT NULL COMMENT '支付类型（1：微信 2：支付宝）',
    `status` tinyint(3) DEFAULT NULL COMMENT '订单状态（0：未支付 1：已支付）',
    `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
    `gmt_create` datetime NOT NULL COMMENT '创建时间',
    `gmt_modified` datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_order_no` (`order_no`),
    KEY `idx_product_id` (`product_id`),
    KEY `idx_seller_id` (`seller_id`),
    KEY `idx_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- 支付日志
-- ----------------------------
DROP TABLE IF EXISTS `store_pay_log`;
CREATE TABLE `store_pay_log` (
    `id` char(19) NOT NULL DEFAULT '',
    `order_no` varchar(20) NOT NULL DEFAULT '' COMMENT '订单号',
    `pay_time` datetime NOT NULL COMMENT '支付完成时间',
    `total_fee` decimal(10,2) DEFAULT '0.01' COMMENT '支付金额（分）',
    `transaction_id` varchar(30) DEFAULT '' COMMENT '交易流水号',
    `trade_state` char(20) DEFAULT '' COMMENT '交易状态',
    `pay_type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付类型（1：微信 2：支付宝）',
    `attr` text COMMENT '其他属性',
    `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
    `gmt_create` datetime NOT NULL COMMENT '创建时间',
    `gmt_modified` datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_order_no` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付日志表';


-- ----------------------------
-- 聊天主表
-- ----------------------------
DROP TABLE IF EXISTS `store_chart_master`;
CREATE TABLE `store_chart_master` (
    `id` char(19) NOT NULL COMMENT '主键',
    `member_id` char(19) NOT NULL DEFAULT '' COMMENT '用户id',
    `another_id` char(19) NOT NULL DEFAULT '' COMMENT '对方用户id',
    `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
    `gmt_create` datetime NOT NULL COMMENT '创建时间',
    `gmt_modified` datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idex_member_id` (`member_id`),
    KEY `idex_another_id` (`another_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='聊天主表';

-- ----------------------------
-- 聊天列表表
-- ----------------------------
DROP TABLE IF EXISTS `store_chart_list`;
CREATE TABLE `store_chart_list` (
    `id` char(19) NOT NULL COMMENT '主键',
    `chart_id` char(19) NOT NULL DEFAULT '' COMMENT '聊天主表id',
    `member_id` char(19) NOT NULL DEFAULT '' COMMENT '用户id',
    `another_id` char(19) NOT NULL DEFAULT '' COMMENT '对方用户id',
    `is_online` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否在线 1:在线 0：离线',
    `unread` bigint(10) unsigned NOT NULL DEFAULT '0' COMMENT '消息未读数',
    `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
    `gmt_create` datetime NOT NULL COMMENT '创建时间',
    `gmt_modified` datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idex_chart_id` (`chart_id`),
    KEY `idex_member_id` (`member_id`),
    KEY `idex_another_id` (`another_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='聊天列表表';

-- ----------------------------
-- 聊天详情表
-- ----------------------------
DROP TABLE IF EXISTS `store_chart_detail`;
CREATE TABLE `store_chart_detail` (
    `id` char(19) NOT NULL COMMENT '主键',
    `chart_id` char(19) NOT NULL DEFAULT '' COMMENT '聊天主表id',
    `member_id` char(19) NOT NULL DEFAULT '' COMMENT '用户id',
    `content` varchar(255) NOT NULL DEFAULT '' COMMENT '聊天内容',
    `time` datetime NOT NULL COMMENT '发送时间',
    `type` tinyint(5) unsigned NOT NULL DEFAULT '0' COMMENT '消息类型 0:普通消息， 1：音频， 2：媒体文件',
    `is_latest` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否是最后一条消息 1:是 0：不是',
    `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
    `gmt_create` datetime NOT NULL COMMENT '创建时间',
    `gmt_modified` datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idex_chart_id` (`chart_id`),
    KEY `idex_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='聊天详情表';