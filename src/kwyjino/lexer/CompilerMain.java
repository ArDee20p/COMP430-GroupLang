package kwyjino.lexer;

import java.util.Arrays;
import java.util.LinkedList;

import kwyjino.tokenizer.*;

public class compilermain {
	public String code="";
	private int codeLength=code.length();
	public String teststring="";
	public String testtypes="";
	
		LinkedList<Token> list=new LinkedList<Token>();
		public void printtok() {
			for(int i=0; i<list.size();i++) {
				System.out.print(list.get(i).getType());
				teststring=teststring+list.get(i).getValue();
				testtypes=testtypes+list.get(i).getType();
			}
		}
		public compilermain(String codeInput){
			code=codeInput;
			codeLength=code.length();
		}
	  private int currentIndex=0;
	  private Token currentToken;
	  private Token previousToken;
	  private boolean UseWarnings=false;
	  ////////////////////////////////////////////////doesn't know what an & is. May or may not be relevant later
	   /**
	   * Updates currentToken to the next valid Token if it is available.
	   *
	   * @return true, if a valid token is available next.
	   */
	  public boolean nextToken() {

	    while (currentIndex<codeLength) { 
	    	
	    	previousToken = currentToken; // in case you need the previous token
	    	 char currentChar = code.charAt(currentIndex);
	      if (Arrays.asList(' ', '\r', '\t', '\n').contains(currentChar)) { // ignore
	    	  skipWhiteSpace();
	    	  continue;
	      }
	      else if (currentChar == '=') { // 2. SET
	        currentToken = new Token("EQ");
	        currentIndex++;
	      }
	      else if (currentChar == '{') { // 2. SET
		        currentToken = new Token("LB");
		        currentIndex++;
		      }
	      else if (currentChar == '}') { // 2. SET
		        currentToken = new Token("RB");
		        currentIndex++;
		      }
	      else if (currentChar == '+') { // 2. add
		        currentToken = new Token("PL");
		        currentIndex++;
		      }
	      else if (currentChar == '-') { // 2. subtract
		        currentToken = new Token("MIN");
		        currentIndex++;
		      }
	      else if (currentChar == '/') { // 2. divide
		        currentToken = new Token("DIV");
		        currentIndex++;
		      }
	      else if (currentChar == '*') { // 2. multiply
		        currentToken = new Token("MULT");
		        currentIndex++;
		      }
	      else if (currentChar == '`') { // string grabber
	    	  	currentIndex++;
		        String variableName="";
		        while(code.charAt(currentIndex)!='`')
		        {
		        	
		        	 variableName= variableName+code.charAt(currentIndex);
		        	 currentIndex++;
		        	 
		        }
		        currentIndex++;
		        currentToken = new Token("COM", variableName);
		        	
		      }
	      else if (currentChar == '"') { // string grabber
	    	  	currentIndex++;
		        String variableName="";
		        while(code.charAt(currentIndex)!='"')
		        {
		        	
		        	 variableName= variableName+code.charAt(currentIndex);
		        	 currentIndex++;
		        	 
		        }
		        currentIndex++;
		        currentToken = new Token("STR", variableName);
		        	
		      }
	      else if (Character.isDigit(currentChar))
	      { // 3. INT
	    	  currentToken = new Token("NUM", readNumber());
	      }
	      else if (Character.isLetter(currentChar))
	      {
	    	  String variableName = readVariable();
	       
	    	  //This is where command cases go. Print, classes, access
	    	  if (variableName.equalsIgnoreCase("print"))
	    	  { // PRINT
	    		  currentToken = new Token("print");
	    	  }
	    	  else if (variableName.equalsIgnoreCase("private"))
	    	  { // private modifier
	    		  currentToken = new Token("priv");
	    	  }
	    	  else if (variableName.equalsIgnoreCase("public"))
	    	  { // Public modifier
	    		  currentToken = new Token("pub");
	    	  }
	    	  else if (variableName.equalsIgnoreCase("obj"))
	    	  { // object
	    		  currentToken = new Token("obj");
	    	  }
		      else
		      { // VAR
		    	  currentToken = new Token("Var", variableName);
		      }
	      }
	      else if (currentChar == '(')
	      { //Lparen
	    	  currentToken = new Token("LP");
	    	  currentIndex++;
		  }
	      else if (currentChar == ')')
	      { // Rparen
	    	  currentToken = new Token("RP");
	    	  currentIndex++;
		  }
	      else if (currentChar == '!')
	      { // enable warnings instead of errors
	    	  UseWarnings=true;
	    	  currentIndex++;
		  }
	      
	      
	      
	      
	      
	      else {
	        System.out.print("Token unknown at "+currentChar);
	      }
	      
	      if(currentToken!=previousToken) {
	      list.add(currentToken);
	      }
	      return true;
	    }
	    return false;
	  }

	  /**
	   * Read Integer as String
	   *
	   * @return String value of Integer Number.
	   */
	  private String readNumber() {
		    StringBuilder sb = new StringBuilder();
		    char currentChar = code.charAt(currentIndex);
		    while (currentIndex<=codeLength && Character.isDigit(currentChar)) {
		      sb.append(currentChar);
		      currentIndex++;
		      if (currentIndex>=codeLength) break;
		      currentChar = code.charAt(currentIndex);
		    }
		    return sb.toString();
		  }

	  /** @return String read from current index. */
	  private String readVariable() {
		    StringBuilder sb = new StringBuilder();
		    char currentChar = code.charAt(currentIndex);
		    while (currentIndex<=codeLength && Character.isLetter(currentChar)) {
		      sb.append(currentChar);
		      currentIndex++;
		      if (currentIndex>=codeLength) break;
		      currentChar = code.charAt(currentIndex);
		    }
		    return sb.toString();
		  }

	  /** Skip WhiteSpace(WS) */
	  private void skipWhiteSpace() {
		    while (currentIndex<=codeLength) {
		      if (Arrays.asList(' ', '\r', '\t', '\n').contains(code.charAt(currentIndex))) {
		        currentIndex++;
		      } else {
		        break;
		      }
		    }
		  }

	  /** Get current Token. */
	  public Token getCurrentToken() {
	    return currentToken;
	  }
	  public Token getPreviousToken() {
		    return previousToken;
		  }
	}