alter table consultas add column ativo tinyint;
update consultas set ativo = 1;