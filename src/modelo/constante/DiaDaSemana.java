package modelo.constante;

public enum DiaDaSemana {

	SEGUNDAFEIRA("Segunda-Feira"), TERCAFEIRA("Terça-Feira"), QUARTAFEIRA(
			"Quarta-Feira"), QUINTAFEIRA("Quinta-Feira"), SEXTAFEIRA(
			"Sexta-Feira"), SABADO("Sábado"), DOMINGO("Domingo");

	private final String nome;

	private DiaDaSemana(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
