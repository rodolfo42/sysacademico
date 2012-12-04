package com.prisila.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.prisila.dao.ResponsavelDao;
import com.prisila.modelo.entidade.Aluno;
import com.prisila.modelo.entidade.Responsavel;

public class ResponsavelDAOTest extends DAOTest {
	
	ResponsavelDao responsavelDao;
	
	@Override
	public void setup() {
		super.setup();
		responsavelDao = new ResponsavelDao(session);
	}
	
	@Test
	public void testeBuscarPorCPF() {
		String cpf = "61938248805";
		
		Responsavel resp = TesteDB.newResponsavel();
		Aluno aluno = TesteDB.newAluno();
		resp.setCpf(cpf);
		resp.adicionaVinculo(aluno);
		
		responsavelDao.salvar(resp);
		assertEquals(resp, responsavelDao.getByCPF(cpf));
	}
}