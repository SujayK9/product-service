CREATE TABLE category
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      datetime NULL,
    last_updated_at datetime NULL,
    is_deleted      BIT(1)       NOT NULL,
    name            VARCHAR(255) NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE product
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      datetime NULL,
    last_updated_at datetime NULL,
    is_deleted      BIT(1)       NOT NULL,
    title           VARCHAR(255) NOT NULL,
    price           DECIMAL      NOT NULL,
    `description`   VARCHAR(1000) NULL,
    image           VARCHAR(255) NOT NULL,
    category_id     BIGINT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE token
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      datetime NULL,
    last_updated_at datetime NULL,
    is_deleted      BIT(1) NOT NULL,
    value           VARCHAR(255) NULL,
    user_id         BIGINT NULL,
    expiry_date     datetime NULL,
    CONSTRAINT pk_token PRIMARY KEY (id)
);

CREATE TABLE user
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      datetime NULL,
    last_updated_at datetime NULL,
    is_deleted      BIT(1) NOT NULL,
    user_id         BIGINT NULL,
    email           VARCHAR(255) NULL,
    hashed_password VARCHAR(255) NULL,
    fullname        VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE token
    ADD CONSTRAINT FK_TOKEN_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);