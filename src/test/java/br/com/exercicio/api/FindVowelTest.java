package br.com.exercicio.api;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.exercicio.impl.FindVowelImpl;
import br.com.exercicio.impl.StreamStringImpl;
import br.com.exercicio.messageutil.PropertiesReader;
import br.com.exercicio.test.builder.FindVowelBuilder;

public class FindVowelTest {
	
	private PropertiesReader propertiesReader;
	private FindVowelBuilder findVowelBuilder;
	
	@Before
	public void setUp() {
		propertiesReader = PropertiesReader.getInstance();
		findVowelBuilder = new FindVowelBuilder();
	}
	
	@Test
	public void enunciationTest(){
		// Cenario que testa o proposto no Hacker Rank
		String stream1 = "aAbBABacfe"; // e
		
		findVowelBuilder.withPropertiesReader(propertiesReader);
		findVowelBuilder.withStreamAPI(new StreamStringImpl(stream1));
		FindVowelImpl findVowel1 = findVowelBuilder.build();
		
		assertEquals("e", findVowel1.searchVowel());
	}
	
	@Test
	public void shouldFindSimpleMatchVowel(){
		// Cria Streams Simples para testes.
		// Todos devem localizar a vogal. Como os cenários abaixo visam realizar os mesmos testes eles foram agrupados em um unico teste unitario
		String simpleStream1 = "ATAta"; // a
		String simpleStream2 = "AHa"; 	// a
		String simpleStream3 = "EHHe"; 	// e
		String simpleStream4 = "IBeTA"; // e
		String simpleStream5 = "LAla"; 	// A
		String simpleStream6 = "HOLU"; 	// O

		findVowelBuilder.withPropertiesReader(propertiesReader);
		findVowelBuilder.withStreamAPI(new StreamStringImpl(simpleStream1));
		FindVowelImpl findVowel1 = findVowelBuilder.build();
		
		findVowelBuilder.withStreamAPI(new StreamStringImpl(simpleStream2));
		FindVowelImpl findVowel2 = findVowelBuilder.build();
		
		findVowelBuilder.withStreamAPI(new StreamStringImpl(simpleStream3));
		FindVowelImpl findVowel3 = findVowelBuilder.build();
		
		findVowelBuilder.withStreamAPI(new StreamStringImpl(simpleStream4));
		FindVowelImpl findVowel4 = findVowelBuilder.build();
		
		findVowelBuilder.withStreamAPI(new StreamStringImpl(simpleStream5));
		FindVowelImpl findVowel5 = findVowelBuilder.build();
		
		findVowelBuilder.withStreamAPI(new StreamStringImpl(simpleStream6));
		FindVowelImpl findVowel6 = findVowelBuilder.build();
		
		assertEquals("a", findVowel1.searchVowel());
		assertEquals("a", findVowel2.searchVowel());
		assertEquals("e", findVowel3.searchVowel());
		assertEquals("e", findVowel4.searchVowel());
		assertEquals("A", findVowel5.searchVowel());
		assertEquals("O", findVowel6.searchVowel());
	}
	
	@Test
	public void shouldNotFindVowelRepeatedVowel(){
		String simpleStream1 = "Taa"; // nao existe. Vogal a repetida
		
		findVowelBuilder.withPropertiesReader(propertiesReader);
		findVowelBuilder.withStreamAPI(new StreamStringImpl(simpleStream1));
		FindVowelImpl findVowel1 = findVowelBuilder.build();
		
		assertEquals(propertiesReader.getProperty(FindVowelAPI.MENSAGEM_ERRO_NAOLOCALIZADO), findVowel1.searchVowel());
	}
	
