package pl.essay.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	public WebSecurityConfig(){
		super();
		System.out.println("WebSecurityConfig starting");
	}
	
	public WebSecurityConfig(boolean x){
		super(x);
		System.out.println("WebSecurityConfig starting");
	}

	/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("before utf8 filter reg");	
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter,CsrfFilter.class);
		System.out.println("after utf8 filter reg");	
	}
*/
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		System.out.println("configure global");	
		auth.inMemoryAuthentication()
		.withUser("stan").password("pass").roles("USER").and()
		.withUser("adm").password("pass").roles("USER", "ADMIN");
	}

}
