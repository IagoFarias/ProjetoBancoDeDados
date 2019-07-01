create database lanchonete;

use lanchonete;

#------------------TABELAS------------------------------------
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


create table itens_cardapio(
codigo int not null,
nome varchar(100),  
preco numeric(5,2),
descricao varchar(100),


constraint pk_item_cardapio primary key (codigo),
constraint chk_item_cardapio check(preco>=0) 
);

create table uso_ingrediente(
cod_item_cardapio int not null,
cod_ingrediente int not null,
quantidade numeric(5,2),

constraint pk_uso_ingrediente primary key (cod_item_cardapio,cod_ingrediente),
constraint fk_ingrediente foreign key (cod_ingrediente) references ingrediente(codigo),
constraint fk_itens_cardapio foreign key (cod_item_cardapio) references itens_cardapio(codigo)
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
constraint fk_cod_item_cardapio foreign key (cod_item_cardapio) references itens_cardapio(codigo),
constraint chk_itens_pedido check(quantidade>=0)
);

create table fornecimentoEstoque (
		
        cod_fornecedor varchar(14) not null,
        cod_ingrediente int not null,
        
        constraint pk_fornecedorEstoque primary key (cod_fornecedor,cod_ingrediente),
        constraint fk1 foreign key (cod_fornecedor)  references fornecedor (cnpj),
        constraint fk2  foreign key (cod_ingrediente) references  ingrediente (codigo)
); 
#------------------------------------------------------------------------

#-------------------VIEWS-------------------------

create view nome_itens_cardapio_pedido as
select numero_pedido,nome,quantidade,codigo 
from itens_pedido,itens_cardapio,pedido
where cod_item_cardapio=codigo
and numero_pedido=numero;

create or replace view Cliente_preferencial as
select c.nome, p.cpf_cliente, count(p.numero) as Numeros_de_Pedidos,sum(valor) as Total_Gasto
from pedido as p
inner join cliente as c on c.cpf=p.cpf_cliente
group by (cpf_cliente)
order by 3 desc;


create or replace view Lucro_por_mes as
select  sum(valor) as Total_Mes_Corrente, count(numero) as Numeros_de_Pedidos , 
month(data_hora) as mes,year(data_hora) as ano from pedido
group by month(data_hora);
        
        
create or replace view Controle_de_Estoque as
select  i.nome as Nome_Ingrediente, i.quantidade_estoque as Qtde_Prod_Estoque, 
f.nome as Nome_Fornecedor , f.contato as Contato_Fornecedor ,f.cnpj as CNPJ
from fornecedor as f inner join fornecimentoEstoque as fe
on fe.cod_fornecedor = f.cnpj
inner join ingrediente as i 
on fe.cod_ingrediente = i.codigo
order by i.quantidade_estoque asc;

#--------------------------------------------------------------------------------------

#---------------------------------INSERTS---------------------------------------------------------
insert into fornecimentoEstoque values('159753',4);
insert into fornecimentoEstoque values('159753',7);
insert into fornecimentoEstoque values('159753',10);
insert into fornecimentoEstoque values('951753',5);
insert into fornecimentoEstoque values('951753',6);
insert into fornecimentoEstoque values('751753',8);
insert into fornecimentoEstoque values('751753',9);
insert into fornecimentoEstoque values('751753',11);


insert into fornecedor values('BSB Alimentos','751753','Sia Trecho 1','33215658'); 
insert into fornecedor values('Lactíneos e CIA','951753','Sia Trecho 2','33525658');
insert into fornecedor values('Friboi','159753','Sia Trecho 3','33212125');


insert into cliente values ('João','45578411122','Rua 01 casa 3','41 5555 2222');
insert into cliente values ('Maria','45578411142','Rua 01 casa 7','41 5555 7722');
insert into cliente values ('Chico','45578411162','Rua 12 casa 3','41 5555 4422');
insert into cliente values ('Chico','1','Rua 12 casa 3','41 5555 4422');

insert into ingrediente values (null,'Presunto',2.5);
insert into ingrediente values (null,'Queijo',2.5);
insert into ingrediente values (null,'Pão',10);
insert into ingrediente values (null,'Hamburguer',10);
insert into ingrediente values (null,'Tomate',2.5);
insert into ingrediente values (null,'Alface',2);
insert into ingrediente values (null,'Hamburguer de Frango',10);
insert into ingrediente values (null,'Refrigerante Lata',20);


insert into itens_cardapio values (1,'Hamburguer',22.99,'Pão,Hambuguer,Alface,Tomate e Queijo');
insert into itens_cardapio values (2,'X-Salada',15.00,'Alface,Tomate e Queijo');
insert into itens_cardapio values (3,'X-Frango',18.00,'Pão,Hambuguer de Frango,Alface,Tomate e Queijo');
insert into itens_cardapio values (4,'Refrigerante Lata',5.00,'310ml Coca-Cola,Fanta,Sprite,Garaná');

insert into uso_ingrediente values(1,6,0.200);#Pão
insert into uso_ingrediente values(1,7,0.300);#Hamb
insert into uso_ingrediente values(1,8,0.100);#Tomate
insert into uso_ingrediente values(1,9,0.050);#Alface
insert into uso_ingrediente values(1,5,0.100);#Queijo

insert into uso_ingrediente values(2,6,0.200);#Pão
insert into uso_ingrediente values(2,8,0.100);#Tomate
insert into uso_ingrediente values(2,9,0.050);#Alface
insert into uso_ingrediente values(2,5,0.100);#Queijo

insert into uso_ingrediente values(3,6,0.200);#Pão
insert into uso_ingrediente values(3,10,0.300);#Hamb. Frango
insert into uso_ingrediente values(3,9,0.050);#Alface
insert into uso_ingrediente values(3,8,0.100);#Tomate
insert into uso_ingrediente values(3,5,0.100);#Queijo

insert into uso_ingrediente values(4,11,1);#Refri

#-----------------------------------------------------------------------

#-----------------------------TRIGGER----------------------------------
delimiter $
create trigger atualiza_estoque_ingrediente after insert on itens_pedido
for each row
begin

 declare qtd numeric(5,2);
 declare cod_ing int;
DECLARE done INT DEFAULT FALSE;



declare quantidades cursor for
select quantidade,cod_ingrediente 
from uso_ingrediente 
where cod_ingrediente in (select cod_ingrediente
						  from uso_ingrediente
						  where cod_item_cardapio = new.cod_item_cardapio)
and cod_item_cardapio = new.cod_item_cardapio;


DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

open quantidades;
fetch next from quantidades into qtd,cod_ing;

update_loop:loop

if done then
leave update_loop;
end if;

update ingrediente
set quantidade_estoque = quantidade_estoque - (new.quantidade * qtd)
where codigo = cod_ing;
fetch next from quantidades into qtd,cod_ing;
end loop;

end $
delimiter ;

#-------------------------------------------------------------------------------

#---------------------------------PROCEDURES-----------------------------------
delimiter $
create procedure atualiza_estoque (in p_codigo int,in p_qtde numeric (5,2))
begin

update ingrediente
set quantidade_estoque = quantidade_estoque+p_qtde
where codigo = p_codigo;

end$
delimiter ;


call atualiza_estoque(2,5.45);

delimiter $
create procedure salvar ()
begin

commit;

end$
delimiter ;

delimiter $
create procedure voltar ()
begin

rollback;

end$
delimiter ;