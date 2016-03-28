package br.com.exercicio.main;

import br.com.exercicio.api.FindVowelAPI;
import br.com.exercicio.api.StreamAPI;
import br.com.exercicio.impl.FindVowelImpl;
import br.com.exercicio.impl.StreamStringImpl;
import br.com.exercicio.messageutil.PropertiesReader;

/**
 * Classe auxiliar para execucao atraves de linha de comando
 *
 */
public class Main {
	
	public static void main(String[] args) {
		
		// Le a Stream enviada na linha de comando.
		String stream = args.length >= 1 ? args[0] : null;
		StreamAPI streamAPI = stream == null? null : new StreamStringImpl(stream);
		
		// Possuira o resultado do processamento
		String resultMessage = null;
		
		try {
			FindVowelAPI findVowelAPI = new FindVowelImpl(PropertiesReader.getInstance(), streamAPI);
			resultMessage = findVowelAPI.searchVowel();
		}
		catch (RuntimeException e) {
			// Nao e necessario retornar o stacktrace para o usuario.
			// Em um cenario real, o stacktrace seria armazenado em logs, bases de dados NoSQL etc
			resultMessage = e.getMessage();
		}

		System.out.println(resultMessage);
	}

}



