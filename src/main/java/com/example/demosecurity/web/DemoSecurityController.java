package com.example.demosecurity.web;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DemoSecurityController {

	// inner books repository
	private static final Map<String, Book> booksMap;
	static {
		Map<String, Book> m = new HashMap<>();
		// @formatter:off
		m.put("uuid1", new Book("uuid1", "978-4-7981-4247-0", "株式会社NTTデータ", "翔泳社",
				"Spring徹底入門 Spring FrameworkによるJavaアプリケーション開発",
				"https://www.seshop.com/static/images/product/18948/L.png", 4400, LocalDate.of(2016, 7, 20)));
		m.put("uuid2", new Book("uuid2", "978-4-7775-1969-9", "槙 俊明", "工学社",
				"はじめての Spring Boot[改訂版]",
				"https://www.kohgakusha.co.jp/bookimages/4427b.jpg", 2750, LocalDate.of(2016, 9, 1)));
		m.put("uuid3", new Book("uuid3", "978-4-7741-8217-9", "長谷川裕一，大野渉，土岐孝平", "技術評論社",
				"はじめての Spring Boot[改訂版]",
				"http://image.gihyo.co.jp/assets/images/cover/2016/9784774182179.jpg", 4180, LocalDate.of(2016, 6, 14)));
		// @formatter:on
		booksMap = Collections.unmodifiableMap(m);
	}

	// processing request URLs
	@GetMapping("/")
	public String root() {
		return "redirect:/home";
	}

	@GetMapping("/home")
	public String home() {
		return "home";
	}

	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}

	@Autowired
	private MessageSource messagesSource;

	@GetMapping("/ec")
	public String ec(Model model, Locale locale,
			@RequestParam(name = "message", required = false) Optional<String> message) {
		model.addAttribute("message", message.orElse(messagesSource.getMessage("ec.desc", null, locale)));
		model.addAttribute("bookList", booksMap.values());
		return "ec";
	}

	@GetMapping("/ecBuy/{uuid}")
	public String ecBuy(Model model, @PathVariable String uuid, @ModelAttribute EcForm ecForm
			) {
		ecForm.setBook(booksMap.get(uuid));
		ecForm.setUsername("DUMMY_USER_NAME");
		model.addAttribute(ecForm);
		return "ecBuy";
	}

	@PostMapping("/ecBuy")
	public String ecBuy(RedirectAttributes redirectAttributes, @Validated EcForm ecForm) {
		redirectAttributes.addAttribute("message", "Thank you " + ecForm.getUsername() + " san");
		return "redirect:/ec";
	}

	// it will occur 500 page
	@GetMapping("/err")
	public String err() {
		int s = 3 / 0;
		return "home" + s;
	}
}
