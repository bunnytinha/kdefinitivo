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

CREATE TABLE Professor(
	matricula_Professor 	VARCHAR(15) 	PRIMARY KEY			,
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
    presenca_dia    VARCHAR(1)	NOT NULL UNIQUE, 
	fk_matricula_Aluno 	VARCHAR(15) 	NOT NULL			,
	
	FOREIGN KEY (fk_matricula_Aluno) REFERENCES Aluno (matricula_Aluno)	
	
    /*O prazo nota será um percentual de nota, por exemplo, todas vezes que o aluno foi chamado ele tirou 90%, a nota final
    do aluno(a2_aluno) será a soma do percentual divido pelo numero de vezes que ele foi chamado(numero_Vezes)* 20, ou seja,
    ((0.9 + 0.9 + 0.9)/3) * 20 = 18. 18 será sua nota final de a1 ou a2.*/
);

INSERT INTO Pessoa(nome_Pessoa, email_Pessoa) VALUE ('professor','professor@exemplo');
SELECT * FROM Pessoa;

INSERT INTO Professor(matricula_Professor, senha_Professor, fk_matricula_Pessoa) VALUE('88888888', 'SENHA', '1');
SELECT * FROM Professor;

INSERT INTO Turma(codigo_Turma, curso_Turma, fk_matricula_Professor) VALUE('ECP2BN', 'engenharia da computação', '88888888');
SELECT * FROM TURMA;

INSERT INTO Pessoa(nome_Pessoa, email_Pessoa) VALUE ('aluno','aluno@exemplo');
INSERT INTO Aluno(matricula_Aluno, fk_matricula_Pessoa, fk_codigo_turma) VALUE ('817233991',5,'ECP2BN');
SELECT * FROM Aluno;

INSERT INTO Nota(valor_Nota, data_Teste, presenca_dia, fk_matricula_Aluno) VALUE (9,'07/05/2020','P','817633921');
SELECT * FROM Nota;
SELECT * FROM Pergunta;




/*SELECT LAST_INSERT_ID ( );*/
