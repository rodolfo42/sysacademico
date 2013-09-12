package com.prisila.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.ValidationException;

import com.prisila.dao.AlunoDao;
import com.prisila.dao.ResponsavelDao;
import com.prisila.modelo.entidade.Aluno;
import com.prisila.modelo.entidade.Responsavel;

public class AlunoControllerTest {
	AlunoDao alunoDao;
	ResponsavelDao responsavelDao;
	MockResult mockResult;
	MockValidator mockValidator;
	
	@Before
	public void setup(){
		alunoDao = Mockito.mock(AlunoDao.class);
		responsavelDao = Mockito.mock(ResponsavelDao.class);
		mockResult = new MockResult();
		mockValidator = new MockValidator();
	}

	@Test
	public void deveRetornarErroPorNaoHaveUmResponsavel() {
		try {
			
			//Quando chamar o responsavelDao.carrega retornar null
			Mockito.when(responsavelDao.carrega(1L)).thenReturn(null);
			
			AlunoController alunoController = new AlunoController(alunoDao, responsavelDao, mockResult, mockValidator);
			
			Responsavel responsavelExistente = new Responsavel();
			responsavelExistente.setId(1L);
			
			Aluno novoAluno = new Aluno();
			Responsavel novoResponsavel = new Responsavel();
			alunoController.cadastrar(novoAluno, novoResponsavel, responsavelExistente, true);
			
		} catch(ValidationException e) {
			//verifica se ha somente um erro
			assertThat(mockValidator.getErrors().size(), is(1));
			String category = mockValidator.getErrors().get(0).getCategory();
			assertThat(category, is("reponsavelExistente.naoexiste"));
		}
	}

}
