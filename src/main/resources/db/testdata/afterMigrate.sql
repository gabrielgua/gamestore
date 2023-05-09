set foreign_key_checks = 0;

delete from jogo;
delete from pedido;
delete from usuario;
delete from categoria;
delete from plataforma;
delete from pedido_jogo;
delete from usuario_jogo;
delete from jogo_categoria;
delete from jogo_plataforma;
delete from forma_pagamento;

set foreign_key_checks = 1;

alter table jogo auto_increment = 1;
alter table pedido auto_increment = 1;
alter table usuario auto_increment = 1;
alter table categoria auto_increment = 1;
alter table plataforma auto_increment = 1;
alter table forma_pagamento auto_increment = 1;

insert into jogo (id, nome, uri_nome,descricao, preco, nota) values
(1, 'Dark Souls™: Remastered', 'dark-souls-remastered','Bate e rola 1.', 129.90, 9.8),
(2, 'Dark Souls™ II: Scholar of The First Sin', 'dark-souls-ii-scholar-of-the-first-sin','Bom jogo, porém os únicos bosses bons são os das DLCs.', 179.90, 7.8),
(3, 'Dark Souls™ III', 'dark-souls-iii', 'Bate e rola 3, boss fights mais cinematográficas da franquia SOULS', 259.89, 9.2),
(4, 'Counter-Strike: Global Offensive', 'counter-strike-global-offensive','Pai dos jogos de tiro.', 79.90, 8.9),
(5, 'Counter Strike 2', 'counter-strike-2', 'Por favor Valve me deixa testar.', 0.0, 8.8),
(6, 'The Legend of Zelda™: Breath of the Wild', 'the-legend-of-zelda-breath-of-the-wild', 'Zelda GOTY', 257.90, 9.8),
(7, 'The Legend of Zelda™: Tears of The Kingdom', 'the-legend-of-zelda-tears-of-the-kingdom', 'GOTY 2023 provavelmente', 357.99, 9.9),
(8, 'Apex Legends', 'apex-legends','Battle Royale da EA', 0.0, 8.8),
(9, 'Bloodborne', 'bloodborne','Mim dê o porte pra PC Miazaki', 199.99, 9.1),
(10, 'Grand Theft Auto V', 'grand-theft-auto-v','GTA 5 V Five', 89.9, 9.6);

insert into categoria (id, nome) values
(1, 'FPS'), (2, 'RPG'), (3, 'Souls-Like'), (4, 'Simulador'), (5, 'Ação');

insert into plataforma (id, nome) values
(1, 'PC'), (2, 'PS4'), (3, 'PS5'), (4, 'Xbox ONE'), (5, 'Xbox Series X/S'), (6, 'Nintendo Switch');

insert into forma_pagamento (id, nome) values
(1, 'Pix'), (2, 'Boleto'), (3, 'Cartão de crédito'), (4, 'Cartão de débito'), (5, 'Cartão presente');

insert into jogo_categoria (jogo_id, categoria_id) values
(1, 2), (1, 3),
(2, 2), (2, 3),
(3, 2), (3, 3),
(4, 1),
(5, 1),
(6, 2), (6, 5),
(7, 2), (7, 5),
(8, 1),
(9, 2), (9, 3),
(10, 4), (10, 5);

insert into jogo_plataforma (jogo_id, plataforma_id) values
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5),
(2, 1), (2, 2), (2, 3), (2, 4), (2, 5),
(3, 1), (3, 2), (3, 3), (3, 4), (3, 5),
(4, 1),
(5, 1),
(6, 6),
(7, 6),
(8, 1), (8, 2), (8, 3), (8, 4), (8, 5),
(9, 2), (9, 3),
(10, 1), (10, 2), (10, 3), (10, 4), (10, 5);

insert into usuario (id, nome, username, tipo, email, senha, data_cadastro) values
(1, "Gabriel", "opaco", "ADMIN", "gabriel.opaco@email.com", "$2a$12$Z7eR/rUV9CusNU3IEDtyhOhmos/sAQOv5W7MuEd/tU9lejmCdmzZ2", utc_timestamp),
(2, null, "usuario", "USER", "usuario.gamer@email.com", "$2a$12$Z7eR/rUV9CusNU3IEDtyhOhmos/sAQOv5W7MuEd/tU9lejmCdmzZ2", utc_timestamp);

insert into usuario_jogo (usuario_id, jogo_id) values
(1, 1), (1, 3), (1, 5),
(2, 2);

insert into pedido (id, codigo, valor_total, data_criacao, data_confirmacao, data_cancelamento, data_reembolso, status, usuario_id, forma_pagamento_id) values
(1, "7221549c-db06-11ed-afa1-0242ac120002", 309.80, utc_timestamp, null, null, null, "CONFIRMADO", 1, 3),
(2, "b751b070-db06-11ed-afa1-0242ac120002", 79.90, utc_timestamp, null, utc_timestamp, null, "CANCELADO", 1, 2),
(3, "d5e719f8-db06-11ed-afa1-0242ac120002", 259.98, utc_timestamp, null,null, null, "CRIADO", 2, 1);

insert into pedido_jogo (pedido_id, jogo_id) values
(1, 1), (1, 2),
(2, 4),
(3, 3);