	@Test
	public void shouldNotFindVowelNoConsonantBeforeVowel(){
		// Os Stream abaixo nao localizarao vogais que atendem o requisito.
		// Como todos os cenários de Stream abaixo visam o mesmo teste e possuem o mesmo retorno, eu os agrupei em um mesmo teste unitario
		String stream1 = "a"; 		// nao existe pois não há consoante antes de vogal
		String stream2 = "T"; 			// nao existe pois não há consoante antes de vogal
		String stream3 = "appp"; 		// nao existe pois não há consoante antes de vogal
		String stream4 = "uUaAEiZ"; 	// nao existe pois não há consoante antes de vogal
		String stream5 = "aIa"; 		// nao existe pois não há consoante antes de vogal
		String stream6 = "AEIOUaeiou"; 	// nao existe pois não há consoante antes de vogal
		
		findVowelBuilder.withPropertiesReader(propertiesReader);
		
		findVowelBuilder.withStreamAPI(new StreamStringImpl(stream1));
		FindVowelImpl findVowel1 = findVowelBuilder.build();
		
		findVowelBuilder.withStreamAPI(new StreamStringImpl(stream2));
		FindVowelImpl findVowel2 = findVowelBuilder.build();
		
		findVowelBuilder.withStreamAPI(new StreamStringImpl(stream3));
		FindVowelImpl findVowel3 = findVowelBuilder.build();
		
		findVowelBuilder.withStreamAPI(new StreamStringImpl(stream4));
		FindVowelImpl findVowel4 = findVowelBuilder.build();
		
		findVowelBuilder.withStreamAPI(new StreamStringImpl(stream5));
		FindVowelImpl findVowel5 = findVowelBuilder.build();
		
		findVowelBuilder.withStreamAPI(new StreamStringImpl(stream6));
		FindVowelImpl findVowel6 = findVowelBuilder.build();
		
		assertEquals(propertiesReader.getProperty(FindVowelAPI.MENSAGEM_ERRO_NAOLOCALIZADO), findVowel1.searchVowel());
		assertEquals(propertiesReader.getProperty(FindVowelAPI.MENSAGEM_ERRO_NAOLOCALIZADO), findVowel2.searchVowel());
		assertEquals(propertiesReader.getProperty(FindVowelAPI.MENSAGEM_ERRO_NAOLOCALIZADO), findVowel3.searchVowel());
		assertEquals(propertiesReader.getProperty(FindVowelAPI.MENSAGEM_ERRO_NAOLOCALIZADO), findVowel4.searchVowel());
		assertEquals(propertiesReader.getProperty(FindVowelAPI.MENSAGEM_ERRO_NAOLOCALIZADO), findVowel5.searchVowel());
		assertEquals(propertiesReader.getProperty(FindVowelAPI.MENSAGEM_ERRO_NAOLOCALIZADO), findVowel6.searchVowel());
	}
	
	@Test
	public void symbolShouldMatchVowel(){
		
		// Cria Streams com mais complexas com símbolos, números e consoantes para testar o match. 
		// Todos devem localizar a vogal e por isso foram agrupados em um unico teste unitario
		String symbolStream1 = "!^T^1Ta"; 	// a
		String symbolStream2 = "AH?@#Ta"; 	// a
		String symbolStream3 = "Z<>Ma"; 	// a
		String symbolStream4 = "IB(!He%¨TA"; //e
		String symbolStream5 = "L**&zAla"; 	// A
		String symbolStream6 = ".,Hç;:'o'\"AkOLU"; //O
		
		findVowelBuilder.withPropertiesReader(propertiesReader);
		findVowelBuilder.withStreamAPI(new StreamStringImpl(symbolStream1));
		FindVowelImpl findVowel1 = findVowelBuilder.build();
		
		findVowelBuilder.withStreamAPI(new StreamStringImpl(symbolStream2));
		FindVowelImpl findVowel2 = findVowelBuilder.build();
		
		findVowelBuilder.withStreamAPI(new StreamStringImpl(symbolStream3));
		FindVowelImpl findVowel3 = findVowelBuilder.build();
		
		findVowelBuilder.withStreamAPI(new StreamStringImpl(symbolStream4));
		FindVowelImpl findVowel4 = findVowelBuilder.build();
		
		findVowelBuilder.withStreamAPI(new StreamStringImpl(symbolStream5));
		FindVowelImpl findVowel5 = findVowelBuilder.build();
		
		findVowelBuilder.withStreamAPI(new StreamStringImpl(symbolStream6));
		FindVowelImpl findVowel6 = findVowelBuilder.build();
		
		assertEquals("a", findVowel1.searchVowel());
		assertEquals("a", findVowel2.searchVowel());
		assertEquals("a", findVowel3.searchVowel());
		assertEquals("e", findVowel4.searchVowel());
		assertEquals("A", findVowel5.searchVowel());
		assertEquals("O", findVowel6.searchVowel());
		
	}
	
