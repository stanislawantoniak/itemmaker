package pl.essay.itemMaker.config;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.*;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.support.OpenSessionInViewInterceptor;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

//public class ApplicationConfig{}

@EnableWebMvc
@Configuration
@ComponentScan({
	"pl.essay.itemMaker",
	"pl.essay.session" 
})

@Import({ WebSecurityConfig.class })
@ImportResource({
	"classpath:/datasource-config.xml",
	"classpath:/language-beans.xml"
})
public class ApplicationConfig {

	@Inject
	SessionFactory sessionFactory;

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}


/*	@Bean 
	OpenSessionInViewInterceptor openSessionInViewInterceptor(){
		OpenSessionInViewInterceptor openSessionInViewInterceptor = new OpenSessionInViewInterceptor(){
			@Override public void preHandle(WebRequest request)
					throws DataAccessException{
				System.out.println("prehandle @@@@@@@@@@@@@@@@@@@@@@@");
				super.preHandle(request);
			}
		};
		openSessionInViewInterceptor.setSessionFactory(this.sessionFactory);
		System.out.println("OpenSessionInViewInterceptor init");
		return openSessionInViewInterceptor;
	}
*/
}
