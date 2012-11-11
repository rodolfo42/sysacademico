package com.prisila.util;

/**
 * Objeto para centralizar o feedback que será enviado á telas após o
 * processamento dos controllers (quando não em validação) Ex: login
 * 
 * @author Rodolfo
 */
public class Mensagem {
	
	public enum TipoMensagem {
		
		ERROR("error"), WARNING("warning"), INFO("info"), SUCCESS("success");
		
		/**
		 * Será usado como a classe do elemento na tela
		 */
		private String tipo;
		
		private TipoMensagem(String tipo) {
			this.tipo = tipo;
		}
		
		public String get() {
			return tipo;
		}	
	}
	
	private TipoMensagem tipoMensagem;
	private String mensagem;
	
	public Mensagem(TipoMensagem tipo, String mensagem) {
		this.tipoMensagem = tipo;
		this.mensagem = mensagem;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public String getTipo() {
		return tipoMensagem.get();
	}
}
