-- Create database
CREATE DATABASE `tasks_local`;

-- Table tasks
CREATE TABLE `tasks_local`.`tasks` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `description` VARCHAR(100) NOT NULL,
    `eventdate` DATE NOT NULL,
    `eventtime` TIME NOT NULL,
    `username` VARCHAR(100) NOT NULL,
    `revised` BOOLEAN NOT NULL,
    `active` BOOLEAN NOT NULL,
    `creationtimestamp` TIMESTAMP NOT NULL,
    `creationuser` VARCHAR(100) NOT NULL,
    `modificationtimestamp` TIMESTAMP NOT NULL,
    `modificationuser` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `tasks_local`.`tasks`(id, description, eventdate, eventtime, username, revised, active, creationtimestamp, creationuser, modificationtimestamp, modificationuser)
VALUES
    (DEFAULT, 'Tasks Prueba 1', CURRENT_DATE(), '22:00:00', 'usuariotest@gmail.com', false, true, CURRENT_TIMESTAMP(), 'usuariotest@gmail.com', CURRENT_TIMESTAMP(), 'admin');

INSERT INTO `tasks_local`.`tasks`(id, description, eventdate, eventtime, username, revised, active, creationtimestamp, creationuser, modificationtimestamp, modificationuser)
VALUES
    (DEFAULT, 'Tasks Prueba 2', CURRENT_DATE(), '22:00:00', 'usuariotest@gmail.com', true, true, CURRENT_TIMESTAMP(), 'usuariotest@gmail.com', CURRENT_TIMESTAMP(), 'admin');

INSERT INTO `tasks_local`.`tasks`(id, description, eventdate, eventtime, username, revised, active, creationtimestamp, creationuser, modificationtimestamp, modificationuser)
VALUES
    (DEFAULT, 'Tasks Prueba 3', CURRENT_DATE(), '22:00:00', 'usuariotest@gmail.com', false, true, CURRENT_TIMESTAMP(), 'usuariotest@gmail.com', CURRENT_TIMESTAMP(), 'admin');

INSERT INTO `tasks_local`.`tasks`(id, description, eventdate, eventtime, username, revised, active, creationtimestamp, creationuser, modificationtimestamp, modificationuser)
VALUES
    (DEFAULT, 'Tasks Prueba 4', CURRENT_DATE(), '22:00:00', 'usuario@gmail.com', false, true, CURRENT_TIMESTAMP(), 'usuario@gmail.com', CURRENT_TIMESTAMP(), 'admin');

