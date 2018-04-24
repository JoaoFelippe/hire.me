package com.domain;

public class Base62 {
	public static String Base_62(int valor) {
		
		String result = "";

		while (valor > 0) {
			int rest = valor % 62;
			result = Conversao(rest) + result;
			valor = (valor-rest)/62;
		}
		
		return result;
	}

	private static String Conversao(int i) {
		if (i >= 0 & i <= 25) {
			i += 97;
			char temp = (char) i;
			return ("" + temp);

		} else if (i >= 26 & i <= 51) {
			i += 39;
			char temp = (char) i;
			return ("" + temp);

		} else {
			i -= 4;
			char temp = (char) i;
			return ("" + temp);
		}

	}





}