	@Test
	public void midExtenseStreamShouldMatchVowel(){
		
		// Cria Streams um pouco mais extensas com símbolos, números e consoante para testar o match. 
		// Todos devem localizar a vogal e por isso foram agrupados em um unico teste unitario
		String extenseStream1 = "!^T^dweou   mvpeso,cseiuhncvpsempcoueh,pqpri´wuc,pot,´wpeiqru,mxEORTUMP EROCWPECWEPCOMWUP   35027345- EORPIGJWEPRGOIEWRKGCPIC!! 04()()()()(OIUEOIUOIUCOEIUEOIUepoiepoeoieu1Ta"; // a
		String extenseStream2 = "!^T^VdaW  qzXc,#$¨(&*%(#qpri´wuc,pot,´wpViqru,mxVORTUMP VROCWPVCWVPCOMWUP   56351618 !a! - VeORPIGJWVPRGOIVWRKGCPIC!! 04(())()(OIUVOIUOIUCOVIUVOIUVpoiVpoVoiVu1Ta"; // e
		String extenseStream3 = "!^T^VdaW  qzXc,#$¨(&*%(#qpri´wuc,pot,´wpViqru,mxVORTUMP VROCWPVCWVPCOMWUP   56351618 !a! - VORPIGJWVPRGOIVWRKGCPIC!! 04(())()(OIUVOIUOIUCOVIUVOIUVpoiVpoVoiVu1Ta"
				+ "!^T^VdaW  qzXc,#$¨(&*%(#qpri´wuc,pot,´wpViqru,mxVORTUMP VROCWPVCWVPCOMWUP   56351618 !a! - VORPIGJWVPRGOIVWRKGCPIC!! 04(())()(OIUVOIUOIUCOVIUVOIUVpoiVpoVoiVu1Ta"
				+ "!^T^VdaW  qzXc,#$¨(&*%(#yjfujfuj´wuc,pot,´wpViqru,mxVORTUMP VROCjyfujfyujfyujWPVCWVPCOMWUP   56351618 !a! - VORPIGJWVPRGOIVWRKGCPIC!! 04(())()(OIUVOIUOIUCOVIUVOIUVpoiVpoVoiVu1Ta"
				+ "!^T^VdaW  qzXc,#$¨(&*%(#qpri´wuc,pot,´wpViqru,mxVORTUMP VROCWPVCfyujyufWVPCOMWUP   56351618 !a! - VORPIGJWVPRGOIVWRKGCPIC!! 04(())()(OIUVOIUOIUCOVIUVOIUVpoiVpoVoiVu1Ta"
				+ "!fyujfyujfyujyfujyfu^T^VdaW  qzXc,#$¨(&*%(#qpri´wuc,pot,´wpViqujru,mxVORTfyujfyujUMP VROCWPVCWVPCOMWUP   56351618 !a! - VORPIGJWVPRGOIVWRKGCPIC!! 04(())()(OIUVOIUOIUCOVIUVOIUVpoiVpoVoiVu1Ta"
				+ "!jfyujyfujfyujyfu^T^VjfyujdaW  qzXc,#$¨(&*%(#qpri´wuc,pot,´wpViqru,mxVORTUMP VRfeyujfyujfOCWPVCWVPCOMWUP   56351618 !a! - VORPIGJWVPRGOIVWRKGCPIC!! 04(())()(OIUVOIUOIUCOVIUVOIUVpoiVpoVoiVu1Ta"
				+ "!^T^VfyujfyudaW  qzXc,#$¨(&*%(#qpri´wuc,pot,´wpViqru,mxVORTUMP VROCWPVCWVPjfyujfyujCOMWUP   56351618 !a! - VORPIGJWVPRGOIVWRKGCPIC!! 04(())()(OIUVOIUOIUCOVIUVOIUVpoiVpoVoiVu1Ta"
				+ "!^T^VdaW  qzXc,#$¨yujfyjfyu(&*%(#qpri´yfujwuc,pot,´wpViqru,mxVORTUfyujfyujfyuMP VROCWPVCWVPCOMWUP   56351618 !a! - VORPIGJWVPRGOIVWRKGCPIC!! 04(())()(OIUVOIUOIUCOVIUVOIUVpoiVpoVoiVu1Ta"; // e
		
		
		findVowelBuilder.withPropertiesReader(propertiesReader);
		findVowelBuilder.withStreamAPI(new StreamStringImpl(extenseStream1));
		FindVowelImpl findVowel1 = findVowelBuilder.build();
		
		findVowelBuilder.withStreamAPI(new StreamStringImpl(extenseStream2));
		FindVowelImpl findVowel2 = findVowelBuilder.build();
		
		findVowelBuilder.withStreamAPI(new StreamStringImpl(extenseStream3));
		FindVowelImpl findVowel3 = findVowelBuilder.build();
		
		assertEquals("a", findVowel1.searchVowel());
		assertEquals("e", findVowel2.searchVowel());
		assertEquals("e", findVowel3.searchVowel());
	}
	
	
	@Test(expected=RuntimeException.class)
	public void noStreamSendShouldThrowException(){
		
		// Cria um objeto nulo de Stream e tenta realizar a procura de vogais.
		// Devera ser lancada uma exception no momento da criação (Fast Fail)
		findVowelBuilder.withPropertiesReader(propertiesReader);
		findVowelBuilder.withStreamAPI(null); // Stream nula
		findVowelBuilder.build();
	}
	
