package web.general;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class AuthentificationServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/views/general/login.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/views/general/login.jsp");
		
		String login_EMAIL = "admin@pizzeria.fr";
		String login_MDP = "admin";
		
		String email = req.getParameter("email");
		String mdp = req.getParameter("mdp");
		
		if(email.equals(login_EMAIL) && mdp.equals(login_MDP)){
			req.getSession().setAttribute("authentifie", true);
			resp.sendRedirect(req.getContextPath()+"/pizzas/list");
		}else{
			req.setAttribute("message", "Mot de passe et/ou email invalide");
			rd = this.getServletContext().getRequestDispatcher("/WEB-INF/views/general/login.jsp");
			rd.forward(req, resp);
		}
		
	}
	
	
}
