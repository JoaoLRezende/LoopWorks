package input.termprocessors;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import constants.LanguageConstants;

/**
 * 
 * Replaces all primitives tokens with int.
 *
 */
public class JavaConsistentVariableRenaming implements ITermProcessor {
	
	public String toString() {
		return this.getClass().getName();
	}
	
	public JavaConsistentVariableRenaming(String init) {
		this();
	}
	
	Set<String> operators = new HashSet<String>();
	Set<String> seperators = new HashSet<String>();
	Set<String> keywords = new HashSet<String>();
	Set<String> literals = new HashSet<String>();
	
	public JavaConsistentVariableRenaming() {
	
		// Populate operator tokens
		operators.add("=");   operators.add(">");  operators.add("<");   operators.add("!");  operators.add("~");  operators.add("?");
		operators.add(":");
		operators.add("->"); operators.add("==");  operators.add(">="); operators.add("<="); operators.add("!=");
		operators.add("&&");  operators.add("||"); operators.add("++");  operators.add("--"); operators.add("+");  operators.add("-");
		operators.add("*");   operators.add("/");  operators.add("&");   operators.add("|");  operators.add("^");  operators.add("%");
		operators.add("<<");  operators.add(">>"); operators.add(">>>"); operators.add("+="); operators.add("-="); operators.add("*=");
		operators.add("/=");  operators.add("&="); operators.add("|=");  operators.add("^="); operators.add("%="); operators.add("<<=");
		operators.add(">>="); operators.add(">>>=");
		
		// Populate seperator tokens
		seperators.add("("); seperators.add(")"); seperators.add("{"); seperators.add("}");   seperators.add("["); seperators.add("]"); 
		seperators.add(";"); seperators.add(","); seperators.add("."); seperators.add("..."); seperators.add("@"); seperators.add("::");
		
		// populate keywords
		keywords.add("abstract"); keywords.add("assert"); keywords.add("boolean"); keywords.add("break"); keywords.add("byte"); keywords.add("case");
		keywords.add("catch"); keywords.add("char"); keywords.add("class"); keywords.add("const"); keywords.add("continue"); keywords.add("default");
		keywords.add("do"); keywords.add("double"); keywords.add("else"); keywords.add("enum"); keywords.add("extends"); keywords.add("final");
		keywords.add("finally"); keywords.add("float"); keywords.add("for"); keywords.add("goto"); keywords.add("if"); keywords.add("implements");
		keywords.add("import"); keywords.add("instanceof"); keywords.add("int"); keywords.add("interface"); keywords.add("long"); keywords.add("native");
		keywords.add("new"); keywords.add("package"); keywords.add("private"); keywords.add("protected"); keywords.add("public"); keywords.add("return");
		keywords.add("short"); keywords.add("static"); keywords.add("strictfp"); keywords.add("super"); keywords.add("switch"); keywords.add("synchronized");
		keywords.add("this"); keywords.add("throw"); keywords.add("throws"); keywords.add("transient"); keywords.add("try"); keywords.add("void");
		keywords.add("volatile"); keywords.add("while");
		
	}
	
	public boolean isOperator(String token) {
		return operators.contains(token);
	}
	
	public boolean isSeperator(String token) {
		return seperators.contains(token);
	}
	
	public boolean isKeyword(String token) {
		return keywords.contains(token);
	}
	
	public boolean isIdentifier(String token) {
		if(isOperator(token) || isSeperator(token) || isKeyword(token)) {
			return false;
		}
		
		char start = token.charAt(0);
		if(Character.isAlphabetic(start) || start == '$' || start == '_') {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public List<String> process(List<String> tokens, int language, int granularity, int tokenType) {
		
		List<String> retval = new LinkedList<String>();
		HashMap<String,String> varmap = new HashMap<String,String>();
		int varnum=1;
		
		for(int i = 0; i < tokens.size(); i++) {
			// Get current token
			String curToken = tokens.get(i);
			
			// Get next token (or blank if last token)
			String nextToken;
			if(i == tokens.size()-1)
				nextToken="";
			else
				nextToken=tokens.get(i+1);
			
			
			// It is a variable if next tokens isent "(" nad i
			if(isIdentifier(curToken) && !nextToken.equals("(") && !Character.isUpperCase(curToken.charAt(0))) {
				String replacement;
				if(!varmap.containsKey(curToken)) {
					varmap.put(curToken, "varname" + varnum++);
				}
				replacement = varmap.get(curToken);
				retval.add(replacement);
			} else {
				retval.add(curToken);
			}
		}
		return retval;
	}
	
}
