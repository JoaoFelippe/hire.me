package com.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.domain.ShortUrl;

@Controller
@RequestMapping("ShortUrl")
public class ShortUrlController {

	@RequestMapping(value = "/short", method = RequestMethod.GET)
	public ModelAndView ShortURLCreate(@RequestParam String url_alias) {
		ModelAndView mv = new ModelAndView("/Home.jsp");

		String url = null, alias = null, Result;

		if (url_alias.split(",").length == 2) {
			url = url_alias.split(",")[0];
			alias = url_alias.split(",")[1];
			Result = ShortUrl.ShortUrl_WithAlias(url, alias);

		} else {
			url = url_alias.split(",")[0];
			Result = ShortUrl.ShortUrl_WithoutAlias(url);
		}

		mv.addObject("Print", Result);
		return mv;
	}
	@RequestMapping(value = "/url", method = RequestMethod.GET)
	public ModelAndView ShortURLReturn(@RequestParam String alias) {
		ModelAndView mv = null;
		alias = alias.substring(19, alias.length());
		String url = ShortUrl.BuscaShortUrl(alias);
		String verif = "with" + url.split("with")[1];
		if(verif.equals("without Error")) {
			mv = new ModelAndView("/RedimensionarUrl.jsp");		
			url = url.split("with")[0];
			mv.addObject("URL",url);
			return mv;			
		}
		mv = new ModelAndView("/Home.jsp");
		String Error = url.split("with")[0];
		mv.addObject("Error",Error);			
		return mv;
		
	}

}
