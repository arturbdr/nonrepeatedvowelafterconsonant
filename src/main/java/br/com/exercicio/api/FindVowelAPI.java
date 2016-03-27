package br.com.exercicio.api;

public interface FindVowelAPI {

	String VOGAIS_CANDIDATAS = "vogais.candidatas";
	String MENSAGEM_ERRO_STREAMVAZIO = "mensagem.erro.streamvazio";
	String MENSAGEM_ERRO_NAOLOCALIZADO = "mensagem.erro.naolocalizado";
	
	/**
	 * Dado uma Stream, verifica qual o primeiro caracter vogal nao repetido em todo o Strem apos uma consoante e a retorna.
	 * @param input - Stream contendo os caracteres
	 * @return a primeira vogal apos uma consoante que tenha sido encontrada apenas uma vez
	 */
	String searchVowel();
}

