package br.com.exercicio.impl.test;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import br.com.exercicio.impl.StreamStringImpl;

public class StreamStringImplTest {
	
	private StreamStringImpl streamStringImpl;
	private char NULL_CHAR_LITERAL = '\u0000';

	
	@Test
	public void shouldReturnACharacter() {
		String stream1 = "AbC";
		streamStringImpl = new StreamStringImpl(stream1);
		
		assertEquals('A', streamStringImpl.getNext());
		assertEquals('b', streamStringImpl.getNext());
		assertEquals('C', streamStringImpl.getNext());
	}
	
	@Test
	public void shouldReturnExistingAStream() {
		String stream1 = "AbC";
		streamStringImpl = new StreamStringImpl(stream1);
		
		assertEquals(true, streamStringImpl.hasNext());
	}
	
	@Test
	public void shouldReturnLiteralNullCharacter() {
		String stream1 = "";
		streamStringImpl = new StreamStringImpl(stream1);
		
		assertEquals(NULL_CHAR_LITERAL, streamStringImpl.getNext());
	}
	
	@Test
	public void shouldNotReturnNextStreamChar() {
		String stream1 = "";
		String stream2 = "A";
		
		
		streamStringImpl = new StreamStringImpl(stream1);
		assertEquals(false, streamStringImpl.hasNext());
		
		streamStringImpl = new StreamStringImpl(stream2);
		streamStringImpl.getNext();
		assertEquals(false, streamStringImpl.hasNext());
	}
	
}
