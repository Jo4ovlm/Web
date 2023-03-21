package web.exemplo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Autenticador
 */
@WebServlet("/login")
public class Autenticador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Autenticador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String login;
		HttpServletRequest httpRequest = (HttpServletRequest )request;
		Cookie[] cookies = httpRequest.getCookies();
		if(cookies != null) {
			for(Cookie ck : cookies) {
				if(ck.getName().equals("usuario")) {
					login = ck.getValue();
					request.getSession().setAttribute("usuario", login);
					request.getRequestDispatcher("WEB-INF/TelaRestrita.jsp").forward(request, response);
				}
			}
		}
		request.getRequestDispatcher("login.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String manterConectado = request.getParameter("manter_conectado");
		if(login.equals("admin") && senha.equals("admin")) {
			request.getSession().setAttribute("usuario", login);
			if(manterConectado != null) {
				Cookie ck = new Cookie("usuario", login);
				ck.setMaxAge(60*60*24*3);
				response.addCookie(ck);
			}
			request.getRequestDispatcher("WEB-INF/TelaRestrita.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("login.html").forward(request, response);
		}
	}

}
