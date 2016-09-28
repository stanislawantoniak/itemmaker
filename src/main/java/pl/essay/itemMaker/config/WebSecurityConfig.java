package pl.essay.itemMaker.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import pl.essay.itemMaker.service.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter,CsrfFilter.class);
		System.out.println("after utf8 filter reg");	

		http
		.authorizeRequests()
		.antMatchers("/resources/**","/signup").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").permitAll()
		.and()
		.logout().permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		//System.out.println("configure global security");	
		//auth
		//.inMemoryAuthentication()
		//.withUser("stan").password("pass").roles("USER").and()
		//.withUser("adm").password("pass").roles("USER", "ADMIN");
	}

	@Bean
	public UserServiceImpl userServiceImpl() {
		return new UserServiceImpl();
	}
	
	
/*	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return super.getAuthenticationTrustResolver();
	}
	*/
	
}
