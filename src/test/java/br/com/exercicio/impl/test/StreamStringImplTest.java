package br.com.exercicio.impl.test;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import br.com.exercicio.impl.StreamStringImpl;

/**
 * Classe de teste referente a StreamStringImpl
 *
 */
public class StreamStringImplTest {
	
	private StreamStringImpl streamStringImpl;
	private char NULL_CHAR_LITERAL = '\u0000';

	
	@Test
	public void shouldReturnACharacter() {
		// Verifica o metodo getNext() retorna o proximo caracter a ser processado
		String stream1 = "AbC";
		streamStringImpl = new StreamStringImpl(stream1);
		
		assertEquals('A', streamStringImpl.getNext());
		assertEquals('b', streamStringImpl.getNext());
		assertEquals('C', streamStringImpl.getNext());
	}
	
	@Test
	public void shouldReturnExistingAStream() {
		// Verifica o metodo hasNext() informa se existe Stream a ser processado
		String stream1 = "AbC";
		streamStringImpl = new StreamStringImpl(stream1);
		
		assertEquals(true, streamStringImpl.hasNext());
	}
	
	@Test
	public void shouldReturnLiteralNullCharacter() {
		// Verifica se o retorno do metodo getNext() para uma Stream vazia/fim da Stream e um char null
		String stream1 = "";
		streamStringImpl = new StreamStringImpl(stream1);
		
		assertEquals(NULL_CHAR_LITERAL, streamStringImpl.getNext());
	}
	
	@Test
	public void shouldNotReturnNextStreamChar() {
		// Verifica se o retorno do metodo hasNext() apos chegar ao fim de uma Stream e falso
		String stream1 = "";
		String stream2 = "A";
		
		
		streamStringImpl = new StreamStringImpl(stream1);
		assertEquals(false, streamStringImpl.hasNext());
		
		streamStringImpl = new StreamStringImpl(stream2);
		streamStringImpl.getNext();
		assertEquals(false, streamStringImpl.hasNext());
	}
	
}
