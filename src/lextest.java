import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class lextest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		cimpilermain a = new cimpilermain("code");
		while(a.nextToken());
		a.printtok();
		assertEquals("code", a.code);
	}

	@Test
	void testVar() {
		Token token = new Token("Var", "variable");
		assertEquals("Var", token.getType());
		assertEquals("variable", token.getValue());
	}

	@Test
	void testNextTokenWhiteSpace() {
	    cimpilermain lexer = new cimpilermain("a = 5");
	    assertTrue(lexer.nextToken());
	    assertEquals("Var", lexer.getCurrentToken().getType());
	    assertEquals("a", lexer.getCurrentToken().getValue());

	    assertTrue(lexer.nextToken());
	    assertEquals("EQ", lexer.getCurrentToken().getType());

	    assertTrue(lexer.nextToken());
	    assertEquals("NUM", lexer.getCurrentToken().getType());
	    assertEquals("5", lexer.getCurrentToken().getValue());

	    assertFalse(lexer.nextToken());
	}

	@Test
	void testNextTokenEmptyInput() {
	    cimpilermain lexer = new cimpilermain("");
	    assertFalse(lexer.nextToken());
	}
	
	@Test
	void testNextTokenInvalidCharacter() {
	    cimpilermain lexer = new cimpilermain("a@");
	    assertTrue(lexer.nextToken());
	    assertEquals("Var", lexer.getCurrentToken().getType());
	    assertEquals("a", lexer.getCurrentToken().getValue());
	}
	
	@Test
	void testNextTokenComplexExpression() {
	    cimpilermain lexer = new cimpilermain("a=(5+3)*2");
	    assertTrue(lexer.nextToken());
	    assertEquals("Var", lexer.getCurrentToken().getType());
	    assertEquals("a", lexer.getCurrentToken().getValue());

	    assertTrue(lexer.nextToken());
	    assertEquals("EQ", lexer.getCurrentToken().getType());

	    assertTrue(lexer.nextToken());
	    assertEquals("LP", lexer.getCurrentToken().getType());

	    assertTrue(lexer.nextToken());
	    assertEquals("NUM", lexer.getCurrentToken().getType());
	    assertEquals("5", lexer.getCurrentToken().getValue());

	}
	
	@Test
	void testLeftBracket() {
	    cimpilermain lexer = new cimpilermain("{");
	    assertTrue(lexer.nextToken());
	    assertEquals("LB", lexer.getCurrentToken().getType());
	}

	@Test
	void testRightBracket() {
	    cimpilermain lexer = new cimpilermain("}");
	    assertTrue(lexer.nextToken());
	    assertEquals("RB", lexer.getCurrentToken().getType());
	}

	@Test
	void testAddition() {
	    cimpilermain lexer = new cimpilermain("+");
	    assertTrue(lexer.nextToken());
	    assertEquals("PL", lexer.getCurrentToken().getType());
	}

	@Test
	void testSubtraction() {
	    cimpilermain lexer = new cimpilermain("-");
	    assertTrue(lexer.nextToken());
	    assertEquals("MIN", lexer.getCurrentToken().getType());
	}

	@Test
	void testDivision() {
	    cimpilermain lexer = new cimpilermain("/");
	    assertTrue(lexer.nextToken());
	    assertEquals("DIV", lexer.getCurrentToken().getType());
	}

	@Test
	void testMultiplication() {
	    cimpilermain lexer = new cimpilermain("*");
	    assertTrue(lexer.nextToken());
	    assertEquals("MULT", lexer.getCurrentToken().getType());
	}

	@Test
	void testComm() {
	    cimpilermain lexer = new cimpilermain("`");
	    assertTrue(lexer.nextToken());
	    assertEquals("COM", lexer.getCurrentToken().getType());
	}

	@Test
	void testString() {
	    cimpilermain lexer = new cimpilermain("\"hello\"");
	    assertTrue(lexer.nextToken());
	    assertEquals("STR", lexer.getCurrentToken().getType());
	    assertEquals("hello", lexer.getCurrentToken().getValue());
	}

	@Test
	void testPrintKeyword() {
	    cimpilermain lexer = new cimpilermain("print");
	    assertTrue(lexer.nextToken());
	    assertEquals("print", lexer.getCurrentToken().getType());
	}

	@Test
	void testPrivateKeyword() {
	    cimpilermain lexer = new cimpilermain("private");
	    assertTrue(lexer.nextToken());
	    assertEquals("priv", lexer.getCurrentToken().getType());
	}

	@Test
	void testPublicKeyword() {
	    cimpilermain lexer = new cimpilermain("public");
	    assertTrue(lexer.nextToken());
	    assertEquals("pub", lexer.getCurrentToken().getType());
	}

	@Test
	void testObjKeyword() {
	    cimpilermain lexer = new cimpilermain("obj");
	    assertTrue(lexer.nextToken());
	    assertEquals("obj", lexer.getCurrentToken().getType());
	}

	@Test
	void testRightParenthesis() {
	    cimpilermain lexer = new cimpilermain(")");
	    assertTrue(lexer.nextToken());
	    assertEquals("RP", lexer.getCurrentToken().getType());
	}
	
	@Test
	void testGetPreviousToken() {
	    cimpilermain lexer = new cimpilermain("a = 5");
	    assertTrue(lexer.nextToken()); // Token: Var
	    assertTrue(lexer.nextToken()); // Token: EQ
	    Token currentToken = lexer.getCurrentToken();
	    Token previousToken = lexer.getPreviousToken();

	    assertEquals("EQ", currentToken.getType());
	    assertEquals("Var", previousToken.getType());
	    assertEquals("a", previousToken.getValue());
	}
	
	@Test
	void testUnknownToken() {
	    String input = "a & b";
	    cimpilermain lexer = new cimpilermain(input);

	    // Set up output capture
	    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));

	    lexer.nextToken(); // Token: Var
	    lexer.nextToken(); // Unknown token: '&'

	    // Reset the System.out
	    System.setOut(System.out);

	    // Check if the expected error message is printed
	    String expectedOutput = "Token unknown at &";
	    assertTrue(outContent.toString().contains(expectedOutput));
	}

}