package com.prisila.test;

import java.util.Arrays;
import java.util.Calendar;

import org.junit.Test;

import com.prisila.modelo.constante.TipoAula;
import com.prisila.modelo.entidade.Aluno;
import com.prisila.modelo.entidade.Curso;
import com.prisila.modelo.entidade.Matricula;
import com.prisila.modelo.entidade.Responsavel;

public class MatriculaDaoTest extends DAOTest {
	
	@Override
	public void setup() {
		super.setup();
	}
	
	@Test
	public void cadastrarMatricula() {
		Matricula matr = new Matricula();
		matr.setAluno(getAlunoTeste());
		matr.setAtivo(true);
		matr.adicionaVinculo(TipoAula.PRATICA_INDIVIDUAL);
		matr.adicionaVinculo(TipoAula.PRATICA_GRUPO);
		matr.setCurso(getCursoTeste());
		matr.setData(Calendar.getInstance());
		matr.setResponsavel(getResponsavelTeste());
	}
	
	private Curso getCursoTeste() {
		Curso curso = new Curso();
		curso.setNome("Curso teste");
		return curso;
	}

	private Responsavel getResponsavelTeste() {
		Responsavel resp = new Responsavel();
		resp.setNome("Responsavel teste");
		resp.setEmail("teste@exemplo.com");
		resp.setTelefone("3142532374");
		resp.setCelular("11998534848");
		resp.setCep("49100000");
		resp.setCpf("39784435039");
		resp.setDataConfirmacao(Calendar.getInstance().getTime());
		return resp;
	}

	private Aluno getAlunoTeste() {
		Aluno a = new Aluno();
		a.setNome("Aluno Teste");
		Calendar dataNasc;
		dataNasc = Calendar.getInstance();
		dataNasc.add(Calendar.YEAR, -20);
		a.setDataNascimento(dataNasc.getTime());
		a.setListaResponsavel(Arrays.asList(getResponsavelTeste()));
		return a;
	}

	@Override
	public void finalize() {
		super.finalize();
	}
}