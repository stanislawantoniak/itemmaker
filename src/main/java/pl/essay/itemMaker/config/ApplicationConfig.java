package pl.essay.itemMaker.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import pl.essay.security.WebSecurityConfig;

public class ApplicationConfig{}
/*
@EnableWebMvc
@Configuration
@ComponentScan({ 
	"pl.essay.security",
	"pl.essay.itemMaker.controller",
	"pl.essay.itemMaker.service",
	"pl.essay.itemMaker.dao", 
	"pl.essay.session" 
})

@Import({ WebSecurityConfig.class })
@ImportResource({
	"classpath:/servlet-context.xml",
	"classpath:/language-beans.xml"
})
public class ApplicationConfig {

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

}
*/