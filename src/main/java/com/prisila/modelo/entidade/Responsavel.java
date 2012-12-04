package com.prisila.modelo.entidade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.validator.Email;
import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.Pattern;

@Entity
public class Responsavel {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty(message = "{resp.cpf.vazio")
	@Pattern(regex = "^[0-9]{11}$", message = "{resp.cpf.invalido}")
	private String cpf;
	
	@NotEmpty(message = "{resp.nome.vazio}")
	private String nome;
	
	@NotEmpty(message = "{resp.endereco.vazio}")
	private String endereco;
	
	@NotEmpty(message = "{resp.cep.vazio}")
	@Pattern(regex = "[0-9]{8}", message = "{resp.cep.invalido}")
	private String cep;
	
	@NotEmpty(message = "{resp.telefone.vazio}")
	@Pattern(regex = "[0-9]{10,11}", message = "{resp.telefone.invalido}")
	private String telefone;
	
	@NotEmpty(message = "{resp.celular.vazio}")
	@Pattern(regex = "[0-9]{10,11}", message = "{resp.celular.invalido}")
	private String celular;
	
	@NotEmpty(message = "{resp.email.vazio}")
	@Email(message = "{resp.email.invalido}")
	private String email;
	
	private Date dataConfirmacao;
	
	@ManyToMany(mappedBy = "listaResponsavel", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Aluno> listaAluno;
	
	public Responsavel() {
		this.listaAluno = new ArrayList<Aluno>();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getCelular() {
		return celular;
	}
	
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getDataConfirmacao() {
		return dataConfirmacao;
	}
	
	public void setDataConfirmacao(Date dataConfirmacao) {
		this.dataConfirmacao = dataConfirmacao;
	}
	
	public List<Aluno> getListaAluno() {
		return Collections.unmodifiableList(listaAluno);
	}
	
	public void adicionaVinculo(Aluno aluno) {
		this.listaAluno.add(aluno);
	}
	
	public void setListaAluno(List<Aluno> listaAluno) {
		this.listaAluno = listaAluno;
	}
	
}
