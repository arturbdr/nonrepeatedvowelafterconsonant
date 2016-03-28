package br.com.exercicio.impl;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import br.com.exercicio.api.FindVowelAPI;
import br.com.exercicio.api.StreamAPI;
import br.com.exercicio.messageutil.PropertiesReader;

/**
 * Classe com metodo a partir de uma Stream localize a
 * o primeiro caracter Vogal, após uma consoante que não se repita no restante da stream
 */
public class FindVowelImpl implements FindVowelAPI {

	/**
	 * Enum contendo os STATUS possiveis das vogais localizadas
	 */
	private enum STATUS_VOGAL {
		CANDIDATE,
		DISCARTED
	}

	/**
	 * Properties necessario para mensagens de informacao ao usuario assim como parametros de vogais candidatas
	 */
	private PropertiesReader propertiesReader;

	/**
	 * Correponde ao Stream a ser utilizado para localizar as vogais
	 */
	private StreamAPI input;


	/**
	 * Construtor que recebe como parametro os objetos necessarios para funcionamento da classe (DI)
	 * @param propertiesReader = embora ele seja um Singleton, atraves da utilizacao de IoC com DI fica transparente para esse classe e facilitam os testes unitarios 
	 */
	public FindVowelImpl(PropertiesReader propertiesReader, StreamAPI input) {
		this.propertiesReader = propertiesReader;
		this.input = input;

		// Fail Fast pattern - Realiza as validacoes o quanto antes para que evitar um processamento que ira falha.
		validatePropetiesFile();
		validateStreamInput();
	}

	/**
	 * Com base em todas as vogais cadastradas como candidastas, monta um Set.
	 * Esse Set será utilizado para evitar processamento extra do Stream caso todas as candidatas estiverem sido descartadas.
	 * @param candidateVowels - Parametro contendo todas as vogais candidatas
	 * @return - Set contendo todas as Vogais candidatas
	 */
	private Set<Character> createCandidateVowels(String candidateVowels) {
		
		Set <Character> uniqueVowels = new HashSet<Character>();
		
		for (int i = 0; i < candidateVowels.length(); i++){
		    char c = candidateVowels.charAt(i);        
		    uniqueVowels.add(c);
		}
		return uniqueVowels;
	}
	
	/**
	 * Metodo interno que verifica se ainda existem vogais candidatas
	 * @return <b>true</b> caso existam vogais candidatas. <br/><b>false</b> caso contrario
	 */
	private boolean existsCandidateVowel(Set<Character> candidateVowels){
		if (candidateVowels.size() == 0) {
			return false;
		}
		return true;
	}


	@Override
	public String searchVowel() {

		// Caracteres vogais totais a serem consideradas candidatos.
		String vowel = propertiesReader.getProperty(VOGAIS_CANDIDATAS);

		// Contem todas as possiveis vogais a serem consideradas candidatas.
		// Esse StringBuffer sera manuseado com o processamento do Stream sendo removida uma vogal 
		// toda vez que ela nao for mais candidata (localizada mais de uma vez ou em posicao nao adequada).
		// Isso evitará processamento desnecessario de todo o Strem
		Set<Character> candidateVowelsControl = createCandidateVowels(vowel);

		// Boolean informando se o caracter anterior e uma consoante
		boolean isPreviousCharacterConsonant = false;

		// Map contendo todas as vogais localizadas em determiado Stream com respectiva contagem de quantas vezes a vogal foi localizada
		Map<Character, STATUS_VOGAL> vowelsFounded = new LinkedHashMap<Character, STATUS_VOGAL>();
		while (input.hasNext()) {

			// Verifica se ja foram descartadas todas as vogais. Caso tenha sido, para o processamento do Stream;
			// Isso evita o processamento desnecessario pois nao ha mais vogais que se adequarao ao requisito
			if (!existsCandidateVowel(candidateVowelsControl)) {
				return propertiesReader.getProperty(MENSAGEM_ERRO_NAOLOCALIZADO);
			}

			// Le o proximo caracter do Stream
			char currentCharecter = input.getNext();

			// Verifica se e o caracter atual e uma letra. Esse tratamento e necessario caso o Stream possua simbolos 
			// ou numeros, por exemplo: ^,@,#,*,&,1,2,3,4, etc
			if (Character.isLetter(currentCharecter)) {

				// Verifica se e uma vogal
				if (vowel.indexOf(currentCharecter) >=0) {

					// Uma vez localizada a vogal, a adiciona ao map e verifica se ela foi localizada previamente.
					// Caso ja tenha sido localizada
					if (vowelsFounded.containsKey(currentCharecter)) {

						// Remove das vogais candidatas
						candidateVowelsControl.remove(currentCharecter);

						vowelsFounded.put(currentCharecter, STATUS_VOGAL.DISCARTED);
					} 
					else {

						// Significa que essa vogal eh candidata pois atende a todos os criterios
						if (isPreviousCharacterConsonant) {

							// Inclui a vogal no Map como possivel candidata
							vowelsFounded.put(currentCharecter, STATUS_VOGAL.CANDIDATE);
						}
						// Siginifica que a voga foi descartada pois embora tenha sido encontrada apenas uma vez no Stream ate esse momento,
						// o caracter anterior nao e uma constante
						else {
							// Significa que como a voga ja apareceu na Stream e seu caracter anterior nao e uma consoante, ela esta automaticamente descartada
							vowelsFounded.put(currentCharecter, STATUS_VOGAL.DISCARTED);
							
							// Remove das vogais candidatas
							candidateVowelsControl.remove(currentCharecter);
						}
					}
					isPreviousCharacterConsonant = false;
				}

				// Entao trata-se de uma consoante e nenhum processamento adicional e necessario
				else {
					isPreviousCharacterConsonant = true;
				}
			}
			else {
				// Nao e consoante e nao e vogal.
				isPreviousCharacterConsonant = false;
			}
		}

		// Agora, com o map preenchido, itera sobre cada vogal adicionada ao map localizando a primeira que tenha sido inserida como CANDIDATA
		for (Map.Entry<Character, STATUS_VOGAL> entry : vowelsFounded.entrySet()) {
			if (entry.getValue() == STATUS_VOGAL.CANDIDATE) {
				Character vogalResultado = entry.getKey();

				return vogalResultado.toString();
			}
		}

		// Significa que nao foram encontradas vogais que atendam aos requisitos
		return propertiesReader.getProperty(MENSAGEM_ERRO_NAOLOCALIZADO);
	} 

	/**
	 * Valida se o arquivo de properties esta preenchido e possui os parametros necessarios
	 */
	private void validatePropetiesFile() {
		if (this.propertiesReader == null) {
			throw new RuntimeException("Arquivo de Propertie Invalido");
		}
	}

	/**
	 * Valida se o stream enviado esta preenchido.
	 */
	private void validateStreamInput() {
		if (input == null) {
			throw new RuntimeException(propertiesReader.getProperty(MENSAGEM_ERRO_STREAMVAZIO));
		}
	}


}
