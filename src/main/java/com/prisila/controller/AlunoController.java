package com.prisila.controller;

import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;

import com.prisila.dao.AlunoDao;
import com.prisila.dao.ResponsavelDao;
import com.prisila.modelo.entidade.Aluno;
import com.prisila.modelo.entidade.Responsavel;
import com.prisila.util.Mensagem;
import com.prisila.util.Mensagem.TipoMensagem;

@Resource
public class AlunoController extends Controller {
	
	private final AlunoDao dao;
	private final ResponsavelDao responsavelDao;
	private final Result result;
	private List<Responsavel> respList;
	private Validator validator;
	
	public AlunoController(AlunoDao dao, ResponsavelDao responsavelDao, Result result, Validator validator) {
		this.dao = dao;
		this.responsavelDao = responsavelDao;
		this.result = result;
		this.validator = validator;
	}
	
	@Get
	@Path("/alunos/cadastrar")
	public void cadastrar() {
	}
	
	@Post
	@Path("/alunos/cadastrar")
	public void cadastrar(Aluno novoAluno, Responsavel novoResponsavel, Responsavel responsavelExistente,
			boolean isResponsavelExistente) {
		Responsavel responsavelASalvar = null;
		if(isResponsavelExistente) {
			if(responsavelExistente.getId() != null) {
				Responsavel responsavelExistenteNoBanco = responsavelDao.carrega(responsavelExistente.getId());
				if(responsavelExistenteNoBanco == null) {
					validator.add(new ValidationMessage("O responsável informado não existe", "reponsavelExistente.naoexiste"));
				} else {
					responsavelASalvar = responsavelExistenteNoBanco;
				}
			} else {
				validator.add(new ValidationMessage("Informe um responsável para representar o aluno", "reponsavelExistente.naofornecido"));
			}
		} else {
			responsavelASalvar = novoResponsavel;
			validator.validate(responsavelASalvar);
		}
		
		validator.validate(novoAluno);
		validator.onErrorUsePageOf(this).cadastrar();
		
		// a partir desta linha, deu tudo certo nas validações
		try {
			dao.salvarAlunoComResponsavel(novoAluno, responsavelASalvar);
			setMensagem(result, new Mensagem(TipoMensagem.SUCCESS, "Aluno cadastrado com sucesso"));
			result.redirectTo(this).listar();
		} catch (Exception e) {
			LOG.error("erro ao salvar aluno e responsavel", e);
			result.forwardTo(ErroController.class).erro500();
		}
	}
	
	@Get
	@Path("/alunos/{id}")
	public Aluno editar(Long id) {
		respList = responsavelDao.listaTudo();
		result.include("responsavelList", respList);
		
		return dao.carrega(id);
	}
	
	@Put
	@Path("/alunos/{aluno.id}")
	public void alterar(Aluno aluno) {
		dao.atualiza(aluno);
		result.redirectTo(this).listar();
	}
	
	@Get
	@Path("/alunos/listar")
	public List<Aluno> listar() {
		return dao.listaTudo();
	}
	
	@Delete
	@Path("/alunos/{id}")
	public void deletar(Long id) {
		dao.deletar(id);
		result.redirectTo(this).listar();
	}
	
}
