create database lanchonete;

# Teste 1
use lanchonete;

create table cliente(
nome varchar(100) not null,
cpf varchar(11) not null,
endereco varchar(200),
fones varchar(100),

constraint pk_cliente primary key (cpf)
);

create table funcionario(
codigo int not null auto_increment,
nome varchar(100),
cpf varchar(11) not null,
endereco varchar(200),
fones varchar(100),  

constraint pk_funcionario primary key (codigo,cpf)
);

create table cardapio(
codigo_item int not null,
nome varchar(100),  
preco numeric(5,2),
descricao varchar(100),

constraint pk_cardapio primary key (codigo_item),
constraint chk_preco check(preco>0)
);


create table itens_cardapio(
codigo_cardapio int not null,
codigo_ingrediente int not null,
quantidade numeric(5,2),


constraint pk_item_cardapio primary key (codigo_cardapio,codigo_ingrediente),
constraint fk_cardapio foreign key (codigo_cardapio) references cardapio(codigo_item),
constraint fk_ingrediente foreign key (codigo_ingrediente) references ingrediente(codigo),
constraint chk_item_cardapio check(quantidade>=0) 
);

create table ingrediente(
codigo int not null auto_increment,
nome varchar(100), 
quantidade_estoque numeric(5,2),

constraint pk_ingrediente primary key (codigo),
constraint chk_ingrediente check(quantidade_estoque>=0)
);

create table fornecedor(
nome varchar(30) not null,
cnpj varchar(14) not null,
endereco varchar(200),
contato varchar(12), 

constraint pk_fornecedor primary key (cnpj)
);

create table pedido(
numero int not null auto_increment,
cpf_cliente varchar(11) not null,
cod_funcionario int not null,
pedido_status varchar(15) DEFAULT 'Pendente',
valor numeric(5,2), 
observacao varchar(200),	
data_hora datetime, 

constraint pk_pedido primary key (numero),
constraint fk_cliente foreign key (cpf_cliente) references cliente(cpf),
constraint fk_funcionario foreign key (cod_funcionario) references funcionario(codigo),
constraint chk_pedido check(valor>=0) 
);


create table itens_pedido(
numero_pedido int not null,
cod_item_cardapio int not null,
quantidade int not null,

constraint pk_itens_pedido primary key (numero_pedido,cod_item_cardapio),
constraint fk_pedido foreign key (numero_pedido) references pedido(numero),
constraint fk_cod_item_cardapio foreign key (cod_item_cardapio) references cardapio(codigo_item),
constraint chk_itens_pedido check(quantidade>=0)
);

insert into cliente values ('João','45578411122','Rua 01 casa 3','41 5555 2222');
insert into cliente values ('Maria','45578411142','Rua 01 casa 7','41 5555 7722');
insert into cliente values ('Chico','45578411162','Rua 12 casa 3','41 5555 4422');

insert into ingrediente values (null,'Presunto',2.5);
insert into ingrediente values (null,'Queijo',2.5);
insert into ingrediente values (null,'Pão',10);
insert into ingrediente values (null,'Hamburguer',5);
insert into ingrediente values (null,'Tomate',2.5);
insert into ingrediente values (null,'Alface',2);




insert into cardapio values (1,'Hamburguer',22.99,'Pão,Hambuguer,Alface,Tomate e queijo');
insert into cardapio values (2,'X-Salada',15.00,'Alface,Tomate e queijo');



insert into itens_cardapio values(1,3,2);
insert into itens_cardapio values(1,4,1);
insert into itens_cardapio values(1,5,0.3);
insert into itens_cardapio values(1,6,0.1);
insert into itens_cardapio values(1,2,0.3);

insert into pedido values(null,'45578411122',null,null,null,sysdate());

insert into itens_pedido values(1,1,1);


drop trigger atualiza_estoque_ingrediente;

delimiter $
create trigger atualiza_estoque_ingrediente after insert on itens_pedido
for each row
begin

update ingrediente
set quantidade_estoque = quantidade_estoque - 
where codigo = (select codigo_ingrediente
				from itens_cardapio
                where codigo_cardapio = new.cod_item_cardapio );

end $
delimiter ;


delimiter $
create procedure atualiza_estoque (in p_codigo int,in p_qtde numeric (5,2))
begin

update ingrediente
set quantidade_estoque = quantidade_estoque+p_qtde
where codigo = p_codigo;

end$
delimiter ;

drop procedure atualiza_estoque;

select * from pedido;


select * from fornecedor;	

select * from ingrediente;

call atualiza_estoque(2,5.45);

select * from itens_cardapio;

select * from cliente;

select * from funcionario;	
