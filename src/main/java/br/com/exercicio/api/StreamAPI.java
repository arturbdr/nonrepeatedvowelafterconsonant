package br.com.exercicio.api;

/**
 * Interface com metodos a serem implementados
 */
public interface StreamAPI {
	
	/**
	 * Uma chamada para getNext() ira retornar o proximo caracter a ser processado na Stream
	 * @return <b>char</b> representando o proximo caracter na Stream. <br/><b>0</b> caso nao exita mais caracteres a serem lidos 
	 */
	public char getNext();

	/**
	 * Uma chamada para hasNext() ira retornar se a stream ainda contem caracteres para processar
	 * @return <b>true</b> caso exista caracteres para processar. <br/><b>false</b> caso contrario
	 */
	public boolean hasNext();
	
	
}
