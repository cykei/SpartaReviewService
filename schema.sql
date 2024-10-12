CREATE TABLE IF NOT EXISTS `Product` (
    `id`          BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `reviewCount` BIGINT(20) NOT NULL,
    `score`       FLOAT  NOT NULL
    ) ENGINE = InnoDB CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `review` (
    `id`           BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `product_id`   BIGINT(20) NOT NULL,
    `user_id`      BIGINT(20) NOT NULL,
    `score`        int,
    `content`      varchar(300),
    `image_url`    varchar(255),
    `created_at`   datetime
    ) ENGINE = InnoDB CHARSET = utf8;

create unique index UX_userId_productId on review (user_id, product_id);