package pl.essay.itemMaker.config;

import javax.servlet.ServletContext;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer{
	

	//just for testing if secinit works
	@Override 
	protected void afterSpringSecurityFilterChain(ServletContext sc){
		super.afterSpringSecurityFilterChain(sc);
		
		System.out.println("afterSpringSecurityFilterChain - check");
	}


}
