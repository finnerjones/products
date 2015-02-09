# ----  !Ups

CREATE TABLE products (
   id BIGINT,
   ean BIGINT,
   name VARCHAR(255),
   description VARCHAR(255));

CREATE TABLE warehouses (
   id BIGINT,
   name VARCHAR(255));


CREATE TABLE stock_items (
   id BIGINT,
   product_id BIGINT,
   warehouse_id BIGINT,
   quantity BIGINT);


# ----  !Downs

DROP TABLE IF EXISTS products;

DROP TABLE IF EXISTS warehouses;

DROP TABLE IF EXISTS stock_items;