	@Test(expected=RuntimeException.class)
	public void noPropertieFileShouldThrowException(){
		
		// Cria um objeto nulo de properties e tenta realizar a procura de vogais.
		// Devera ser lancada uma exception
		String stream = "Agk";
		
		findVowelBuilder.withPropertiesReader(null); // Propertie nulo
		findVowelBuilder.withStreamAPI(new StreamStringImpl(stream));
		findVowelBuilder.build();
	}
	
	
	@Test
	public void noStreamSendShouldReturnMessage(){
		
		// Cria um Stream sem dados e tenta localizar a vogal
		// Devera ser retornada uma mensagem de vogal nao encontrada no Stream informado.
		
		findVowelBuilder.withPropertiesReader(propertiesReader);
		findVowelBuilder.withStreamAPI(new StreamStringImpl(null)); // Conteudo do Stream é nulo
		FindVowelImpl findVowel = findVowelBuilder.build();
		assertEquals(propertiesReader.getProperty(FindVowelAPI.MENSAGEM_ERRO_NAOLOCALIZADO), findVowel.searchVowel());
	}
	
	
	@Test
	public void shouldNotMatchEarlier() {
		// Esse teste visa acabar com todas as possibilidades de vogais logo no começo do teste.
		// A implementacao feita visa essa melhoria de performance nao onerando o processamento desnecessariamente
		
		String extenseStream1 = "aeiouAEIOU nao ha mais opcoes de vogal e o stream nao será mais lido!^T^VdaW  qzXc,#$¨(&*%(#qpri´wuc,pot,´wpViqru,mxVORTUMP VROCWPV"
				+ "CWVPCOMWUP   56351618 !a! - VORPIGJWVPRGOIVWRKGCPIC!! 04(())()(OIUVOIUOIUCOVIUVOIUVpoiVpoVoiVu1Ta"
				+ "!^T^VdaW  qzXc,#$¨(&*%(#qpri´wuc,pot,´wpViqru,mxVORTUMP VROCWPVCWVPCOMWUP   56351618 !a! - VORPIGJWVPRGOIVWRKGCPIC!! 04(())()(OIUVOIUOIUCOVIUVOIUVpoiVpoVoiVu1Ta"
				+ "!^T^VdaW  qzXc,#$¨(&*%(#yjfujfuj´wuc,pot,´wpViqru,mxVORTUMP VROCjyfujfyujfyujWPVCWVPCOMWUP   56351618 !a! - VORPIGJWVPRGOIVWRKGCPIC!! 04(())()(OIUVOIUOIUCOVIUVOIUVpoiVpoVoiVu1Ta"
				+ "!^T^VdaW  qzXc,#$¨(&*%(#qpri´wuc,pot,´wpViqru,mxVORTUMP VROCWPVCfyujyufWVPCOMWUP   56351618 !a! - VORPIGJWVPRGOIVWRKGCPIC!! 04(())()(OIUVOIUOIUCOVIUVOIUVpoiVpoVoiVu1Ta"
				+ "!fyujfyujfyujyfujyfu^T^VdaW  qzXc,#$¨(&*%(#qpri´wuc,pot,´wpViqujru,mxVORTfyujfyujUMP VROCWPVCWVPCOMWUP   56351618 !a! - VORPIGJWVPRGOIVWRKGCPIC!! 04(())()(OIUVOIUOIUCOVIUVOIUVpoiVpoVoiVu1Ta"
				+ "!jfyujyfujfyujyfu^T^VjfyujdaW  qzXc,#$¨(&*%(#qpri´wuc,pot,´wpViqru,mxVORTUMP VRfeyujfyujfOCWPVCWVPCOMWUP   56351618 !a! - VORPIGJWVPRGOIVWRKGCPIC!! 04(())()(OIUVOIUOIUCOVIUVOIUVpoiVpoVoiVu1Ta"
				+ "!^T^VfyujfyudaW  qzXc,#$¨(&*%(#qpri´wuc,pot,´wpViqru,mxVORTUMP VROCWPVCWVPjfyujfyujCOMWUP   36546 !a! - VORPIGJWVPRGOIVWRKGCPIC!! 04(())()(OIUVOIUOIUCOVIUVOIUVpoiVpoVoiVu1Ta"
				+ "!^T^VfyujfyudaW  qzXc,#$¨(&*%(#qpri´wuc,pot,´wpViqru,mxVORTUMP VROCWPVCWVPjfyujfyujCOMWUP   5635fewfwe1618 !a! - VORPIGJWVPRGOIVWRKGCPIC!! 04(())(wefwe)(OIUVOIUOIUCOVIUVOIUVpoiVpoVoiVu1Ta"
				+ "!^T^VfyujfyudaW  qzXc,#$¨(&*%(#qpri´wuc,pot,´wpViqru,mxVORTUMP VROCWPVCWVPjfyujfyujCOwefewfewMWUP   56351618 !a! - VORPIfwefGJWVPRGOIVWRKGCPIC!! 04(())()(OIUVOIUOIUCOVIUVOIUVpoiVpoVoiVu1Ta"
				+ "!^T^VfyujfyudaW  qzXc,#fwefwe$¨(&*%(#qpri´wuc,pot,fewfwe´wpViqru,mxVORTUMP VROCWPVCWVPjfyujfyujCOMWUP   56351618 !a! - VORPIGJWVPRGOIVWRKGCPIC!! 04(())()(OIUVOIUOIUCOVIUVOIUVpoiVpoVoiVu1Ta"
				+ "!^T^VfyujfyudaW  qzXc,#$¨(&*%(#qpri´wuc,pot,´wpViqru,mxVORTUMP VROCWPVCWVPjfyujfyujCOMWUP   5637658758651618 !a! - VfwefORPIGJWVPRGOIVWRKGCwfPIC!! 04(())()(OIUVOIUOIUCOVIUVOIUVpoiVpoVoiVu1Ta"
				+ "!^T^VfyujfyudaW  qzXc,#$¨(&*%(#qpri´wuc,pot,´wpViqru,mxVORTUMP VROCWPVCWVPjfyujfyujCOMWUP   56351618 !a! - ewf!! 04(())()(OIUVOIUOIUCOVIUVOIUVpoiVpoVoiVu1Ta"
				+ "!^T^VfyujfyudaW  qzXc,#$¨(&*%(#qpri´wuc,pot,´wpViqru,mxVORTUMP VROCWPVCWVPjfyujfyujCOMWUP   56351908-0-09-618 !a! - VORwefwefwefPIGJWVPRGOIVWRKGCPIC!! 04(())()(OIUVOIUOIUCOVIUVOIUVpoiVpoVoiVu1Ta"
				+ "!^T^VdaW  qzXc,#$¨yujfyjfyu(&*%(#qpri´yfujwuc,pot,´wpViqru,mxVORTUfyujfyujfyuMP VROCWPVCWVPCOMWUP   56351618 !a! - VORPIGJWVPRGOIVWRKGCPIC!! 04(())()(OIUVOIUOIUCOVIUVOIUVpoiVpoVoiVu1Ta";
		
		findVowelBuilder.withPropertiesReader(propertiesReader);
		findVowelBuilder.withStreamAPI(new StreamStringImpl(extenseStream1));
		FindVowelImpl findVowel = findVowelBuilder.build();
		
		assertEquals(propertiesReader.getProperty(FindVowelAPI.MENSAGEM_ERRO_NAOLOCALIZADO), findVowel.searchVowel());
	}
	
}
