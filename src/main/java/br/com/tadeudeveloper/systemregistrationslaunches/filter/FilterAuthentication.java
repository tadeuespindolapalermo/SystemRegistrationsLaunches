package br.com.tadeudeveloper.systemregistrationslaunches.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.tadeudeveloper.systemregistrationslaunches.entities.People;
import br.com.tadeudeveloper.systemregistrationslaunches.util.JPAUtil;

@WebFilter(urlPatterns = "/*")
public class FilterAuthentication implements Filter {
	
	@Inject
	private JPAUtil jpaUtil;
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();		
		
		People userLogged = (People) session.getAttribute("userLogged");
		//String userLogged = (String) session.getAttribute("userLogged");
		
		String url = req.getServletPath();
		
		if(!url.equalsIgnoreCase("index.jsf") && userLogged == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsf");
			dispatcher.forward(request, response);
			return;
		} else {
			// Executa as ações do request e do response
			chain.doFilter(request, response);
		}
		
		/*if(!url.equalsIgnoreCase("index.jsf")  && userLogged == null ||
				(userLogged != null && userLogged.trim().isEmpty())) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsf");
			dispatcher.forward(request, response);
			return;
		} else {
			// Executa as ações do request e do response
			chain.doFilter(request, response);
		}*/
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void init(FilterConfig config) throws ServletException {
		//JPAUtil.getEntityManager();
		jpaUtil.getEntityManager();
	}	
	
}
