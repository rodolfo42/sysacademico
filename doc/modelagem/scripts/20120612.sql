CREATE DATABASE IF NOT EXISTS sysacademico;

CREATE TABLE dia_semana (
cd_dia_semana INT PRIMARY KEY,
nm_dia_semana VARCHAR(255)
);

CREATE TABLE periodo (
cd_periodo INT PRIMARY KEY,
yy_ano YEAR,
ds_periodo VARCHAR(255)
);

CREATE TABLE aula (
cd_turma INT,
cd_aula INT,
ts_aula MEDIUMINT,
cd_professor INT,
PRIMARY KEY(cd_turma,cd_aula)
);

CREATE TABLE turma (
cd_turma INT PRIMARY KEY,
cd_curso INT,
cd_etapa INT,
cd_periodo INT,
FOREIGN KEY(cd_periodo) REFERENCES periodo (cd_periodo)
);

CREATE TABLE professor_fase (
cd_etapa INT,
cd_professor INT,
PRIMARY KEY(cd_etapa,cd_professor)
);

CREATE TABLE professor (
cd_professor INT PRIMARY KEY,
nm_professor VARCHAR(255),
ds_tel_prof CHAR(11),
ds_cel_prof CHAR(11),
ds_email_prof VARCHAR(255)
);

CREATE TABLE professor_horario (
cd_professor INT,
cd_horario INT,
PRIMARY KEY(cd_professor,cd_horario),
FOREIGN KEY(cd_professor) REFERENCES professor (cd_professor)
);

CREATE TABLE horario (
cd_horario INT PRIMARY KEY,
hr_horario TIME,
cd_dia_semana INT,
FOREIGN KEY(cd_dia_semana) REFERENCES dia_semana (cd_dia_semana)
);

CREATE TABLE sala (
cd_sala INT PRIMARY KEY,
nm_sala VARCHAR(255)
);

CREATE TABLE turma_matricula (
cd_turma INT,
cd_matricula INT,
vl_nota1 DECIMAL(15,2),
vl_nota2 DECIMAL(15,2),
PRIMARY KEY(cd_turma,cd_matricula),
FOREIGN KEY(cd_turma) REFERENCES turma (cd_turma)
);

CREATE TABLE aula_matricula (
cd_turma INT,
cd_aula INT,
cd_matricula INT,
ic_presenca TINYINT(1),
PRIMARY KEY(cd_turma,cd_aula,cd_matricula),
FOREIGN KEY(cd_aula,,) REFERENCES aula (cd_turma,cd_aula)
);

CREATE TABLE responsavel (
cd_responsavel INT PRIMARY KEY,
ds_cpf_resp CHAR(11),
nm_resp VARCHAR(255),
ds_end_resp VARCHAR(255),
ds_cep_resp VARCHAR(255),
ds_tel_resp CHAR(11),
ds_cel_resp CHAR(11),
ds_email_resp VARCHAR(255),
dt_confirm_dados DATE
);

CREATE TABLE cliente (
cd_cliente INT PRIMARY KEY,
nm_cliente VARCHAR(255),
dt_nasc_cliente DATE
);

CREATE TABLE cliente_responsavel (
cd_cliente INT,
cd_responsavel INT,
PRIMARY KEY(cd_cliente,cd_responsavel),
FOREIGN KEY(cd_cliente) REFERENCES cliente (cd_cliente),
FOREIGN KEY(cd_responsavel) REFERENCES responsavel (cd_responsavel)
);

CREATE TABLE mod_aula (
cd_mod_aula INT PRIMARY KEY,
ds_mod_aula VARCHAR(255)
);

CREATE TABLE tipo_curso (
cd_tipo_curso INT PRIMARY KEY,
nm_tipo_curso VARCHAR(255)
);

CREATE TABLE sala_alocada (
cd_sala_alocada INT PRIMARY KEY,
cd_sala INT,
cd_horario INT,
cd_periodo INT,
cd_turma INT,
FOREIGN KEY(cd_sala) REFERENCES sala (cd_sala),
FOREIGN KEY(cd_horario) REFERENCES horario (cd_horario),
FOREIGN KEY(cd_periodo) REFERENCES periodo (cd_periodo),
FOREIGN KEY(cd_turma) REFERENCES turma (cd_turma)
);

CREATE TABLE curso (
cd_curso INT PRIMARY KEY,
nm_curso VARCHAR(255),
cd_mod_aula INT,
FOREIGN KEY(cd_mod_aula) REFERENCES mod_aula (cd_mod_aula)
);

CREATE TABLE matricula (
cd_matricula INT PRIMARY KEY,
dt_matricula DATE,
ic_teoria TINYINT(1),
ic_pratica TINYINT(1),
cd_cliente INT,
cd_responsavel INT,
cd_tipo_curso INT,
cd_curso INT,
FOREIGN KEY(cd_responsavel) REFERENCES responsavel (cd_responsavel),
FOREIGN KEY(cd_tipo_curso) REFERENCES tipo_curso (cd_tipo_curso),
FOREIGN KEY(cd_curso) REFERENCES curso (cd_curso)
);

CREATE TABLE fase (
cd_fase INT PRIMARY KEY,
ic_aprovacao TINYINT(1),
cd_matricula INT,
cd_etapa INT,
cd_periodo INT,
FOREIGN KEY(cd_matricula) REFERENCES matricula (cd_matricula),
FOREIGN KEY(cd_periodo) REFERENCES periodo (cd_periodo)
);

CREATE TABLE etapa (
cd_curso INT,
cd_etapa INT PRIMARY KEY,
ds_ciclo VARCHAR(255),
cd_tipo_curso INT,
FOREIGN KEY(cd_curso) REFERENCES curso (cd_curso),
FOREIGN KEY(cd_tipo_curso) REFERENCES tipo_curso (cd_tipo_curso)
);

ALTER TABLE aula ADD FOREIGN KEY(cd_turma) REFERENCES turma (cd_turma);
ALTER TABLE aula ADD FOREIGN KEY(cd_professor) REFERENCES professor (cd_professor);
ALTER TABLE turma ADD FOREIGN KEY(cd_curso) REFERENCES curso (cd_curso);
ALTER TABLE turma ADD FOREIGN KEY(cd_etapa) REFERENCES etapa (cd_etapa);
ALTER TABLE professor_fase ADD FOREIGN KEY(cd_etapa) REFERENCES etapa (cd_etapa);
ALTER TABLE professor_fase ADD FOREIGN KEY(cd_professor) REFERENCES professor (cd_professor);
ALTER TABLE professor_horario ADD FOREIGN KEY(cd_horario) REFERENCES horario (cd_horario);
ALTER TABLE turma_matricula ADD FOREIGN KEY(cd_matricula) REFERENCES matricula (cd_matricula);
ALTER TABLE aula_matricula ADD FOREIGN KEY(cd_matricula) REFERENCES matricula (cd_matricula);
ALTER TABLE fase ADD FOREIGN KEY(cd_etapa) REFERENCES etapa (cd_etapa);
