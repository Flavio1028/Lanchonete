create database saojudasexpress;
use saojudasexpress;
create table cardapio
(
	codigo int not null,
    nome varchar(50),
    tipo char not null,
    descricao varchar(300),
    preco double,
    
    primary key(codigo)
);

insert into cardapio values 
/*Entradas*/
(111,'LINGUIÇA CHIPS','E','Pedaços de linguiça calabresa envolvidos com batata chips crocante.',6.95),
(112,'COUVERT PARMÊ','E','Brioche, pão de queijo, gravatinha de queijo, manteiga, grissini, torradas',9.35),
(113,'DRUMETES EMPANADOS','E','Generosa porção de drumets de frango temperados à moda Parmê. Acompanha delicioso molho barbecue.',12.85),
(114,'ESCONDIDINHO','E','Nossa versão do escondidinho tipicamente brasileiro em três deliciosas opções: camarão, carne seca ou frango.',15.65),
/*Principal*/
(211,'X-TUDO','P','Pão, hambúrguer, franco, bacon, presunto, queijo, alface, tomate, ovo',7.00),
(212,'X-SALADA','P','Pão, hambúrguer, queijo, salada',2.50),
(213,'X-BANG BANG','P','Pão, 2 hamburguer, calabresa, bacon, frango, 2 ovos, presunto, queijo , salada',14.65),
(214,'PÃO DE QUEIJO','P','Porção com 5 pães  de queijos grandes',7.55),
(215,'DANTE','P','Ceneoura ralada, catupiry, peito de peru no pão preto',5.66),
/*Bebidas*/
(311,'Suco de Laranja','B','1 litro de suco',3.50),
(312,'Suco de Limão','B','1 litro de suco',3.50),
(313,'Suco de Abacaxi','B','1 litro de suco',3.50),
(314,'Suco de goiaba','B','1 litro de suco',3.50),
(315,'Taça de vinho','B','1 taça de vinho',4.00),
(316,'Água','B','Garrafa 500 Ml',4.00),
(317,'Refrigerante','B','Todos os tipos',5.00),
/*Sobremesas*/ 
(411,'PROFITEROLES','S','Mini bombas recheadas com sorvete e cobertas com calda quente de chocolate.',19.35),
(412,'TAÇA DE SORVETE','S','Duas bolas de sorvete de creme cobertas com calda de chocolate.',12.99),
(413,'TORTA DO DIA','S','Generosa fatia da torta do dia. Adicione uma bola de sorvete.',22.33);