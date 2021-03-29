package com.mycompany.formsafe;
import java_cup.runtime.*;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import com.mycompany.handlers.ErrorCommands;
import com.mycompany.formats.ErrorIndigo;
import static com.mycompany.formsafe.symSqForm.*;
%%

%class SqFormLexic
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
Number = [0-9];
OptionsRequired = "\"SI\""|"\"NO\""
AlignmentComponent = "\"CENTRO\""|"\"IZQUIERDO\""|"\"DERECHA\""|"\"JUSTIFICAR\""
%state STRING

%%

		<YYINITIAL> "\"NOMBRE_CAMPO\""		{System.out.println("Token: NAME_C");
						 return new Symbol(NAME_C, yyline + 1, yycolumn + 1, yytext());}
<YYINITIAL> "\"NOMBRE\""		{System.out.println("Token: NAME_C");
						 return new Symbol(NAME, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"FORMULARIO\""		{System.out.println("Token: FORMULARIO");
						 return new Symbol(FORM, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"VISIBILIDAD\""		{System.out.println("Token: FORMULARIO");
						 return new Symbol(VISIBILITY, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"PUBLIC\""		{System.out.println("Token: FORMULARIO");
						 return new Symbol(PUBLIC, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"PRIVATE\""		{System.out.println("Token: FORMULARIO");
						 return new Symbol(PRIVATE, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"CLASE\""			{System.out.println("Token: CLASE");
						 return new Symbol(CLASSN, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"TEXTO_VISIBLE\""		{System.out.println("Token: TEXTO_VISIBLE");
						 return new Symbol(TEXTV, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"ALINEACION\""		{System.out.println("Token: ALINEACION");
						 return new Symbol(ALIGNMENT, yyline + 1, yycolumn + 1, yytext());}
		
	<YYINITIAL> "\"CAMPO_TEXTO\""		{System.out.println("Token: CAMPO_TEXTO");
						 return new Symbol(TEXT_CAMP, yyline + 1, yycolumn + 1, yytext());}	
	<YYINITIAL> "\"AREA_TEXTO\""		{System.out.println("Token: AREA_TEXTO");
						 return new Symbol(TEXT_AREA, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"CHECKBOX\""			{System.out.println("Token: CHECKBOX");
						 return new Symbol(CHECKBOX, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"RADIO\""			{System.out.println("Token: RADIO");
						 return new Symbol(RATIO, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"FICHERO\""			{System.out.println("Token: FICHERO");
						 return new Symbol(FILE, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"IMAGEN\""			{System.out.println("Token: IMAGEN");
						 return new Symbol(IMAGE, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"COMBO\""			{System.out.println("Token: COMBO");
						 return new Symbol(COMBO, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"BOTON\""			{System.out.println("Token: BOTON");
						 return new Symbol(BUTTON, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"REQUERIDO\""			{System.out.println("Token: REQUERIDO");
						 return new Symbol(REQUIRED, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"OPCIONES\""			{System.out.println("Token: OPCIONES");
						 return new Symbol(OPTIONS, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"FILAS\""			{System.out.println("Token: FILAS");
						 return new Symbol(ROWS, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"COLUMNAS\""			{System.out.println("Token: COLUMNAS");
						 return new Symbol(COLS, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"URL\""			{System.out.println("Token: URL");
						 return new Symbol(URL, yyline + 1, yycolumn + 1, yytext());}



<YYINITIAL>{
			{ AlignmentComponent }	{System.out.println("Token: AlineacionComponente: "+yytext()+"l");
					return new Symbol(POSALIGNMENT, yyline + 1, yycolumn + 1, yytext());}
						"\"SI\""	{System.out.println("Token: Opcion Requerida: "+yytext()+"l");
					return new Symbol(BOOL, yyline + 1, yycolumn + 1, yytext());}
					"\"NO\""	{System.out.println("Token: Opcion Requerida: "+yytext()+"l");
					return new Symbol(BOOL, yyline + 1, yycolumn + 1, yytext());}
    "{" {System.out.println("Token: OPEN_CURLY");
        return new Symbol(OPEN_CURLY,yyline+1,yycolumn+1, yytext());}
    "}" {System.out.println("Token: CLOSE_CURLY");
        return new Symbol(CLOSE_CURLY,yyline+1,yycolumn+1, yytext());}
    "(" {System.out.println("Token: OPEN_PARENTHESIS");
        return new Symbol(OPEN_PARENTHESIS,yyline+1,yycolumn+1, yytext());}
    ")" {System.out.println("Token: CLOSE_PARENTHESIS");
        return new Symbol(CLOSE_PARENTHESIS,yyline+1,yycolumn+1, yytext());}  
    ":" {System.out.println("Token: COLON");
        return new Symbol(COLON,yyline+1,yycolumn+1, yytext());}  
    "," {System.out.println("Token: COMA");
        return new Symbol(COMA,yyline+1,yycolumn+1, yytext());}  
    "\"" {System.out.println("Token Cadena "+yytext());
            string.setLength(0); yybegin(STRING);}        
    "db.formularios" {System.out.println("Token: DBFORM");
        return new Symbol(DBFORM,yyline+1,yycolumn+1, yytext());}  
	"db.usuarios"		{System.out.println("Token: DBUSER");
        return new Symbol(DBUSER,yyline+1,yycolumn+1, yytext());}  
    "new.formulario"    {System.out.println("Token: NEWFORM");
        return new Symbol(NEWFORM,yyline+1,yycolumn+1, yytext());}  
	"DATOS_RECOPILADOS"	{System.out.println("Token: DATA");
        return new Symbol(DATA,yyline+1,yycolumn+1, yytext());}  
    "\"ID_FORMULARIO\"" {System.out.println("Token: IDFORM");
        return new Symbol(IDFORM,yyline+1,yycolumn+1, yytext());}  
    "\"TITULO\""    {System.out.println("Token: FORMTITTLE");
        return new Symbol(FORMTITTLE,yyline+1,yycolumn+1, yytext());}  
    "\"TEMA\""  {System.out.println("Token: TOPIC");
        return new Symbol(TOPIC,yyline+1,yycolumn+1, yytext());}  
    "\"USUARIO_CREACION\""  {System.out.println("Token: USER_CREATOR");
        return new Symbol(USER_CREATOR,yyline+1,yycolumn+1, yytext());}  
    "\"ESTRUCTURA\""    {System.out.println("Token: STRUCT");
        return new Symbol(STRUCT,yyline+1,yycolumn+1, yytext());}  
    "\"ID_COMPONENTE\""    {System.out.println("Token: ID_COMPONENT");
        return new Symbol(ID_COMPONENT,yyline+1,yycolumn+1, yytext());}  
	"\"USUARIO\""    {System.out.println("Token: USER");
        return new Symbol(USER,yyline+1,yycolumn+1, yytext());}  
	"\"PASSWORD\"" {System.out.println("Token: PASSWORD");
        return new Symbol(PASSWORD,yyline+1,yycolumn+1, yytext());}  
	"\"FECHA\"" {System.out.println("Token: DATE");
        return new Symbol(DATE,yyline+1,yycolumn+1, yytext());}  	
    {WhiteSpace}    {/*Do nothing*/}
}

<STRING>{
    	\"                             { yybegin(YYINITIAL);
						String result = string.toString();
				                       	if(result.contains(" ")||result.contains("\n")||result.contains("\r")||result.contains("\t")){
								System.out.println(" CON ESPACIOS: "+string.toString()); 
								return new Symbol(TEXT_WITHSPACES,yyline+1, yycolumn+1,string.toString());
							}else{
								System.out.println(" SIN ESPACIOS: "+string.toString()); 
								return new Symbol(TEXT_NOSPACES,yyline+1, yycolumn+1,string.toString());
							}
						}
	    [^\"]+                   { string.append(yytext()); }    
}

[^] {System.out.println("Error Token: "+yytext());
	ErrorIndigo newError = new ErrorIndigo(yytext(), "ERROR", yyline+1, yycolumn+1);
	errorCommands.addError(newError);
	return new Symbol(DUNO,yyline+1,yycolumn+1, yytext());}
