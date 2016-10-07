package pl.essay.itemMaker.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {// implements WebApplicationInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { ApplicationConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
 /*   @Override  
    public void onStartup(ServletContext container) throws ServletException {
        container.addFilter("OpenSessionInViewFilter_h5", new OpenSessionInViewFilter()); 
    }
	*/
}