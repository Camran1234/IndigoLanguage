package filemanager;
import Parameter.ErrorCommands;
import Parameter.ErrorIndigo;
import java_cup.runtime.*;
import static filemanager.symResponse.*;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

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
		 /* Type Data */
		 "\"Error\"" {System.out.println("Token: ERROR"); 
			return new Symbol(ERROR, yyline + 1, yycolumn + 1, yytext());}
		 "\"Text\"" {System.out.println("Token: OUT"); 
			return new Symbol(OUT, yyline + 1, yycolumn + 1, yytext());}
			"\"File\"" {System.out.println("Token: FILE"); 
			return new Symbol(FILE, yyline + 1, yycolumn + 1, yytext());}
			 "\"Warning\"" {System.out.println("Token: WARNING"); 
			return new Symbol(WARNING, yyline + 1, yycolumn + 1, yytext());}
			 "\"Row\"" {System.out.println("Token: ROW"); 
			return new Symbol(ROW, yyline + 1, yycolumn + 1, yytext());}
			 "\"Col\"" {System.out.println("Token: COL"); 
			return new Symbol(COL, yyline + 1, yycolumn + 1, yytext());}


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
		"\""	{System.out.print("Cadena: "+yytext());
				string.setLength(0); yybegin(STRING);}		
		"!ini_respuesta"		{System.out.println("Token: APERTURE");
										return new Symbol(APERTURE, yyline + 1, yycolumn + 1, yytext());}
	 "fin_respuesta!"		{System.out.println("Token: END");
										return new Symbol(END, yyline + 1, yycolumn + 1, yytext());}
	 "!ini_respuestas"		{System.out.println("Token: GREATAPERTURE");
										return new Symbol(GREATAPERTURE, yyline + 1, yycolumn + 1, yytext());}
	 "!fin_respuestas"		{System.out.println("Token: GREATCLOSE");
											return new Symbol(GREATCLOSE, yyline + 1, yycolumn + 1, yytext());}
	 "\"bloque_parametros\""	{System.out.println("Token: BLOCK_PARAMETER");
											return new Symbol(BLOCK_PARAMETER, yyline + 1, yycolumn + 1, yytext());}
	 

		/* Text */
		{WhiteSpace}  {/* empty */}
	}
	
	<STRING> {
		\"	{ yybegin(YYINITIAL);
			  String result = string.toString();
			  System.out.println(result+"\'");
			  return new Symbol(TEXT, yyline + 1, yycolumn + 1, result);}
		[^\"]+	{ string.append(yytext()); }
	}

	[^] {System.out.println("Error en el lexema: "+yytext());
		String errorMessage = "Lexema no reconocido ";
		ErrorIndigo newError = new ErrorIndigo(errorMessage, yytext(), yyline+1, yycolumn+1);
		errorCommands.addError(newError);
		return new Symbol(DUNO,yyline+1, yycolumn+1,string.toString());}
	