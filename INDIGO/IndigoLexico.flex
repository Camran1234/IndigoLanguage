package com.mycompany.indigo;
import java_cup.runtime.*;
import static com.mycompany.indigo.symIndigo.*;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import com.mycompany.handlers.ErrorCommands;
import com.mycompany.formats.Error;
%%

%class IndigoLex
%cup
%unicode
%line
%column
%public

%{
	StringBuffer string = new StringBuffer();
	ErrorCommands errorCommands= new ErrorCommands(true);
%}

/* Regular Expressions */
    LineTerminator = \r|\n|\r\n
    WhiteSpace     = {LineTerminator} | [ \t\f]
/*Integer Numbers*/
//NumbersIntegral = \"[0-9]+\"
/* Options */
OptionsRequired = "\"SI\""|"\"NO\""
AlignmentComponent = "\"CENTRO\""|"\"IZQUIERDO\""|"\"DERECHA\""|"\"JUSTIFICAR\""
IniSolicitud = \![i|I][n|N][i|I]_[s|S][o|O][l|L][i|I][c|C][i|I][t|T][u|U][d|D]
IniSolicitudes = \![i|I][n|N][i|I]_[s|S][o|O][l|L][i|I][c|C][i|I][t|T][u|U][d|D][e|E][s|S]

%state STRING

%%

