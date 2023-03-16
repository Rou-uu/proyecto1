
package mx.unam.ciencias.edd;

import java.io.*;

public class Main {

	private static int verifyFlagO(String[] a) { //Verifica si hay -o en la linea de comandos para escribir un nuevo archivo
		for (int i = 0; i < a.length; i++)  
			if (a[i].contains("-o"))
				return i+1; //Regresa la posicion del identificador para nombrar al nuevo archivo

		return -1; //En caso de no haber bandera, regresarar un valor negativo
	}

	private static boolean verifyFlagR(String[] a) { //Verifica si hay -r en la linea de comando para la reversa
		for (String x : a) 
			if (x.contains("-r"))
				return true;

		return false;
	}

	public static void main(String [] args) throws IOException {
		try {
			Lista <String> archivos = new Lista <String>();

			for (int i = 0; i < args.length; i++) {
				if (args[i].contains(".txt")) {
					int pos;
					if (!((pos = verifyFlagO(args)) == i))
						archivos.agregaFinal(args[i]);
				}
			}

			if (archivos.esVacia())
				throw new FileNotFoundException();

			// Auxiliares para leer el archivo
			BufferedReader reader = new BufferedReader(new FileReader(archivos.getPrimero()));;
			Lista<Frase> frases = new Lista<Frase>();
			String temp = null;


			for (int i = 0; i < archivos.getLongitud(); i++) {
				reader = new BufferedReader(new FileReader(archivos.get(i)));
				while ((temp = reader.readLine()) != null) {
					frases.agregaFinal(new Frase(temp));
				}
			}

			Lista <Frase> r = frases.mergeSort(frases);

			if (verifyFlagR(args))
				r = r.reversa();

			int pos;

			if ((pos = verifyFlagO(args)) > 0) {
				String directorio = new File(".").getAbsolutePath();
				String ident = args[pos];

				File fichero = new File(directorio, ident);
				BufferedWriter writer = new BufferedWriter(new FileWriter(fichero));

				for (Frase f : r)
					writer.write(f.toString() + "\n");

				writer.close();

				return;

			}

			for (Frase f : r)
				System.out.println(f);

			reader.close();

		} catch (FileNotFoundException e) {
			System.out.println("Lo siento, no se encontro su archivo, intente agregarlo en la linea de comandos o desde el codigo");
		}
	}
}