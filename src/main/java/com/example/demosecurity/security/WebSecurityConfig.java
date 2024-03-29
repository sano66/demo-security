package com.example.demosecurity.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

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
		// 認証なしでアクセス可能なページを設定する issues/13
		http.authorizeRequests().antMatchers("/", "/home", "/ec").permitAll();
		
		// 権限に応じてアクセスできるページを設定する issues/12
		http.authorizeRequests().antMatchers("/ecBuy/*").hasRole("USER");
		http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN");

		// 独自のログイン画面を使用する issues/9
		http.authorizeRequests().anyRequest().authenticated();
		http.formLogin().loginPage("/login").permitAll();

		// ログアウト時にセッションを無効にし、クッキーを削除する issues/8
		// super.configure(http);
		http.logout().invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll();
	}

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 複数のユーザによる利用を可能にする issues/10
//		auth.inMemoryAuthentication()
//		.withUser("U001").password("{noop}P001").roles("USER")
//		.and().withUser("U002").password("{noop}P002").roles("USER").disabled(true)
//		.and().withUser("U003").password("{noop}P003").roles("USER", "ADMIN")
//		.and().withUser("A001").password("{noop}P001").roles("ADMIN");
		// issues/11
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(NoOpPasswordEncoder.getInstance());
	}
}