//Grammars

	/* keywords */
	<YYINITIAL> {IniSolicitudes}		{System.out.println("Token: !ini_solicitudes");
						return new Symbol(START, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "!fin_solicitudes"		{System.out.println("Token: !fin_solicitudes");
						return new Symbol(FINAL, yyline + 1, yycolumn + 1, yytext());}
	
	<YYINITIAL> {IniSolicitud}		{System.out.println("Token: !ini_solicitud ");
						 return new Symbol(APERTURE, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "fin_solicitud!"		{System.out.println("Token: fin_solicitud! ");
						 return new Symbol(END, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"CREDENCIALES_USUARIO\"" 	{System.out.println("Token: CREDENCIALES_USUARIO");
						 return new Symbol(CREDENTIAL, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"USUARIO\"" 			{System.out.println("Token: USUARIO");
						 return new Symbol(USER, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"PASSWORD\""		 	{System.out.println("Token: PASSWORD");
						 return new Symbol(PASSWORD, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"FECHA_CREACION\""	 	{System.out.println("Token: FECHA_CREACION");
						 return new Symbol(DATE, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"FECHA_MODIFICACION\"" 	{System.out.println("Token: FECHA_CREACION");
						 return new Symbol(DATE, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"USUARIO_ANTIGUO\""	 	{System.out.println("Token: USUARIO_ANTIGUO");
						 return new Symbol(PAST_USER, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"USUARIO_NUEVO\""	 	{System.out.println("Token: USUARIO_NUEVO");
						 return new Symbol(NEW_USER, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"NUEVO_PASSWORD\""	 	{System.out.println("Token: NUEVO_PASSWORD");
						 return new Symbol(NEW_PASSWORD, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"CREAR_USUARIO\""	 	{System.out.println("Token: CREAR_USUARIO");
						 return new Symbol(CREATE_U, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"MODIFICAR_USUARIO\""	 	{System.out.println("Token: MODIFICAR_USUARIO");
						 return new Symbol(MODIFY_U, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"ELIMINAR_USUARIO\""	 	{System.out.println("Token: ELIMINAR_USUARIO");
						 return new Symbol(DELETE_U, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"LOGIN_USUARIO\""	 	{System.out.println("Token: LOGIN_USUARIO");
						 return new Symbol(LOGIN_U, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"NUEVO_FORMULARIO\""	 	{System.out.println("Token: NUEVO_FORMULARIO");
						 return new Symbol(NEW_F, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"ELIMINAR_FORMULARIO\"" 	{System.out.println("Token: ELIMINAR_FORMULARIO");
						 return new Symbol(DELETE_F, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"MODIFICAR_FORMULARIO\"" 	{System.out.println("Token: MODIFICAR_FORMULARIO");
						 return new Symbol(MODIFY_F, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"PARAMETROS_FORMULARIO\"" 	{System.out.println("Token: PARAMETROS_FORMULARIO");
						 return new Symbol(FORM_PARAMETERS, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"ID\""	 		{System.out.println("Token: ID");
						 return new Symbol(ID, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"TITULO\""		 	{System.out.println("Token: NOMBRE");
						 return new Symbol(TITTLE, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"TEMA\""	 		{System.out.println("Token: TEMA");
						 return new Symbol(TOPIC, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"NOMBRE\""	 		{System.out.println("Token: TEMA");
						 return new Symbol(NAME_F, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"AGREGAR_COMPONENTE\""	{System.out.println("Token: AGREGAR_COMPONENTE");
						 return new Symbol(ADD_C, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"ELIMINAR_COMPONENTE\""	{System.out.println("Token: ELIMINAR_COMPONENTE");
						 return new Symbol(DELETE_C, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"MODIFICAR_COMPONENTE\""	{System.out.println("Token: MODIFICAR_COMPONENTE");
						 return new Symbol(MODIFY_C, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"PARAMETROS_COMPONENTE\""	{System.out.println("Token: PARAMETROS_COMPONENTE");
						 return new Symbol(C_PARAMETERS, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"NOMBRE_CAMPO\""		{System.out.println("Token: NOMBRE_CAMPO");
						 return new Symbol(NAME_C, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"FORMULARIO\""		{System.out.println("Token: FORMULARIO");
						 return new Symbol(FORM, yyline + 1, yycolumn + 1, yytext());}
	<YYINITIAL> "\"CLASE\""			{System.out.println("Token: CLASE");
						 return new Symbol(CLASS, yyline + 1, yycolumn + 1, yytext());}
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



	/* end keywords */
	<YYINITIAL> {
		/* Literals */
		/*See declaration space to acknowolage the meanings*/
		{ AlignmentComponent }	{System.out.println("Token: AlineacionComponente: "+yytext()+"l");
					return new Symbol(POSALIGNMENT, yyline + 1, yycolumn + 1, yytext());}
		{ OptionsRequired }	{System.out.println("Token: Opcion Requerida: "+yytext()+"l");
					return new Symbol(BOOL, yyline + 1, yycolumn + 1, yytext());}

		/* Plain Symbols */
		"<"		{System.out.println("Token: <");
				return new Symbol(LESS, yyline + 1, yycolumn + 1, yytext());}
		">"		{System.out.println("Token: >");
				return new Symbol(GREATER, yyline + 1, yycolumn + 1, yytext());}
		":"		{System.out.println("Token: :");
				return new Symbol(COLON, yyline + 1, yycolumn + 1, yytext());}
		"{"		{System.out.println("Token: {");
				return new Symbol(OPEN_CURLY, yyline + 1, yycolumn + 1, yytext());}
		"}"		{System.out.println("Token: }");
				return new Symbol(CLOSE_CURLY, yyline + 1, yycolumn + 1, yytext());}
		"["		{System.out.println("Token: [");
				return new Symbol(OPEN_BRACKET, yyline + 1, yycolumn + 1, yytext());}
		"]"		{System.out.println("Token: ]");
				return new Symbol(CLOSE_BRACKET, yyline + 1, yycolumn + 1, yytext());}
		\"		{System.out.print("Cadena: "+yytext());
				string.setLength(0); yybegin(STRING);}
		","		{System.out.println("Token: ,");
				return new Symbol(COMA, yyline + 1, yycolumn + 1, yytext());}

		/* White Spaces */
		{WhiteSpace} {/* ignore */}
	}
	 <STRING> {
	      \"                             { yybegin(YYINITIAL);
						String result = string.toString();
				                       	if(result.contains(" ")||result.contains("\n")||result.contains("\r")||result.contains("\t")){
								System.out.println(" CON ESPACIOS: "+string.toString()); 
								return new Symbol(TEXTWS,yyline+1, yycolumn+1,string.toString());
							}else{
								System.out.println(" SIN ESPACIOS: "+string.toString()); 
								return new Symbol(TEXTWIS,yyline+1, yycolumn+1,string.toString());
							}
						}
	      [^\"]+                   { string.append(yytext()); }
	    }



	[^]	{System.out.println("Error en el lexema: "+yytext());
		String errorMessage = "Lexema no reconocido ";
		Error newError = new Error(errorMessage, yytext(), yyline+1, yycolumn+1);
		errorCommands.addError(newError);}