DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS portfolios;
DROP TABLE IF EXISTS contexts;

CREATE TABLE users
(
  id                INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
  mail              VARCHAR UNIQUE                     NOT NULL,
  display_name      VARCHAR UNIQUE                     NOT NULL,
  password          VARCHAR                            NOT NULL,
  registration_time TIMESTAMP DEFAULT now(),
);

CREATE TABLE portfolios
(
  id           INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
  user_id      INTEGER                            NOT NULL,
  display_name VARCHAR                            NOT NULL,
  description  VARCHAR,

  CONSTRAINT user_id_portfolio_display_name UNIQUE (user_id, display_name),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE contexts
(
  id           INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
  user_id      INTEGER                            NOT NULL,
  display_name VARCHAR                            NOT NULL,
  description  VARCHAR,

  CONSTRAINT user_id_context_display_name UNIQUE (user_id, display_name),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
);