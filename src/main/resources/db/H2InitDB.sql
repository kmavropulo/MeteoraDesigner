DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS portfolios;
DROP TABLE IF EXISTS contexts;
DROP TABLE IF EXISTS tasks;

CREATE TABLE users
(
  id                INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
  email             VARCHAR UNIQUE                     NOT NULL,
  display_name      VARCHAR UNIQUE                     NOT NULL,
  password          VARCHAR                            NOT NULL,
  registration_time TIMESTAMP DEFAULT now()            NOT NULL,
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

CREATE TABLE tasks
(
  id                           INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
  user_id                      INTEGER                            NOT NULL,
  display_name                 VARCHAR                            NOT NULL,
  description                  VARCHAR,
  planned_start_task_timestamp TIMESTAMP                          NOT NULL,
  planned_stop_task_timestamp  TIMESTAMP                          NOT NULL,
  actual_start_task_timestamp  TIMESTAMP,
  actual_stop_task_timestamp   TIMESTAMP,
  portfolio_id                 INTEGER,

  CONSTRAINT user_id_task_display_name UNIQUE (user_id, display_name),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (portfolio_id) REFERENCES portfolios (id) ON DELETE CASCADE ON UPDATE CASCADE
);