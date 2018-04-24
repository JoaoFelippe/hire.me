package com.domain;

import com.dao.ShortUrlDAO;

public class ShortUrl {
	private static String Print_withoutError(String alias, String ShortUrl, long Totaltime) {
		String temp01 = "{\n" + "   'alias': '" + alias + "',\n";
		String temp02 = "   'url': '" + ShortUrl + "',\n";
		String temp03 = "   'statistics': {\n" + "       'time_taken': '" + Totaltime + "ms'\n";
		String temp04 = "   }\n" + "}";

		String PrintFinal = temp01 + temp02 + temp03 + temp04;
		return PrintFinal;

	}

	private static String Print_withError(String alias, String tipoError) {
		if (tipoError.equals("1")) {
			String temp01 = "{\n" + "   'alias': '" + alias + "',\n";
			String temp02 = "   'err_code': '00" + tipoError + "',\n";
			String temp03 = "   'description': 'CUSTOM ALIAS ALREADY EXISTS'\n}";
			String PrintFinal = temp01 + temp02 + temp03;
			return PrintFinal;

		}
		String temp01 = "{\n" + "   'alias': '" + alias + "',\n";
		String temp02 = "   'err_code': '00" + tipoError + "',\n";
		String temp03 = "   'description': 'SHORTENED URL NOT FOUND'\n}";
		String PrintFinal = temp01 + temp02 + temp03;
		return PrintFinal;

	}

	public static String ShortUrl_WithoutAlias(String url) {
		long Initialtime = System.currentTimeMillis();
		int ID = (int) Math.round(Math.random() * 1000000);
		//int ID = (int) Math.round(Math.random() * 10000);
		String alias = Base62.Base_62(ID);

		new ShortUrlDAO().insertAlias(url, alias, ID);
		String ShortUrl = "http://shortener/u/" + alias;
		long Totaltime = System.currentTimeMillis() - Initialtime;
		return Print_withoutError(alias, ShortUrl, Totaltime);

	}

	public static String ShortUrl_WithAlias(String url, String alias) {
		long Initialtime = System.currentTimeMillis();
		String VerifAlias = new ShortUrlDAO().buscaAlias(alias)[1];

		if (alias.equals(VerifAlias)) {
			return Print_withError(alias, "1");
		}
		
		//int ID = (int) Math.round(Math.random() * 10000);
		int ID = (int) Math.round(Math.random() * 1000000);
		new ShortUrlDAO().insertAlias(url, alias, ID);
		String ShortUrl = "http://shortener/u/" + alias;
		long Totaltime = System.currentTimeMillis() - Initialtime;
		return Print_withoutError(alias, ShortUrl, Totaltime);

	}

	public static String BuscaShortUrl(String alias) {
		String url = new ShortUrlDAO().buscaAlias(alias)[0];
		if (url == null) {			 
			return Print_withError(alias, "2") + "with Error";
		}
		return url  + "without Error";

	}

}
