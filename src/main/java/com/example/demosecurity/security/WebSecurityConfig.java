package com.example.demosecurity.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(WebSecurity web) throws Exception {
		// Spring Securityのデバッグ出力を有効にする issues/6
		web.debug(true);

		// リソースファイルを認証対象外とする issues/7
		web.ignoring().antMatchers("/resources/**");
	}
}
