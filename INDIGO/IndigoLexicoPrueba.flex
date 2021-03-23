


%%

%class IndigoLex
%standalone
%unicode
%line
%column
%public


%{
	StringBuffer string = new StringBuffer();
%}

/* Regular Expressions */
    LineTerminator = \r|\n|\r\n
    WhiteSpace     = {LineTerminator} | [ \t\f]

/*Integer Numbers*/
NumbersIntegral = [0-9]+
/* Options */
OptionsRequired = "SI"|"NO"
AlignmentComponent = "CENTRO"|"IZQUIERDO"|"DERECHA"|"JUSTIFICAR"
IniSolicitud = \![i|I][n|N][i|I]_[s|S][o|O][l|L][i|I][c|C][i|I][t|T][u|U][d|D]
IniSolicitudes = \![i|I][n|N][i|I]_[s|S][o|O][l|L][i|I][c|C][i|I][t|T][u|U][d|D][e|E][s|S]

%state STRING
%%

//Grammars

	/* keywords */
	
	<YYINITIAL> "!fin_solicitudes"		{System.out.println("Token: !fin_solicitudes");
						}
	<YYINITIAL> {IniSolicitud}			{System.out.println("Token: !ini_solicitud ");
						}
	<YYINITIAL> {IniSolicitudes}		{System.out.println("Token: !ini_solicitudes");
				}
	<YYINITIAL> "fin_solicitud!"			{System.out.println("Token: fin_solicitud! ");
						}
	<YYINITIAL> "\"CREDENCIALES_USUARIO\"" 	{System.out.println("Token: CREDENCIALES_USUARIO");
						}
	<YYINITIAL> "\"USUARIO\"" 			{System.out.println("Token: USUARIO");
						}
	<YYINITIAL> "\"PASSWORD\""		 	{System.out.println("Token: PASSWORD");
						}
	<YYINITIAL> "\"FECHA_CREACION\""	 	{System.out.println("Token: FECHA_CREACION");
						}
	<YYINITIAL> "\"FECHA_MODIFICACION\"" 	{System.out.println("Token: FECHA_CREACION");
						}
	<YYINITIAL> "\"USUARIO_ANTIGUO\""	 	{System.out.println("Token: USUARIO_ANTIGUO");
						}
	<YYINITIAL> "\"USUARIO_NUEVO\""	 	{System.out.println("Token: USUARIO_NUEVO");
						}
	<YYINITIAL> "\"NUEVO_PASSWORD\""	 	{System.out.println("Token: NUEVO_PASSWORD");
						}
	<YYINITIAL> "\"CREAR_USUARIO\""	 	{System.out.println("Token: CREAR_USUARIO");
						}
	<YYINITIAL> "\"MODIFICAR_USUARIO\""	 	{System.out.println("Token: MODIFICAR_USUARIO");
						}
	<YYINITIAL> "\"ELIMINAR_USUARIO\""	 	{System.out.println("Token: ELIMINAR_USUARIO");
						}
	<YYINITIAL> "\"LOGIN_USUARIO\""	 	{System.out.println("Token: LOGIN_USUARIO");
						}
	<YYINITIAL> "\"NUEVO_FORMULARIO\""	 	{System.out.println("Token: NUEVO_FORMULARIO");
						}
	<YYINITIAL> "\"ELIMINAR_FORMULARIO\"" 	{System.out.println("Token: ELIMINAR_FORMULARIO");
						}
	<YYINITIAL> "\"MODIFICAR_FORMULARIO\"" 	{System.out.println("Token: MODIFICAR_FORMULARIO");
						}
	<YYINITIAL> "\"PARAMETROS_FORMULARIO\"" 	{System.out.println("Token: PARAMETROS_FORMULARIO");
						}
	<YYINITIAL> "\"ID\""	 		{System.out.println("Token: ID");
						}
	<YYINITIAL> "\"TITULO\""		 	{System.out.println("Token: NOMBRE");
						}
	<YYINITIAL> "\"TEMA\""	 		{System.out.println("Token: TEMA");
						}
	<YYINITIAL> "\"NOMBRE\""	 		{System.out.println("Token: TEMA");
						}
	<YYINITIAL> "\"AGREGAR_COMPONENTE\""	{System.out.println("Token: AGREGAR_COMPONENTE");
						}
	<YYINITIAL> "\"ELIMINAR_COMPONENTE\""	{System.out.println("Token: ELIMINAR_COMPONENTE");
						}
	<YYINITIAL> "\"MODIFICAR_COMPONENTE\""	{System.out.println("Token: MODIFICAR_COMPONENTE");
						}
	<YYINITIAL> "\"PARAMETROS_COMPONENTE\""	{System.out.println("Token: PARAMETROS_COMPONENTE");
						}
	<YYINITIAL> "\"INDICE\""			{System.out.println("Token: INDICE");
						}
	<YYINITIAL> "\"NOMBRE_CAMPO\""		{System.out.println("Token: NOMBRE_CAMPO");
						}
	<YYINITIAL> "\"FORMULARIO\""		{System.out.println("Token: FORMULARIO");
						}
	<YYINITIAL> "\"CLASE\""			{System.out.println("Token: CLASE");
						}
	<YYINITIAL> "\"TEXTO_VISIBLE\""		{System.out.println("Token: TEXTO_VISIBLE");
						}
	<YYINITIAL> "\"ALINEACION\""		{System.out.println("Token: ALINEACION");
						}
		
	<YYINITIAL> "\"CAMPO_TEXTO\""		{System.out.println("Token: CAMPO_TEXTO");
						}	
	<YYINITIAL> "\"AREA_TEXTO\""		{System.out.println("Token: AREA_TEXTO");
						}
	<YYINITIAL> "\"CHECKBOX\""			{System.out.println("Token: CHECKBOX");
						}
	<YYINITIAL> "\"RADIO\""			{System.out.println("Token: RADIO");
						}
	<YYINITIAL> "\"FICHERO\""			{System.out.println("Token: FICHERO");
						}
	<YYINITIAL> "\"IMAGEN\""			{System.out.println("Token: IMAGEN");
						}
	<YYINITIAL> "\"COMBO\""			{System.out.println("Token: COMBO");
						}
	<YYINITIAL> "\"BOTON\""			{System.out.println("Token: BOTON");
						}
	<YYINITIAL> "\"REQUERIDO\""			{System.out.println("Token: REQUERIDO");
						}
	<YYINITIAL> "\"OPCIONES\""			{System.out.println("Token: OPCIONES");
						}
	<YYINITIAL> "\"FILAS\""			{System.out.println("Token: FILAS");
						}
	<YYINITIAL> "\"COLUMNAS\""			{System.out.println("Token: COLUMNAS");
						}
	<YYINITIAL> "\"URL\""			{System.out.println("Token: URL");
						}
	<YYINITIAL> "\"SI\""			{System.out.println("Token: SI");
						}
	<YYINITIAL> "\"NO\""			{System.out.println("Token: NO");
						}



	/* end keywords */
	<YYINITIAL> {
		/* Literals */
		/*See declaration space to acknowolage the meanings*/
		{NumbersIntegral}	{System.out.println("Token: Number Integral "+yytext()); 
					}
		{ AlignmentComponent }	{System.out.println("Token: AlineacionComponente: "+yytext()+"l");
					}
		{ OptionsRequired }	{System.out.println("Token: Opcion Requerida: "+yytext()+"l");
					}

		/* Plain Symbols */
		"<"		{System.out.println("Token: <");
				}
		">"		{System.out.println("Token: >");
				}
		":"		{System.out.println("Token: :");
				}
		"{"		{System.out.println("Token: {");
				}
		"}"		{System.out.println("Token: }");
				}
		"["		{System.out.println("Token: [");
				}
		"]"		{System.out.println("Token: ]");
				}
		\"		{System.out.print("Cadena "+yytext());
				string.setLength(0); yybegin(STRING);}
		","		{System.out.println("Token: ,");}

		/* White Spaces */
		{WhiteSpace} {/* ignore */}
	}
	 <STRING> {
	      \"                             { yybegin(YYINITIAL);
						String result = string.toString();
				                       	if(result.contains(" ")||result.contains("\n")||result.contains("\r")||result.contains("\t")){
								System.out.println(" CON ESPACIOS: "+string.toString()); 
							}else{
								System.out.println(" SIN ESPACIOS: "+string.toString()); 
							}
						}
	      [^\"]+                   { string.append(yytext()); }
	    }

	[^]	{System.out.println("Error en el lexema: "+yytext());}