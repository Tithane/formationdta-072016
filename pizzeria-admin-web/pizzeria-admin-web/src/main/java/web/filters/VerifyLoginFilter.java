package web.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyLoginFilter implements Filter {
	
	private static final List<String> URL_SANS_AUTH = Arrays.asList("/login", "/logout", "/media");


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		Boolean isAuthentifie = (Boolean) httpRequest.getSession().getAttribute("authentifie");
		
		String requestURI = httpRequest.getRequestURI();
		
		boolean isUrlSansAuth = URL_SANS_AUTH.stream().anyMatch(chemin -> requestURI.contains(chemin));
		

		if (isAuthentifie != null || isUrlSansAuth) {
			chain.doFilter(request, response);
		} else {
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
