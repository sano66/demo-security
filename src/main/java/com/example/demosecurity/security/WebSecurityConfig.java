package com.example.demosecurity.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 独自のログイン画面を使用する issues/9
		http.authorizeRequests().anyRequest().authenticated();
		http.formLogin().loginPage("/login").permitAll();

		// ログアウト時にセッションを無効にし、クッキーを削除する issues/8
		// super.configure(http);
		http.logout().invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll();
	}

}
