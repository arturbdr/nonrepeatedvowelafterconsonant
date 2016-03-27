package br.com.exercicio.messageutil;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Classe simples apenas para leitura do arquivo de properties application.properties
 * Em ambientes com Spring ou Apache DeltaSpike, a leitura e a gestão de acesso aos arquivos
 * de properties ficariam a cargo do framework  
 *
 */
public class PropertiesReader {

	private final Properties applicationProperties = new Properties();

	/**
	 *  Construtor privado para previnir novas instancias
	 */
	private PropertiesReader() {

		InputStream in = this.getClass().getClassLoader().getResourceAsStream("application.properties");
		try {
			applicationProperties.load(in);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Implementacao do pattern Lazy Holder para implementação de Singleton
	 */
	private static class LazyHolder {
		private static final PropertiesReader INSTANCE = new PropertiesReader();
	}

	public static PropertiesReader getInstance() {
		return LazyHolder.INSTANCE;
	}

	/**
	 * Retorna uma propriedade do arquivo application.properties
	 * @param key - chave desejada para leitura
	 * @return - O valor do propertie solicitado
	 */
	public String getProperty(String key) {
		return applicationProperties.getProperty(key);
	}
}



