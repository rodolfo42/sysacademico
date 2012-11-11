package com.prisila.modelo.entidade;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Pattern;

import com.prisila.util.GeralUtil;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "login" }))
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty(message = "{usuario.nome.obrigatorio}")
	private String nome;
	
	@NotEmpty(message = "{usuario.login.obrigatorio}")
	@Pattern(regex = "^[a-zA-Z0-9]+$", message = "{usuario.login.alfanumerico}")
	private String login;
	
	@NotEmpty(message = "{usuario.senha.obrigatoria}")
	private String senha;
	
	@NotNull
	private boolean admin;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void criptografarSenha() {
		setSenha(GeralUtil.hashMD5(getSenha()));
	}

	public boolean isAdmin() {
		return admin;
	}
	
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}