INSERT INTO `usuarios` (username,password,enabled) VALUES('jose','$2a$10$Jf1B1DvYy3spSruEe8kf4OXx1jeyPaOgTHPgXiUaUQQ/s/O.PWhbu',1);
INSERT INTO `usuarios` (username,password,enabled) VALUES('admin','$2a$10$8t2e9DEl.ZSajFHzwu/JKexkpmgoIpH6JQsK.rWlseVjAxCCzuf/K',1);

INSERT INTO `roles` (nombre) VALUES('ROLE_USER');
INSERT INTO `roles` (nombre) VALUES('ROLE_ADMIN');

INSERT INTO `usuarios_roles` (usuario_id,role_id) VALUES(1,1);
INSERT INTO `usuarios_roles` (usuario_id,role_id) VALUES(2,2);
INSERT INTO `usuarios_roles` (usuario_id,role_id) VALUES(2,1);

INSERT INTO usuario (username,password,email) VALUES ('User','Contraseña','@example.ex');
INSERT INTO usuario (username,password,email) VALUES ('Ana19r','Password','@example.ex');
INSERT INTO usuario (username,password,email) VALUES ('Ppe','Codigo','@example.ex');
INSERT INTO usuario (username,password,email) VALUES ('12_jos_12','Pass','@example.ex');
INSERT INTO usuario (username,password,email) VALUES ('12lnv','Encriptado','@example.ex');
INSERT INTO usuario (username,password,email) VALUES ('diosjfs9','Enigma','@example.ex');
INSERT INTO usuario (username,password,email) VALUES ('ifo23jrie','Intrrogante','@example.ex');
INSERT INTO password (cuenta,pass,usuario_id) VALUES ('Una Cuenta', 'Una contraseña',1);
INSERT INTO password (cuenta,pass,usuario_id) VALUES ('Segunda Cuenta', 'Segunda contraseña',1);
INSERT INTO password (cuenta,pass,usuario_id) VALUES ('Otra Cuenta', 'Otra contraseña',2);