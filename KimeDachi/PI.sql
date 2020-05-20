DROP DATABASE Sistema_Dojo; 
CREATE DATABASE Sistema_Dojo;
USE Sistema_Dojo;

CREATE TABLE Pessoa (
	matricula_Pessoa 	INT  AUTO_INCREMENT	PRIMARY KEY		,
	nome_Pessoa 		VARCHAR(126) 	NOT NULL			,
	email_Pessoa 		VARCHAR(75)	NOT NULL UNIQUE 		
);
CREATE TABLE Pergunta(
	id_Pergunta 		VARCHAR(15) 		PRIMARY KEY,
	pergunta_Pergunta 	VARCHAR(150) 	NOT NULL			,
	resposta_Pergunta 	VARCHAR(150) 					,
	tema_Pergunta 		VARCHAR(100) 	NOT NULL		
);
CREATE TABLE Temas(
	id_Tema 		VARCHAR(15) PRIMARY KEY	
);


CREATE TABLE Professor(
	matricula_Professor 	VARCHAR(15) 	PRIMARY KEY		,
	senha_Professor		VARCHAR(10) 	NOT NULL 			,
    fk_matricula_Pessoa INT NOT NULL,
	FOREIGN KEY (fk_matricula_Pessoa) REFERENCES Pessoa (matricula_Pessoa)
);
CREATE TABLE Turma(
	codigo_Turma 		VARCHAR(15) 	PRIMARY KEY			,
	curso_Turma 		VARCHAR(50) 	NOT NULL			,
	fk_matricula_Professor	VARCHAR(15)	NOT NULL			,
	FOREIGN KEY (fk_matricula_Professor) REFERENCES Professor (matricula_Professor)
);
CREATE TABLE Aluno(
	matricula_Aluno 	VARCHAR(15)  NOT NULL	PRIMARY KEY	,
	fk_matricula_Pessoa 	INT 	NOT NULL UNIQUE		,
	fk_codigo_Turma 	VARCHAR(15) 	NOT NULL			,
	FOREIGN KEY (fk_matricula_Pessoa) REFERENCES Pessoa (matricula_Pessoa)	,
	FOREIGN KEY (fk_codigo_Turma) REFERENCES Turma (codigo_Turma)
);
/*CREATE TABLE AlunosTurma(
	id_AlunosTurma		INT 		AUTO_INCREMENT PRIMARY KEY	,
	codigo_Turma 		VARCHAR(15) 	NOT NULL			,
	fk_matricula_Aluno 	VARCHAR(15) 	NOT NULL			,
	FOREIGN KEY (fk_matricula_Aluno) REFERENCES Aluno (fk_matricula_Aluno)
);*/
CREATE TABLE Nota(
	id_Nota 		INT 		AUTO_INCREMENT PRIMARY KEY	,
	valor_Nota 		DOUBLE 		NOT NULL			,
	data_Teste 		VARCHAR(11) 		NOT NULL			,
    presenca_dia    VARCHAR(1)	NOT NULL , 
	fk_matricula_Aluno 	VARCHAR(15) 	NOT NULL			,
	
	FOREIGN KEY (fk_matricula_Aluno) REFERENCES Aluno (matricula_Aluno)	
    ON UPDATE CASCADE
	
    /*O prazo nota será um percentual de nota, por exemplo, todas vezes que o aluno foi chamado ele tirou 90%, a nota final
    do aluno(a2_aluno) será a soma do percentual divido pelo numero de vezes que ele foi chamado(numero_Vezes)* 20, ou seja,
    ((0.9 + 0.9 + 0.9)/3) * 20 = 18. 18 será sua nota final de a1 ou a2.*/
);

INSERT INTO Pessoa(nome_Pessoa, email_Pessoa) VALUE ('professor','professor@exemplo');
INSERT INTO Professor(matricula_Professor, senha_Professor, fk_matricula_Pessoa) VALUE('88888888', 'SENHA', '1');
SELECT * FROM Pessoa;
SELECT * FROM Professor;

INSERT INTO Turma(codigo_Turma, curso_Turma, fk_matricula_Professor) VALUE('ECP2BN', 'engenharia da computação', '88888888');
INSERT INTO Turma(codigo_Turma, curso_Turma, fk_matricula_Professor) VALUE('ECP3BN', 'engenharia da computação', '88888888');
INSERT INTO Turma(codigo_Turma, curso_Turma, fk_matricula_Professor) VALUE('ECP1BN', 'engenharia eletrônica', '88888888');
INSERT INTO Turma(codigo_Turma, curso_Turma, fk_matricula_Professor) VALUE('ECP0BN', 'tecnologia da informação', '88888888');

SELECT * FROM TURMA;

