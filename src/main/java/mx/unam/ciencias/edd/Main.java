
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

		//Leer archivos en la linea de comandos o entrada estandar
		try {
			Lista <String> archivos = new Lista <String>();

			for (int i = 0; i < args.length; i++) {
				if (args[i].contains(".txt")) {
					int pos;
					if (!((pos = verifyFlagO(args)) == i))
						archivos.agregaFinal(args[i]);
				}
			}

			Lista<Frase> frases = new Lista<Frase>();

			if (archivos.esVacia()) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String temp = null;

				while ((temp = reader.readLine()) != null) {
					frases.agregaFinal(new Frase(temp));
				}

				reader.close();
			}

			if (!archivos.esVacia()) {
				// Auxiliares para leer el archivo
				BufferedReader reader = new BufferedReader(new FileReader(archivos.getPrimero()));;
				String temp = null;

				for (int i = 0; i < archivos.getLongitud(); i++) {
					reader = new BufferedReader(new FileReader(archivos.get(i)));
					while ((temp = reader.readLine()) != null) {
						frases.agregaFinal(new Frase(temp));
					}
				}

				reader.close();
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

				System.out.println("El archivo se creo en la direccion: " + directorio);

				return;

			}

			for (Frase f : r)
				System.out.println(f);


		} catch (FileNotFoundException e) {
			System.out.println("Lo siento, no se encontro su archivo");
		}
	}
}