package AnalizadorL;

import java.io.File;

public class ALexico
{
	public static void main(String[] args)
	{
		//Cambiar ruta para cada compu
				String ruta="D:/6to Semestre/LyA1/Unidad 4/Analizador/src/AnalizadorL/Lexer.flex";
				//URL ruta = getClass().getResource("/AnalizadorL/Lexer.flex");
				generateLexer(ruta);
	}

	public static void generateLexer(String ruta) {
		File archivo = new File(ruta);
		JFlex.Main.generate(archivo);
	}
}
