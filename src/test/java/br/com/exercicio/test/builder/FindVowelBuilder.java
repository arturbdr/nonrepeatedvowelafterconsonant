package br.com.exercicio.test.builder;

import br.com.exercicio.api.StreamAPI;
import br.com.exercicio.impl.FindVowelImpl;
import br.com.exercicio.messageutil.PropertiesReader;

/**
 * Classe para facilitar a Criacao de FindVowel
 * Design Pattern - Test Data Builders
 */
public class FindVowelBuilder {
	
	private PropertiesReader propertiesReader; 
	private StreamAPI input;

	
	public FindVowelBuilder(){}
	
	
	public FindVowelBuilder withPropertiesReader(PropertiesReader propertiesReader){
		this.propertiesReader = propertiesReader;
		return this;
	}
	
	public FindVowelBuilder withStreamAPI(StreamAPI input) {
		this.input = input;
		return this;
	}
	
	public FindVowelImpl build() {
		return new FindVowelImpl(propertiesReader, input);
	}
	
	
}
