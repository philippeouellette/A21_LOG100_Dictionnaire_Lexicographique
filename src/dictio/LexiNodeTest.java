package dictio;

import static org.junit.Assert.*;

import org.junit.Test;

public class LexiNodeTest {

	@Test
	public void AddingEmptyWordTest() {
		LexiNode DictionnaryTest = new LexiNode();
		DictionnaryTest.add_word("", "a definition for testing purposes");
		assertEquals(0, DictionnaryTest.getChilds().size());
        assertEquals(Character.MIN_VALUE, DictionnaryTest.getRepresentative_letter());
        assertEquals(null, DictionnaryTest.getCurrent_word());
        assertEquals(null, DictionnaryTest.getDefinition());
	}

    @Test
    public void AddingWordTest0(){
        LexiNode DictionnaryTest = new LexiNode();
		DictionnaryTest.add_word("test", "a definition for testing purposes");
        assertEquals(1, DictionnaryTest.getChilds().size());
        LexiNode first_node = DictionnaryTest.getChilds().get(0);
        assertEquals('t', first_node.getRepresentative_letter());

        LexiNode second_node = first_node.getChilds().get(0);
        assertEquals('e', second_node.getRepresentative_letter());
        
        LexiNode third_node = second_node.getChilds().get(0);
        assertEquals('s', third_node.getRepresentative_letter());
        
        LexiNode fourth_node = third_node.getChilds().get(0);
        assertEquals('t', fourth_node.getRepresentative_letter());
        assertEquals("test", fourth_node.getCurrent_word());
        assertEquals("a definition for testing purposes", fourth_node.getDefinition());
    }

    @Test
    public void SearchWordsTest0(){
        LexiNode DictionnaryTest = new LexiNode();
		DictionnaryTest.add_word("test", "a definition for testing purposes");
        assertEquals("test§", DictionnaryTest.search_words("t"));
        assertEquals("test§", DictionnaryTest.search_words("te"));
        assertEquals("test§", DictionnaryTest.search_words("tes"));
        assertEquals("test & a definition for testing purposes§", DictionnaryTest.search_words("test"));
    }

    @Test
    public void SearchWordsTest1(){
        LexiNode DictionnaryTest = new LexiNode();
		DictionnaryTest.add_word("premiermot", "a definition for testing purposes");
		DictionnaryTest.add_word("premiermotprime", "a definition for testing purposes");
        DictionnaryTest.add_word("deuxiememot", "a definition for testing purposes");

        assertEquals("premiermot§premiermotprime§", 
                     DictionnaryTest.search_words("premier"));
        assertEquals("premiermot & a definition for testing purposes§premiermotprime§§", 
                      DictionnaryTest.search_words("premiermot"));
        assertEquals("premiermotprime§", 
                      DictionnaryTest.search_words("premiermotpri"));
        assertEquals("premiermotprime & a definition for testing purposes§", 
                      DictionnaryTest.search_words("premiermotprime"));
        assertEquals("deuxiememot§", DictionnaryTest.search_words("d"));
    }

    @Test
    public void EditWordTest0(){
        LexiNode DictionnaryTest = new LexiNode();
		DictionnaryTest.add_word("pm", "a definition for premiermot");
        
        assertEquals("a definition for premiermot", 
                      DictionnaryTest.getChilds().get(0).getChilds().get(0).getDefinition());
        DictionnaryTest.edit_word("pm", "a definition for premiermot but a little different");
        assertEquals("a definition for premiermot but a little different", 
                      DictionnaryTest.getChilds().get(0).getChilds().get(0).getDefinition());
    }

    @Test
    public void EditWordTest1(){
        LexiNode DictionnaryTest = new LexiNode();
		DictionnaryTest.add_word("pm", "a definition for premiermot");
    
        DictionnaryTest.edit_word("pm", "a definition for premiermot but a little different");

        assertEquals("pm§", DictionnaryTest.search_words("p"));
        DictionnaryTest.edit_word("pmp", "a definition for premiermot but for the prime version");
        assertEquals("pm & a definition for premiermot but a little different§pmp§§", DictionnaryTest.search_words("pm"));
        assertEquals("a definition for premiermot but for the prime version", 
                      DictionnaryTest.getChilds().get(0).getChilds().get(0).getChilds().get(0).getDefinition());
    }

    @Test
    public void GetAllWordsTest(){
        LexiNode DictionnaryTest = new LexiNode();
		DictionnaryTest.add_word("pomme", "a definition");
		DictionnaryTest.add_word("poire", "a definition");
		DictionnaryTest.add_word("banane", "a definition");
		DictionnaryTest.add_word("ananas", "a definition");
		DictionnaryTest.add_word("framboise", "a definition");

        assertEquals("pomme§poire§banane§ananas§framboise§", DictionnaryTest.get_all_words());
    }

    @Test
    public void GetAllWordsAndDefinitionTest(){
        LexiNode DictionnaryTest = new LexiNode();
		DictionnaryTest.add_word("pomme", "a definition");
		DictionnaryTest.add_word("poire", "a definition");
		DictionnaryTest.add_word("banane", "a definition");
		DictionnaryTest.add_word("ananas", "a definition");
		DictionnaryTest.add_word("framboise", "a definition");

        assertEquals("pomme & a definition\npoire & a definition\nbanane & a definition\nananas & a definition\nframboise & a definition\n", DictionnaryTest.get_all_words_and_definitions());
    }
}
