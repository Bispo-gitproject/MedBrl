alter table paciente add column ativo tinyint;
update paciente set ativo = 1;