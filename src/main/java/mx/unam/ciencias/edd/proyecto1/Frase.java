
package mx.unam.ciencias.edd.proyecto1;

import mx.unam.ciencias.edd.*;

public class Frase implements Comparable<Frase> {
	private String contenido, normalizado;
	private int longitud;

	public Frase(String s) {
		contenido = s;
		normalizado = normalizar(contenido);
		longitud = s.length();
	}

	public String getContenido() {
		return contenido;
	}

	public int getLongit() {
		return longitud;
	}

	public int compareTo(Frase f) {

		int times = min(normalizado.length(), f.normalizado.length());

		for (int i = 0; i < times; i++) {
			if (normalizado.charAt(i) < f.normalizado.charAt(i))
				return -1;

			if (normalizado.charAt(i) > f.normalizado.charAt(i))
				return 1;
		}

		if (longitud < f.longitud)
			return -1;

		else if (longitud > f.longitud)
			return 1;

		return 0;
	}

	public boolean equals(Frase f) {
		int times = min(f.longitud, longitud);

		for (int i = 0; i < times; i++) {
			if (!(contenido.charAt(i) == f.contenido.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	private int min(int a, int b) {
		if (a < b)
			return a;

		return b;
	}

	private String normalizar(String s) {

		String temp = s.toLowerCase();

		temp = temp.replaceAll("[áäàâ]", "a");
    	temp = temp.replaceAll("[éëèê]", "e");
    	temp = temp.replaceAll("[íïìî]", "i");
    	temp = temp.replaceAll("[óöòô]", "o");
    	temp = temp.replaceAll("[úüùû]", "u");
    	temp = temp.replaceAll("ñ", "n");
		temp = temp.replaceAll(" ", "");
		temp = temp.replaceAll("[^a-z,^A-Z,^0-9]", "");
		temp = temp.trim();

		return temp;
	}

	public String toString() {
		return contenido;
	}

	public String norm() {
		return normalizado;
	}
}