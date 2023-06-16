CREATE TABLE category
(
    id   BIGSERIAL NOT NULL,
    category_name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE product
(
    id          BIGSERIAL NOT NULL,
    cost        INT4,
    product_name        VARCHAR(255),
    quantity    INT4,
    category_id INT8,
    PRIMARY KEY (id)
);

CREATE TABLE characteristic
(
    id   BIGSERIAL NOT NULL,
    characteristic_name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE link_product_characteristic
(
    id                BIGSERIAL NOT NULL,
    product_id        INT8      NOT NULL,
    characteristic_id INT8      NOT NULL,
    PRIMARY KEY (id)
);


ALTER TABLE IF EXISTS product
    ADD CONSTRAINT fk_product_to_category
    FOREIGN KEY (category_id) REFERENCES category (id);


INSERT INTO category(id, category_name)
VALUES (1, 'computers'),
       (2, 'food'),
       (3, 'sport');

INSERT INTO product(id, product_name, cost, quantity, category_id)
VALUES (1, 'Lenovo', 1000, 12, 1),
       (2, 'Acer', 1200, 8, 1),
       (3, 'Burger', 3, 135, 2),
       (4, 'Pizza', 5, 29, 2),
       (5, 'Ball', 32, 234, 3),
       (6, 'Kettlebell', 48, 28, 3);

INSERT INTO characteristic(id, characteristic_name)
VALUES (1, 'long'),
       (2, 'red'),
       (3, 'short'),
       (4, 'green'),
       (5, 'blue'),
       (6, 'light');

INSERT INTO link_product_characteristic(id, product_id, characteristic_id)
VALUES (1, 1, 3),
       (2, 1, 4),
       (3, 2, 1),
       (4, 2, 4),
       (5, 3, 2),
       (6, 3, 6),
       (7, 4, 3),
       (8, 4, 5),
       (9, 5, 1),
       (10, 5, 2),
       (11, 6, 5),
       (12, 6, 6);


