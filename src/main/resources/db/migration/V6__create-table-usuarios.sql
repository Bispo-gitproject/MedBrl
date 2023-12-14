create table usuarios(

                        id bigint not null auto_increment,
                        nome varchar(100) not null,
                        data date not null,
                        cpf varchar(20) not null unique,
                        telefone varchar(20) not null,
                        email varchar(100) not null unique,
                        logradouro varchar(100) not null,
                        bairro varchar(100) not null,
                        cep varchar(9) not null,
                        complemento varchar(100),
                        numero varchar(20),
                        uf char(2) not null,
                        cidade varchar(100) not null,
                        cargo varchar(100) not null,
                        permissao varchar(100) not null,
                        usuario varchar(100) not null,
                        senha varchar(255) not null,
                        ativo tinyint default false,

                        primary key(id)

);