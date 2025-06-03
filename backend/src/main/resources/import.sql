INSERT INTO tb_funcao (authority) VALUES ('ROLE_ADMIN');
INSERT INTO tb_funcao (authority) VALUES ('ROLE_ENTERPRISE');
INSERT INTO tb_funcao (authority) VALUES ('ROLE_USER');

INSERT INTO tb_usuario (nome, email, senha, cpf, logradouro, numero, complemento, bairro, cidade, estado, cep) VALUES ('Usuário Administrador', 'admin@admin.com', '$2b$12$BHXovfbzgeGnGrTMwiR7teSGs1z0UrQ0AO6mBucYZ4vWpbs4wWFF6', '12345678912', 'Rua Exemplo', '123', 'Complemento teste', 'Bairro Exemplo', 'Cidade Exemplo', 'EX', '00000000');
INSERT INTO tb_usuario (nome, email, senha, cpf, logradouro, numero, complemento, bairro, cidade, estado, cep) VALUES ('Erick Berger', 'erick@gmail.com', '$2b$12$DqykjZBA29fhWIAwLqpTOeX9xqt.GstmBlK/qcbJDwRp5rla19VyW', '32165498787', 'Rua das Flores', '321', 'Casa', 'Bairro das Rosas', 'Florianópolis', 'SC', '11111111');
INSERT INTO tb_usuario (nome, email, senha, cpf, logradouro, numero, complemento, bairro, cidade, estado, cep) VALUES ('João da Silva', 'joao@gmail.com', '$2b$12$4qhIiuwAmZcUOWY1CM8BQe6//mH4kxLeBbJ0tIQpzX3A8zKOYCTUC', '65432198778', 'Rua Jorge Souza', '416', 'Sítio', 'Canto da Vila', 'Chapecó', 'SC', '22222222');
INSERT INTO tb_usuario (nome, email, senha, cpf, logradouro, numero, complemento, bairro, cidade, estado, cep) VALUES ('Maria Fernanda Alves', 'maria@gmail.com', '$2b$12$4qhIiuwAmZcUOWY1CM8BQe6//mH4kxLeBbJ0tIQpzX3A8zKOYCTUC', '12345678900', 'Av. das Palmeiras', '102', 'Ap 302', 'Centro', 'Florianópolis', 'SC', '88000000');
INSERT INTO tb_usuario (nome, email, senha, cpf, logradouro, numero, complemento, bairro, cidade, estado, cep) VALUES ('Carlos Eduardo Mendes', 'carlos@gmail.com', '$2b$12$4qhIiuwAmZcUOWY1CM8BQe6//mH4kxLeBbJ0tIQpzX3A8zKOYCTUC', '98765432100', 'Rua das Hortênsias', '230', 'Casa', 'Jardim América', 'Joinville', 'SC', '89200000');
INSERT INTO tb_usuario (nome, email, senha, cpf, logradouro, numero, complemento, bairro, cidade, estado, cep) VALUES ('Ana Luiza Rocha', 'ana@gmail.com', '$2b$12$4qhIiuwAmZcUOWY1CM8BQe6//mH4kxLeBbJ0tIQpzX3A8zKOYCTUC', '32178965400', 'Travessa das Oliveiras', '77', 'Casa 5', 'Vila Nova', 'Blumenau', 'SC', '89000000');

INSERT INTO tb_empresa (razao_social, nome_fantasia, cnpj, codigo_ativacao, usuario_id) VALUES ('Tech Eventos e Soluções Digitais LTDA', 'TechCheckin', '32456789000110', '8f2a7e3c9a1b4f608d3e1b6f4f8e93cd', 2);

INSERT INTO tb_usuario_funcao (usuario_id, funcao_id) VALUES (1, 1);
INSERT INTO tb_usuario_funcao (usuario_id, funcao_id) VALUES (2, 2);
INSERT INTO tb_usuario_funcao (usuario_id, funcao_id) VALUES (3, 3);

INSERT INTO tb_evento (nome, localizacao, data_inicio_evento, data_fim_evento, ativo, empresa_id) VALUES ('Semana de Inovação Tecnológica 2025', 'Auditório Central - Universidade Federal de Tecnologia', '2025-08-12 09:00:00', '2025-08-16 17:00:00', true, 1);

INSERT INTO tb_evento_usuario (evento_id, usuario_id) VALUES (1, 2);
INSERT INTO tb_evento_usuario (evento_id, usuario_id) VALUES (1, 3);
INSERT INTO tb_evento_usuario (evento_id, usuario_id) VALUES (1, 4);
INSERT INTO tb_evento_usuario (evento_id, usuario_id) VALUES (1, 5);
INSERT INTO tb_evento_usuario (evento_id, usuario_id) VALUES (1, 6);