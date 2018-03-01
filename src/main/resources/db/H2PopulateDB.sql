DELETE FROM users;
DELETE FROM contexts;
DELETE FROM portfolios;
DELETE FROM tasks;
DELETE FROM users_roles;
DELETE FROM external_contexts_internal_contexts;
DELETE FROM tasks_contexts;
DELETE FROM tasks_pointed_completion_states;
DELETE FROM external_tasks_internal_tasks;
DELETE FROM tasks_blocked_by_the_task_tasks_blocking_the_task;

INSERT INTO users (email, display_name, password, role) VALUES
  ('konstantin.n.mavropulo@gmail.com', 'silicicArcher', '123'),
  ('konstantin.n.mavropulo@gmail.com1', 'silicicArcher1', '123'),
  ('konstantin.n.mavropulo@gmail.com2', 'silicicArcher2', '123'),
  ('konstantin.n.mavropulo@gmail.com3', 'silicicArcher3', '123');

INSERT INTO contexts (user_id, display_name, description) VALUES
  (1, 'meteoraProjects',
   'Connected with tools enhancing life focus, creativity and productivity to maximize ' ||
   'efficiency of a human bean.');

INSERT INTO portfolios (user_id, display_name, description) VALUES
  (1, 'meteoraProjects',
   'Contain tasks to create the tools enhancing life focus, creativity and productivity to ' ||
   'maximize efficiency of a human bean.');

INSERT INTO tasks (user_id, display_name, planned_start_task_timestamp, planned_stop_task_timestamp)
VALUES
  (1, 'first test task', '2018-02-23 21:18:33.064', '2018-03-01 21:18:33.064');

INSERT INTO users_roles (user_id, role)
VALUES
  (1, 'ROLE_ADMIN');
