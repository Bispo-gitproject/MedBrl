alter table consultas add column motivo_cancelamento tinyint;
update consultas set motivo_cancelamento = null;