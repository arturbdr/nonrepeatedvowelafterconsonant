package br.com.exercicio.impl;

import br.com.exercicio.api.StreamAPI;

/**
 * Classe e responsavel por implementar uma Stream.
 * Sera passado no construtor uma String representando uma Stream.
 * 
 * Nessa implementacao e possivel ler todos os itens da Stream um a um entretanto, uma vez
 * alcancado o fim da Stream, nao sendo possivel reiniciar seu fluxo de leitura.
 * 
 */
public class StreamStringImpl implements StreamAPI {

	/**
	 *  Armazenara todos os caracteres da String enviada (respectivo Stream)
	 */
	private String charStream = null;

	/**
	 * Realiza o controle das posicoes
	 */
	private Integer stringPosition = null;

	/**
	 * Construtor que recebe uma String contendo os caracteres do Stream e os armazena em uma variavel interna
	 */
	public StreamStringImpl(String characterStream) {
		if (characterStream != null && characterStream.trim().length() > 0) {
			this.charStream = characterStream;
		}
		this.stringPosition = 0;
	}

	@Override
	public Character getNext() {
		if (hasNext()) {
			//retorna o proximo elemento da Stream e incrementa o contador de posicoes
			return this.charStream.charAt(stringPosition++);
		}
		else {
			return null;
		}
	}

	@Override
	public boolean hasNext() {
		if (this.charStream == null || this.charStream.length() == 0) {
			return false;
		}
		else {
			if (this.stringPosition >= this.charStream.length()) {
				return false;
			}
			else {
				return true;
			}
		}
	}
}