INSERT INTO Pessoa(nome_Pessoa, email_Pessoa) VALUE ('icaro','icaro@exemplo');
INSERT INTO Aluno(matricula_Aluno, fk_matricula_Pessoa, fk_codigo_turma) VALUE ('817233991',2,'ECP3BN');
INSERT INTO Pessoa(nome_Pessoa, email_Pessoa) VALUE ('henrique','henrique@exemplo');
INSERT INTO Aluno(matricula_Aluno, fk_matricula_Pessoa, fk_codigo_turma) VALUE ('817233992',3,'ECP3BN');
INSERT INTO Pessoa(nome_Pessoa, email_Pessoa) VALUE ('gabriel','gabriel@exemplo');
INSERT INTO Aluno(matricula_Aluno, fk_matricula_Pessoa, fk_codigo_turma) VALUE ('817233993',4,'ECP3BN');
INSERT INTO Pessoa(nome_Pessoa, email_Pessoa) VALUE ('lucas','lucas@exemplo');
INSERT INTO Aluno(matricula_Aluno, fk_matricula_Pessoa, fk_codigo_turma) VALUE ('817233994',5,'ECP3BN');
INSERT INTO Pessoa(nome_Pessoa, email_Pessoa) VALUE ('nubia','nubia@exemplo');
INSERT INTO Aluno(matricula_Aluno, fk_matricula_Pessoa, fk_codigo_turma) VALUE ('817233995',6,'ECP3BN');
INSERT INTO Pessoa(nome_Pessoa, email_Pessoa) VALUE ('victor','victor@exemplo');
INSERT INTO Aluno(matricula_Aluno, fk_matricula_Pessoa, fk_codigo_turma) VALUE ('817233996',7,'ECP3BN');
INSERT INTO Pessoa(nome_Pessoa, email_Pessoa) VALUE ('aluno','aluno@exemplo');
INSERT INTO Aluno(matricula_Aluno, fk_matricula_Pessoa, fk_codigo_turma) VALUE ('817233997',8,'ECP3BN');
INSERT INTO Pessoa(nome_Pessoa, email_Pessoa) VALUE ('aluno2','aluno2@exemplo');
INSERT INTO Aluno(matricula_Aluno, fk_matricula_Pessoa, fk_codigo_turma) VALUE ('817233998',9,'ECP3BN');
INSERT INTO Pessoa(nome_Pessoa, email_Pessoa) VALUE ('aluno3','aluno3@exemplo');
INSERT INTO Aluno(matricula_Aluno, fk_matricula_Pessoa, fk_codigo_turma) VALUE ('817233999',10,'ECP3BN');
INSERT INTO Pessoa(nome_Pessoa, email_Pessoa) VALUE ('aluno4','aluno4@exemplo');
INSERT INTO Aluno(matricula_Aluno, fk_matricula_Pessoa, fk_codigo_turma) VALUE ('817233921',11,'ECP3BN');
INSERT INTO Pessoa(nome_Pessoa, email_Pessoa) VALUE ('aluno5','aluno5@exemplo');
INSERT INTO Aluno(matricula_Aluno, fk_matricula_Pessoa, fk_codigo_turma) VALUE ('817233911',12,'ECP2BN');
INSERT INTO Pessoa(nome_Pessoa, email_Pessoa) VALUE ('aluno6','aluno6@exemplo');
INSERT INTO Aluno(matricula_Aluno, fk_matricula_Pessoa, fk_codigo_turma) VALUE ('817234991',13,'ECP2BN');
INSERT INTO Pessoa(nome_Pessoa, email_Pessoa) VALUE ('aluno7','aluno7@exemplo');
INSERT INTO Aluno(matricula_Aluno, fk_matricula_Pessoa, fk_codigo_turma) VALUE ('817233990',14,'ECP2BN');
INSERT INTO Pessoa(nome_Pessoa, email_Pessoa) VALUE ('aluno8','aluno8@exemplo');
INSERT INTO Aluno(matricula_Aluno, fk_matricula_Pessoa, fk_codigo_turma) VALUE ('817233900',15,'ECP2BN');



INSERT INTO Pergunta(id_Pergunta, pergunta_Pergunta, resposta_Pergunta, tema_Pergunta) VALUE ('p1','quanto é a raiz de 64','8','matemática');
INSERT INTO Pergunta(id_Pergunta, pergunta_Pergunta, resposta_Pergunta, tema_Pergunta) VALUE ('p2','quanto é 2*5','10','matemática');
INSERT INTO Pergunta(id_Pergunta, pergunta_Pergunta, resposta_Pergunta, tema_Pergunta) VALUE ('p3','quanto é 10-5','5','matemática');
INSERT INTO Pergunta(id_Pergunta, pergunta_Pergunta, resposta_Pergunta, tema_Pergunta) VALUE ('p4','quanto é 10^2 ','100','matemática');
INSERT INTO Pergunta(id_Pergunta, pergunta_Pergunta, resposta_Pergunta, tema_Pergunta) VALUE ('p5','quanto é a raiz de 100','10','matemática');
INSERT INTO Temas(id_Tema) VALUE ('matemática');


INSERT INTO Pergunta(id_Pergunta, pergunta_Pergunta, resposta_Pergunta, tema_Pergunta) VALUE ('p6','qual é a classe que armazena atributos em privado e representa algo do mundo real','objeto','POO');
INSERT INTO Pergunta(id_Pergunta, pergunta_Pergunta, resposta_Pergunta, tema_Pergunta) VALUE ('p7','como imprimir algo no terminal java?','System.out.println("");','POO');
INSERT INTO Pergunta(id_Pergunta, pergunta_Pergunta, resposta_Pergunta, tema_Pergunta) VALUE ('p8','como se declara uma String com o texto "oi"?','String texto="oi";','POO');
INSERT INTO Temas(id_Tema) VALUE ('POO');

SELECT * FROM Nota;
SELECT * FROM Pergunta;

SELECT * FROM Aluno;


INSERT INTO Temas(id_Tema) VALUE ('matemática');


/*on update cascade on delete cascade

SELECT LAST_INSERT_ID ( );*/
