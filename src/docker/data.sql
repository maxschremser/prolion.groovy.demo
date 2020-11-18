DROP TABLE IF EXISTS file_walker;

CREATE TABLE file_walker (
  id SERIAL PRIMARY KEY,
  path VARCHAR(250) NOT NULL,
  directory BOOLEAN,
  name VARCHAR(250) NOT NULL,
  type VARCHAR(20) DEFAULT NULL,
  size INTEGER DEFAULT 0,
  modified DATE,
  scanned DATE
);

CREATE SEQUENCE hibernate_sequence START 1;