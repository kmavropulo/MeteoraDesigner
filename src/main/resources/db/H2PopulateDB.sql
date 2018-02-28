DELETE FROM users;
DELETE FROM contexts;
DELETE FROM portfolios;
DELETE FROM tasks;

//ALTER SEQUENCE global_seq RESTART WITH 1000;

INSERT INTO users (email, display_name, password) VALUES
  ('konstantin.n.mavropulo@gmail.com', 'silicicArcher', '123');

INSERT INTO contexts (user_id, display_name, description) VALUES
  (0, 'meteoraProjects',
   'Connected with tools enhancing life focus, creativity and productivity to maximize efficiency of a human bean.');

INSERT INTO portfolios (user_id, display_name, description) VALUES
  (0, 'meteoraProjects',
   'Contain tasks to create the tools enhancing life focus, creativity and productivity to maximize efficiency of a human bean.');

INSERT INTO tasks (user_id, display_name, planned_start_task_timestamp, planned_stop_task_timestamp)
VALUES
  (0, 'first test task', '2018-02-23 21:18:33.364', '2018-02-29 00:00:00');
