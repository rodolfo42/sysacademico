package dao;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.Length;

@Entity
public class Produto {

	@Id
	@GeneratedValue
	private Long id;
	
	@Length(min=5)
	private String nome;
	private String descricao;
	private double preco;
	private String data;

	public String getNome() {
		return nome;
	}
	
	public Produto aplicaDesconto() {
		this.setPreco(getPreco() * 0.9);
		return this;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
