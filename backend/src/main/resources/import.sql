INSERT INTO tb_funcao (authority) VALUES ('ROLE_ADMIN');
INSERT INTO tb_funcao (authority) VALUES ('ROLE_ENTERPRISE');
INSERT INTO tb_funcao (authority) VALUES ('ROLE_USER');

INSERT INTO tb_usuario (tipo_usuario, nome, email, senha, cpf, logradouro, numero, complemento, bairro, cidade, estado, cep, razao_social, cnpj, codigo_ativacao) VALUES ('PESSOA_FISICA', 'Usuário Administrador', 'admin@admin.com', '$2b$12$BHXovfbzgeGnGrTMwiR7teSGs1z0UrQ0AO6mBucYZ4vWpbs4wWFF6', '12345678912', 'Rua Exemplo', '123', 'Complemento teste', 'Bairro Exemplo', 'Cidade Exemplo', 'EX', '00000000', null, null, null);
INSERT INTO tb_usuario (tipo_usuario, nome, email, senha, cpf, logradouro, numero, complemento, bairro, cidade, estado, cep, razao_social, cnpj, codigo_ativacao) VALUES ('PESSOA_FISICA', 'Erick Berger', 'erick@gmail.com', '$2b$12$4qhIiuwAmZcUOWY1CM8BQe6//mH4kxLeBbJ0tIQpzX3A8zKOYCTUC', '32165498787', 'Rua das Flores', '321', 'Casa', 'Bairro das Rosas', 'Florianópolis', 'SC', '11111111', null, null, null);
INSERT INTO tb_usuario (tipo_usuario, nome, email, senha, cpf, logradouro, numero, complemento, bairro, cidade, estado, cep, razao_social, cnpj, codigo_ativacao) VALUES ('PESSOA_FISICA', 'João da Silva', 'joao@gmail.com', '$2b$12$4qhIiuwAmZcUOWY1CM8BQe6//mH4kxLeBbJ0tIQpzX3A8zKOYCTUC', '65432198778', 'Rua Jorge Souza', '416', 'Sítio', 'Canto da Vila', 'Chapecó', 'SC', '22222222', null, null, null);
INSERT INTO tb_usuario (tipo_usuario, nome, email, senha, cpf, logradouro, numero, complemento, bairro, cidade, estado, cep, razao_social, cnpj, codigo_ativacao) VALUES ('EMPRESA', 'TechCheckin', 'marketing@techcheckin.com', '$2b$12$4qhIiuwAmZcUOWY1CM8BQe6//mH4kxLeBbJ0tIQpzX3A8zKOYCTUC', null, 'Avenida Papa João Paulo I', '0', 'Andar 3', 'Jardim Presidente Dutra', 'Guarulhos', 'SP', '07174000', 'Tech Eventos e Soluções Digitais LTDA', '32456789000110', '8f2a7e3c9a1b4f608d3e1b6f4f8e93cd');

INSERT INTO tb_usuario_funcao (usuario_id, funcao_id) VALUES (1, 1);
INSERT INTO tb_usuario_funcao (usuario_id, funcao_id) VALUES (2, 3);
INSERT INTO tb_usuario_funcao (usuario_id, funcao_id) VALUES (3, 3);
INSERT INTO tb_usuario_funcao (usuario_id, funcao_id) VALUES (4, 2);

INSERT INTO tb_evento (nome, localizacao, data_inicio_evento, data_fim_evento, ativo) VALUES ('Semana de Inovação Tecnológica 2025', 'Auditório Central - Universidade Federal de Tecnologia', '2025-08-12 09:00:00', '2025-08-16 17:00:00', true);

INSERT INTO tb_evento_usuario (evento_id, usuario_id) VALUES (1, 2);
INSERT INTO tb_evento_usuario (evento_id, usuario_id) VALUES (1, 3);