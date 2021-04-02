package com.mycompany.sqform;
import com.mycompany.formats.ErrorIndigo;
import java_cup.runtime.*;
import com.mycompany.handlers.*;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import static com.mycompany.sqform.symSq.*;

%%

%class SqLexic
%cup
%unicode
%line
%column
%public

%{
	StringBuffer string = new StringBuffer();
	ErrorCommands errorCommands = new ErrorCommands(true);
%}

    LineTerminator = \r|\n|\r\n
    WhiteSpace     = {LineTerminator} | [ \t\f]
    NumbersIntegral = [0-9]+ 
    TextNoSpace	= [^ \"\n\t\r"{""}""]""[""!""<"">"">=""<=""="",""\'"]+
	Consulta = \"CONSULTA-{NumbersIntegral}\"

%state STRING
%%

//Grammars

	/* keywords */
	 
	 <YYINITIAL>{	 
		/* Simple Symbols */
		"<"	{ System.out.println("Token: LESS"); 
			return new Symbol(LESS, yyline + 1, yycolumn + 1, yytext());}
		">"	{ System.out.println("Token: GREATER"); 
					return new Symbol(GREATER, yyline + 1, yycolumn + 1, yytext());}
		":"	{ System.out.println("Token: COLON");
			return new Symbol(COLON, yyline + 1, yycolumn + 1, yytext());}
		"{"	{ System.out.println("Token: OPEN_CURLY");
		return new Symbol(OPEN_CURLY, yyline + 1, yycolumn + 1, yytext());}
		"}"	{ System.out.println("Token: CLOSE_CURLY");
		return new Symbol(CLOSE_CURLY, yyline + 1, yycolumn + 1, yytext());}
		"["	{ System.out.println("Token: OPEN_BRACKET");
		return new Symbol(OPEN_BRACKET, yyline + 1, yycolumn + 1, yytext());}
		"]"	{ System.out.println("Token: CLOSE_BRACKET");
		return new Symbol(CLOSE_BRACKET, yyline + 1, yycolumn + 1, yytext());}
		","	{ System.out.println("Token: COMA");
		return new Symbol(COMA, yyline + 1, yycolumn + 1, yytext());}
		"->"	{ System.out.println("Token: TO");
				return new Symbol(TO, yyline + 1, yycolumn + 1, yytext());}
		"<>" {System.out.println("Token: DIFFERENCE");
											return new Symbol(DIFFERENCE, yyline + 1, yycolumn + 1, yytext());}
		">="	{ System.out.println("Token: MORE_THAN");
		return new Symbol(MORE_THAN, yyline + 1, yycolumn + 1, yytext());}
		"<="	{ System.out.println("Token: LESSER_THAN");
		return new Symbol(LESSER_THAN, yyline + 1, yycolumn + 1, yytext());}
		"="	{ System.out.println("Token: EQUAL");
		return new Symbol(EQUAL, yyline + 1, yycolumn + 1, yytext());}
		"\""	{System.out.println("Token: QUOTE_D");
		return new Symbol(QUOTE_D, yyline + 1, yycolumn + 1, yytext());}
		"\'"	{System.out.print("Cadena: "+yytext());
				string.setLength(0); yybegin(STRING);}
		"AND"	{ System.out.println("Token: AND");
											return new Symbol(AND, yyline + 1, yycolumn + 1, yytext());}
	 "OR"	{ System.out.println("Token: OR");
											return new Symbol(OR, yyline + 1, yycolumn + 1, yytext());}
	 "NOT"	{ System.out.println("Token: NOT");
											return new Symbol(NOT, yyline + 1, yycolumn + 1, yytext());}
	 "WHERE"	{ System.out.println("Token: WHERE");
											return new Symbol(WHERE, yyline + 1, yycolumn + 1, yytext());}
		

		"!ini_solicitud"		{System.out.println("Token: APERTURE");
										return new Symbol(APERTURE, yyline + 1, yycolumn + 1, yytext());}
	 "fin_solicitud!"		{System.out.println("Token: END");
										return new Symbol(END, yyline + 1, yycolumn + 1, yytext());}
	 "!ini_solicitudes"		{System.out.println("Token: GREATAPERTURE");
										return new Symbol(GREATEAPERTURE, yyline + 1, yycolumn + 1, yytext());}
	 "!fin_solicitudes"		{System.out.println("Token: GREATCLOSE");
											return new Symbol(GREATCLOSE, yyline + 1, yycolumn + 1, yytext());}
	 "\"CONSULTAR_DATOS\""	{System.out.println("Token: CONSULTDATA");
											return new Symbol(CONSULTDATA, yyline + 1, yycolumn + 1, yytext());}
	 "\"CONSULTAS\""		{System.out.println("Token: INQUIRIES");
											return new Symbol(INQUIRIES, yyline + 1, yycolumn + 1, yytext());}
	 {Consulta} {System.out.println("Token: CONSULT");
											return new Symbol(CONSULT, yyline + 1, yycolumn + 1, yytext());}
	 "SELECT TO FORM"		{System.out.println("Token: SELECT");
											return new Symbol(SELECT, yyline + 1, yycolumn + 1, yytext());}

		/* Text */
		{NumbersIntegral} {System.out.println("Token: NUMBER");
		return new Symbol(NUMBER, yyline + 1, yycolumn + 1, yytext());}
		{WhiteSpace}  {/* empty */}
		{TextNoSpace} {System.out.println("Token: TEXT: "+yytext());
			return new Symbol(TEXT, yyline + 1, yycolumn + 1, yytext());}
	}
	
	<STRING> {
		\'	{ yybegin(YYINITIAL);
			  String result = string.toString();
			  System.out.println(result+"\'");
			  return new Symbol(SPECIFICATION, yyline + 1, yycolumn + 1, result);}
		[^\'\"]+	{ string.append(yytext()); }
	}
	
	

	
