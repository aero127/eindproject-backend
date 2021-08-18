INSERT INTO users (username, password, enabled, email, first_name, last_name) VALUES ('Admin', '$2a$10$q/rYVLnKiyHpoZv8jD3ce.SB4hbyf0oVOYUZYJyWoT39F/4oGkhta', TRUE, 'admin@mtbrental.com', 'Arnold', 'van Dijk');
INSERT INTO users (username, password, enabled, email, first_name, last_name) VALUES ('Novi', '$2a$10$q/rYVLnKiyHpoZv8jD3ce.SB4hbyf0oVOYUZYJyWoT39F/4oGkhta', TRUE, 'novi@hogeschoolnovi.nl', 'Nova', 'Eeken');
INSERT INTO users (username, password, enabled, email, first_name, last_name) VALUES ('User', '$2a$10$q/rYVLnKiyHpoZv8jD3ce.SB4hbyf0oVOYUZYJyWoT39F/4oGkhta', TRUE, 'user@mtbrental.com', 'Jeroen', 'vd Boom');

INSERT INTO authorities (username, authority) VALUES ('Novi', 'USER');
INSERT INTO authorities (username, authority) VALUES ('User', 'USER');
INSERT INTO authorities (username, authority) VALUES ('Admin', 'ADMIN');

INSERT INTO bike (bike_name, quantity_total, price_per_day) VALUES ('MTB_26_inch', '250', 35);