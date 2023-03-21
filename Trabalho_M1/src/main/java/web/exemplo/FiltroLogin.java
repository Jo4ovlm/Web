package web.exemplo;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class FiltroLogin
 */
@WebFilter("/login.html")
public class FiltroLogin implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroLogin() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		boolean manterConectado = false;
		String userName = null;
		HttpServletRequest httpRequest = (HttpServletRequest )request;
		Cookie[] cookies = httpRequest.getCookies();
		if(cookies != null) {
			for(Cookie ck : cookies) {
				if(ck.getName().equals("usuario")) {
					manterConectado = true;
					userName = ck.getValue();
				}
			}
		}
		if(manterConectado) {
			httpRequest.getSession().setAttribute("usuario", userName );
			request.getRequestDispatcher("WEB-INF/TelaRestrita.jsp").forward(request, response);
		}
		request.getRequestDispatcher("login.html").forward(request, response);
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
