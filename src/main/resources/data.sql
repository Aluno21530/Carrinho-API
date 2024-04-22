-- Drop existing tables if they exist
DROP TABLE IF EXISTS order_cart_itens CASCADE;
DROP TABLE IF EXISTS order_cart CASCADE;
DROP TABLE IF EXISTS iten CASCADE;
DROP TABLE IF EXISTS product CASCADE;
DROP TABLE IF EXISTS restaurant_menu CASCADE;
DROP TABLE IF EXISTS restaurant CASCADE;
DROP TABLE IF EXISTS client CASCADE;

-- Drop existing sequences if they exist
DROP SEQUENCE IF EXISTS order_cart_seq;
DROP SEQUENCE IF EXISTS iten_seq;
DROP SEQUENCE IF EXISTS client_seq;
DROP SEQUENCE IF EXISTS product_seq;
DROP SEQUENCE IF EXISTS restaurant_seq;

-- Create sequences
CREATE SEQUENCE client_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE iten_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE order_cart_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE product_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE restaurant_seq START WITH 1 INCREMENT BY 50;

-- Create tables
CREATE TABLE client (
                        id BIGINT NOT NULL,
                        complement VARCHAR(255),
                        name VARCHAR(255),
                        zip_code VARCHAR(255),
                        PRIMARY KEY (id)
);

CREATE TABLE restaurant (
                            id BIGINT NOT NULL,
                            complement VARCHAR(255),
                            name VARCHAR(255),
                            zip_code VARCHAR(255),
                            PRIMARY KEY (id)
);

CREATE TABLE product (
                         id BIGINT NOT NULL,
                         available BOOLEAN,
                         restaurant_id BIGINT,
                         name VARCHAR(255),
                         unit_value VARCHAR(255),
                         PRIMARY KEY (id),
                         FOREIGN KEY (restaurant_id) REFERENCES restaurant
);

CREATE TABLE order_cart (
                            id BIGINT NOT NULL,
                            closed BOOLEAN NOT NULL,
                            payment_method TINYINT CHECK (payment_method BETWEEN 0 AND 1),
                            total_amount FLOAT(53),
                            client_id BIGINT NOT NULL,
                            PRIMARY KEY (id),
                            FOREIGN KEY (client_id) REFERENCES client
);

CREATE TABLE iten (
                      id BIGINT NOT NULL,
                      quantity INTEGER NOT NULL,
                      order_cart_id BIGINT,
                      product_id BIGINT,
                      PRIMARY KEY (id),
                      FOREIGN KEY (order_cart_id) REFERENCES order_cart,
                      FOREIGN KEY (product_id) REFERENCES product
);


CREATE TABLE restaurant_menu (
                                 restaurant_id BIGINT NOT NULL,
                                 menu_id BIGINT NOT NULL UNIQUE,
                                 FOREIGN KEY (restaurant_id) REFERENCES restaurant,
                                 FOREIGN KEY (menu_id) REFERENCES product
);

CREATE TABLE order_cart_itens (
                                  order_cart_id BIGINT NOT NULL,
                                  itens_id BIGINT NOT NULL UNIQUE,
                                  FOREIGN KEY (order_cart_id) REFERENCES order_cart,
                                  FOREIGN KEY (itens_id) REFERENCES iten
);

INSERT INTO restaurant (id, zip_code, complement, name) VALUES
                                                            (1, '12345', 'Complement 1', 'Restaurant A'),
                                                            (2, '54321', 'Complement 2', 'Restaurant B');

INSERT INTO client (id, zip_code, complement, name) VALUES
                                                        (1, '54321', 'Complement 3', 'Client A'),
                                                        (2, '12345', 'Complement 4', 'Client B');
INSERT INTO product (id, available, name, unit_value, restaurant_id) VALUES
                                                                        (1, true, 'Product A', 10.5, 1),
                                                                        (2, true, 'Product B', 15.75, 1),
                                                                        (3, true, 'Product C', 20.0, 2);
INSERT INTO order_cart (id, payment_method, closed, total_amount, client_id) VALUES
                                                                               (1, 0, false, 50.25, 1),
                                                                               (2, 1, false, 0.0, 2);