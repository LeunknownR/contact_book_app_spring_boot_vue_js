USE mysql;

DROP DATABASE IF EXISTS db_contact_book;
CREATE DATABASE db_contact_book;
-- Category
CREATE TABLE contact_category(
    id CHAR(3) NOT NULL,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
)
    DEFAULT CHARSET = utf8;
-- Contact
CREATE TABLE contact(
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(30),
    is_favorite BIT,
    contact_category_id CHAR(3),
    PRIMARY KEY (id),
    FOREIGN KEY (contact_category_id) REFERENCES contact_category(id)
)
    DEFAULT CHARSET = utf8;
-- Phone
CREATE TABLE contact_phone(
    id INT AUTO_INCREMENT NOT NULL,
    number CHAR(9) NOT NULL,
    contact_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (contact_id) REFERENCES contact(id)
)
    DEFAULT CHARSET = utf8;