package pl.essay.security;

import java.util.Map;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.DelegatingFilterProxy;

public class SecurityInitializer {//extends AbstractSecurityWebApplicationInitializer{
	
/*
	@Override 
	protected void afterSpringSecurityFilterChain(ServletContext sc){
		super.afterSpringSecurityFilterChain(sc);
		
		Map<String, FilterRegistration> m = (Map<String, FilterRegistration>) sc.getFilterRegistrations();
		
		for (Map.Entry<String, FilterRegistration> f : m.entrySet())
			System.out.println("key: "+f.getKey()+" val: "+f.getValue().getClassName());
		
		//ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext( sc );
		//System.out.println(ctx.toString());
		//DelegatingFilterProxy f = (DelegatingFilterProxy) ctx.getBean("springSecurityFilterChain");
		//System.out.println(f.toString());
		
		System.out.println("afterSpringSecurityFilterChain");
	}
*/

}
