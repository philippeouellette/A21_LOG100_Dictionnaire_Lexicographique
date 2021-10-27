package dictio;

import static org.junit.Assert.*;

import org.junit.Test;

public class add_wordTest {

	@Test
	public void test() {
		LexiNode DictionnaryTest = new LexiNode();
		DictionnaryTest.add_word("", "a definition for testing purposes");
		assertEquals(0, DictionnaryTest.getChilds().size());
        assertEquals(Character.MIN_VALUE, DictionnaryTest.getRepresentative_letter());
        assertEquals(null, DictionnaryTest.getCurrent_word());
        assertEquals(null, DictionnaryTest.getDefinition());
	}
}
